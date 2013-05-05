package ersteVersuche.Vertrag;

// TODO: hier muss noch was gemacht werden

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;

import ersteVersuche.Material.Haus;
import ersteVersuche.Material.Immobilie;
import ersteVersuche.Material.Kaufvertrag;
import ersteVersuche.Material.Makler;
import ersteVersuche.Material.Mietvertrag;
import ersteVersuche.Material.Person;
import ersteVersuche.Material.Vertrag;
import ersteVersuche.Material.Wohnung;
import ersteVersuche.Services.ImmoService;

public class VertragsmenuWerkzeug {
	private final VertragsmenuGUI _GUI;
	private Makler _aktuellerMakler;
	private final VertragNeuWerkzeug _vertragNeu;

	private final ImmoService _service;

	private List<Person> _personen = LadePersonen();
	private List<Immobilie> _immobilien = LadeImmobilien();

	public VertragsmenuWerkzeug(ImmoService service) {

		_service = service;

		_GUI = new VertragsmenuGUI();
		_vertragNeu = new VertragNeuWerkzeug(_personen, _immobilien);
		_GUI.AddVertragAddListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				AddVertrag();
			}

		});

		_GUI.AddRefreshAddListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				_personen = LadePersonen();
				_immobilien = LadeImmobilien();

			}
		});

		_GUI.GetTableModel().SetData(LadeVertrag());
	}

	private void AddVertrag() {
		Vertrag i = _vertragNeu.ErstelleVertrag();

		if (i != null && AddVertragSQL(i)) {
			_GUI.GetTableModel().AddVertrag(i);
		}
		_GUI.repaint();
	}

	public void ZeigeVertragsMenu() {
		_GUI.setVisible(true);
		// _makler = makler;
	}

	private boolean AddVertragSQL(Vertrag v) {
		_service.addVertrag(v);
		return true;
	}

	private List<Vertrag> LadeVertrag() {

		List<Vertrag> result = new ArrayList<Vertrag>();
		result.addAll(LadeKaufvertrag());
		result.addAll(LadeMietvertrag());

		return result;
	}

	private List<Kaufvertrag> LadeKaufvertrag() {
		List<Kaufvertrag> result = new ArrayList<Kaufvertrag>(
				_service.getAllKaufvertraegeForMakler(_aktuellerMakler));

		return result;
	}

	private List<Mietvertrag> LadeMietvertrag() {
		List<Mietvertrag> result = new ArrayList<Mietvertrag>(
				_service.getAllMietvertraegeForMakler(_aktuellerMakler));

		return result;
	}

	private Person FindePerson(int pid) {
		Person result = null;
		for (Person p : _personen)
			if (p.getPID() == pid) {
				result = p;
				break;
			}

		return result;
	}

	private Immobilie FindeImmobilie(int id) {
		Immobilie result = null;
		for (Immobilie i : _immobilien)
			if (i.getID() == id) {
				result = i;
				break;
			}
		return result;
	}

	private List<Immobilie> LadeImmobilien() {

		List<Immobilie> result = new ArrayList<Immobilie>();
		result.addAll(LadeImmobilienHaeuser());
		result.addAll(LadeImmobilienWohnungen());

		return result;
	}

	private List<Haus> LadeImmobilienHaeuser() {
		List<Haus> result = new ArrayList<Haus>(
				_service.getAllHaeuserForMakler(_aktuellerMakler));

		return result;
	}

	private List<Wohnung> LadeImmobilienWohnungen() {
		List<Wohnung> result = new ArrayList<Wohnung>(
				_service.getAllWohnungenForMakler(_aktuellerMakler));

		return result;
	}

	private List<Person> LadePersonen() {
		List<Person> result = new ArrayList<Person>(_service.getAllPersons());

		return result;
	}

	public void AddWindowListener(WindowListener l) {
		_GUI.addWindowListener(l);
	}

}
