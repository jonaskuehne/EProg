
public class Names {

	public static String[] toGerman(String name) {
		
		// return empty if nothing
		if (name.length() == 0) {
			return new String[]{"", ""};
		}
		
		String summary = "";
		
		// get summary
		while(name.length() != 0) {
			// get fragment
			String fragment = name.substring(0, 3);
			
			if (fragment.equals("mor")) {
				summary = "w" + summary;
			} else {
				summary = "m" + summary;
			}
			name = name.substring(3);
		}
		
		String germanName = "";
		
		// first part
		if (summary.charAt(0) == 'w') {
			germanName = "mutter";
		} else {
			germanName = "vater";
		}
		
		// second part
		if (summary.length() > 1) {
			germanName = "gross" + germanName;
		}
		
		// fill
		for (int i = 2; i < summary.length(); ++i) {
			germanName = "ur" + germanName;
		}
		
		// first char to upper case
		germanName = ("" + germanName.charAt(0)).toUpperCase() + germanName.substring(1);
		
		return new String[]{germanName, summary};
	}
}
