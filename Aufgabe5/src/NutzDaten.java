
public class NutzDaten {
	
	private  int _pageid;
	private int _logid;
	private String _data;
	public NutzDaten(int _pageid, int _logid, String _data) {
		super();
		this._pageid = _pageid;
		this._logid = _logid;
		this._data = _data;
	}
	
	public int GetPAGEID()
	{
		return _pageid;
	}
	
	public int GetLOGID()
	{
		return _logid;
	}
	
	/**
	 * Die LogID ist als letztes gewählt, damit bei einem Crash beim Schreiben die LogID,
	 * wenn überhaupt vorhanden, kleiner ist, als die eigentliche LogID, sodass beim Recovery
	 * diese aus den Logdaten wiederhergestellt wird.
	 * Die PageID ist im Dateinamen festgehalten.
	 */
	public String toString()
	{
		return _data+"|"+_logid;
		
	}
	

}
