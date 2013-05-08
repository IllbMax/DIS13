package ersteVersuche.Material;

import java.sql.Date;

public class Kaufvertrag extends Vertrag {

	private int anzahlRaten;
	private float ratenzins;

	private Haus haus;

	public Kaufvertrag() {
	}

	public Kaufvertrag(int vertragsnr, Date datum, String ort, Person person,
			Haus immobilie, int anzahlRaten, float ratenzins) {
		super(vertragsnr, datum, ort, person);

		this.anzahlRaten = anzahlRaten;
		this.ratenzins = ratenzins;
		this.haus = immobilie;
	}

	/**
	 * @return the anzahlRaten
	 */
	public int getAnzahlRaten() {
		return anzahlRaten;
	}

	/**
	 * @param anzahlRaten
	 *            the anzahlRaten to set
	 */
	public void setAnzahlRaten(int anzahlRaten) {
		this.anzahlRaten = anzahlRaten;
	}

	/**
	 * @return the ratentins
	 */
	public float getRatenzins() {
		return ratenzins;
	}

	/**
	 * @param ratentins
	 *            the ratentins to set
	 */
	public void setRatenzins(float ratenzins) {
		this.ratenzins = ratenzins;
	}

	/**
	 * @return the haus
	 */
	public Haus getHaus() {
		return haus;
	}

	/**
	 * @param immobilie
	 *            the immobilie to set
	 */
	public void setHaus(Haus haus) {
		this.haus = haus;
	}

	@Override
	public boolean equals(Object obj) {
		if (!super.equals(obj))
			return false;
		Kaufvertrag k = (Kaufvertrag) obj;
		if (k == null)
			return false;
		if (k == this)
			return true;

		return anzahlRaten == k.anzahlRaten && ratenzins == k.ratenzins;

	}

	@Override
	public Immobilie getImmobilie() {
		return haus;
	}

}
