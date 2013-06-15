package es.unex.gexcall.tarea10;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	private ProgressDialog pd;
	private EditText provincia;
	private EditText municipio;
	private Button consultar;
	private TextView respuesta;
	private String resultado;
	private Context context;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		context = this;
		
		provincia = (EditText) findViewById(R.id.provincia);
		municipio = (EditText) findViewById(R.id.municipio);
		consultar = (Button) findViewById(R.id.consultar);
		
		consultar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				new MyAsyncTask().execute("");
				pd = ProgressDialog.show(context, "Por favor espere","Realizando conversión", true, false);
			}
		});
		
	}
	
	private class MyAsyncTask extends AsyncTask<String, Void, Object>{

		@Override
		protected Integer doInBackground(String... args) {
			CargaDatosWS ws = new CargaDatosWS();
			resultado = ws.getProvincia(provincia.getText().toString(),municipio.getText().toString());
			return 1;
		}
		
		protected void onPostExecute(Object result) {
			pd.dismiss();
			respuesta = (TextView) findViewById(R.id.respuesta);
			respuesta.setText(resultado);
			super.onPostExecute(result);
		}
		
	}

}
