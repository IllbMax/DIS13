
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
	
	

}
