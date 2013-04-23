package ersteVersuche;

import ersteVersuche.Material.Wohnung;

public class TableModelImmobilieWohnung extends ATableModel<Wohnung> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final String[] _header = new String[] { "Ort", "PLZ", "Straße", "Haus Nr.",
			"Fläche [m²]", "Stockwerk", "Mietpreis", "Zimmer", "Balkon", "EBK" };
	final Class<?>[] _colClasses = new Class<?>[] { String.class, String.class,
			String.class, String.class, Float.class, Integer.class,
			Float.class, Integer.class, Boolean.class, Boolean.class };

	public TableModelImmobilieWohnung() {

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
	protected Object ConvertElementToCell(Wohnung t, int col) {

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
	protected boolean IsElementEditable(Wohnung t, int col) {
		return true;
	}

	@Override
	protected boolean EditElement(Wohnung t, int col, Object value) {
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

		case 5:
			int stockwerke = (Integer) value; // Float.parseFloat(value.toString());
			t.setStockwerk(stockwerke);
			break;
		case 6:
			float preis = (Float) value; // Float.parseFloat(value.toString());
			t.setMietpreis(preis);
			break;
		case 7:
			int zimmer = (Integer) value; // Float.parseFloat(value.toString());
			t.setZimmer(zimmer);
			break;

		case 8:
			boolean balkon = (Boolean) value; // Float.parseFloat(value.toString());
			t.setBalkon(balkon);
			break;
		case 9:
			boolean ebk = (Boolean) value; // Float.parseFloat(value.toString());
			t.setEBK(ebk);
			break;

		default:
			return false;
		}

		return true;
	}

	public boolean DeleteWohnung(Wohnung t) {
		return _data.remove(t);
	}

	public boolean DeleteWohnung(int row) {
		if (row < 0 || row >= _data.size())
			return false;
		_data.remove(row);
		return true;
	}

	public void AddWohnung(Wohnung t) {
		_data.add(t);
	}
}
