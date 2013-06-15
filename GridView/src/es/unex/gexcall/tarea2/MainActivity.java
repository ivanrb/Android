package es.unex.gexcall.tarea2;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private ArrayList<Dibujo> lista;
	
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_main);
	        
	        lista = new ArrayList<Dibujo>();
	        rellenarObjetos();
	        GridView gridView = (GridView)findViewById(R.id.gridview);
	        gridView.setAdapter(new Adapter(this,lista));
	        
	        gridView.setOnItemClickListener(new OnItemClickListener(){

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
						long arg3) {
					// TODO Auto-generated method stub
					Toast.makeText(getApplicationContext(), "Elemento: "+arg2, Toast.LENGTH_SHORT).show();
				}
	        	
	        });
	    }

	private void rellenarObjetos() {
		// TODO Auto-generated method stub
		lista.add(new Dibujo(R.drawable.color1,"Color"));
    	lista.add(new Dibujo(R.drawable.gradient1,"Gradient"));
    	lista.add(new Dibujo(R.drawable.shape1,"Shape"));
    	lista.add(new Dibujo(R.drawable.color2,"Color"));
    	lista.add(new Dibujo(R.drawable.rotacion, "Rotacion"));
    	lista.add(new Dibujo(R.drawable.color3,"Color"));
    	lista.add(new Dibujo(R.drawable.shape2,"Shape"));
    	lista.add(new Dibujo(R.drawable.gradient2,"Gradient"));
	}
}
