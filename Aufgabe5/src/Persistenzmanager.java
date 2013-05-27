import java.io.Serializable;
import java.util.ArrayList;



public class Persistenzmanager {
	
	private ArrayList<BufferData> _puffer;
	private int transaktioncount;

	/**
	 * voll so krass nen Konstruktor^^
	 */
	public Persistenzmanager() {
		super();
		_puffer = new ArrayList<BufferData>();
		transaktioncount = 0;
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
		if(bufferd)
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
		return 42;
		//TODO Hier müssen die LogDaten persistent gespeichert werden.
	}
	
	private void saveNutz(NutzDaten ND)
	{
		//TODO Hier müssen die Daten persistent gespeichert werden. 
	}	
}
