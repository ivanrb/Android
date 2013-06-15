package es.unex.gexcall.tarea3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GraphicsView extends SurfaceView implements SurfaceHolder.Callback{

	private Paint mPaint;
	private Redibujar redibujar;
	private int x;
	private int y;
	private int radio;
	private Boolean mover;
	private int xMax, xMin, yMax, yMin;
	
	
	
	public GraphicsView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub	
		getHolder().addCallback(this);
		redibujar = new Redibujar(this);

	}
	
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		x = getWidth()/2;
		y = getHeight()/2;

		radio = 75;
		
		mover = false;
		redibujar.setRunning(true);
		redibujar.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		boolean retry = true;
		redibujar.setRunning(false);
		while(retry) try{
			redibujar.join();
			retry = false;
		}catch (InterruptedException e){
			
		}
	}
	
	@Override
	public void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub		
		mPaint = new Paint();

		xMax = x + radio;
		xMin = x - radio;
		yMax = y + radio;
		yMin = y - radio;
		
		canvas.drawColor(Color.WHITE);
		mPaint.setColor(Color.BLUE);
		canvas.drawCircle(x, y, radio, mPaint);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		switch(event.getAction()){
		case MotionEvent.ACTION_DOWN:
			if(comprobarRadio((int)event.getX(), (int)event.getY()))
				mover = true;
			break;
		case MotionEvent.ACTION_MOVE:
			if(mover){
				x = (int)event.getX();
				y = (int)event.getY();
			}
			break;
		case MotionEvent.ACTION_UP:
			mover = false;
			break;
		}
		
		return true;
	}
	
	/*
	 * Este método comprueba si la pulsación se ha realizado dentro del circulo.
	 */
	public boolean comprobarRadio(int x, int y){
		if(x<xMax && x>xMin && y<yMax && y>yMin)
			return true;
		return false;
	}
}
