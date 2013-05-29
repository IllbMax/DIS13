import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;



/**
 *In der ersten Phase muss es in den kompletten Nutzdaten nach der höchsten LogID suchen (n),
 *Die Anzahl (l) der LogDaten zählen (Anzahl==LogID) und for(int i = n+1;i<=l;i++) Log i redo'n.  
 */
public class RecovreyWerkzeug {
	
	public static void main(String[] args) {
		new RecovreyWerkzeug().red_amna_lyse();
		for(int i = 0; i<Integer.parseInt(args[0]);i++)
		{
			Client c = new Client(i);
			Thread t = new Thread(c);
			t.start();
		}		
	}
	
	/**
	 * Der Methodenname ist eine Amalgamierung aus "zurück" "verdammt" und "analysieren"(was zu tun ist).
	 * Nun ist es das Verfahren (zur Wutbewältigung) der schwedischen Politikerin (alte kleine pummelige Frau) 
	 * wenn sie extrem wütend ist (mit rotem Kopf)
	 */
	private void red_amna_lyse()
	{
		File LogOrdner = new File("LogDaten");
		LogOrdner.mkdir();
		File NutzOrdner = new File("NutzDaten");
		NutzOrdner.mkdir();
		 
		for(File f : LogOrdner.listFiles())
		{
			int LOGID = Integer.parseInt(f.getName());
			int pageID = -1;
			String data = "";
			BufferedReader br;
			try {
				br = new BufferedReader(new FileReader(f));
				String[] infos = br.readLine().split("\\|");
				pageID = Integer.parseInt(infos[1]);
				data = infos[2];
			} catch (FileNotFoundException e1) {
				continue;
				
			} catch (Exception e) {
				continue;
			}
			
			File NutzDatei = new File(NutzOrdner.getAbsolutePath()+File.separator+pageID);
			
			if(readLogID(NutzDatei)<LOGID)//hallo, hier endet Phase1 :D
			{
				System.out.println("Ändere Datei "+pageID+": "+data+"|"+LOGID);
				PrintWriter pWriter = null;
				try {
					pWriter = new PrintWriter(new FileWriter(NutzDatei));
		            pWriter.print(data+"|"+LOGID);
		            pWriter.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
				finally
				{
					if(pWriter!=null) pWriter.close();
				}
			}
		}
		
	}
	
	/**
	 * Ließt die LogID aus der NutzDatei aus und scheitert dabei nie.
	 * @param NutzDatei
	 * @return -1 oder (selten) was anderes
	 */
	private int readLogID(File NutzDatei)
	{
		BufferedReader br=null;
		try {
			System.out.println("File: " + NutzDatei.getName());			
			br = new BufferedReader(new FileReader(NutzDatei));
			String line = br.readLine();
			System.out.println(line);
			int id = Integer.parseInt(line.split("\\|")[1]);
			System.out.println("Find LOGID = " + id);
			return id;
		} catch (Throwable e) {
			e.printStackTrace();
			return -1;
		}
		finally
		{
			if(br!=null)
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
	

}
