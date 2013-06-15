package es.unex.gexcall.tarea6;

import android.content.SearchRecentSuggestionsProvider;

public class Sugerencias extends SearchRecentSuggestionsProvider{
	public final static String AUTHORITY = "com.example.Sugerencias";
    public final static int MODE = DATABASE_MODE_QUERIES;

    public Sugerencias() {
        setupSuggestions(AUTHORITY, MODE);
    }

}
