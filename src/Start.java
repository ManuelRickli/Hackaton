/**
 * Created by michael1337 on 02/05/17.
 */
public class Start {

	public static final String FOLDER = "tmp_chunks";
	public static final int MAXINDEXSIZE = 1000;
	public static final int CHUNKSIZE = 1000000;
	public static final int PREFIXLENGTH = 4;
	public static final int MAXCHUNKS = 0; // only for testing! may be set to 0 to disable
	public static final int[] SCOPES = {1,3,2};

	public static String mode = "index";
	public static String path = "";
	public static String file = "test";

	/**
	 * Start this class to run the programm.
	 * When no arguments are applied, the file "test" will be read out.
	 * @param args file you want to work with
	 */
	public static void main(String[] args) {

		if (args != null) {
			if (args.length > 0) {
				mode = args[0];
			}
			if (args.length > 1) {
				path = args[1];
			}
			if (args.length > 2) {
				file = args[2];
			}
		}

		System.out.println("mode: "+mode);
		System.out.println("path: "+path);
		System.out.println("file: "+file);

		long bench = System.currentTimeMillis();

		if (mode.equals("sort")) {

			System.out.println("Creating chunks...");
			Sort.sort();

		} else if (mode.equals("index")) {

			Index root = IndexBuilder.createIndexTree();

		} else {

			System.out.println("unknown mode: "+mode);
			System.out.println("Please chose 'sort' or 'index'.");

		}

		System.out.println("Done. It took "+(System.currentTimeMillis()-bench)+" milisecs");

	}

}
