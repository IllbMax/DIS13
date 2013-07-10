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

	File daten = new File("/home/thorsten/Dokumente/SE2/DIS13/Aufgabe8/src/transactions.txt");
	Reader is;
	ArrayList<String[]> zeilen;
	HashMap<Integer, Integer> itemList;

	public static void main(String[] args) {
		DataMining dm = new DataMining();
		dm.aprioriAlgorithm();

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
		Set<ArrayList<Integer>> einitemsets = findFrequent1Itemsets();
		Lx.add(einitemsets);
		for(ArrayList<Integer> einset : einitemsets)
		{
			System.out.println("Frequent Item Set mit einem Item: "+einset);
		}
		System.out.println("Anzahl FIS mit einem Item: "+einitemsets.size());
		for(int k=1;Lx.size()==k;k++)
		{
			
			Set<ArrayList<Integer>> canditaten = GenerateCandidates(Lx.get(k-1));
			HashMap<ArrayList<Integer>,Integer> canditatenmap = new HashMap<ArrayList<Integer>,Integer>();
			for(ArrayList<Integer> can :canditaten)
			{
				canditatenmap.put(can, 0);
				
			}
			
			for(String[] transaction : zeilen)
			{
				for(ArrayList<Integer> candidat : canditaten)
				{
					if(listInArray(candidat, transaction))
					{
						int count = canditatenmap.get(candidat);
						count++;
						canditatenmap.put(candidat, count); 
					}
				}
			}
			Set<ArrayList<Integer>> antwortmenge = new HashSet<ArrayList<Integer>>();
			int grossT = zeilen.size();
			for(ArrayList<Integer> candidat : canditaten)
			{
				if(((double)canditatenmap.get(candidat))/grossT >= 0.01)
				{
					antwortmenge.add(candidat);
					System.out.println("Frequent Item Set mit "+(k+1)+" Items: "+candidat);
				}
			}
			System.out.println("Anzahl FIS mit "+(k+1)+" Items: "+antwortmenge.size());
			
			if(antwortmenge.size() > 0)
				Lx.add(antwortmenge);
		}
	}
}
