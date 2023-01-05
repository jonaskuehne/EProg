import java.util.ArrayList;

public class AtLeastSubsequence {

	public static boolean subsequence(String s, String t, int n) {
		
		ArrayList<String> combs = new ArrayList<>();
		combs.add("");
		
		getCombs(combs, s, t);
		
		int count = 0;
		
		for (String comb : combs) {
			if (comb.equals(t)) {
				++count;
			}
		}
		
        return n <= count;
    }
	
	// faster than you would think my friend
	public static void getCombs(ArrayList<String> store, String s, String t) {
		if (s.length() == 0) {
			return;
		}
		
		int l = store.size();
		
		for (int i = 0; i < l; ++i) {
			// here's where the magic happens
			if (t.contains(s.charAt(0)+ "") && store.get(i).length() < t.length()) {
				store.add(store.get(i) + s.charAt(0));
			}
		}
		
		getCombs(store, s.substring(1), t);
	}
}

