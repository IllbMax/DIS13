package Artikel;


import java.util.Date;
import java.util.HashMap;

import Datum.dayID;


public class ArtikelID {
	private int _artikelID;
	private String _artikel;
	private ProduktGruppe _produktGruppe;
	private static HashMap<String, ArtikelID> instanzen = new HashMap<String, ArtikelID>();
	
	public ArtikelID(String artikel) {
		_artikel = artikel;
		_artikelID = getArtikelID(artikel);
		_produktGruppe = ProduktGruppe.getInstanze(artikel);
		instanzen.put(artikel, this);
	}

	

	public static ArtikelID getInstanze(String artikel)
	{
		
		if(instanzen.containsKey(artikel)){
			return instanzen.get(artikel);
		}
		return new ArtikelID(artikel);
		
	}
	
	private int getArtikelID(String artikel) {
		// TODO Auto-generated method stub
		return 0;
	}
}
