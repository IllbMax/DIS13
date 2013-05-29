
public class BufferData {
	
	private boolean _commited;
	private int _taid;
	private int _pageid;
	private LogData _logData;
	private String _data;
/**
 * 	
 * @param _taid
 * @param _pageid
 * @param _logid
 * @param _data
 */
	public BufferData( int _taid, int _pageid, LogData logData,
			String _data) {
		super();
		this._commited = false;
		this._taid = _taid;
		this._pageid = _pageid;
		this._logData = logData;
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
	public LogData GetLOGDATA()
	{
		return _logData;
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
		
	return new NutzDaten(_pageid,_logData.GetLOGID(),_data);
		
	}
	
	public void SetData(String data)
	{
		_data= data;
	}
	 public String GetData()
	 {
		 return _data;
	 }

	public void SetLOGDATA(LogData logData) {
		_logData = logData;
		
	}

}
