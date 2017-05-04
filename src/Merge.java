import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by michael1337 on 03/05/17.
 */
public class Merge {

	/**
	 * This method merges all the files in the tmp_chunks folder into one superfile.
	 */
	public static void mergeAll() {

		File dir = new File(Start.FOLDER);
		if (dir.exists() && dir.isDirectory()) {
			File[] files;
			int n=1;
			// TODO: Known bug! file name length is an issue with more than 10 chunks to start with!!!
			do {
				files = dir.listFiles();

				if (files.length > 1) {
					mergeLayer(files, n);
					n++;
				} else {
					files = null;
				}
			} while (files != null);
		}

	}

	/**
	 * Merge a "layer" of files with - if possible - the same name length.
	 * @param files files in dir to merge.
	 * @param n the length of the file name.
	 */
	private static void mergeLayer(File[] files, int n) {
		File fa = null;
		File fb = null;
		int f = files.length;
		for (File file : files) {
			if (file.getName().length() == n) {
				if (fa == null) {
					fa = file;
				} else {
					fb = file;

					merge(fa.getPath(),fb.getPath());
					f -= 2;
					fa = null;
					fb = null;
				}
			}
		}
		if (f>0) {
			for (File file : files) {
				if (file.exists() && file != fa) {
					if (Math.abs(file.getName().length()-n) <= n) {
						fb = file;
						break;
					}
				}
			}
			if ((fa != null) && (fb != null)) {
				merge(fa.getPath(),fb.getPath());
			}
		}
	}

	private static void merge(String sa, String sb) {
		String con = sa.concat(sb).replaceAll("\\D", "");

		String s1;
		String s2;

		try {
			BufferedReader b1 = new BufferedReader(new FileReader(sa));
			BufferedReader b2 = new BufferedReader(new FileReader(sb));
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(Start.FOLDER+ File.separator+con));

			try {
				s1 = b1.readLine();
				s2 = b2.readLine();

				while (s1!=null && s2!=null) {
					if (s1.compareTo(s2) < 0) {
						bufferedWriter.write(s1);
						bufferedWriter.newLine();
						s1 = b1.readLine();
					} else {
						bufferedWriter.write(s2);
						bufferedWriter.newLine();
						s2 = b2.readLine();
					}
				}
				while (s1 != null) {
					bufferedWriter.write(s1);
					bufferedWriter.newLine();
					s1 = b1.readLine();
				}
				while (s2 != null) {
					bufferedWriter.write(s2);
					bufferedWriter.newLine();
					s2 = b2.readLine();
				}

				bufferedWriter.close();

				try {
					Files.delete(Paths.get(sa));
					Files.delete(Paths.get(sb));
				} catch (IOException e) {
					e.printStackTrace();
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
