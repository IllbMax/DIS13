
public class LogData {
	
	private int _logid;
	private int _taid;
	private int _pageid;
	private String _data;
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
	public String toString()
	{
		return "ta"+_taid+"|page"+_pageid+"|log"+_logid+"|"+_data;
		
	}
	
	
	 

}
