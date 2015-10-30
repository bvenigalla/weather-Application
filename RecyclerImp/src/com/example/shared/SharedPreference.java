package com.example.shared;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.example.recyclerimp.Data;

import android.content.SharedPreferences;

import com.google.gson.Gson;

import android.content.Context;

public class SharedPreference {
	
	public static final String PREFS_NAME = "WEATHER_GRID";
	public static final String FAVORITES = "List_Favorite";
	
	public SharedPreference(){
		super();
	}
	
	public void saveFavorites(Context context, List<Data> favorites){
		SharedPreferences settings;
		SharedPreferences.Editor editor = null;
		
		settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
		
		Gson gson = new Gson();
		String jsonFavorites = gson.toJson(favorites);
		
		editor.putString(FAVORITES, jsonFavorites);
		
		editor.commit();
		
	}
	
	public void addFavorite(Context context, Data product){
		
		List<Data> favorites = getFavorites(context);
		if (favorites == null){
			favorites = new ArrayList<Data>();
		favorites.add(product);
		saveFavorites(context, favorites);
		}
	}
	
	public void removeFavorite(Context context, Data product){
		ArrayList<Data> favorites = getFavorites(context);
		if(favorites != null){
			favorites.remove(product);
			saveFavorites(context, favorites);
		}
			
	}
	
	public ArrayList<Data> getFavorites(Context context){
		SharedPreferences settings;
		List<Data> favorites;
		
		settings = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
		if(settings.contains(FAVORITES)){
			String jsonFavorites = settings.getString(FAVORITES, null);
			Gson gson = new Gson();
			Data[] favoriteelements = gson.fromJson(jsonFavorites, Data[].class);
			favorites = Arrays.asList(favoriteelements);
			
			
		}else
			return null;
		
			return(ArrayList<Data>) favorites;
	}

}
