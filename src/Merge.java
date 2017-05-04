import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by michael1337 on 03/05/17.
 */
public class Merge {

	public static void mergeAll() {

		Merge.merge("tmp_chunks/0", "tmp_chunks/1");


	}


	public static void merge(String sa, String sb) {
		String con = sa.concat(sb).replaceAll("\\D", "");

		String s1;
		String s2;

		try {
			BufferedReader b1 = new BufferedReader(new FileReader(sa));
			BufferedReader b2 = new BufferedReader(new FileReader(sb));
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("tmp_chunks"+ File.separator+con));

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
