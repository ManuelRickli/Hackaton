import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Collections;

/**
 * Created by michael1337 on 03/05/17.
 */
public class Sort {

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
			Save.saveChunk(s,n);
			n++;
		}

		Load.close();

	}



}
