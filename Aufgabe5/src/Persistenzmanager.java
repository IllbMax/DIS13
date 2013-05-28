import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;



public class Persistenzmanager {
	
	private ArrayList<BufferData> _puffer;
	private int transaktioncount;
	private static Persistenzmanager _instance=null;

	/**
	 * voll so krass nen Konstruktor^^
	 */
	public Persistenzmanager() {
		super();
		_puffer = new ArrayList<BufferData>();
		transaktioncount = 0;
	}
	
	/**
	 * Mehtode um das Sigelton-Patter einzuhalten
	 * @return die einzige Instanz des Persistenzmanagers 
	 */
	public static Persistenzmanager getInstance()
	{
		if(_instance== null)
		{
			_instance = new Persistenzmanager();
		}
		return _instance;
	}
	
	/**
	 * Methode zum Starten einer Transaktion, die jeder Transaktion eine Laufzeit-eindeutige ID zuweist. 
	 * @return Die TAID für diese Transaktion
	 */
	public synchronized int beginTransaktion()
	{
		return ++transaktioncount;
	}
	
	/**
	 * Mehtode um eine Transaktion zu beenden.
	 * Biser wird davon ausgegangen, dass sie erfolgreich durchgeführt wurde^^
	 * @param taid ID, um welche Transaktion es sich handelt, die beendet werden soll.
	 * @return Wahrheitswert, der widerspiegelt, ob die Transaktion erfolgreich verlaufen ist. Biser statisch true.
	 */
	public synchronized boolean commit(int taid)
	{
		for(BufferData BD : _puffer)
		{
			if(BD.GetTAID() == taid)BD.SetCommited(true);
		}
		return true;
	}
	
	/**
	 * Methode, die die Daten im Puffer verändert, oder erstellt. Das Übernehmen in die Nutz-Daten ist ausgelagert
	 * @param taid
	 * @param pageid
	 * @param data
	 */
	public synchronized void write(int taid, int pageid, String data)
	{
		boolean bufferd = false;
		BufferData usedData = null;
		for(BufferData BD : _puffer)
		{
			if(BD.GetPAGEID() == pageid)
				{
					bufferd = true;
					usedData = BD;
				}
		}
		if(bufferd)//Fall, dass die Daten schon im Puffer sind
		{
			//P(pageid); laut Aufgabenstellung nicht notwendig
			int logid = saveLog(new LogData( taid, pageid, data));
			usedData.SetData(data);
			usedData.SetLOGID(logid);
		}
		else
		{
			//P(pageid); laut Aufgabenstellung nicht notwendig
			int logid = saveLog(new LogData( taid, pageid, data));
			_puffer.add(new BufferData(taid,pageid,logid,data));
		}
			
		saveNutz();
	}
		
	/**
	 * Für den Fall, dass der Puffer voll ist, werden alle committeten Daten gespeichert und aus dem Puffer entfernt.
	 */
	private void saveNutz()
	{
		if(_puffer.size()>5)
		{
			for(BufferData BD : _puffer)
			{
				if(BD.GetCommited())
				{
					saveNutz(BD.MakeUseData());
					_puffer.remove(BD);
				}
			}
		}
	}
	
	private int saveLog(LogData logdata)
	{
		FileReader fr;
		String helpstring="";
		int result=0;
		int helpint =0;
		try {
			fr = new FileReader("LogDatei.txt");
			BufferedReader br = new BufferedReader(fr);
			while(helpstring!=null)
			{
				helpstring = br.readLine();
				/*
				 * Ermittlung der höchsten LogID
				 */
				if(helpstring != null&& result < (helpint = Integer.parseInt(helpstring.split("|")[2].substring(3))))
				{
					result = helpint;
				}
			}
			logdata.SetLOGID(result+1);
			
			PrintWriter pWriter = new PrintWriter(new FileWriter("LogDatei.txt", true));
	        pWriter.println(logdata.toString());
	        pWriter.flush(); 
		
	        return result+1;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	private void saveNutz(NutzDaten ND)
	{
		//TODO Hier müssen die Daten persistent gespeichert werden.
		/*
		 * es muss geprüft werden, ob es die PageID bereits gibt. 
		 * Wenn ja, muss diese ersetzt werden, 
		 * wenn nein, muss nur ND.toString+"\n" an die Datei angehängt werden.
		 * 
		 * Ich hab kein Bock auf diese Methode...
		 */
	}	
}
