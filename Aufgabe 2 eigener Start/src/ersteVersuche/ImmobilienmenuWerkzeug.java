package ersteVersuche;



public class ImmobilienmenuWerkzeug {
	private ImmobilienmenuGUI _GUI;
	
	public ImmobilienmenuWerkzeug() {
		_GUI = new ImmobilienmenuGUI();
	}

	public void ZeigeImmobilienMenu() {
		_GUI.setVisible(true);
		
	}
}
