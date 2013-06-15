package es.gexcall.unex.tarea8;

import java.util.Calendar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class MainActivity extends AppWidgetProvider {
	
	private final String APPWIDGET_UPDATE = "android.appwidget.action.APPWIDGET_UPDATE";
	private final String FORCE_WIDGET_UPDATE = "es.gexcall.unex.tarea8.FORCE_WIDGET_UPDATE";
	private final String DATE_CHANGED = "android.intent.action.DATE_CHANGED";
	private final String TIME_SET = "android.intent.action.TIME_SET";
	private PendingIntent pendingIntent;
	private AlarmManager alarmManager;

	public void onReceive(Context context, Intent intent)
    {
		
		AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
	    ComponentName thisAppWidget = new ComponentName(context.getPackageName(), MainActivity.class.getName());
	    int[] appWidgetIds = appWidgetManager.getAppWidgetIds(thisAppWidget);
		
        if (intent.getAction().equals(APPWIDGET_UPDATE) || intent.getAction().equals(DATE_CHANGED)
        		|| intent.getAction().equals(TIME_SET) || intent.getAction().equals(FORCE_WIDGET_UPDATE))
        	updateWidget(context, appWidgetManager, appWidgetIds,"0");


    }
	
	private void updateWidget(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds, String string) {
		final int N = appWidgetIds.length;
		Calendar c = Calendar.getInstance();
    	
		for (int i = 0; i < N; i++) {
			int appWidgetId = appWidgetIds[i];
	
			RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.activity_main);
			
			views.setTextViewText(R.id.diaNum, ""+c.get(Calendar.DAY_OF_MONTH));
			switch(c.get(Calendar.DAY_OF_WEEK)){
				case Calendar.MONDAY:
					views.setTextViewText(R.id.diaText, "Lunes");
					break;
				case Calendar.TUESDAY:
					views.setTextViewText(R.id.diaText, "Martes");
					break;
				case Calendar.WEDNESDAY:
					views.setTextViewText(R.id.diaText, "Miércoles");
					break;
				case Calendar.THURSDAY:
					views.setTextViewText(R.id.diaText, "Jueves");
					break;
				case Calendar.FRIDAY:
					views.setTextViewText(R.id.diaText, "Viernes");
					break;
				case Calendar.SATURDAY:
					views.setTextViewText(R.id.diaText, "Sábado");
					break;
				case Calendar.SUNDAY:
					views.setTextViewText(R.id.diaText, "Domingo");
					break;
			}
			switch(c.get(Calendar.MONTH)){
				case Calendar.JANUARY:
					views.setTextViewText(R.id.mes, "Enero");
					break;
				case Calendar.FEBRUARY:
					views.setTextViewText(R.id.mes, "Febrero");
					break;
				case Calendar.MARCH:
					views.setTextViewText(R.id.mes, "Marzo");
					break;
				case Calendar.APRIL:
					views.setTextViewText(R.id.mes, "Abril");
					break;
				case Calendar.MAY:
					views.setTextViewText(R.id.mes, "Mayo");
					break;
				case Calendar.JUNE:
					views.setTextViewText(R.id.mes, "Junio");
					break;
				case Calendar.JULY:
					views.setTextViewText(R.id.mes, "Julio");
					break;
				case Calendar.AUGUST:
					views.setTextViewText(R.id.mes, "Agosto");
					break;
				case Calendar.SEPTEMBER:
					views.setTextViewText(R.id.mes, "Septiembre");
					break;
				case Calendar.OCTOBER:
					views.setTextViewText(R.id.mes, "Octubre");
					break;
				case Calendar.NOVEMBER:
					views.setTextViewText(R.id.mes, "Noviembre");
					break;
				case Calendar.DECEMBER:
					views.setTextViewText(R.id.mes, "Diciembre");
					break;
			}

			appWidgetManager.updateAppWidget(appWidgetId, views);
			
		}
		
		Intent intent = new Intent(context,MainActivity.class);
		intent.setAction(FORCE_WIDGET_UPDATE);
		
        pendingIntent = PendingIntent.getBroadcast(context, 1, intent, PendingIntent.FLAG_CANCEL_CURRENT);

        c.setTimeInMillis(System.currentTimeMillis());
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.HOUR, 0);
		c.set(Calendar.AM_PM, Calendar.AM);
		c.add(Calendar.DAY_OF_MONTH, 1); 

		alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
		alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
		
	}
	
}
