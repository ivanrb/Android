package es.unex.gexcall.calculadora;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Calculadora extends Activity {
	private Button	boton0;
	private Button	boton1;	
	private Button	boton2;
	private Button	boton3;
	private Button	boton4;
	private Button	boton5;	
	private Button	boton6;
	private Button	boton7;	
	private Button	boton8;
	private Button	boton9;	
	private Button	botonSum;
	private Button	botonRes;
	private Button	botonMul;
	private Button	botonDiv;	
	private Button	botonIgu;
	private Button	botonPun;
	
	private TextView pantallaArriba;
	private TextView pantallaMedio;
	private TextView pantallaAbajo;
	
	private String a = new String("");
	private String operador;
	private String b = new String("");
	private String resultado;
	
	private boolean contPunto = false;
	private boolean contOperador = false;
	private boolean operacionRealizada = false;
	
	
	private int operacion;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calculadora);
		
		boton0 = (Button) findViewById(R.id.boton0);
		boton1 = (Button) findViewById(R.id.boton1);
		boton2 = (Button) findViewById(R.id.boton2);
		boton3 = (Button) findViewById(R.id.boton3);
		boton4 = (Button) findViewById(R.id.boton4);
		boton5 = (Button) findViewById(R.id.boton5);
		boton6 = (Button) findViewById(R.id.boton6);
		boton7 = (Button) findViewById(R.id.boton7);
		boton8 = (Button) findViewById(R.id.boton8);
		boton9 = (Button) findViewById(R.id.boton9);
		botonSum = (Button) findViewById(R.id.botonSum);
		botonRes = (Button) findViewById(R.id.botonRes);
		botonMul = (Button) findViewById(R.id.botonMul);
		botonIgu = (Button) findViewById(R.id.botonIgu);
		botonDiv = (Button) findViewById(R.id.botonDiv);
		botonPun = (Button) findViewById(R.id.boton_);
		
		pantallaArriba = (TextView) findViewById(R.id.pantallaArriba);
		pantallaMedio = (TextView) findViewById(R.id.pantallaMedio);
		pantallaAbajo = (TextView) findViewById(R.id.pantallaAbajo);
		
		
		boton0.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(operacionRealizada)
					borrarPantalla();
				if(!contOperador){
					if(a.length()<12){
						a=a.concat("0");
						pantallaAbajo.setText(a);
					}
					else{
						Toast.makeText(getApplicationContext(), "Seleccione operación a realizar", Toast.LENGTH_SHORT).show();
					}
				}
				else{
					if(b.length()<12){
						pantallaArriba.setText(a);
						pantallaMedio.setText(operador);
						b=b.concat("0");
						pantallaAbajo.setText(b);
					}
					else{
						Toast.makeText(getApplicationContext(), "Seleccione operación a realizar", Toast.LENGTH_SHORT).show();
					}
				}
			}

		});

		boton1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(operacionRealizada)
					borrarPantalla();
				if(!contOperador){
					if(a.length()<12){
						a=a.concat("1");
						pantallaAbajo.setText(a);
					}
					else{
						Toast.makeText(getApplicationContext(), "Seleccione operación a realizar", Toast.LENGTH_SHORT).show();
					}
				}
				else{
					if(b.length()<12){
						pantallaArriba.setText(a);
						pantallaMedio.setText(operador);
						b=b.concat("1");
						pantallaAbajo.setText(b);
					}
					else{
						Toast.makeText(getApplicationContext(), "Seleccione operación a realizar", Toast.LENGTH_SHORT).show();
					}
				}
			}
		});		
		
		boton2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(operacionRealizada)
					borrarPantalla();
				if(!contOperador){
					if(a.length()<12){
						a=a.concat("2");
						pantallaAbajo.setText(a);
					}
					else{
						Toast.makeText(getApplicationContext(), "Seleccione operación a realizar", Toast.LENGTH_SHORT).show();
					}
				}
				else{
					if(b.length()<12){
						pantallaArriba.setText(a);
						pantallaMedio.setText(operador);
						b=b.concat("2");
						pantallaAbajo.setText(b);
					}
					else{
						Toast.makeText(getApplicationContext(), "Seleccione operación a realizar", Toast.LENGTH_SHORT).show();
					}
				}
			}
		});

		boton3.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(operacionRealizada)
					borrarPantalla();
				if(!contOperador){
					if(a.length()<12){
						a=a.concat("3");
						pantallaAbajo.setText(a);
					}
					else{
						Toast.makeText(getApplicationContext(), "Seleccione operación a realizar", Toast.LENGTH_SHORT).show();
					}
				}
				else{
					if(b.length()<12){
						pantallaArriba.setText(a);
						pantallaMedio.setText(operador);
						b=b.concat("3");
						pantallaAbajo.setText(b);
					}
					else{
						Toast.makeText(getApplicationContext(), "Seleccione operación a realizar", Toast.LENGTH_SHORT).show();
					}
				}
			}
		});

		boton4.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(operacionRealizada)
					borrarPantalla();
				if(!contOperador){
					if(a.length()<12){
						a=a.concat("4");
						pantallaAbajo.setText(a);
					}
					else{
						Toast.makeText(getApplicationContext(), "Seleccione operación a realizar", Toast.LENGTH_SHORT).show();
					}
				}
				else{
					if(b.length()<12){
						pantallaArriba.setText(a);
						pantallaMedio.setText(operador);
						b=b.concat("4");
						pantallaAbajo.setText(b);
					}
					else{
						Toast.makeText(getApplicationContext(), "Seleccione operación a realizar", Toast.LENGTH_SHORT).show();
					}
				}
			}
		});
	
		boton5.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(operacionRealizada)
					borrarPantalla();
				if(!contOperador){
					if(a.length()<12){
						a=a.concat("5");
						pantallaAbajo.setText(a);
					}
					else{
						Toast.makeText(getApplicationContext(), "Seleccione operación a realizar", Toast.LENGTH_SHORT).show();
					}
				}
				else{
					if(b.length()<12){
						pantallaArriba.setText(a);
						pantallaMedio.setText(operador);
						b=b.concat("5");
						pantallaAbajo.setText(b);
					}
					else{
						Toast.makeText(getApplicationContext(), "Seleccione operación a realizar", Toast.LENGTH_SHORT).show();
					}
				}
			}
		});		
		
		boton6.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(operacionRealizada)
					borrarPantalla();
				if(!contOperador){
					if(a.length()<12){
						a=a.concat("6");
						pantallaAbajo.setText(a);
					}
					else{
						Toast.makeText(getApplicationContext(), "Seleccione operación a realizar", Toast.LENGTH_SHORT).show();
					}
				}
				else{
					if(b.length()<12){
						pantallaArriba.setText(a);
						pantallaMedio.setText(operador);
						b=b.concat("6");
						pantallaAbajo.setText(b);
					}
					else{
						Toast.makeText(getApplicationContext(), "Seleccione operación a realizar", Toast.LENGTH_SHORT).show();
					}
				}
			}
		});
	
		boton7.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(operacionRealizada)
					borrarPantalla();
				if(!contOperador){
					if(a.length()<12){
						a=a.concat("7");
						pantallaAbajo.setText(a);
					}
					else{
						Toast.makeText(getApplicationContext(), "Seleccione operación a realizar", Toast.LENGTH_SHORT).show();
					}
				}
				else{
					if(b.length()<12){
						pantallaArriba.setText(a);
						pantallaMedio.setText(operador);
						b=b.concat("7");
						pantallaAbajo.setText(b);
					}
					else{
						Toast.makeText(getApplicationContext(), "Seleccione operación a realizar", Toast.LENGTH_SHORT).show();
					}
				}
			}
		});

		boton8.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(operacionRealizada)
					borrarPantalla();
				if(!contOperador){
					if(a.length()<12){
						a=a.concat("8");
						pantallaAbajo.setText(a);
					}
					else{
						Toast.makeText(getApplicationContext(), "Seleccione operación a realizar", Toast.LENGTH_SHORT).show();
					}
				}
				else{
					if(b.length()<12){
						pantallaArriba.setText(a);
						pantallaMedio.setText(operador);
						b=b.concat("8");
						pantallaAbajo.setText(b);
					}
					else{
						Toast.makeText(getApplicationContext(), "Seleccione operación a realizar", Toast.LENGTH_SHORT).show();
					}
				}
			}
		});
	
		boton9.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(operacionRealizada)
					borrarPantalla();
				if(!contOperador){
					if(a.length()<12){
						a=a.concat("9");
						pantallaAbajo.setText(a);
					}
					else{
						Toast.makeText(getApplicationContext(), "Seleccione operación a realizar", Toast.LENGTH_SHORT).show();
					}
				}
				else{
					if(b.length()<12){
						pantallaArriba.setText(a);
						pantallaMedio.setText(operador);
						b=b.concat("9");
						pantallaAbajo.setText(b);
					}
					else{
						Toast.makeText(getApplicationContext(), "Seleccione operación a realizar", Toast.LENGTH_SHORT).show();
					}
				}
			}
		});
		
		botonPun.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(!contPunto){
					if(!contOperador){
						if(a.contentEquals("")){
							a=a.concat("0.");
							pantallaAbajo.setText(a);
							contPunto = true;
						}
						else{
							a=a.concat(".");
							pantallaAbajo.setText(a);
							contPunto = true;
						}
					}
					else{
						if(b.contentEquals("")){
							b=b.concat("0.");
							pantallaAbajo.setText(b);
							contPunto = true;
						}
						else{
							b=b.concat(".");
							pantallaAbajo.setText(b);
							contPunto = true;
						}
					}
				}
			}
		});
		
		botonSum.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(!contOperador){
					pantallaMedio.setText(a);
					operador = new String("+");
					pantallaAbajo.setText(operador);
					contOperador = true;
					operacion = 1;
					contPunto = false;
				}
			}
		});
		
		botonRes.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(!contOperador){
					pantallaMedio.setText(a);
					operador = new String("-");
					pantallaAbajo.setText(operador);
					contOperador = true;
					operacion = 2;
					contPunto = false;
				}
			}
		});
		
		botonMul.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(!contOperador){
					pantallaMedio.setText(a);
					operador = new String("*");
					pantallaAbajo.setText(operador);
					contOperador = true;
					operacion = 3;
					contPunto = false;
				}
			}
		});
		
		botonDiv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(!contOperador){
					pantallaMedio.setText(a);
					operador = new String("/");
					pantallaAbajo.setText(operador);
					contOperador = true;
					operacion = 4;
					contPunto = false;
				}
			}
		});
		
		botonIgu.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				float x;
				float y;
				float result = 0;
				if(!contOperador || b.contentEquals("")){
					Toast.makeText(getApplicationContext(), "Operacion incompleta", Toast.LENGTH_SHORT).show();
				}
				else{
					x = Float.parseFloat(a);
					y = Float.parseFloat(b);
					
					switch (operacion) {
						case 1:
							result = x + y;
							break;
						case 2:
							result = x - y;
							break;
						case 3:
							result = x * y;
							break;
						case 4:
							result = x / y;
							break;
					}
					a=a.concat(" "+operador+" "+b);
					pantallaArriba.setText(a);
					pantallaMedio.setText("=");
					resultado = Float.toString(result);
					pantallaAbajo.setText(resultado);
					operacionRealizada=true;
				}
			}
		});
		
	}
	
	public void borrarPantalla() {
		a = new String("");
		b = new String("");
		operador = new String("");
		
		contOperador=false;
		contPunto=false;
		operacionRealizada=false;
		
		pantallaArriba.setText("");
		pantallaMedio.setText("");
		pantallaAbajo.setText("");	
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.calculadora, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getOrder()) {
		case 1:
			borrarPantalla();
			break;

		case 2:
			Intent intent = new Intent(getApplicationContext(),AcercaDe.class);
			startActivity(intent);
			break;
		}
		return true;
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		finish();
	}

}
