package Artikel;

import java.util.HashMap;


public class ProduktGruppe {

	private static HashMap<String, ProduktGruppe> instanzen = new HashMap<String, ProduktGruppe>();
	private int _produktGruppeID;
	private String _produktGruppe;
	private ProduktFamilie _produkteFamilie;
	
	public ProduktGruppe(String artikel) {
	_produktGruppe = getProduktGruppe(artikel);
	_produktGruppeID = getProduktGruppeID(artikel);
	_produkteFamilie = ProduktFamilie.getInstanze(artikel);
	}
	
	

	public static ProduktGruppe getInstanze(String artikel) {
		if(instanzen.containsKey(artikel)){
			return instanzen.get(artikel);
		}
		return new ProduktGruppe(artikel);
	}

	private int getProduktGruppeID(String artikel) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	private String getProduktGruppe(String artikel) {
		// TODO Auto-generated method stub
		return null;
	}
}
