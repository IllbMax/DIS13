package ersteVersuche.Vertrag;

import ersteVersuche.ATableModel;
import ersteVersuche.Material.Person;

public class TableModelPerson extends ATableModel<Person> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final String[] _header = new String[] { "Vorname", "Nachname", "Adresse" };

	public TableModelPerson() {

	}

	@Override
	protected String[] GetRowHeader() {
		return _header;
	}

	@Override
	protected Object ConvertElementToCell(Person t, int col) {

		switch (col) {
		case 0:
			return t.getVorname();
		case 1:
			return t.getNachname();
		case 2:
			return t.getAdresse();

		default:
			return null;
		}
	}

	@Override
	protected boolean IsElementEditable(Person t, int col) {
		return false;
	}

	@Override
	protected boolean EditElement(Person t, int col, Object value) {
		return false;
	}

	public boolean DeletePerson(Person t) {
		return _data.remove(t);
	}

	public boolean DeletePerson(int row) {
		if (row < 0 || row >= _data.size())
			return false;
		_data.remove(row);
		return true;
	}

	public void AddPerson(Person t) {
		_data.add(t);
	}

}
