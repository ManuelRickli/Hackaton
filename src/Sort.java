import java.io.*;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by michael1337 on 03/05/17.
 */
public class Sort {

	/**
	 * Main entry point to sort the big data.
	 * First slices the big data into biteable chunks and sorts them.
	 * Then it merges all the chunks to create a new sorted big file.
	 * @param path the path of the file with the unsorted big data.
	 */
	public static void sort(String path) {

		Load.initialize(path);

		String[] s;
		int n=0;
		while ((s = Load.getNextChunk()) != null) {
			Arrays.sort(s);
//			for (String str : s) {
//				System.out.println(str);
//			}
//			System.out.println("---");
			Save.saveChunk(s,String.valueOf(n));
			n++;
		}

		Load.close();
		
	}

	public static void merge(String sa, String sb) {
		File path1 = new File(sa);
		File path2 = new File(sb);
		String s1;
		String s2;

		if (path1.exists() && path1.isFile() && path2.exists() && path2.isFile()) try {
			BufferedReader b1 = new BufferedReader(new FileReader(sa));
			BufferedReader b2 = new BufferedReader(new FileReader(sb));

			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("tmp_chunks" + File.separator + sa.concat(sb)));

			try {
				s1 = b1.readLine();
				s2 = b2.readLine();

				while (s1 != null && s2 != null) {
					switch (s1.compareTo(s2)) {
						case 0:
							bufferedWriter.write(s1);
							bufferedWriter.newLine();
							s1 = b1.readLine();
							break;
						case 1:
							bufferedWriter.write(s1);
							bufferedWriter.newLine();
							s1 = b1.readLine();
							break;
						case -1:
							bufferedWriter.write(s2);
							bufferedWriter.newLine();
							s2 = b2.readLine();
							break;
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
				} catch (NoSuchFileException e) {
					e.printStackTrace();
				} catch (DirectoryNotEmptyException e) {
					e.printStackTrace();
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
