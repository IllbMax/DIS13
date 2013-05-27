package ersteVersuche.Material;

import java.sql.Date;

public class Mietvertrag extends Vertrag {

	private Date mietbeginn;
	private int dauer;
	private float nebenkosten;

	private Wohnung wohnung;

	public Mietvertrag() {
	}

	public Mietvertrag(int vertragsnr, Date datum, String ort, Person person,
			Wohnung immobilie, Date mietbeginn, int dauer, float nebenkosten) {
		super(vertragsnr, datum, ort, person);

		this.mietbeginn = mietbeginn;
		this.dauer = dauer;
		this.nebenkosten = nebenkosten;
		this.wohnung = immobilie;
	}

	/**
	 * @return the haus
	 */
	public Wohnung getWohnung() {
		return wohnung;
	}

	/**
	 * @param immobilie
	 *            the immobilie to set
	 */
	public void setWohnung(Wohnung wohnung) {
		this.wohnung = wohnung;
	}

	/**
	 * @return the mietbeginn
	 */
	public Date getMietbeginn() {
		return mietbeginn;
	}

	/**
	 * @param mietbeginn
	 *            the mietbeginn to set
	 */
	public void setMietbeginn(Date mietbeginn) {
		this.mietbeginn = mietbeginn;
	}

	/**
	 * @return the dauer
	 */
	public int getDauer() {
		return dauer;
	}

	/**
	 * @param dauer
	 *            the dauer to set
	 */
	public void setDauer(int dauer) {
		this.dauer = dauer;
	}

	/**
	 * @return the nebenkosten
	 */
	public float getNebenkosten() {
		return nebenkosten;
	}

	/**
	 * @param nebenkosten
	 *            the nebenkosten to set
	 */
	public void setNebenkosten(float nebenkosten) {
		this.nebenkosten = nebenkosten;
	}

	@Override
	public boolean equals(Object obj) {
		if (!super.equals(obj))
			return false;
		Mietvertrag m = (Mietvertrag) obj;
		if (m == null)
			return false;
		if (m == this)
			return true;

		return dauer == m.dauer && nebenkosten == m.nebenkosten
				&& mietbeginn.equals(m.mietbeginn) && wohnung.equals(m.wohnung);

	}

	@Override
	public Immobilie getImmobilie() {
		return wohnung;
	}

}
