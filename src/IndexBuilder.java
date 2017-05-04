/**
 * Created by michael1337 on 03/05/17.
 */
public class IndexBuilder {

	/**
	 * Builds root and predefined nodes.
	 * @return
	 */
	public static Index createIndexTree() {

		Index root = new Index("",0,-1);

		build(root);

		return null;
	}

	/**
	 * As long as i.end-i.start > MAXINDEXSIZE or end==-1, the index will be broken down into multiple subindizes.
	 * @param i the index that should be build.
	 */
	public static void build(Index i) {

	}

}
