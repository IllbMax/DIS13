package ersteVersuche.Vertrag;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ersteVersuche.ATableModel;
import ersteVersuche.Material.Kaufvertrag;
import ersteVersuche.Material.Mietvertrag;
import ersteVersuche.Material.Vertrag;

public class TableModelVertrag extends ATableModel<Vertrag> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private IClassState _state;

	private final IClassState _stateAlle, _stateKauf, _stateMiet;

	public TableModelVertrag() {
		_stateAlle = new VertragBaseState();
		_stateKauf = new VertragKaufvertragState();
		_stateMiet = new VertragMietvertragState();

		_state = _stateKauf;
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
	protected Object ConvertElementToCell(Vertrag t, int col) {

		return _state.ConvertElementToCell(t, col);
	}

	@Override
	protected boolean IsElementEditable(Vertrag t, int col) {
		return false;
	}

	@Override
	protected boolean EditElement(Vertrag t, int col, Object value) {
		return _state.EditElement(t, col, value);
	}

	@Override
	public void SetData(List<Vertrag> data) {
		_stateAlle.getList().clear();
		_stateMiet.getList().clear();
		_stateKauf.getList().clear();

		for (Vertrag v : data)
			AddVertrag(v);
	}

	public boolean DeleteVertrag(Vertrag t) {
		boolean result = true;

		if (t instanceof Vertrag)
			result &= _stateAlle.getList().remove(t);
		if (t instanceof Mietvertrag)
			result &= _stateMiet.getList().remove(t);
		if (t instanceof Kaufvertrag)
			result &= _stateKauf.getList().remove(t);
		return result;
	}

	public void AddVertrag(Vertrag t) {
		if (t instanceof Vertrag)
			_stateAlle.getList().add(t);
		if (t instanceof Mietvertrag)
			_stateMiet.getList().add(t);
		if (t instanceof Kaufvertrag)
			_stateKauf.getList().add(t);
	}

	public void SwitchToVertraege() {
		_state = _stateAlle;
		_data = _state.getList();
	}

	public void SwitchToKaufvertrag() {
		_state = _stateKauf;
		_data = _state.getList();
	}

	public void SwitchToMietvertrag() {
		_state = _stateMiet;
		_data = _state.getList();
	}

	private interface IClassState {
		List<Vertrag> getList();

		String[] GetRowHeader();

		java.lang.Class<?> getColumnClass(int columnIndex);

		boolean EditElement(Vertrag t, int col, Object value);

		Object ConvertElementToCell(Vertrag t, int col);
	}

	private class VertragBaseState implements IClassState {
		final String[] _header = new String[] { "Vertragsnummer", "Ort",
				"Datum", "Immobilie ID", "Person" };
		final Class<?>[] _colClasses = new Class<?>[] { Integer.class,
				String.class, Date.class, Integer.class, String.class };
		List<Vertrag> _data;

		public VertragBaseState() {
			_data = new ArrayList<Vertrag>();
		}

		@Override
		public List<Vertrag> getList() {
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
		public Object ConvertElementToCell(Vertrag t, int col) {

			switch (col) {
			case 0:
				return t.getVertragsnr();
			case 1:
				return t.getDatum();
			case 2:
				return t.getOrt();
			case 3:
				return t.getImmobilie().getID();
			case 4:
				return t.getPerson().getNachname() + ", "
						+ t.getPerson().getNachname();

			default:
				return null;
			}
		}

		@Override
		public boolean EditElement(Vertrag t, int col, Object value) {
			return false;
		}
	}

	private class VertragKaufvertragState implements IClassState {

		final String[] _header = new String[] { "Vertragsnummer", "Ort",
				"Datum", "Anzahl Raten", "Ratenzins", "Haus ID", "Person" };
		final Class<?>[] _colClasses = new Class<?>[] { Integer.class,
				String.class, Date.class, Integer.class, Float.class,
				Integer.class, String.class };

		List<Vertrag> _data;

		public VertragKaufvertragState() {
			_data = new ArrayList<Vertrag>();
		}

		@Override
		public List<Vertrag> getList() {
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
		public Object ConvertElementToCell(Vertrag t2, int col) {
			if (!(t2 instanceof Kaufvertrag))
				throw new ArithmeticException("asd");

			Kaufvertrag t = (Kaufvertrag) t2;

			switch (col) {
			case 0:
				return t.getVertragsnr();
			case 1:
				return t.getDatum();
			case 2:
				return t.getOrt();
			case 3:
				return t.getAnzahlRaten();
			case 4:
				return t.getRatenzins();
			case 5:
				return t.getImmobilie().getID();
			case 6:
				return t.getPerson().getNachname() + ", "
						+ t.getPerson().getNachname();

			default:
				return null;
			}
		}

		@Override
		public boolean EditElement(Vertrag t2, int col, Object value) {
			return false;
		}
	}

	private class VertragMietvertragState implements IClassState {

		final String[] _header = new String[] { "Vertragsnummer", "Ort",
				"Datum", "Mietbeginn", "Dauer [Monat]", "Nebenkosten",
				"Haus ID", "Person" };
		final Class<?>[] _colClasses = new Class<?>[] { Integer.class,
				String.class, Date.class, Date.class, Integer.class,
				Float.class, Integer.class, String.class };

		List<Vertrag> _data;

		public VertragMietvertragState() {
			_data = new ArrayList<Vertrag>();
		}

		@Override
		public List<Vertrag> getList() {
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
		public Object ConvertElementToCell(Vertrag t2, int col) {
			if (!(t2 instanceof Mietvertrag))
				throw new ArithmeticException("asd");

			Mietvertrag t = (Mietvertrag) t2;
			switch (col) {
			case 0:
				return t.getVertragsnr();
			case 1:
				return t.getDatum();
			case 2:
				return t.getOrt();
			case 3:
				return t.getMietbeginn();
			case 4:
				return t.getDauer();
			case 5:
				return t.getNebenkosten();
			case 6:
				return t.getImmobilie().getID();
			case 7:
				return t.getPerson().getNachname() + ", "
						+ t.getPerson().getNachname();

			default:
				return null;
			}
		}

		@Override
		public boolean EditElement(Vertrag t2, int col, Object value) {
			return false;
		}
	}
}
