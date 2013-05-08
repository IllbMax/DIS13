package ersteVersuche.Services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import ersteVersuche.Material.Haus;
import ersteVersuche.Material.Immobilie;
import ersteVersuche.Material.Kaufvertrag;
import ersteVersuche.Material.Makler;
import ersteVersuche.Material.Mietvertrag;
import ersteVersuche.Material.Person;
import ersteVersuche.Material.Vertrag;
import ersteVersuche.Material.Wohnung;

/**
 * Klasse zur Verwaltung aller Datenbank-Entitäten.
 * 
 * TODO: Aktuell werden alle Daten im Speicher gehalten. Ziel der Übung ist es,
 * schrittweise die Datenverwaltung in die Datenbank auszulagern. Wenn die
 * Arbeit erledigt ist, werden alle Sets dieser Klasse überflüssig.
 */
public class ImmoService {
	// Datensätze im Speicher
	private final Set<Makler> makler = new HashSet<Makler>();
	private final Set<Person> personen = new HashSet<Person>();
	private final Set<Haus> haeuser = new HashSet<Haus>();
	private final Set<Wohnung> wohnungen = new HashSet<Wohnung>();
	private final Set<Mietvertrag> mietvertraege = new HashSet<Mietvertrag>();
	private final Set<Kaufvertrag> kaufvertraege = new HashSet<Kaufvertrag>();

	// Hibernate Session
	private final SessionFactory sessionFactory;
	private final Session _session;

	public ImmoService() {
		sessionFactory = new Configuration().configure().buildSessionFactory();

		_session = sessionFactory.openSession();
	}

	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		getSession().close();
	}

	/**
	 * Finde einen Makler mit gegebener Id
	 * 
	 * @param id
	 *            Die ID des Maklers
	 * @return Makler mit der ID oder null
	 */
	public Makler getMaklerById(int id) {
		return (Makler) getSession().get(Makler.class, id);
	}

	/**
	 * Finde einen Makler mit gegebenem Login
	 * 
	 * @param login
	 *            Der Login des Maklers
	 * @return Makler mit der ID oder null
	 */
	public Makler getMaklerByLogin(String login) {
		return (Makler) getSession()
				.createQuery("FROM Makler as makler where makler.login = ?")
				.setString(0, login).uniqueResult();
	}

	public Makler getMaklerByLoginPasswort(String login, String passwort) {

		return (Makler) getSession()
				.createQuery(
						"FROM Makler as makler where makler.login = ? AND makler.passwort = ?")
				.setString(0, login).setString(1, passwort).uniqueResult();
	}

	/**
	 * Gibt alle Makler zurück
	 */
	public Set<Makler> getAllMakler() {

		List makler = getSession().createQuery("FROM Makler as makler").list();

		Set<Makler> m = new HashSet<Makler>(makler);

		return m;
	}

	/**
	 * Finde eine Person mit gegebener Id
	 * 
	 * @param id
	 *            Die ID der Person
	 * @return Person mit der ID oder null
	 */
	public Person getPersonById(int pid) {
		return (Person) getSession().get(Person.class, pid);

	}

	/**
	 * Fügt einen Makler hinzu
	 * 
	 * @param m
	 *            Der Makler
	 */
	public boolean addMakler(Makler m) {

		getSession().beginTransaction();
		Integer id = (Integer) getSession().save(m);
		getSession().getTransaction().commit();
		if (id != null) {
			m.setId(id);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Löscht einen Makler
	 * 
	 * @param m
	 *            Der Makler
	 */
	public void deleteMakler(Makler m) {
		getSession().beginTransaction();
		getSession().delete(m);
		getSession().getTransaction().commit();
	}

	/**
	 * Fügt eine Person hinzu
	 * 
	 * @param p
	 *            Die Person
	 */
	public boolean addPerson(Person p) {

		getSession().beginTransaction();
		Integer id = (Integer) getSession().save(p);
		getSession().getTransaction().commit();

		if (id != null) {
			p.setPID(id);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Gibt alle Personen zurück
	 */
	public Set<Person> getAllPersons() {

		List person = getSession().createQuery("FROM Person as person").list();

		Set<Person> m = new HashSet<Person>(person);

		return m;
	}

	/**
	 * Löscht eine Person
	 * 
	 * @param p
	 *            Die Person
	 */
	public void deletePerson(Person p) {
		getSession().beginTransaction();
		getSession().delete(p);
		getSession().getTransaction().commit();
	}

	/**
	 * Fügt ein Haus hinzu
	 * 
	 * @param h
	 *            Das Haus
	 */
	public boolean addHaus(Haus h) {
		getSession().beginTransaction();
		Integer id = (Integer) getSession().save(h);
		getSession().getTransaction().commit();

		if (id != null) {
			h.setID(id);
			return true;
		} else {
			return false;
		}
	}

	public void addImmobilie(Immobilie i) {
		if (i instanceof Wohnung)
			addWohnung((Wohnung) i);
		if (i instanceof Haus)
			addHaus((Haus) i);

	}

	/**
	 * Gibt alle Häuser eines Maklers zurück
	 * 
	 * @param m
	 *            Der Makler
	 * @return Alle Häuser, die vom Makler verwaltet werden
	 */
	public Set<Haus> getAllHaeuserForMakler(Makler m) {
		if (m == null)
			return new HashSet<Haus>();
		List haus = getSession()
				.createQuery("FROM Haus as haus WHERE haus.verwalter= ?")
				.setEntity(0, m).list();

		Set<Haus> h = new HashSet<Haus>(haus);

		return h;
	}

	/**
	 * Findet ein Haus mit gegebener ID
	 * 
	 * @param m
	 *            Der Makler
	 * @return Das Haus oder null, falls nicht gefunden
	 */
	public Haus getHausById(int id) {
		return (Haus) getSession().get(Haus.class, id);

	}

	/**
	 * Löscht ein Haus
	 * 
	 * @param p
	 *            Das Haus
	 */
	public void deleteHaus(Haus h) {
		getSession().beginTransaction();
		getSession().delete(h);
		getSession().getTransaction().commit();
	}

	/**
	 * Fügt eine Wohnung hinzu
	 * 
	 * @param w
	 *            die Wohnung
	 */
	public boolean addWohnung(Wohnung w) {
		getSession().beginTransaction();
		Integer id = (Integer) getSession().save(w);
		getSession().getTransaction().commit();

		if (id != null) {
			w.setID(id);
			return true;
		} else {
			return false;
		}
	}

	public void deleteImmobilie(Immobilie i) {
		if (i instanceof Wohnung)
			deleteWohnung((Wohnung) i);
		if (i instanceof Haus)
			deleteHaus((Haus) i);
	}

	/**
	 * Gibt alle Wohnungen eines Maklers zurück
	 * 
	 * @param m
	 *            Der Makler
	 * @return Alle Wohnungen, die vom Makler verwaltet werden
	 */
	public Set<Wohnung> getAllWohnungenForMakler(Makler m) {
		if (m == null)
			return new HashSet<Wohnung>();

		List wohnung = getSession()
				.createQuery(
						"FROM Wohnung as wohnung WHERE wohnung.verwalter = ?")
				.setEntity(0, m).list();

		Set<Wohnung> w = new HashSet<Wohnung>(wohnung);

		return w;
	}

	/**
	 * Findet eine Wohnung mit gegebener ID
	 * 
	 * @param id
	 *            Die ID
	 * @return Die Wohnung oder null, falls nicht gefunden
	 */
	public Wohnung getWohnungById(int id) {

		return (Wohnung) getSession().get(Wohnung.class, id);

	}

	/**
	 * Löscht eine Wohnung
	 * 
	 * @param p
	 *            Die Wohnung
	 */
	public void deleteWohnung(Wohnung w) {
		getSession().beginTransaction();
		getSession().delete(w);
		getSession().getTransaction().commit();
	}

	/**
	 * Fügt einen Mietvertrag hinzu
	 * 
	 * @param w
	 *            Der Mietvertrag
	 */
	public boolean addMietvertrag(Mietvertrag m) {
		getSession().beginTransaction();
		Integer id = (Integer) getSession().save(m);
		getSession().getTransaction().commit();

		if (id != null) {
			m.setVertragsnr(id);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Fügt einen Kaufvertrag hinzu
	 * 
	 * @param w
	 *            Der Kaufvertrag
	 */
	public boolean addKaufvertrag(Kaufvertrag k) {
		getSession().beginTransaction();
		Integer id = (Integer) getSession().save(k);
		getSession().getTransaction().commit();

		if (id != null) {
			k.setVertragsnr(id);
			return true;
		} else {
			return false;
		}
	}

	public void addVertrag(Vertrag v) {
		if (v instanceof Kaufvertrag)
			addKaufvertrag((Kaufvertrag) v);
		if (v instanceof Mietvertrag)
			addMietvertrag((Mietvertrag) v);

	}

	/**
	 * Gibt alle Mietverträge zu Wohnungen eines Maklers zurück
	 * 
	 * @param m
	 *            Der Makler
	 * @return Alle Mietverträge, die zu Wohnungen gehören, die vom Makler
	 *         verwaltet werden
	 */
	public Set<Mietvertrag> getAllMietvertraegeForMakler(Makler m) {
		List mietvertrag = getSession()
				.createQuery(
						"FROM Mietvertrag as mietvertrag WHERE mietvertrag.wohnung.verwalter= ?")
				.setEntity(0, m).list();

		Set<Mietvertrag> w = new HashSet<Mietvertrag>(mietvertrag);

		return w;
	}

	/**
	 * Gibt alle Kaufverträge zu Wohnungen eines Maklers zurück
	 * 
	 * @param m
	 *            Der Makler
	 * @return Alle Kaufverträge, die zu Häusern gehören, die vom Makler
	 *         verwaltet werden
	 */
	public Set<Kaufvertrag> getAllKaufvertraegeForMakler(Makler m) {
		if (m == null)
			return new HashSet<Kaufvertrag>();
		List kaufvertrag = getSession()
				.createQuery(
						"FROM Kaufvertrag as kaufvertrag WHERE kaufvertrag.haus.verwalter= ?")
				.setEntity(0, m).list();

		Set<Kaufvertrag> w = new HashSet<Kaufvertrag>(kaufvertrag);

		return w;
	}

	/**
	 * Findet einen Mietvertrag mit gegebener Vertragsnummer
	 * 
	 * @param vnr
	 *            Die Vertragsnummer
	 * @return Der Mietvertrag oder null, falls nicht gefunden
	 */
	public Mietvertrag getMietvertragByVNR(int vnr) {
		return (Mietvertrag) getSession().get(Mietvertrag.class, vnr);
	}

	/**
	 * Findet alle Mietverträge, die Wohnungen eines gegebenen Verwalters
	 * betreffen
	 * 
	 * @param id
	 *            Der Verwalter
	 * @return Set aus Mietverträgen
	 */
	public Set<Mietvertrag> getMietvertragByVerwalter(Makler m) {
		List mietvertrag = getSession()
				.createQuery(
						"FROM Mietvertrag as mietvertrag WHERE mietvertrag.wohnung.verwalter= ?")
				.setEntity(0, m).list();

		Set<Mietvertrag> w = new HashSet<Mietvertrag>(mietvertrag);

		return w;
	}

	/**
	 * Findet alle Kaufverträge, die Häuser eines gegebenen Verwalters betreffen
	 * 
	 * @param id
	 *            Der Verwalter
	 * @return Set aus Kaufverträgen
	 */
	public Set<Kaufvertrag> getKaufvertragByVerwalter(Makler m) {
		List kaufvertrag = getSession()
				.createQuery(
						"FROM Kaufvertrag as kaufvertrag WHERE kaufvertrag.haus.verwalter= ?")
				.setEntity(0, m).list();

		Set<Kaufvertrag> w = new HashSet<Kaufvertrag>(kaufvertrag);

		return w;
	}

	/**
	 * Findet einen Kaufvertrag mit gegebener Vertragsnummer
	 * 
	 * @param vnr
	 *            Die Vertragsnummer
	 * @return Der Kaufvertrag oder null, falls nicht gefunden
	 */
	public Kaufvertrag getKaufvertragByVNR(int vnr) {
		return (Kaufvertrag) getSession().get(Kaufvertrag.class, vnr);
	}

	/**
	 * Löscht einen Mietvertrag
	 * 
	 * @param m
	 *            Der Mietvertrag
	 */
	public void deleteMietvertrag(Mietvertrag m) {
		getSession().beginTransaction();
		getSession().delete(m);
		getSession().getTransaction().commit();
	}

	/**
	 * Fügt einige Testdaten hinzu
	 */
	public void addTestData() {
		// Hibernate Session erzeugen
		Session session = sessionFactory.openSession();

		session.beginTransaction();

		Makler m = new Makler();
		m.setName("Max Mustermann");
		m.setAdresse("Am Informatikum 9");
		m.setLogin("max");
		m.setPasswort("max");
		getSession().save(m);

		// TODO: Dieser Makler wird im Speicher und der DB gehalten
		this.addMakler(m);
		session.save(m);
		session.getTransaction().commit();

		session.beginTransaction();

		Person p1 = new Person();
		p1.setAdresse("Informatikum");
		p1.setNachname("Mustermann");
		p1.setVorname("Erika");

		Person p2 = new Person();
		p2.setAdresse("Reeperbahn 9");
		p2.setNachname("Albers");
		p2.setVorname("Hans");

		session.save(p1);
		session.save(p2);

		// TODO: Diese Personen werden im Speicher und der DB gehalten
		this.addPerson(p1);
		this.addPerson(p2);
		session.getTransaction().commit();

		// Hibernate Session erzeugen
		session.beginTransaction();
		Haus h = new Haus();
		h.setOrt("Hamburg");
		h.setPLZ(22527);
		h.setStrasse("Vogt-Kölln-Straße");
		h.setHausNr(2);
		h.setFlaeche(384);
		h.setStockwerke(5);
		h.setKaufpreis(10000000);
		h.setGarten(true);
		h.setVerwalter(m);

		session.save(h);

		// TODO: Dieses Haus wird im Speicher und der DB gehalten
		this.addHaus(h);
		session.getTransaction().commit();

		// Hibernate Session erzeugen
		session = sessionFactory.openSession();
		session.beginTransaction();
		Makler m2 = (Makler) session.get(Makler.class, m.getId());
		// Set<Immobilie> immos = m2.;
		// Iterator<Immobilie> it = immos.iterator();

		// while (it.hasNext()) {
		// Immobilie i = it.next();
		// System.out.println("Immo: " + i.getOrt());
		// }
		session.close();

		Wohnung w = new Wohnung();
		w.setOrt("Hamburg");
		w.setPLZ(22527);
		w.setStrasse("Vogt-Kölln-Straße");
		w.setHausNr(3);
		w.setFlaeche(120);
		w.setStockwerk(4);
		w.setMietpreis(790);
		w.setEBK(true);
		w.setBalkon(false);
		w.setVerwalter(m);
		this.addWohnung(w);

		w = new Wohnung();
		w.setOrt("Berlin");
		w.setPLZ(22527);
		w.setStrasse("Vogt-Kölln-Straße");
		w.setHausNr(3);
		w.setFlaeche(120);
		w.setStockwerk(4);
		w.setMietpreis(790);
		w.setEBK(true);
		w.setBalkon(false);
		w.setVerwalter(m);
		this.addWohnung(w);

		Kaufvertrag kv = new Kaufvertrag();
		kv.setHaus(h);
		kv.setPerson(p1);
		kv.setVertragsnr(9234);
		kv.setDatum(new java.sql.Date(System.currentTimeMillis()));
		kv.setOrt("Hamburg");
		kv.setAnzahlRaten(5);
		kv.setRatenzins(4);
		this.addKaufvertrag(kv);

		Mietvertrag mv = new Mietvertrag();
		mv.setWohnung(w);
		mv.setPerson(p2);
		mv.setVertragsnr(23112);
		mv.setDatum(new java.sql.Date(System.currentTimeMillis() - 1000000000));
		mv.setOrt("Berlin");
		mv.setMietbeginn(new java.sql.Date(System.currentTimeMillis()));
		mv.setNebenkosten(65);
		mv.setDauer(36);
		this.addMietvertrag(mv);
	}

	/**
	 * aktualisiert Objekt Person
	 */
	public void AktualisierePerson(Person p) {
		getSession().beginTransaction();
		getSession().save(p);
		getSession().flush();
		getSession().refresh(p);
		getSession().getTransaction().commit();
	}

	/**
	 * aktualisiert Objekt Makler
	 */
	public void AktualisiereMakler(Makler m) {
		getSession().beginTransaction();
		getSession().save(m);
		getSession().flush();
		getSession().refresh(m);
		getSession().getTransaction().commit();
	}

	/**
	 * aktualisiert Objekt Vertrag
	 */

	public void AktualisiereVertrag(Vertrag v) {
		getSession().beginTransaction();
		getSession().save(v);
		getSession().flush();
		getSession().refresh(v);
		getSession().getTransaction().commit();
	}

	/**
	 * aktualisiert Objekt Immobilie
	 */
	public void AktualisiereImmobilie(Immobilie i) {
		getSession().beginTransaction();
		getSession().save(i);
		getSession().flush();
		getSession().refresh(i);
		getSession().getTransaction().commit();
	}

	public Session getSession() {
		return _session;
	}
}
