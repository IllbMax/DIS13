package ersteVersuche.Material;

import java.sql.Date;

public abstract class Vertrag {

	private int _vertragsnr;
	private Date _datum;
	private String _ort;

	protected Immobilie _immobilie;
	private Person _person;

	public Vertrag(int vertragsnr, Date datum, String ort, Person person,
			Immobilie immobilie) {
		_vertragsnr = vertragsnr;
		_datum = datum;
		_ort = ort;

		_person = person;
		_immobilie = immobilie;
	}

	/**
	 * @return the vertragsnr
	 */
	public int getVertragsnr() {
		return _vertragsnr;
	}

	/**
	 * @param vertragsnr
	 *            the vertragsnr to set
	 */
	public void setVertragsnr(int vertragsnr) {
		this._vertragsnr = vertragsnr;
	}

	/**
	 * @return the datum
	 */
	public Date getDatum() {
		return _datum;
	}

	/**
	 * @param datum
	 *            the datum to set
	 */
	public void setDatum(Date datum) {
		this._datum = datum;
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
		this._ort = ort;
	}

	/**
	 * @return the immobilie
	 */
	public Immobilie getImmobilie() {
		return _immobilie;
	}

	/**
	 * @return the person
	 */
	public Person getPerson() {
		return _person;
	}

	/**
	 * @param person
	 *            the person to set
	 */
	public void setPerson(Person person) {
		this._person = person;
	}

}
