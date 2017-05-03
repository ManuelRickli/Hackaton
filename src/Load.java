import java.io.*;

/**
 * Created by michael1337 on 03/05/17.
 */
public class Load {

	private static final int chunk=3;

	private static int n=0;
	private static BufferedReader bufferedReader;

	public static boolean initialize(String path) {

		File file = new File(path);

		if (file.exists() && file.isFile()) {
			try {
				bufferedReader = new BufferedReader(new FileReader(path));
			} catch (FileNotFoundException e) {
				return false;
			}
		}

		return true;
	}

	public static String[] getNextChunk() {
		String line;
		n = 0;
		String[] s = new String[chunk];

		try {
			while (((line = bufferedReader.readLine()) != null) && (n<chunk)) {
				s[n] = line;
				n++;
			}

			if ((n<chunk)&&(n>0)) {
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
