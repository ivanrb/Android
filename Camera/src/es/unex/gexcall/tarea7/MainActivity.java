package es.unex.gexcall.tarea7;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.Images.Media;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity implements SurfaceHolder.Callback, OnClickListener,
Camera.PictureCallback{
	SurfaceView vistaCamara;
	SurfaceHolder surfaceHolder;
	Camera camara;
	private Button flash;
	private Button color;
	private boolean flashOn = false;
	private boolean colorOn = true;
	Bitmap bmp;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		
		vistaCamara = (SurfaceView) findViewById(R.id.vistaCamara);
		
		surfaceHolder = vistaCamara.getHolder();
		surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		surfaceHolder.addCallback(this);
		
		vistaCamara.setFocusable(true);
		vistaCamara.setFocusableInTouchMode(true);
		vistaCamara.setClickable(true);
		vistaCamara.setOnClickListener(this);
		
		flash = (Button) findViewById(R.id.flash);
		color = (Button) findViewById(R.id.color);
		
		flash.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(flashOn){
					Camera.Parameters parameters = camara.getParameters();
					parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
					camara.setParameters(parameters);
					flash.setText(R.string.flashOn);
					flashOn = false;
				}
				else{
					Camera.Parameters parameters = camara.getParameters();
					parameters.setFlashMode(Camera.Parameters.FLASH_MODE_ON);
					camara.setParameters(parameters);
					flash.setText(R.string.flashOff);
					flashOn = true;
				}
			}
		});
		
		color.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(colorOn){
					Camera.Parameters parameters = camara.getParameters();
					parameters.setColorEffect(Camera.Parameters.EFFECT_MONO);
					camara.setParameters(parameters);
					color.setText(R.string.colorOn);
					colorOn = false;
				}
				else{
					Camera.Parameters parameters = camara.getParameters();
					parameters.setColorEffect(Camera.Parameters.EFFECT_NONE);
					camara.setParameters(parameters);
					color.setText(R.string.colorOff);
					colorOn = true;
				}
			}
		});
		
	}

	@Override
	public void onPictureTaken(byte[] data, Camera camera) {
		Uri imageFileUri =	getContentResolver().insert(Media.EXTERNAL_CONTENT_URI, new ContentValues());
		try {
			OutputStream imageFileOS =	getContentResolver().openOutputStream(imageFileUri);
			imageFileOS.write(data);
			imageFileOS.flush();
			imageFileOS.close();
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}

		Intent EligeImagenIntent = new Intent(Intent.ACTION_PICK,
		android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		startActivityForResult(EligeImagenIntent, 0);
	}

	@Override
	public void onClick(View v) {
		//Tomar fotografía
		camara.takePicture(null, null, this);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		camara = Camera.open();
		camara.setDisplayOrientation(90);
		try
		{
			camara.setPreviewDisplay(holder);
			Camera.Parameters parameters = camara.getParameters();
			parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
			parameters.setColorEffect(Camera.Parameters.EFFECT_NONE);
			camara.setParameters(parameters);
		}catch (IOException exception){
			camara.release();
		}
		//Comenzar previsualización
		camara.startPreview(); 
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		//Parar previsualización y liberar cámara
		camara.stopPreview();
		camara.release();
	}
	
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
		super.onActivityResult(requestCode, resultCode, intent);
		if (resultCode == RESULT_OK) {
			//Obtener imagen seleccionada y mostrarla
			Uri imageFileUri = intent.getData();
			try {
				bmp = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageFileUri), 
						null, null);
			} catch (FileNotFoundException e) {}
			color.setVisibility(Button.INVISIBLE);
			flash.setVisibility(Button.INVISIBLE);
			vistaCamara.setVisibility(SurfaceView.INVISIBLE);
			ImageView ImagenElegida = (ImageView) findViewById(R.id.foto);
			ImagenElegida.setImageBitmap(bmp);
			ImagenElegida.setVisibility(ImageView.VISIBLE);
		}
	}

}
