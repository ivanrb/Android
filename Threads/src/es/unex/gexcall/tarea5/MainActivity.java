package es.unex.gexcall.tarea5;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private Tarea tarea;
	private Button comenzar;
	private TextView multiplicando;
	private TextView veces;
	private TextView en10;
	private TextView en3000;
	private long tiempo;
	private Handler handlerEn10;
	private Handler handlerEn3000;
	private int iteraciones;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		comenzar = (Button) findViewById(R.id.buttonComenzar);
		multiplicando = (TextView) findViewById(R.id.TextViewMultiplicando);
		veces = (TextView) findViewById(R.id.TextViewIteracion);
		en10 = (TextView) findViewById(R.id.TextViewEn10);
		en3000 = (TextView) findViewById(R.id.TextViewEn3000);
		
		handlerEn3000 = new Handler(){
			public void handleMessage(Message msg) {
				en3000.setText((Long)msg.obj-tiempo+" ms");
			}
		};
		
		handlerEn10 = new Handler(){
			public void handleMessage(Message msg) {
				en10.setText(iteraciones+" op");
				Toast.makeText(getApplicationContext(), "Operaciones en 10 segundos fijadas", Toast.LENGTH_SHORT).show();
			}
		};
	
	}

	public void comenzarProgreso(View view){
		tarea = new Tarea();
		tarea.execute();
	}
	
	public void cancelarProgreso(View view){
		if (tarea!=null)
			tarea.cancel(false);
		comenzar.setEnabled(true);
		handlerEn10.removeCallbacksAndMessages(null);
		handlerEn3000.removeCallbacksAndMessages(null);
	}

	public void finalizarApp(View view){
		Intent intent = new Intent(this, EscuchaAlarma.class);
		intent.putExtra("operaciones", en10.getText());
		intent.putExtra("tiempo", en3000.getText());
		PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 1234321, intent, 1);
		AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
		alarmManager.set(AlarmManager.RTC_WAKEUP,System.currentTimeMillis() + (5 * 1000),pendingIntent);
		finish();
	}
	
	private class Tarea extends AsyncTask<Void, Integer, Boolean>{
		
		int numero;
		
		@Override
		protected Boolean doInBackground(Void... params) {
			// TODO Auto-generated method stub
			for (iteraciones = 1; iteraciones <= 10000; iteraciones++) {
				numero = (numero * numero) % 10000;
				
				if(isCancelled())
					break;	     
				
				publishProgress(numero,iteraciones);
			}
			iteraciones--;
			return true;
		}
		
		@Override
		protected void onProgressUpdate(Integer... values) {
			// TODO Auto-generated method stub
			int progreso = values[0].intValue();
			int iteracion = values[1].intValue();
			
			multiplicando.setText(": "+progreso+" :");
			veces.setText(": "+iteracion+" :");
			if(iteracion == 3000){
				Message msg = new Message();
				msg.obj = System.currentTimeMillis();
				handlerEn3000.sendMessage(msg);
				Toast.makeText(getApplicationContext(), "Milisegundos para 3000 operaciones fijados", Toast.LENGTH_SHORT).show();
			}
		}
		
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			tiempo = System.currentTimeMillis();
			comenzar.setEnabled(false);
			numero = 1234;
			Message msg = new Message();
			handlerEn10.sendMessageDelayed(msg, 10000);
		}
		
		@Override
		protected void onPostExecute(Boolean result) {
			// TODO Auto-generated method stub
			if(result){
				comenzar.setEnabled(true);
				Toast.makeText(getApplicationContext(), "Fin de la tarea", Toast.LENGTH_SHORT).show();
			}
		}
		
		@Override
		protected void onCancelled() {
			// TODO Auto-generated method stub
			super.onCancelled();
		}

	}
	
}
