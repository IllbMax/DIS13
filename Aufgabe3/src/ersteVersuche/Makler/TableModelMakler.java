package ersteVersuche.Makler;

import ersteVersuche.ATableModel;
import ersteVersuche.Material.Makler;

public class TableModelMakler extends ATableModel<Makler> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final String[] _header = new String[] { "Name", "Adresse", "Login",
			"Passwort" };
	private String _lastLoginValue;

	public TableModelMakler() {

	}

	@Override
	protected String[] GetRowHeader() {
		return _header;
	}

	@Override
	protected Object ConvertElementToCell(Makler t, int col) {

		switch (col) {
		case 0:
			return t.getName();
		case 1:
			return t.getAdresse();
		case 2:
			return t.getLogin();
		case 3:
			return t.getPasswort();

		default:
			return null;
		}
	}

	@Override
	protected boolean IsElementEditable(Makler t, int col) {
		return true;
	}

	@Override
	protected boolean EditElement(Makler t, int col, Object value) {
		Object old = ConvertElementToCell(t, col);

		_lastLoginValue = t.getLogin();

		if (old.equals(value))
			return false;

		switch (col) {
		case 0:
			t.setName(value.toString());
			break;
		case 1:
			t.setAdresse(value.toString());
			break;
		case 2:
			t.setLogin(value.toString());
			break;
		case 3:
			t.setPasswort(value.toString());
			break;

		default:
			return false;
		}

		return true;
	}

	public boolean DeleteMakler(Makler t) {
		return _data.remove(t);
	}

	public boolean DeleteMakler(int row) {
		if (row < 0 || row >= _data.size())
			return false;
		_data.remove(row);
		return true;
	}

	public void AddMakler(Makler t) {
		_data.add(t);
	}

	public String getLastLoginValue() {
		return _lastLoginValue;
	}
}
