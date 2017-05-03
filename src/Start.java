/**
 * Created by michael1337 on 02/05/17.
 */
public class Start {

	/**
	 * Start this class to run the programm.
	 * When no arguments are applied, the file "test" will be read out.
	 * @param args file you want to work with
	 */
	public static void main(String[] args) {

		String path = "test";

		if ((args != null) && (args.length > 0)) path = args[0];

		Sort.sort(path);

		Index root = new Index("",0,-1);

		IndexBuilder.build(root);

	}

}
