import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DataMining {

	File daten = new File("./transactions.txt");
	Reader is;
	ArrayList<String[]> zeilen;
	HashMap<Integer, Integer> itemList;

	public static void main(String[] args) {
		DataMining dm = new DataMining();
		System.out.println(dm.findFrequent1Itemsets().size());

	}

	public DataMining() {

		zeilen = new ArrayList<String[]>();
		itemList = new HashMap<Integer, Integer>();
		try {
			is = new FileReader(daten);

			BufferedReader br = new BufferedReader(is);
			String zeile = "";
			do {
				zeile = br.readLine();
				if (zeile != null) {
					String[] items = zeile.split(" ");
					zeilen.add(items);
				}

			} while (zeile != null);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Set<ArrayList<Integer>> findFrequent1Itemsets() {
		int grossT = zeilen.size();

		for (String[] sarray : zeilen) {
			for (String s : sarray) {

				int itemnumber = Integer.parseInt(s);
				if (!itemList.containsKey(itemnumber)) {
					itemList.put(itemnumber, 1);

				} else {
					int count = itemList.get(itemnumber);
					count++;

					itemList.put(itemnumber, count);
				}

			}
		}
		Set<ArrayList<Integer>> result = new HashSet<ArrayList<Integer>>();

		for (int dingens : itemList.keySet()) {

			if (((double) itemList.get(dingens)) / grossT >= 0.01) {
				ArrayList<Integer> l = new ArrayList<Integer>();
				l.add(dingens);
				result.add(l);
			}
		}
		return result;
	}

	public Set<ArrayList<Integer>>  GenerateCandidates(Set<ArrayList<Integer>> L)
	{  Set<ArrayList<Integer>> C= new HashSet<ArrayList<Integer>>();
		for (ArrayList<Integer> ali : L  )
			{ 
			for (ArrayList<Integer> ali2 : L  )
			{ 
		if( listVergleich(ali,ali2,ali.size()-1) && ali.get(ali.size()-1)<ali2.get(ali2.size()-1))
					{ ArrayList<Integer> c = (ArrayList<Integer>) ali.clone();
					c.add(ali2.get(ali2.size()-1));
					if (!prune(c,L))
						
					 C.add(c);
					}
				
		
				

			}
			}
		return C;
	}



	public boolean listVergleich(List<Integer> l1, List<Integer> l2, int size) {
		if (l1.size() != l2.size())
			return false;
		if (l1.size() < size)
			return false;

		for (int i = 0; i < size; i++)
			if (!l1.get(i).equals(l2.get(i)))
				return false;
		return true;
	}

	public boolean listVergleich(List<Integer> l1, List<Integer> l2) {
		if (l1.size() != l2.size())
			return false;

		for (int i = 0; i < l1.size(); i++)
			if (!l1.get(i).equals(l2.get(i)))
				return false;
		return true;
	}

	public boolean prune(List<Integer> c, Set<ArrayList<Integer>> L) {

		for (int i = 0; i < c.size(); i++) {
			List<Integer> s = new ArrayList<Integer>(c);
			s.remove(i);
			if (!listInSet(s, L))
				return true;
		}

		return false;

	}

	public boolean listInSet(List<Integer> s, Set<ArrayList<Integer>> L) {
		for (List<Integer> l : L)
			if (listVergleich(s, l))
				return true;
		return false;
	}
	
	private boolean listInArray(List<Integer> l,String[] a)
	{
		for(int item : l)
		{
			boolean b=false;
			for(int i = 0; i<a.length&&!b;i++)
			{
				int temp = Integer.parseInt(a[i]);
				b = item == temp;
			}
			if(!b) return false;
		}
		return true;
	}
	
	public void aprioriAlgorithm(){
		ArrayList<Set<ArrayList<Integer>>> Lx = new ArrayList<Set<ArrayList<Integer>>>();
		Lx.add(findFrequent1Itemsets());
		
		for(int k=2;Lx.size()<=k-1;k++)
		{
			Set<ArrayList<Integer>> canditaten = GenerateCandidates(Lx.get(k-1));
			for(String[] transaction : zeilen)
			{
				for(ArrayList<Integer> candidat : canditaten)
				{
					if(listInArray(candidat, transaction))
					{
						//TODO Unser Kanditatenset abändern, sodass man hier weiter arbeiten kann.
						/*irgendwie müssten wir hier jetzt pro Kandidat einen Zähler hochzählen.
						* wir könnten das Canditatenset zu einem Set<HashMap<Integer,Integer>> machen zu die Canditaten als Schlüssel, 
						* ihre Häufigkeit als ihren Wert speichern
						*/ 
					}
				}
			}
			Set<ArrayList<Integer>> antwortmenge = new HashSet<ArrayList<Integer>>();
			int grossT = zeilen.size();
			for(ArrayList<Integer> candidat : canditaten)
			{
				//TODO hier dann die Zählung anwenden
				if(/*((double)candidat.count)/grossT >= 0.01 */false)
				{
					antwortmenge.add(candidat);
				}
			}
			if(antwortmenge.size() > 0)
				Lx.add(antwortmenge);
		}
	}
}
