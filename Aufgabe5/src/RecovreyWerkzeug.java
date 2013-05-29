/**
 *In der ersten Phase muss es in den kompletten Nutzdaten nach der höchsten LogID suchen (n),
 *Die Anzahl (l) der LogDaten zählen (Anzahl==LogID) und for(int i = n+1;i<=l;i++) Log i redo'n.  
 */
public class RecovreyWerkzeug {
	
	public static void main(String[] args) {
		for(int i = 0; i<Integer.parseInt(args[0]);i++)
		{
			Client c = new Client(i);
			Thread t = new Thread(c);
			t.start();
		}
	}	

}
