package ersteVersuche.Material;

public class Wohnung extends Immobilie {

	private int stockwerk;
	private float mietpreis;
	private int zimmer;
	private boolean balkon;
	private boolean ebk;

	public Wohnung() {
	}

	public Wohnung(int id, String ort, int plz, String strasse, int hausNr,
			float flaeche, int stockwerk, float mietpreis, int zimmer,
			boolean balkon, boolean ebk) {
		super(id, ort, plz, strasse, hausNr, flaeche);

		this.stockwerk = stockwerk;
		this.mietpreis = mietpreis;
		this.zimmer = zimmer;
		this.balkon = balkon;
		this.ebk = ebk;
	}

	/**
	 * @return the stockwerk
	 */
	public int getStockwerk() {
		return this.stockwerk;
	}

	/**
	 * @param stockwerk
	 *            the stockwerk to set
	 */
	public void setStockwerk(int stockwerk) {
		this.stockwerk = stockwerk;
	}

	/**
	 * @return the mietpreis
	 */
	public float getMietpreis() {
		return this.mietpreis;
	}

	/**
	 * @param mietpreis
	 *            the mietpreis to set
	 */
	public void setMietpreis(float mietpreis) {
		this.mietpreis = mietpreis;
	}

	/**
	 * @return the zimmer
	 */
	public int getZimmer() {
		return this.zimmer;
	}

	/**
	 * @param zimmer
	 *            the zimmer to set
	 */
	public void setZimmer(int zimmer) {
		this.zimmer = zimmer;
	}

	/**
	 * @return the balkon
	 */
	public boolean isBalkon() {
		return this.balkon;
	}

	/**
	 * @param balkon
	 *            the balkon to set
	 */
	public void setBalkon(boolean balkon) {
		this.balkon = balkon;
	}

	/**
	 * @return the eBK
	 */
	public boolean isEBK() {
		return this.ebk;
	}

	/**
	 * @param eBK
	 *            the eBK to set
	 */
	public void setEBK(boolean eBK) {
		this.ebk = eBK;
	}

	@Override
	public boolean equals(Object obj) {
		if (!super.equals(obj))
			return false;
		Wohnung w = (Wohnung) obj;
		if (w == null)
			return false;
		if (w == this)
			return true;

		return stockwerk == w.stockwerk && mietpreis == w.mietpreis
				&& zimmer == w.zimmer && balkon == w.balkon && ebk == w.ebk;
	}

}
