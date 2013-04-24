package ersteVersuche.Material;

public abstract class Immobilie {
	private int _id;
	private String _ort;
	private int _plz;
	private String _strasse;
	private String _hausNr;
	private float _flaeche;
	private String _makler;

	public Immobilie(int id, String ort, int plz, String strasse,
			String hausNr, float flaeche) {
		_id = id;
		_ort = ort;
		_plz = plz;
		_strasse = strasse;
		_hausNr = hausNr;
		_flaeche = flaeche;
	}

	/**
	 * @return the iD
	 */
	public int getID() {
		return _id;
	}

	/**
	 * @param iD
	 *            the iD to set
	 */
	public void setID(int iD) {
		_id = iD;
	}

	/**
	 * @return the ort
	 */
	public String getOrt() {
		return _ort;
	}

	/**
	 * @param ort
	 *            the ort to set
	 */
	public void setOrt(String ort) {
		_ort = ort;
	}

	/**
	 * @return the pLZ
	 */
	public int getPLZ() {
		return _plz;
	}

	/**
	 * @param pLZ
	 *            the pLZ to set
	 */
	public void setPLZ(int pLZ) {
		_plz = pLZ;
	}

	/**
	 * @return the strasse
	 */
	public String getStrasse() {
		return _strasse;
	}

	/**
	 * @param strasse
	 *            the strasse to set
	 */
	public void setStrasse(String strasse) {
		_strasse = strasse;
	}

	/**
	 * @return the hausNr
	 */
	public String getHausNr() {
		return _hausNr;
	}

	/**
	 * @param hausNr
	 *            the hausNr to set
	 */
	public void setHausNr(String hausNr) {
		_hausNr = hausNr;
	}

	/**
	 * @return the flaeche
	 */
	public float getFlaeche() {
		return _flaeche;
	}

	/**
	 * @param flaeche
	 *            the flaeche to set
	 */
	public void setFlaeche(float flaeche) {
		_flaeche = flaeche;
	}

	public String getMakler() {
		return _makler;
	}

	public void setMakler(String makler) {
		this._makler = makler;
	}

}
