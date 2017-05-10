import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by michael1337 on 03/05/17.
 */
public class Merge {

	private static int layer = 1;
	private static int n = 0;

	/**
	 * This method merges all the files in the tmp_chunks folder into one superfile.
	 */
	public static void mergeAll() {

		File dir = new File(Start.path+Start.FOLDER);
		if (dir.exists() && dir.isDirectory()) {
			File[] files;
			boolean merged = false;
			do {
				files = dir.listFiles();

				if (files.length > 1) {
					merged = mergeLayer(files);
					layer++;
					n = 0;
				} else {
					files = null;
				}
			} while (merged && (files != null));
		}

	}

	/**
	 * Merge a "layer" of files with - if possible - the same name length.
	 * @param files files in dir to merge.
	 */
	private static boolean mergeLayer(File[] files) {
		File fa = null;
		File fb = null;
		boolean merged = false;
		int f = files.length;
		for (File file : files) {
			if (FileNamer.getLayer(file.getName()) == layer) {
				if (fa == null) {
					fa = file;
				} else {
					fb = file;

					merge(fa.getName(),fb.getName());
					f -= 2;
					fa = null;
					fb = null;
					merged = true;
				}
			}
		}
		if (f>0) {
			int dist = -1;
			for (File file : files) {
				if (file.exists() && file != fa) {
					int d = Math.abs(FileNamer.getLayer(file.getName())-layer);
					if ((dist == -1) || (d < dist)) {
						fb = file;
						dist = d;
					}
				}
			}
			if ((fa != null) && (fb != null)) {
				merge(fa.getName(),fb.getName());
				merged = true;
			}
		}
		return merged;
	}

	private static void merge(String sa, String sb) {
		String con = FileNamer.getName(sa).concat(FileNamer.getName(sb)).replaceAll("\\D", "");

		String s1;
		String s2;
		System.out.println("merge "+sa+" and "+sb);

		try {
			BufferedReader b1 = new BufferedReader(new FileReader(Start.path+Start.FOLDER+File.separator+sa));
			BufferedReader b2 = new BufferedReader(new FileReader(Start.path+Start.FOLDER+File.separator+sb));
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(Start.path+Start.FOLDER+ File.separator+FileNamer.getFileName(layer+1,n)));
			n++;

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
					Files.delete(Paths.get(Start.path+Start.FOLDER+File.separator+sa));
					Files.delete(Paths.get(Start.path+Start.FOLDER+File.separator+sb));
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
