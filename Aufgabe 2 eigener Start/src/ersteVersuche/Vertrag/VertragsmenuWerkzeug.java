package ersteVersuche.Vertrag;

import java.awt.event.WindowListener;

public class VertragsmenuWerkzeug {
	private VertragsmenuGUI _GUI;
	
	public VertragsmenuWerkzeug() 
	{
		_GUI = new VertragsmenuGUI();
	}

	public void ZeigeVertragsMenu() {
		_GUI.setVisible(true);
		
	}
	
	public void AddWindowListener(WindowListener l) {
		_GUI.addWindowListener(l);
	}

}
