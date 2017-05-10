import java.io.*;

/**
 * Created by michael1337 on 03/05/17.
 */
public class IndexBuilder {

	private static File file;

	/**
	 * Builds root and predefined nodes.
	 * @return
	 */
	public static Index createIndexTree() {

		Index root = new Index("",0,-1);

		file = new File(Start.path+Start.file);

		if (file.exists() && file.isFile()) {
			System.out.println("file exists "+file.getName());
			firstNodes(root);
		}

		return null;
	}

	/**
	 * Creates three predefined nodes named "ftp://", "http://" and "https://".
	 * It then goes on to build the subsequent layers as usual. (build())
	 */
	public static void firstNodes(Index i) {

		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(Start.path+Start.file));

			String line = "";

			Index ftp = new Index("ftp://",1,0);
			Index http = new Index("http://",0,0);
			Index https = new Index("https://",0,0);

			int endFtp=0,endHttp=0,endHttps=0;
			int mode = 0;
			int n = 0;

			try {
				while ((line = bufferedReader.readLine()) != null) {
					switch (mode) {
						case 0:
							if (!line.substring(0,6).equals("ftp://")) {
								endFtp = n;
								mode ++;
							}
							break;
						case 1:
							if (!line.substring(0,7).equals("http://")) {
								endHttp = n;
								mode ++;
							}
							break;
						case 2:
							if (!line.substring(0,8).equals("https://")) {
								endHttps = n;
								mode ++;
							}
							break;
					}
					n++;
				}

				ftp.setEnd(endFtp);
				http.setStart(endFtp+1);
				http.setEnd(endHttp);
				https.setStart(endHttp+1);
				https.setEnd(n);

				build(ftp);
				build(http);
				build(https);
			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	/**
	 * As long as i.end-i.start > MAXINDEXSIZE or end==-1, the index will be broken down into multiple subindizes.
	 * @param i the index that should be build.
	 */
	public static void build(Index i) {

		System.out.println("index "+i.getNode()+": "+i.getStart()+", "+i.getEnd());

	}

}
