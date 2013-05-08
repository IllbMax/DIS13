package ersteVersuche.Material;

import java.sql.Date;

public abstract class Vertrag {

	private int vertragsnr;
	private Date datum;
	private String ort;

	private Person person;

	public Vertrag() {
	}

	public Vertrag(int vertragsnr, Date datum, String ort, Person person) {
		this.vertragsnr = vertragsnr;
		this.datum = datum;
		this.ort = ort;

		this.person = person;

	}

	/**
	 * @return the vertragsnr
	 */
	public int getVertragsnr() {
		return vertragsnr;
	}

	/**
	 * @param vertragsnr
	 *            the vertragsnr to set
	 */
	public void setVertragsnr(int vertragsnr) {
		this.vertragsnr = vertragsnr;
	}

	/**
	 * @return the datum
	 */
	public Date getDatum() {
		return datum;
	}

	/**
	 * @param datum
	 *            the datum to set
	 */
	public void setDatum(Date datum) {
		this.datum = datum;
	}

	/**
	 * @return the ort
	 */
	public String getOrt() {
		return ort;
	}

	/**
	 * @param ort
	 *            the ort to set
	 */
	public void setOrt(String ort) {
		this.ort = ort;
	}

	/**
	 * @return the person
	 */
	public Person getPerson() {
		return person;
	}

	/**
	 * @param person
	 *            the person to set
	 */
	public void setPerson(Person person) {
		this.person = person;
	}

	public abstract Immobilie getImmobilie();

	@Override
	public boolean equals(Object obj) {
		Vertrag v = (Vertrag) obj;
		if (v == null)
			return false;
		if (v == this)
			return true;

		return vertragsnr == v.vertragsnr && datum.equals(v.datum)
				&& ort.equals(v.ort) && person.equals(v.person);
	}

	@Override
	public int hashCode() {
		return vertragsnr;
	}
}
