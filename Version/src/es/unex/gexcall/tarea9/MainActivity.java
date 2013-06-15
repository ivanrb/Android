package es.unex.gexcall.tarea9;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	SharedPreferences prefs;
	int verGuarda;
	int contador;
	boolean recordar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		prefs = getSharedPreferences("MisPreferencias",Context.MODE_PRIVATE);		
    	SharedPreferences.Editor editor = prefs.edit();
    	PackageInfo pInfo;
    	
    	try {        	
    		
            pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            verGuarda = prefs.getInt("laVersion", 0);
            contador = prefs.getInt("contador", 1);
            recordar = prefs.getBoolean("noAnunciar", false);
            int verActual = pInfo.versionCode;

            if ( verGuarda == 0)  {
                //Primera ejecución, nueva instalación
            	if(Build.VERSION.SDK_INT < Build.VERSION_CODES.GINGERBREAD_MR1){
            		dialogoVersion();
            	}
            }
            else { //No es primera ejecución
            	if (verGuarda < verActual) {
           		//Actualizado
                	dialogoActualizado();
                	contador = 0;
                	editor.putInt("contador", contador);
            	}
            	if (verGuarda == verActual) {
            		//No se detecta ninguna actualización
            		if(contador>5 && contador%2 == 0 && !recordar){
            			dialogoVotar();
            		}
            	}
            	if (verGuarda > verActual) {
            		Toast.makeText(getApplicationContext(), "Has pasado de la versión "+verGuarda+ " a la "+verActual+
            				". Tu móvil es un "+Build.MODEL+" con sistema operativo "+Build.VERSION.RELEASE, Toast.LENGTH_LONG).show();
                	contador = 0;
            		editor.putInt("contador", contador);
            	}
            }

    		ActualizaEtiquetas();
         	editor.putInt("laVersion",verActual);
         	editor.putInt("contador", contador+1);
    		editor.commit();
    	
        } catch (NameNotFoundException e) {
            e.printStackTrace();
        }
	}

	public void ActualizaEtiquetas() {

		TextView et1 = (TextView) findViewById(R.id.TextView02);
		TextView et2 = (TextView) findViewById(R.id.TextView04);
		TextView et3 = (TextView) findViewById(R.id.TextView06);
			
		prefs = getSharedPreferences("MisPreferencias",Context.MODE_PRIVATE);		
    	//SharedPreferences.Editor editor = prefs.edit();

        int a = prefs.getInt("laVersion", 1);
        int b = prefs.getInt("contador", 1);
        boolean c = prefs.getBoolean("noAnunciar", false);
        
        et1.setText(Integer.toString(a));
        et2.setText(Integer.toString(b));
		et3.setText(Boolean.toString(c));		

	}
	
	public void dialogoVersion(){
		AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
		
		builder.setMessage("Tiene una version demasiado antigua. \n" +
		"Por favor, actualice el sistema para un mejor funcionamiento").setTitle("Necesita actualizar").setIcon(R.drawable.ic_launcher);
		
		builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				dialog.cancel();
			}
		});
		
		builder.create();
		builder.show();
	}
	
	public void dialogoVotar() {
		AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
		
		builder.setMessage("¿Tienes unos segundos para darnos tu opinión?").setTitle("¡Danos tu voto!");
		
		builder.setPositiveButton("Puntuar", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				prefs = getSharedPreferences("MisPreferencias",Context.MODE_PRIVATE);		
		    	SharedPreferences.Editor editor = prefs.edit();
		    	editor.putBoolean("noAnunciar", true);
		    	editor.commit();
		    	Toast.makeText(getApplicationContext(), "Gracias por tu voto", Toast.LENGTH_SHORT).show();
			}
		});
		
		builder.setNeutralButton("Ahora no", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				dialog.cancel();
			}
		});
		
		builder.setNegativeButton("No recordar", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				prefs = getSharedPreferences("MisPreferencias",Context.MODE_PRIVATE);		
		    	SharedPreferences.Editor editor = prefs.edit();
		    	editor.putBoolean("noAnunciar", true);
		    	editor.commit();
			}
		});
		
		builder.create();
		builder.show();
	}
	
	private void dialogoActualizado() {
		AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
		
		builder.setMessage("Cambios en la versión: \n" +
						   "-Cambio 1\n"+
						   "-Cambio 2\n"+
						   "-Cambio 3\n").setTitle("Actualizado a la versión"+verGuarda).setIcon(R.drawable.ic_launcher);
		
		builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				dialog.cancel();
			}
		});
		
		builder.create();
		builder.show();		
	}

}
