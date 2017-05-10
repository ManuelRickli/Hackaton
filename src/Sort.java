import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
/**
 * Created by michael1337 on 03/05/17.
 */
public class Sort {

	/**
	 * Main entry point to sort the big data.
	 * First slices the big data into biteable chunks and sorts them.
	 * Then it merges all the chunks to create a new sorted big file.
	 *
	 * @param path the path of the file with the unsorted big data.
	 */
	public static void sort() {

		Load.initialize();

		String[] s;
		int n = 0;
		while ((s = Load.getNextChunk()) != null) {
			Arrays.sort(s);
			System.out.println("sorted: "+n);
//			for (String str : s) {
//				System.out.println(str);
//			}
//			System.out.println("---");
			Save.saveChunk(s, n);
			n++;
			if ((Start.MAXCHUNKS>0) && ((n>=Start.MAXCHUNKS))) break;
		}

		Load.close();

		Merge.mergeAll();
	}

}
