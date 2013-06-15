package es.unex.gexcall.tarea5;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

public class EscuchaAlarma extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		try {
			Bundle bundle = intent.getExtras();
			String operaciones = bundle.getString("operaciones");
			String tiempo = bundle.getString("tiempo");
			Toast.makeText(context, "En 10 segundos: "+operaciones+"\nNúmero 3000: "+tiempo, Toast.LENGTH_SHORT).show();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
}
