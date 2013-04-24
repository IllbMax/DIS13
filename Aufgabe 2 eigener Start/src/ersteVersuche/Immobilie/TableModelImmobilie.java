package ersteVersuche.Immobilie;

import java.util.ArrayList;
import java.util.List;

import ersteVersuche.ATableModel;
import ersteVersuche.Material.Haus;
import ersteVersuche.Material.Immobilie;
import ersteVersuche.Material.Wohnung;

public class TableModelImmobilie extends ATableModel<Immobilie> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private IClassState _state;

	private final IClassState _stateAlle, _stateHaus, _stateWohnung;

	public TableModelImmobilie() {
		_stateAlle = new ImmobilienBaseState();
		_stateHaus = new ImmobilieHausState();
		_stateWohnung = new ImmobilieWohnungState();

		_state = _stateHaus;
		_data = _state.getList();
	}

	@Override
	protected String[] GetRowHeader() {
		return _state.GetRowHeader();
	}

	@Override
	public java.lang.Class<?> getColumnClass(int columnIndex) {
		return _state.getColumnClass(columnIndex);
	};

	@Override
	protected Object ConvertElementToCell(Immobilie t, int col) {

		return _state.ConvertElementToCell(t, col);
	}

	@Override
	protected boolean IsElementEditable(Immobilie t, int col) {
		return true;
	}

	@Override
	protected boolean EditElement(Immobilie t, int col, Object value) {
		return _state.EditElement(t, col, value);
	}

	public boolean DeleteImmobilie(Immobilie t) {
		boolean result = true;

		if (t instanceof Immobilie)
			result &= _stateAlle.getList().remove(t);
		if (t instanceof Wohnung)
			result &= _stateWohnung.getList().remove(t);
		if (t instanceof Haus)
			result &= _stateHaus.getList().remove(t);
		return result;
	}

	public void AddImmobilie(Immobilie t) {
		if (t instanceof Immobilie)
			_stateAlle.getList().add(t);
		if (t instanceof Wohnung)
			_stateWohnung.getList().add(t);
		if (t instanceof Haus)
			_stateHaus.getList().add(t);
	}

	public void SwitchToImmobilien() {
		_state = _stateAlle;
		_data = _state.getList();
	}

	public void SwitchToHaus() {
		_state = _stateHaus;
		_data = _state.getList();
	}

	public void SwitchToWohnung() {
		_state = _stateWohnung;
		_data = _state.getList();
	}

	private interface IClassState {
		List<Immobilie> getList();

		String[] GetRowHeader();

		java.lang.Class<?> getColumnClass(int columnIndex);

		boolean EditElement(Immobilie t, int col, Object value);

		Object ConvertElementToCell(Immobilie t, int col);
	}

	private class ImmobilienBaseState implements IClassState {
		final String[] _header = new String[] { "Ort", "PLZ", "Straße",
				"Haus Nr.", "Fläche [m²]" };
		final Class<?>[] _colClasses = new Class<?>[] { String.class,
				Integer.class, String.class, String.class, Float.class };
		List<Immobilie> _data;

		public ImmobilienBaseState() {
			_data = new ArrayList<Immobilie>();
		}

		@Override
		public List<Immobilie> getList() {
			return _data;
		}

		@Override
		public String[] GetRowHeader() {
			return _header;
		}

		@Override
		public java.lang.Class<?> getColumnClass(int columnIndex) {
			return _colClasses[columnIndex];
		};

		@Override
		public Object ConvertElementToCell(Immobilie t, int col) {

			switch (col) {
			case 0:
				return t.getOrt();
			case 1:
				return t.getPLZ();
			case 2:
				return t.getStrasse();
			case 3:
				return t.getHausNr();
			case 4:
				return t.getFlaeche();

			default:
				return null;
			}
		}

		@Override
		public boolean EditElement(Immobilie t, int col, Object value) {
			Object old = ConvertElementToCell(t, col);

			if (old.equals(value))
				return false;

			switch (col) {
			case 0:
				t.setOrt(value.toString());
				break;
			case 1:
				t.setPLZ(Integer.parseInt(value.toString()));
				break;
			case 2:
				t.setStrasse(value.toString());
				break;
			case 3:
				t.setHausNr(Integer.parseInt(value.toString()));
				break;
			case 4:
				float f = Float.parseFloat(value.toString());
				t.setFlaeche(f);
				break;

			default:
				return false;
			}

			return true;
		}
	}

	private class ImmobilieHausState implements IClassState {

		final String[] _header = new String[] { "Ort", "PLZ", "Straße",
				"Haus Nr.", "Fläche [m²]", "Stockwerke", "Kaufpreis", "Garten" };
		final Class<?>[] _colClasses = new Class<?>[] { String.class,
				Integer.class, String.class, String.class, Float.class,
				Integer.class, Float.class, Boolean.class };

		List<Immobilie> _data;

		public ImmobilieHausState() {
			_data = new ArrayList<Immobilie>();
		}

		@Override
		public List<Immobilie> getList() {
			return _data;
		}

		@Override
		public String[] GetRowHeader() {
			return _header;
		}

		@Override
		public java.lang.Class<?> getColumnClass(int columnIndex) {
			return _colClasses[columnIndex];
		};

		@Override
		public Object ConvertElementToCell(Immobilie t2, int col) {
			if (!(t2 instanceof Haus))
				throw new ArithmeticException("asd");

			Haus t = (Haus) t2;

			switch (col) {
			case 0:
				return t.getOrt();
			case 1:
				return t.getPLZ();
			case 2:
				return t.getStrasse();
			case 3:
				return t.getHausNr();
			case 4:
				return t.getFlaeche();
			case 5:
				return t.getStockwerke();
			case 6:
				return t.getKaufpreis();
			case 7:
				return t.isGarten();

			default:
				return null;
			}
		}

		@Override
		public boolean EditElement(Immobilie t2, int col, Object value) {
			if (!(t2 instanceof Haus))
				throw new ArithmeticException("asd");

			Haus t = (Haus) t2;

			Object old = ConvertElementToCell(t, col);

			if (old.equals(value))
				return false;

			switch (col) {
			case 0:
				t.setOrt(value.toString());
				break;
			case 1:
				t.setPLZ(Integer.parseInt(value.toString()));
				break;
			case 2:
				t.setStrasse(value.toString());
				break;
			case 3:
				t.setHausNr(Integer.parseInt(value.toString()));
				break;
			case 4:
				float flaeche = (Float) value;
				t.setFlaeche(flaeche);
				break;
			case 5:
				int stockwerke = (Integer) value;
				t.setStockwerke(stockwerke);
				break;
			case 6:
				float preis = (Float) value;
				t.setKaufpreis(preis);
				break;
			case 7:
				boolean garten = (Boolean) value;
				t.setGarten(garten);
				break;

			default:
				return false;
			}

			return true;
		}
	}

	private class ImmobilieWohnungState implements IClassState {

		final String[] _header = new String[] { "Ort", "PLZ", "Straße",
				"Haus Nr.", "Fläche [m²]", "Stockwerk", "Mietpreis", "Zimmer",
				"Balkon", "EBK" };
		final Class<?>[] _colClasses = new Class<?>[] { String.class,
				Integer.class, String.class, String.class, Float.class,
				Integer.class, Float.class, Integer.class, Boolean.class,
				Boolean.class };

		List<Immobilie> _data;

		public ImmobilieWohnungState() {
			_data = new ArrayList<Immobilie>();
		}

		@Override
		public List<Immobilie> getList() {
			return _data;
		}

		@Override
		public String[] GetRowHeader() {
			return _header;
		}

		@Override
		public java.lang.Class<?> getColumnClass(int columnIndex) {
			return _colClasses[columnIndex];
		};

		@Override
		public Object ConvertElementToCell(Immobilie t2, int col) {
			if (!(t2 instanceof Wohnung))
				throw new ArithmeticException("asd");

			Wohnung t = (Wohnung) t2;
			switch (col) {
			case 0:
				return t.getOrt();
			case 1:
				return t.getPLZ();
			case 2:
				return t.getStrasse();
			case 3:
				return t.getHausNr();
			case 4:
				return t.getFlaeche();
			case 5:
				return t.getStockwerk();
			case 6:
				return t.getMietpreis();
			case 7:
				return t.getZimmer();
			case 8:
				return t.isBalkon();
			case 9:
				return t.isEBK();

			default:
				return null;
			}
		}

		@Override
		public boolean EditElement(Immobilie t2, int col, Object value) {
			if (!(t2 instanceof Wohnung))
				throw new ArithmeticException("asd");

			Wohnung t = (Wohnung) t2;
			Object old = ConvertElementToCell(t, col);

			if (old.equals(value))
				return false;

			switch (col) {
			case 0:
				t.setOrt(value.toString());
				break;
			case 1:
				t.setPLZ(Integer.parseInt(value.toString()));
				break;
			case 2:
				t.setStrasse(value.toString());
				break;
			case 3:
				t.setHausNr(Integer.parseInt(toString()));
				break;
			case 4:
				float f = Float.parseFloat(value.toString());
				t.setFlaeche(f);
				break;

			case 5:
				int stockwerke = (Integer) value;
				t.setStockwerk(stockwerke);
				break;
			case 6:
				float preis = (Float) value;
				t.setMietpreis(preis);
				break;
			case 7:
				int zimmer = (Integer) value;
				t.setZimmer(zimmer);
				break;

			case 8:
				boolean balkon = (Boolean) value;
				t.setBalkon(balkon);
				break;
			case 9:
				boolean ebk = (Boolean) value;
				t.setEBK(ebk);
				break;

			default:
				return false;
			}

			return true;
		}
	}
}
