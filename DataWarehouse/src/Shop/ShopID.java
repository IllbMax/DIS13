package Shop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;

import de.dis2011.data.DB2ConnectionManager;
import de.dis2011.data.Makler;

import Datum.dayID;
import Datum.monthID;

public class ShopID {
	private int shopID; 
	private String shop;
	private StadtID stadt;
	private static HashMap<String, ShopID> instanzen = new HashMap<String, ShopID>();
	
	@SuppressWarnings("deprecation")
	public ShopID(String thisshop) {
		super();
		
		this.shop = thisshop;
		this.stadt = StadtID.getInstanze(thisshop.split(" ")[1]);
		instanzen.put(thisshop, this);
	}
	
	public static ShopID getInstanze(String thisshop)
	{
		
		if(instanzen.containsKey(thisshop)){
			return instanzen.get(thisshop);
		}
		return new ShopID(thisshop);
		
	}

	public String getShop()
	{
		return shop;
	}
	
	public StadtID getStadtID()
	{
		return stadt;
	}

	private int getShopID()
	{
		
			try {
				// Hole Verbindung
				Connection con = DB2ConnectionManager.getInstance().getConnection();

				// Erzeuge Anfrage
				String selectSQL = "SELECT id FROM Makler WHERE name = ?";
				PreparedStatement pstmt = con.prepareStatement(selectSQL);
				pstmt.setInt(1, shop);

				// FŸhre Anfrage aus
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					
					ts.setId(id);
					ts.setName(rs.getString("name"));
					ts.setAddress(rs.getString("address"));
					ts.setLogin(rs.getString("login"));
					ts.setPassword(rs.getString("password"));

					rs.close();
					pstmt.close();
					return ts;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		}
	}
}
