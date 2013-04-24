/**
 * 
 */
package ersteVersuche.Material;

/**
 * @author Max Menzel
 * 
 */
public class Haus extends Immobilie {

	private int _stockwerke;
	private float _kaufpreis;
	private boolean _garten;

	public Haus(int id, String ort, int plz, String strasse, int hausNr,
			float flaeche, int stockwerke, float kaufpreis, boolean garten) {
		super(id, ort, plz, strasse, hausNr, flaeche);
		_stockwerke = stockwerke;
		_kaufpreis = kaufpreis;
		_garten = garten;
	}

	/**
	 * @return the stockwerke
	 */
	public int getStockwerke() {
		return _stockwerke;
	}

	/**
	 * @param stockwerke
	 *            the stockwerke to set
	 */
	public void setStockwerke(int stockwerke) {
		_stockwerke = stockwerke;
	}

	/**
	 * @return the kaufpreis
	 */
	public float getKaufpreis() {
		return _kaufpreis;
	}

	/**
	 * @param kaufpreis
	 *            the kaufpreis to set
	 */
	public void setKaufpreis(float kaufpreis) {
		_kaufpreis = kaufpreis;
	}

	/**
	 * @return the garten
	 */
	public boolean isGarten() {
		return _garten;
	}

	/**
	 * @param garten
	 *            the garten to set
	 */
	public void setGarten(boolean garten) {
		_garten = garten;
	}

}
