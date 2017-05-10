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

		Index root = new Index("",0,-1,0,0);

		file = new File(Start.path+Start.file);

		if (file.exists() && file.isFile()) {
			firstNodes(root);
		}

		return root;
	}

	/**
	 * Creates three predefined nodes named "ftp://", "http://" and "https://".
	 * It then goes on to build the subsequent layers as usual. (build())
	 */
	public static void firstNodes(Index i) {

		try {
			BufferedReader bufferedReader = new BufferedReader(new FileReader(Start.path+Start.file));

			String line = "";

			Index ftp = new Index("ftp://",1,0,0);
			Index http = new Index("http://",0,0,0);
			Index https = new Index("https://",0,0,0);

			i.add(ftp);
			i.add(http);
			i.add(https);

			int endFtp=0,endHttp=0,endHttps=0;
			long endBytesFtp=0,endBytesHttp=0;
			int mode = 0;
			int n = 0;
			long byteStart = 0;

			try {
				while ((line = bufferedReader.readLine()) != null) {
					if (mode == 2) break;
					switch (mode) {
						case 0:
							if (!line.substring(0,6).equals("ftp://")) {
								endFtp = n;
								mode ++;
								endBytesFtp = byteStart;
							}
							break;
						case 1:
							if (!line.substring(0,7).equals("http://")) {
								endHttp = n;
								mode ++;
								endBytesHttp = byteStart;
							}
							break;
					}
					n++;
					byteStart += line.length()*Start.BYTESPERCHAR;
				}

				ftp.setEnd(endFtp);

				http.setByteStart(endBytesFtp+Start.BYTESPERCHAR);
				http.setStart(endFtp+1);
				http.setEnd(endHttp);

				https.setByteStart(endBytesHttp+Start.BYTESPERCHAR);
				https.setStart(endHttp+1);
				https.setEnd(n);

				build(ftp,0);
				build(http,0);
				build(https,0);
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
	public static void build(Index i, int offset) {

		System.out.println("index "+i.getNode()+": "+i.getStart()+", "+i.getEnd());

		int d = i.getEnd()-i.getStart();
		int off = i.getNode().length();

		if (d>Start.MAXINDEXSIZE) {
			int n = i.getStart();
			long startByte = i.getByteStart();

			try {
				RandomAccessFile raf = new RandomAccessFile(file, "r");

				raf.seek(startByte);

				String line;
				while (n < i.getStart()+d/2) {
					line = raf.readLine();
					n++;
					startByte += line.length()*Start.BYTESPERCHAR;
				}

				String key = null;

				while (n < i.getEnd()) {
					line = raf.readLine();
					n++;
					startByte += line.length()*Start.BYTESPERCHAR;
					if (line.length() >= offset+off+Start.SCOPE) {
						key = line.substring(offset+off,offset+off+Start.SCOPE);
						break;
					}
				}

				if (key != null) {
					while (n < i.getEnd()) {
						line = raf.readLine();
						n++;
						startByte += line.length() * Start.BYTESPERCHAR;
						if (line.length() >= offset + off + Start.SCOPE) {
							String nkey = line.substring(offset + off, offset + off + Start.SCOPE);
							if (!key.equals(nkey)) {

								//Index one =

								break;
							}
						}
					}
				}

				raf.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

}
