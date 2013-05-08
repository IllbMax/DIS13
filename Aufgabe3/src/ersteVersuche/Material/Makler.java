package ersteVersuche.Material;

public class Makler {
	private int id;
	private String name;
	private String adresse;
	private String login;
	private String passwort;

	public Makler() {
	}

	public Makler(String name, String adresse, String login, String passwort) {
		this.name = name;
		this.adresse = adresse;
		this.login = login;
		this.passwort = passwort;
	}

	public String getPasswort() {
		return this.passwort;
	}

	public void setPasswort(String passwort) {
		this.passwort = passwort;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getAdresse() {
		return this.adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the iD
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * @param iD
	 *            the iD to set
	 */
	public void setId(int iD) {
		this.id = iD;
	}

	@Override
	public boolean equals(Object obj) {
		Makler m = (Makler) obj;
		if (m == null)
			return false;
		if (m == this)
			return true;

		return id == m.id && name.equals(m.name)
				&& adresse.equals(m.adresse) && login.equals(m.login)
				&& passwort.equals(m.passwort);
	}

	@Override
	public int hashCode() {
		return id;
	}

}
