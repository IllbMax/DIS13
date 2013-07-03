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
		System.out.println(dm.findFrequent1Itemsets());

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

			System.out.println((double) (itemList.get(dingens) / grossT));
			if (((double) itemList.get(dingens)) / grossT >= 0.01) {
				ArrayList<Integer> l = new ArrayList<Integer>();
				l.add(dingens);
				result.add(l);
			}
		}
		return result;
	}

	public void GenerateCandidates()
	{
		for (ArrayList<Integer> ali : )
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
}
