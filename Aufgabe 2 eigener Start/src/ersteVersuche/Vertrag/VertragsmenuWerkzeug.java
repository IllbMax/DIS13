package ersteVersuche.Vertrag;

public class VertragsmenuWerkzeug {
	private VertragsmenuGUI _GUI;
	
	public VertragsmenuWerkzeug() 
	{
		_GUI = new VertragsmenuGUI();
	}

	public void ZeigeVertragsMenu() {
		_GUI.setVisible(true);
		
	}

}
