
public class LogData {
	
	private int _logid;
	private int _taid;
	private int _pageid;
	private String _data;
	private boolean _failed;
	public LogData( int _taid, int _pageid, String _data) {
		super();
		
		this._taid = _taid;
		this._pageid = _pageid;
		this._data = _data;
	}
	
	/**
	 * @return _taid
	 */
	public int GetTAID()
	{
		return _taid;
	}
/**
 * @return _pageid
 */
	public int GetPAGEID()
	{
		return _pageid;
	}
	
	/**
	 * 
	 * @return _logid
	 */
	public int GetLOGID()
	{
		return _logid;
	}
	public void SetLOGID( int logid)
	{
		_logid=logid;
	}
	
	/**
	 * Da die LogID im Dateinamen steht, wird diese nicht im toString erwähnt.
	 */
	public String toString()
	{
		return _taid+"|"+_pageid+"|"+_data;
		
	}

	public void SetFAILED(boolean b) {
		_failed = b;
		
	}

	public boolean is_failed() {
		return _failed;
	}
	
	
	 

}
