package es.unex.gexcall.tarea4;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {

	private TextView texto;
	private ImageView imagen;
	
	private Button rotar;
	private Button agrupar;
	private Button animar;
	private Button apagar;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		texto = (TextView) findViewById(R.id.texto);
		imagen = (ImageView) findViewById(R.id.imagen);
		
		rotar = (Button) findViewById(R.id.rotar);
		agrupar = (Button) findViewById(R.id.agrupar);
		animar = (Button) findViewById(R.id.animar);
		apagar = (Button) findViewById(R.id.apagar);
		
		
		
		rotar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ObjectAnimator animacion = ObjectAnimator.ofFloat(imagen, "rotation",0f,360f);
				animacion.setDuration(3000);
				animacion.start();
			}
		});
		
		agrupar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(),R.animator.animacion);
				set.setTarget(imagen);
				set.start();
			}
		});
		
		animar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ObjectAnimator animacion = ObjectAnimator.ofFloat(texto, "translationX",0f,100f);
				animacion.setDuration(3000);
				animacion.start();
			}
		});
		
		apagar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ObjectAnimator animacion = ObjectAnimator.ofFloat(imagen, "alpha",1f,0f);
				animacion.setDuration(3000);
				animacion.start();
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
