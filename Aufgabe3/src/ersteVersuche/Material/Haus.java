/**
 * 
 */
package ersteVersuche.Material;

/**
 * @author Max Menzel
 * 
 */
public class Haus extends Immobilie {

	private int stockwerke;
	private float kaufpreis;
	private boolean garten;

	public Haus() {
	}

	public Haus(int id, String ort, int plz, String strasse, int hausNr,
			float flaeche, int stockwerke, float kaufpreis, boolean garten) {
		super(id, ort, plz, strasse, hausNr, flaeche);
		this.stockwerke = stockwerke;
		this.kaufpreis = kaufpreis;
		this.garten = garten;
	}

	/**
	 * @return the stockwerke
	 */
	public int getStockwerke() {
		return stockwerke;
	}

	/**
	 * @param stockwerke
	 *            the stockwerke to set
	 */
	public void setStockwerke(int stockwerke) {
		this.stockwerke = stockwerke;
	}

	/**
	 * @return the kaufpreis
	 */
	public float getKaufpreis() {
		return kaufpreis;
	}

	/**
	 * @param kaufpreis
	 *            the kaufpreis to set
	 */
	public void setKaufpreis(float kaufpreis) {
		this.kaufpreis = kaufpreis;
	}

	/**
	 * @return the garten
	 */
	public boolean isGarten() {
		return garten;
	}

	/**
	 * @param garten
	 *            the garten to set
	 */
	public void setGarten(boolean garten) {
		this.garten = garten;
	}

	@Override
	public boolean equals(Object obj) {
		if (!super.equals(obj))
			return false;
		Haus h = (Haus) obj;
		if (h == null)
			return false;
		if (h == this)
			return true;

		return stockwerke == h.stockwerke && garten == h.garten
				&& kaufpreis == h.kaufpreis;

	}

}
