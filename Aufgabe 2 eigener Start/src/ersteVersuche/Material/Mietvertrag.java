package ersteVersuche.Material;

import java.sql.Date;

public class Mietvertrag extends Vertrag {

	private Date _mietbeginn;
	private int _dauer;
	private float _nebenkosten;

	public Mietvertrag(int vertragsnr, Date datum, String ort, Person person,
			Wohnung immobilie, Date mietbeginn, int dauer, float nebenkosten) {
		super(vertragsnr, datum, ort, person, immobilie);

		_mietbeginn = mietbeginn;
		_dauer = dauer;
		_nebenkosten = nebenkosten;
	}

	/**
	 * @return the immobilie
	 */
	@Override
	public Wohnung getImmobilie() {
		return (Wohnung) _immobilie;
	}

	/**
	 * @return the haus
	 */
	public Wohnung getWohnung() {
		return (Wohnung) _immobilie;
	}

	/**
	 * @param immobilie
	 *            the immobilie to set
	 */
	public void setWohnung(Wohnung wohnung) {
		this._immobilie = wohnung;
	}

	/**
	 * @return the mietbeginn
	 */
	public Date getMietbeginn() {
		return _mietbeginn;
	}

	/**
	 * @param mietbeginn
	 *            the mietbeginn to set
	 */
	public void setMietbeginn(Date mietbeginn) {
		this._mietbeginn = mietbeginn;
	}

	/**
	 * @return the dauer
	 */
	public int getDauer() {
		return _dauer;
	}

	/**
	 * @param dauer
	 *            the dauer to set
	 */
	public void setDauer(int dauer) {
		this._dauer = dauer;
	}

	/**
	 * @return the nebenkosten
	 */
	public float getNebenkosten() {
		return _nebenkosten;
	}

	/**
	 * @param nebenkosten
	 *            the nebenkosten to set
	 */
	public void setNebenkosten(float nebenkosten) {
		this._nebenkosten = nebenkosten;
	}

}
