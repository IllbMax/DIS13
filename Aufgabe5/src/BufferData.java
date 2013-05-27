
public class BufferData {
	
	private boolean _commited;
	private int _taid;
	private int _pageid;
	private int _logid;
	private String _data;
/**
 * 	
 * @param _taid
 * @param _pageid
 * @param _logid
 * @param _data
 */
	public BufferData( int _taid, int _pageid, int _logid,
			String _data) {
		super();
		this._commited = false;
		this._taid = _taid;
		this._pageid = _pageid;
		this._logid = _logid;
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
	
	public boolean  GetCommited()
	{
		return _commited;
	}
	
	public void SetCommited(boolean commited)
	{
		_commited= commited;
	}
	
	public NutzDaten MakeUseData()
	{ 
		
	return new NutzDaten(_pageid,_logid,_data);
		
	}
	
	public void SetData(String data)
	{
		_data= data;
	}

}
