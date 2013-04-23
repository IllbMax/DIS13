package ersteVersuche;

import ersteVersuche.Material.Haus;

public class TableModelImmobilieHaus extends ATableModel<Haus> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final String[] _header = new String[] { "Ort", "PLZ", "Straße", "Haus Nr.",
			"Fläche [m²]", "Stockwerke", "Kaufpreis", "Garten" };
	final Class<?>[] _colClasses = new Class<?>[] { String.class, String.class,
			String.class, String.class, Float.class, Integer.class,
			Float.class, Boolean.class };

	public TableModelImmobilieHaus() {

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
	protected Object ConvertElementToCell(Haus t, int col) {

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
	protected boolean IsElementEditable(Haus t, int col) {
		return true;
	}

	@Override
	protected boolean EditElement(Haus t, int col, Object value) {
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
			float flaeche = (Float) value; // Float.parseFloat(value.toString());
			t.setFlaeche(flaeche);
			break;
		case 5:
			int stockwerke = (Integer) value; // Float.parseFloat(value.toString());
			t.setStockwerke(stockwerke);
			break;
		case 6:
			float preis = (Float) value; // Float.parseFloat(value.toString());
			t.setKaufpreis(preis);
			break;
		case 7:
			boolean garten = (Boolean) value; // Float.parseFloat(value.toString());
			t.setGarten(garten);
			break;

		default:
			return false;
		}

		return true;
	}

	public boolean DeleteHaus(Haus t) {
		return _data.remove(t);
	}

	public boolean DeleteHaus(int row) {
		if (row < 0 || row >= _data.size())
			return false;
		_data.remove(row);
		return true;
	}

	public void AddHaus(Haus t) {
		_data.add(t);
	}
}
