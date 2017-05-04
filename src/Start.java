/**
 * Created by michael1337 on 02/05/17.
 */
public class Start {

	public static final String FOLDER = "tmp_chunks";
	public static final int MAXINDEXSIZE = 3;
	public static final int CHUNKSIZE = 10;//1000000
	public static final int MAXCHUNKS = 20; // only for testing! may be set to 0 to disable
	public static final int[] SCOPES = {1,3,2};

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

		Index root = IndexBuilder.createIndexTree();

	}

}
