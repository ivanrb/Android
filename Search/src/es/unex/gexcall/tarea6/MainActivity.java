package es.unex.gexcall.tarea6;

import android.app.Activity;
import android.app.SearchManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.SearchRecentSuggestions;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	private Button busqueda;
	private static final String DATABASE_NAME = "myDB.db";
	private static final String DATABASE_TABLE_NAME = "ANIMALES";
	private static final String DATABASE_CREATE_TABLE = 
		"create table if not exists " + DATABASE_TABLE_NAME + 
		" (_id integer primary key autoincrement, " +
		" animalEsp text not null, " +
		" animalEng text not null)";
	private static final String DATABASE_DELETE_TABLE = "drop table if exists "
	        + DATABASE_TABLE_NAME;
	private SQLiteDatabase myDB;
	private SearchRecentSuggestions suggestions = new SearchRecentSuggestions(this, Sugerencias.AUTHORITY, Sugerencias.MODE);
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		myDB = openOrCreateDatabase(DATABASE_NAME, Context.MODE_PRIVATE, null);
		
		myDB.execSQL(DATABASE_DELETE_TABLE);
		myDB.execSQL(DATABASE_CREATE_TABLE);
		
		ContentValues newRow = new ContentValues();
		newRow.put("animalEsp", "gato");
		newRow.put("animalEng", "cat");
		myDB.insert(DATABASE_TABLE_NAME, null, newRow);	
		
		newRow = new ContentValues();
		newRow.put("animalEsp", "perro");
		newRow.put("animalEng", "dog");
		myDB.insert(DATABASE_TABLE_NAME, null, newRow);	
		
		newRow = new ContentValues();
		newRow.put("animalEsp", "pajaro");
		newRow.put("animalEng", "bird");
		myDB.insert(DATABASE_TABLE_NAME, null, newRow);	
		
		newRow = new ContentValues();
		newRow.put("animalEsp", "pez");
		newRow.put("animalEng", "fish");
		myDB.insert(DATABASE_TABLE_NAME, null, newRow);	
		
		newRow = new ContentValues();
		newRow.put("animalEsp", "vaca");
		newRow.put("animalEng", "cow");
		myDB.insert(DATABASE_TABLE_NAME, null, newRow);	

		
		busqueda = (Button)findViewById(R.id.buscar);
        busqueda.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				onSearchRequested();
			}
		});
        
        final Intent queryIntent = getIntent();
        final String queryAction = queryIntent.getAction();
       
        if (Intent.ACTION_SEARCH.equals(queryAction)) {
            String query = queryIntent.getStringExtra(SearchManager.QUERY);
            
            suggestions.saveRecentQuery(query, null);  
            BusquedayPresentaciondeResultados(query);
            finish();
        }
	}
	
	void BusquedayPresentaciondeResultados(String query){
    	 boolean encontrado = false;
		 String[] resultColumns = new String[] {"_id", "animalEsp", "animalEng"};
		 Cursor cursor = myDB.query(DATABASE_TABLE_NAME, resultColumns, null, null, null, null, null, null);
		 Integer cindex = cursor.getColumnIndex("animalEsp");
		 if (cursor.moveToFirst()) {
				do {
					if(cursor.getString(cindex).compareTo(query) == 0){
						 Toast.makeText(getApplicationContext(),query + " = " + cursor.getString(cindex+1), Toast.LENGTH_LONG).show();
						 encontrado=true;
					}
				} while (cursor.moveToNext());	
		 }
		 if(!encontrado)
			 Toast.makeText(getApplicationContext(), "Animal no encontrado", Toast.LENGTH_SHORT).show();
    }

	@Override
	public void onBackPressed() {
		myDB.close();
		suggestions.clearHistory();
		finish();
	}

}
