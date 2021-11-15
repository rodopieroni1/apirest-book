package com.company.book.backend.respose;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class ResponceRest {
	
	private ArrayList<HashMap<String, String>> metadata = new ArrayList<>();
	
	//get y set  de los metadata que vamos a poder introducir mensajes al usuario
	public ArrayList<HashMap<String, String>> getMetadata(){
		return metadata;	
	} 
	
	public void setMetadata(String tipo, String codigo, String fecha) {
		
		HashMap<String, String> mapa = new HashMap<String, String>();
		
		mapa.put("tipo", tipo);
		mapa.put("codigo", codigo);
		mapa.put("fecha", fecha);
		
		metadata.add(mapa);
	}

}
