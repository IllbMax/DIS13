package ersteVersuche.Material;

import java.sql.Date;

public abstract class Vertrag {

	private int _vertragsnr;
	private Date _datum;
	private String _ort;

	private Person _person;

	public Vertrag() {
	}

	public Vertrag(int vertragsnr, Date datum, String ort, Person person) {
		_vertragsnr = vertragsnr;
		_datum = datum;
		_ort = ort;

		_person = person;

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

	public abstract Immobilie getImmobilie();

	@Override
	public boolean equals(Object obj) {
		Vertrag v = (Vertrag) obj;
		if (v == null)
			return false;
		if (v == this)
			return true;

		return _vertragsnr == v._vertragsnr && _datum.equals(v._datum)
				&& _ort.equals(v._ort) && _person.equals(v._person);
	}

	@Override
	public int hashCode() {
		return _vertragsnr;
	}
}
