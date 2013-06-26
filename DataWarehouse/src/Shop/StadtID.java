package Shop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import dis2011_kopie.DB2ConnectionManager;

public class StadtID {
	
	private int stadtID;
	private String stadt;
	private String land;
private static HashMap<String, StadtID> instanzen = new HashMap<String, StadtID>();


	
	@SuppressWarnings("deprecation")
	public StadtID(String thisstadt) {
		super();
		
		this.stadt = thisstadt;
		this.land = "Deutschland";
		instanzen.put(thisstadt, this);
	}
	public static StadtID getInstanze(String thisstadt) {

		if(instanzen.containsKey(thisstadt)){
			return instanzen.get(thisstadt);
		}
		return new StadtID(thisstadt);
	
	}
	public int getStadtID()
	{

		try {
			// Hole Verbindung
			Connection con = DB2ConnectionManager.getInstance().getConnection();

			// Erzeuge Anfrage
			String selectSQL = "SELECT id FROM StadtID WHERE name = ?";
			PreparedStatement pstmt = con.prepareStatement(selectSQL);
			pstmt.setString(1, stadt);

			// FŸhre Anfrage aus
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				
				

				rs.close();
				pstmt.close();
				return rs.getInt("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	}

