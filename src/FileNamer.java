/**
 * Created by michael1337 on 04/05/17.
 *
 * Used as a collection of methods, which are necessary for correct file naming and file name reading.
 */
public class FileNamer {

	/**
	 * Returns full name of file in correct format.
	 * @param layer merge-layer.
	 * @param n number of the file.
	 * @return formatted file name.
	 */
	public static String getFileName(int layer, int n) {
		String name = "";
		name = String.valueOf(layer);
		while (name.length()<Start.PREFIXLENGTH) {
			name = "0"+name;
		}
		name = name + String.valueOf(n);
		return name;
	}

	/**
	 * Returns full name of file in correct format.
	 * @param layer merge-layer.
	 * @param n number of the file as String.
	 * @return formatted file name.
	 */
	public static String getFileName(int layer, String n) {
		String name = "";
		name = String.valueOf(layer);
		while (name.length()<Start.PREFIXLENGTH) {
			name = "0"+name;
		}
		name = name + n;
		return name;
	}

	/**
	 * Returns merge-layer of file.
	 * @param name of file.
	 * @return merge-layer of file.
	 */
	public static int getLayer(String name) {
		int n = 0;
		n = Integer.parseInt(name.substring(0,Start.PREFIXLENGTH));
		return n;
	}

	/**
	 * Returns "name" of file. This means it cuts of the merge-layer information.
	 * @param file full name of the file.
	 * @return name of file without merge-layer.
	 */
	public static String getName(String file) {
		return file.substring(Start.PREFIXLENGTH);
	}

}
