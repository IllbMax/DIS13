package ersteVersuche.Material;

public class Wohnung extends Immobilie {

	private int _stockwerk;
	private float _mietpreis;
	private int _zimmer;
	private boolean _balkon;
	private boolean _ebk;

	public Wohnung() {
	}

	public Wohnung(int id, String ort, int plz, String strasse, int hausNr,
			float flaeche, int stockwerk, float mietpreis, int zimmer,
			boolean balkon, boolean ebk) {
		super(id, ort, plz, strasse, hausNr, flaeche);

		_stockwerk = stockwerk;
		_mietpreis = mietpreis;
		_zimmer = zimmer;
		_balkon = balkon;
		_ebk = ebk;
	}

	/**
	 * @return the stockwerk
	 */
	public int getStockwerk() {
		return _stockwerk;
	}

	/**
	 * @param stockwerk
	 *            the stockwerk to set
	 */
	public void setStockwerk(int stockwerk) {
		_stockwerk = stockwerk;
	}

	/**
	 * @return the mietpreis
	 */
	public float getMietpreis() {
		return _mietpreis;
	}

	/**
	 * @param mietpreis
	 *            the mietpreis to set
	 */
	public void setMietpreis(float mietpreis) {
		_mietpreis = mietpreis;
	}

	/**
	 * @return the zimmer
	 */
	public int getZimmer() {
		return _zimmer;
	}

	/**
	 * @param zimmer
	 *            the zimmer to set
	 */
	public void setZimmer(int zimmer) {
		_zimmer = zimmer;
	}

	/**
	 * @return the balkon
	 */
	public boolean isBalkon() {
		return _balkon;
	}

	/**
	 * @param balkon
	 *            the balkon to set
	 */
	public void setBalkon(boolean balkon) {
		_balkon = balkon;
	}

	/**
	 * @return the eBK
	 */
	public boolean isEBK() {
		return _ebk;
	}

	/**
	 * @param eBK
	 *            the eBK to set
	 */
	public void setEBK(boolean eBK) {
		_ebk = eBK;
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

		return _stockwerk == w._stockwerk && _mietpreis == w._mietpreis
				&& _zimmer == w._zimmer && _balkon == w._balkon
				&& _ebk == w._ebk;
	}

}
