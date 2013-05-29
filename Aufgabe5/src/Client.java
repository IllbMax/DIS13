

public class Client implements Runnable{

	private Persistenzmanager pm;
	private static String[][][] blub ={{{"1"},{"Hallo"}},{{"2"},{"ich"}},{{"3"},{"muss"}},{{"4"},{"doofe"}},{{"5"},{"Daten"}},{{"6"},{"schreiben"}},
		{{"7"},{"Hallo"}},{{"8"},{"ich"}},{{"9"},{"muss"}},{{"10"},{"doofe"}},{{"11"},{"Daten"}},{{"12"},{"schreiben"}}};
	private String[][] writes;
	
	

	public Client(int i) {
		super();
		pm = Persistenzmanager.getInstance();
		writes = blub[i];
		
	}
	
	/**
	 * Führt die eigentlichen Operationen auf dem Persistenzmanager aus.
	 */
	public void run() 
	{
		int taid = pm.beginTransaktion();
		for(String[] data : writes)
		{
			pm.write(taid, Integer.parseInt(data[0]), data[1]);
			data[1] = data[1]+"haha";
			try {
				this.wait((long) (Math.random()*100));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		pm.commit(taid);
	}

}
