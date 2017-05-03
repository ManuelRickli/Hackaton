/**
 * Created by michael1337 on 02/05/17.
 */
public class Start {

	public static void main(String[] args) {

		String path = "test";

		if ((args != null) && (args.length > 0)) path = args[0];

		Sort.sort(path);


	}

}
