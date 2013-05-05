package ersteVersuche.Material;

public abstract class Immobilie {
	private int _id;
	private String _ort;
	private int _plz;
	private String _strasse;
	private int _hausNr;
	private float _flaeche;

	public Immobilie(int id, String ort, int plz, String strasse, int hausNr,
			float flaeche) {
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
	public int getHausNr() {
		return _hausNr;
	}

	/**
	 * @param hausNr
	 *            the hausNr to set
	 */
	public void setHausNr(int hausNr) {
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

	@Override
	public String toString() {
		return _strasse + " " + _hausNr + ", " + _plz + " " + _ort + " ("
				+ _flaeche + "m²)";
	}
}
