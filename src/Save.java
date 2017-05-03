import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by michael1337 on 03/05/17.
 */
public class Save {

	public static void saveChunk(String[] chunk,int n) {
		if (chunk!=null) {
			try {
				BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("tmp_chunks"+ File.separator+String.valueOf(n)));

				if (bufferedWriter != null) {
					for (String s : chunk) {
						bufferedWriter.write(s);
						bufferedWriter.newLine();
					}
				}

				bufferedWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
