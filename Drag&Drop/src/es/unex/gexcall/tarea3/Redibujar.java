package es.unex.gexcall.tarea3;

import android.graphics.Canvas;

public class Redibujar extends Thread{
	private static final long FPS=10;
	private GraphicsView view;
	private boolean running = false;
	
	public Redibujar(GraphicsView graphicsView) {
		// TODO Auto-generated constructor stub
		this.view = graphicsView;
	}

	public void setRunning(boolean run) {
		// TODO Auto-generated method stub
		running = run;
	}
	
	public void run(){
		long ticksPS = 1000/FPS;
		long startTime;
		long sleepTime;
		
		while(running){
			Canvas canvas = null;
			startTime = System.currentTimeMillis();
			try{
				canvas = view.getHolder().lockCanvas();
				synchronized(view.getHolder()){
					view.onDraw(canvas);
				}
			}finally{
				if(canvas != null){
					view.getHolder().unlockCanvasAndPost(canvas);
				}
			}
			sleepTime = ticksPS - System.currentTimeMillis() - startTime;
			try {
				if (sleepTime > 0)
					sleep(sleepTime);
				else
					sleep(10);
			} catch (Exception e) {

			}
		}
	}
}
