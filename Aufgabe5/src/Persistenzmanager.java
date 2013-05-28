import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
		File directory = new File("LogDaten");
		int anzahlLogdaten = directory.list().length;
		try {
			logdata.SetLOGID(anzahlLogdaten+1);
			
			PrintWriter pWriter = new PrintWriter(new FileWriter("LogDaten/"+anzahlLogdaten+1));
	        pWriter.print(logdata.toString());
	        pWriter.flush(); 
		
	        return anzahlLogdaten+1;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return anzahlLogdaten+1;
	}
	
	private void saveNutz(NutzDaten ND)
	{
		try {
			FileOutputStream fis = new FileOutputStream("NutzDaten/"+ND);
			for(char c : ND.toString().toCharArray())
			{
				fis.write((byte)c);
			}
		} catch (FileNotFoundException e) {
			 try{
		            PrintWriter pWriter = new PrintWriter(new FileWriter("NutzDaten/"+ND));
		            pWriter.print(ND);
		            pWriter.flush();
		        }catch(IOException ioe){
		            ioe.printStackTrace();
		        } 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
}
