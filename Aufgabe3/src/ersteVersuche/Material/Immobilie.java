package ersteVersuche.Material;

public abstract class Immobilie {
	private int id;
	private String ort;
	private int plz;
	private String strasse;
	private int hausNr;
	private float flaeche;

	private Makler verwalter;

	public Immobilie() {
	}

	public Immobilie(int id, String ort, int plz, String strasse, int hausNr,
			float flaeche) {
		this.id = id;
		this.ort = ort;
		this.plz = plz;
		this.strasse = strasse;
		this.hausNr = hausNr;
		this.flaeche = flaeche;
	}

	/**
	 * @return the iD
	 */
	public int getID() {
		return this.id;
	}

	/**
	 * @param iD
	 *            the iD to set
	 */
	public void setID(int iD) {
		this.id = iD;
	}

	/**
	 * @return the ort
	 */
	public String getOrt() {
		return this.ort;
	}

	/**
	 * @param ort
	 *            the ort to set
	 */
	public void setOrt(String ort) {
		this.ort = ort;
	}

	/**
	 * @return the pLZ
	 */
	public int getPLZ() {
		return this.plz;
	}

	/**
	 * @param pLZ
	 *            the pLZ to set
	 */
	public void setPLZ(int pLZ) {
		this.plz = pLZ;
	}

	/**
	 * @return the strasse
	 */
	public String getStrasse() {
		return this.strasse;
	}

	/**
	 * @param strasse
	 *            the strasse to set
	 */
	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}

	/**
	 * @return the hausNr
	 */
	public int getHausNr() {
		return this.hausNr;
	}

	/**
	 * @param hausNr
	 *            the hausNr to set
	 */
	public void setHausNr(int hausNr) {
		this.hausNr = hausNr;
	}

	/**
	 * @return the flaeche
	 */
	public float getFlaeche() {
		return this.flaeche;
	}

	/**
	 * @param flaeche
	 *            the flaeche to set
	 */
	public void setFlaeche(float flaeche) {
		this.flaeche = flaeche;
	}

	@Override
	public String toString() {
		return this.strasse + " " + this.hausNr + ", " + this.plz + " "
				+ this.ort + " (" + this.flaeche + "m²)";
	}

	@Override
	public boolean equals(Object obj) {
		Immobilie i = (Immobilie) obj;
		if (i == null)
			return false;
		if (i == this)
			return true;

		return id == i.id && plz == i.plz && ort.equals(i.ort)
				&& strasse.equals(i.strasse) && hausNr == i.hausNr
				&& flaeche == i.flaeche;
	}

	@Override
	public int hashCode() {
		return id;
	}

	/**
	 * @return the verwalter
	 */
	public Makler getVerwalter() {
		return verwalter;
	}

	/**
	 * @param verwalter
	 *            the verwalter to set
	 */
	public void setVerwalter(Makler verwalter) {
		this.verwalter = verwalter;
	}

}
