package ersteVersuche.Material;

public class Person {

	private int pid;
	private String vorname;
	private String nachname;
	private String adresse;

	public Person() {
	}

	public Person(int pid, String vorname, String nachname, String adresse) {
		this.pid = pid;
		this.vorname = vorname;
		this.nachname = nachname;
		this.adresse = adresse;
	}

	/**
	 * @return the adresse
	 */
	public String getAdresse() {
		return this.adresse;
	}

	/**
	 * @param adresse
	 *            the adresse to set
	 */
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	/**
	 * @return the nachname
	 */
	public String getNachname() {
		return this.nachname;
	}

	/**
	 * @param nachname
	 *            the nachname to set
	 */
	public void setNachname(String nachname) {
		this.nachname = nachname;
	}

	/**
	 * @return the vorname
	 */
	public String getVorname() {
		return this.vorname;
	}

	/**
	 * @param vorname
	 *            the vorname to set
	 */
	public void setVorname(String vorname) {
		this.vorname = vorname;
	}

	public int getPID() {
		return this.pid;
	}

	public void setPID(int pid) {
		this.pid = pid;
	}

	@Override
	public String toString() {
		return this.nachname + ", " + this.vorname;
	}

	@Override
	public boolean equals(Object obj) {
		Person p = (Person) obj;
		if (p == null)
			return false;
		if (p == this)
			return true;

		return pid == p.pid && nachname.equals(p.nachname)
				&& adresse.equals(p.adresse) && vorname.equals(p.vorname);
	}

	@Override
	public int hashCode() {
		return pid;
	}

}
