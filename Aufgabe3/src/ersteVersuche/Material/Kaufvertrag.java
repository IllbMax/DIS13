package ersteVersuche.Material;

import java.sql.Date;

public class Kaufvertrag extends Vertrag {

	private int _anzahlRaten;
	private float _ratenzins;

	public Kaufvertrag(int vertragsnr, Date datum, String ort, Person person,
			Haus immobilie, int anzahlRaten, float ratenzins) {
		super(vertragsnr, datum, ort, person, immobilie);

		_anzahlRaten = anzahlRaten;
		_ratenzins = ratenzins;
	}

	/**
	 * @return the anzahlRaten
	 */
	public int getAnzahlRaten() {
		return _anzahlRaten;
	}

	/**
	 * @param anzahlRaten
	 *            the anzahlRaten to set
	 */
	public void setAnzahlRaten(int anzahlRaten) {
		this._anzahlRaten = anzahlRaten;
	}

	/**
	 * @return the ratentins
	 */
	public float getRatenzins() {
		return _ratenzins;
	}

	/**
	 * @param ratentins
	 *            the ratentins to set
	 */
	public void setRatenzins(float ratenzins) {
		this._ratenzins = ratenzins;
	}

	/**
	 * @return the immobilie
	 */
	@Override
	public Haus getImmobilie() {
		return (Haus) _immobilie;
	}

	/**
	 * @return the haus
	 */
	public Haus getHaus() {
		return (Haus) _immobilie;
	}

	/**
	 * @param immobilie
	 *            the immobilie to set
	 */
	public void setHaus(Haus haus) {
		this._immobilie = haus;
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

		return _anzahlRaten == k._anzahlRaten && _ratenzins == k._ratenzins;

	}

}
