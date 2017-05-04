/**
 * Created by michael1337 on 02/05/17.
 */
public class Start {

	public static final String FOLDER = "tmp_chunks";
	public static final int MAXINDEXSIZE = 3;
	public static final int CHUNKSIZE = 3;//1000000

	/**
	 * Start this class to run the programm.
	 * When no arguments are applied, the file "test" will be read out.
	 * @param args file you want to work with
	 */
	public static void main(String[] args) {

		String path = "urls/urls-sample.txt";

		if ((args != null) && (args.length > 0)) path = args[0];

		System.out.println("Creating chunks...");

		long bench = System.currentTimeMillis();

		Sort.sort(path);

		System.out.println("Done. It took "+(System.currentTimeMillis()-bench)+" milisecs");

		Index root = new Index("",0,-1);

		IndexBuilder.build(root);

	}

}
