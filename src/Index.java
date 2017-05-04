import java.util.ArrayList;

/**
 * Created by michael1337 on 03/05/17.
 */
public class Index {

	private String node;
	private int start,end;

	private ArrayList<Index> children;

	/**
	 * Index is the tree data structure that will be used to determine the approximate destination of your required link.
	 * Also helps with finding out how many links there actually are.
	 */
	public Index(String node, int start, int end) {
		this.node = node;
		this.start = start;
		this.end = end;
	}

	/**
	 * Adds the index to the children list.
	 * @param i Index to add.
	 */
	public void add(Index i) {
		if (children == null) children = new ArrayList<Index>();
		children.add(i);
	}

	/**
	 * Returns whether an Index is in this Indexes children list.
	 * @param i Index to search for.
	 * @return true if contained. false if not or children == null.
	 */
	public boolean contains(Index i) {
		if (children != null) return children.contains(i);
		return false;
	}

	///////////////////////////////////////////

	public String getNode() {
		return node;
	}
	public void setNode(String node) {
		this.node = node;
	}

	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}

	public ArrayList<Index> getChildren() {
		return children;
	}
	public void setChildren(ArrayList<Index> children) {
		this.children = children;
	}
}
