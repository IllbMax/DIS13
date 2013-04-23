package ersteVersuche;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public abstract class ATableModel<T> extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	protected List<T> _data;

	protected ATableModel() {
		this._data = new ArrayList<T>();
	}

	@Override
	public String getColumnName(int col) {
		return GetRowHeader()[col];
	}

	@Override
	public int getRowCount() {
		return _data.size();
	}

	@Override
	public int getColumnCount() {
		return GetRowHeader().length;
	}

	@Override
	public Object getValueAt(int row, int col) {
		return ConvertElementToCell(_data.get(row), col);
	}

	@Override
	public boolean isCellEditable(int row, int col) {
		return IsElementEditable(_data.get(row), col);
	}

	@Override
	public void setValueAt(Object value, int row, int col) {
		// feuert immer, wenn Zelle (nach Bearbeiten, auch ohne
		// Änderung) verlassen wird (außer durch Escape)
		if (EditElement(_data.get(row), col, value)) {
			fireTableCellUpdated(row, col);
		}
	}

	public T GetElement(int row) {
		return _data.get(row);
	}

	/**
	 * Gibt ein String[] mit den Texten des Headers der Tabelle zurück Die Länge
	 * Dient gleichzeitig zur Bestimmung der Spalten-Anzahl
	 * 
	 * @return String[] mit Spaltennamen
	 */
	protected abstract String[] GetRowHeader();

	/**
	 * Bestimmt den Wert, den Element @see t in der Spalte @see col annimmt
	 * 
	 * @param t
	 *            Elemtent, welches dargestellt werden soll
	 * @param col
	 *            Spalte des Elements
	 * @return Wert in der Tabelle
	 */
	protected abstract Object ConvertElementToCell(T t, int col);

	/**
	 * Bestimmt, ob das Element bearbeitet werden darf
	 * 
	 * @param t
	 *            zu prüfenes Element
	 * @param col
	 *            zu prüfene Spalte
	 * @return true, wenn es bearbeitet werden darf, sonst false
	 */
	protected abstract boolean IsElementEditable(T t, int col);

	/**
	 * Bearbeitet das Element @see t
	 * 
	 * @param t
	 *            Element
	 * @param col
	 *            Veränderte Spalte
	 * @param value
	 *            neuer Wert
	 * @return true, wenn daten verändert wurden, sonst false
	 */
	protected abstract boolean EditElement(T t, int col, Object value);

}