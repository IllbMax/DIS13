package ersteVersuche;

import ersteVersuche.Material.Immobilie;

public class TableModelImmobilie extends ATableModel<Immobilie> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final String[] _header = new String[] { "Ort", "PLZ", "Straße", "Haus Nr.",
			"Fläche [m²]" };
	final Class<?>[] _colClasses = new Class<?>[] { String.class, String.class,
			String.class, String.class, Float.class };

	public TableModelImmobilie() {

	}

	@Override
	protected String[] GetRowHeader() {
		return _header;
	}

	@Override
	public java.lang.Class<?> getColumnClass(int columnIndex) {
		return _colClasses[columnIndex];
	};

	@Override
	protected Object ConvertElementToCell(Immobilie t, int col) {

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
	protected boolean IsElementEditable(Immobilie t, int col) {
		return true;
	}

	@Override
	protected boolean EditElement(Immobilie t, int col, Object value) {
		Object old = ConvertElementToCell(t, col);

		if (old.equals(value))
			return false;

		switch (col) {
		case 0:
			t.setOrt(value.toString());
			break;
		case 1:
			t.setPLZ(value.toString());
			break;
		case 2:
			t.setStrasse(value.toString());
			break;
		case 3:
			t.setHausNr(value.toString());
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

	public boolean DeleteImmobilie(Immobilie t) {
		return _data.remove(t);
	}

	public boolean DeleteImmobilie(int row) {
		if (row < 0 || row >= _data.size())
			return false;
		_data.remove(row);
		return true;
	}

	public void AddImmobilie(Immobilie t) {
		_data.add(t);
	}
}
