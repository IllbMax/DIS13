import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;



public class Persistenzmanager {
	
	private ArrayList<BufferData> _puffer;
	private int transaktioncount;
	private static Persistenzmanager _instance=null;
	private HashMap<Integer, List<LogData>> _TransactionLogBuffer;
	

	/**
	 * voll so krass nen Konstruktor^^
	 */
	public Persistenzmanager() {
		super();
		_puffer = new ArrayList<BufferData>();
		transaktioncount = 0;
		_TransactionLogBuffer = new HashMap<Integer, List<LogData>>();
	}
	
	/**
	 * Mehtode um das Sigelton-Patter einzuhalten
	 * @return die einzige Instanz des Persistenzmanagers 
	 */
	public synchronized static Persistenzmanager getInstance()
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
		int TAID = ++transaktioncount;
		_TransactionLogBuffer.put(TAID, new LinkedList<LogData>());
		return TAID;
	}
	
	/**
	 * Mehtode um eine Transaktion zu beenden.
	 * Biser wird davon ausgegangen, dass sie erfolgreich durchgeführt wurde^^
	 * @param taid ID, um welche Transaktion es sich handelt, die beendet werden soll.
	 * @return Wahrheitswert, der widerspiegelt, ob die Transaktion erfolgreich verlaufen ist. Biser statisch true.
	 */
	public synchronized boolean commit(int taid)
	{
		List<LogData> blub = _TransactionLogBuffer.get(taid);
		for(LogData LD :blub)
		{
			if(LD.is_failed())return false;
		}
		for(LogData LD :blub)
		{
			LD.SetLOGID(getLogCount()+1);
			saveLog(LD);
		}
		_TransactionLogBuffer.remove(taid);
		for(BufferData BD : _puffer)
		{
			if(BD.GetTAID() == taid) BD.SetCommited(true);
		}
		
		return true;
	}
	
	private synchronized int getLogCount()
	{
		File directory = new File("LogDaten");
		return directory.list().length;
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
		LogData boing = new LogData( taid, pageid, data);
		_TransactionLogBuffer.get(taid).add(boing);
		if(bufferd)//Fall, dass die Daten schon im Puffer sind
		{
			//P(pageid); laut Aufgabenstellung nicht notwendig
			if(usedData.GetCommited())
			{
			usedData.SetData(data);
			usedData.SetLOGDATA(boing);
			}else
			{
				boing.SetFAILED(true);
			}
		}
		else
		{
			//P(pageid); laut Aufgabenstellung nicht notwendig
			_puffer.add(new BufferData(taid,pageid,boing,data));
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
			for(BufferData BD : (ArrayList<BufferData>)_puffer.clone())
			{
				if(BD.GetCommited())
				{
					saveNutz(BD.MakeUseData());
					_puffer.remove(BD);
				}
			}
		}
	}
	
	private void saveLog(LogData logdata)
	{
		
		try {			
			PrintWriter pWriter = new PrintWriter(new FileWriter("LogDaten"+File.separator+logdata.GetLOGID()));
	        pWriter.print(logdata.toString());
	        pWriter.flush(); 
		
	        
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void saveNutz(NutzDaten ND)
	{
		File file = new File("NutzDaten"+File.separator+ND.GetPAGEID());
		try {
			PrintWriter pWriter = new PrintWriter(new FileWriter(file));
            pWriter.print(ND.toString());
            pWriter.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
}
