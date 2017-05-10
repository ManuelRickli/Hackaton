import java.io.*;

/**
 * Created by michael1337 on 03/05/17.
 */
public class Load {

	private static int n=0;
	private static BufferedReader bufferedReader;

	/**
	 * This method initializes the reading of the big data.
	 * @param path path of file to read
	 * @return false if something went wrong
	 */
	public static boolean initialize() {

		File file = new File(Start.path);

		if (file.exists() && file.isFile()) {
			try {
				bufferedReader = new BufferedReader(new FileReader(Start.path+Start.file));
			} catch (FileNotFoundException e) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Please don't forget to close all the streams after using them.
	 */
	public static void close() {
		try {
			bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Bites the next chunk of length chunk from the big data.
	 * @return the biteable chunk you might want to sort.
	 */
	public static String[] getNextChunk() {
		String line;
		n = 0;
		String[] s = new String[Start.CHUNKSIZE];

		try {
			while (((line = bufferedReader.readLine()) != null) && (n<Start.CHUNKSIZE)) {
				s[n] = line;
				n++;
			}

			if ((n<Start.CHUNKSIZE)&&(n>0)) {
				String[] newS = new String[n];
				for (int i=0; i<n; i++) { newS[i] = s[i]; }
				return newS;
			}

			if (n==0) return null;

			return s;

		} catch (IOException e) {
			return null;
		}
	}

}
