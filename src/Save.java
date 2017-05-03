import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by michael1337 on 03/05/17.
 */
public class Save {

	/**
	 * Save a chunk into the folder "tmp_chunks" with the name n.
	 * @param chunk the (hopefully) sorted array to save.
	 * @param n the name of the file.
	 */
	public static void saveChunk(String[] chunk,String n) {
		if (chunk!=null) {
			try {
				File folder = new File("tmp_chunks");
				if (!folder.exists()) {
					try {
						folder.mkdir();
					} catch (Exception e) {}
				}

				BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("tmp_chunks"+ File.separator+n));

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
