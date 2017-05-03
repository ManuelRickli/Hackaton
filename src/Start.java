/**
 * Created by michael1337 on 02/05/17.
 */
public class Start {

	public static void main(String[] args) {

		if (args != null) {
			for (String s : args) {
				Sort.sort(s);
			}
		}

	}

}
