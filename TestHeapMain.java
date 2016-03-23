import java.util.logging.*;

/**
 * @author Vaikunth Sridharan Heap class & TestHeapMain class
 */
class Heap {

	private int heap[];
	private int costs[];
	private int length = 0, minNode = 0;
	private int leftChildNode = 0;
	private int rightChildNode = 0;
	private int temp = 0;
	private int nodeParent = 0;

	/**
	 * Heap Class constructor with field that initializes our heap & costs with
	 * the given size
	 * 
	 * This heap will be heapified up/down & extracted according to the cost and
	 * not the regular heap way.
	 * 
	 * 
	 * @param
	 */
	public Heap(int iniSize) {
		setHeap(new int[iniSize]);
		setCosts(new int[iniSize]);
		for (int i = 1; i < iniSize; i++) {
			getCosts()[i] = Integer.MAX_VALUE;

		}
	}

	/**
	 * 
	 * This function will initialize the Heap with set of values (iterated over
	 * the passed value)
	 * 
	 * @param return type : Integer
	 */
	public void initializeHeap(int value) {

		for (int iHeap = 0; iHeap < value; iHeap++)
			getHeap()[iHeap] = iHeap;

		setLength(value);
	}

	/**
	 * Insert individual values into the heap. Note : this is used only for
	 * testing Heap operations
	 * 
	 * @param
	 * 
	 */
	public void insert(int value) {

		if (getLength() == 0) {
			getHeap()[getLength()] = value;
			setLength(getLength() + 1);
		} else {

			getHeap()[getLength()] = value;
			heapifyUp(getLength());
			setLength(getLength() + 1);
		}
	}

	/**
	 * Displaying the heap elements
	 * 
	 * 
	 */
	public void printHeap() {

		for (int m = 0; m < getLength(); m++) {
			System.out.print(getHeap()[m] + "	");

		}
		System.out.println();
	}

	/**
	 * Printing the costs
	 * 
	 * @param
	 */

	public int printcosts(int i) {
		if (getCosts()[i] != Integer.MAX_VALUE) {

			return getCosts()[i];
		} else {

			return -1;
		}

	}

	/**
	 * 
	 * 
	 * The algorithm mentioned in Kleinberg and Tardos Textbook (Chapter 2 Pg.
	 * 61)
	 * 
	 * 
	 * @param
	 */
	public void heapifyUp(int i) {

		if (i >= 1) {

			nodeParent = (i - 1) / 2;

			if (getCosts()[getHeap()[i]] < getCosts()[getHeap()[nodeParent]]) {
				temp = getHeap()[nodeParent];
				getHeap()[nodeParent] = getHeap()[i];
				getHeap()[i] = temp;
				heapifyUp(nodeParent);

			}
		}

	}

	/**
	 * 
	 * Checking whether the heap is empty or not, this is homologous to List
	 * isEmpty() function
	 * 
	 * @param
	 * 
	 */
	public Boolean isEmpty() {
		if (getLength() == 0) {
			return true;
		} else
			return false;

	}

	/**
	 * Function to clean the heap
	 * 
	 * @param
	 */
	public void heapClear() {
		setLength(0);
	}

	/**
	 * Function that returns me the first index, just a sample one which was
	 * used for testing my heap operation
	 * 
	 * @param
	 */
	public int firstIndex() {

		if (getHeap()[0] != -1)
			return getHeap()[0];
		else
			return 0;

	}

	/**
	 * Extracting the root node of the heap which is the minimum in case of
	 * Min-Heap
	 * 
	 * @param
	 */
	public int extractMin() {

		if (getLength() != 0) {
			setLength(getLength() - 1);
			minNode = getHeap()[0];
			getHeap()[0] = getHeap()[getLength()];
			getHeap()[getLength()] = -1;
			if (getHeap()[1] != -1) {

				if (((getHeap()[2] != -1))) {
					if ((getCosts()[getHeap()[0]] < getCosts()[getHeap()[2]]))
						heapifyDown(0);
				}
				if (((getHeap()[1] != -1))) {

					if (getCosts()[getHeap()[0]] < getCosts()[getHeap()[1]]) {
						heapifyDown(0);
					}
				}
			}

		} else
			return -1;

		return minNode;

	}

	/**
	 * An important operation of Heap used by Greedy Algorithms, we change the
	 * key and apply heapifyDown appropriately
	 * 
	 * @param w
	 *            - weight
	 * @param nextNode
	 *            - adjacent node(s)
	 * @param u
	 *            - current node
	 * @return
	 */
	public int changeKey(int w, int nextNode, int u) {

		int returnvalue = -2;

		if (w < getCosts()[nextNode]) {
			getCosts()[nextNode] = w;
			returnvalue = u;
		}
		if (getHeap()[nextNode] != -1)
			heapifyUp(nextNode);
		else
			heapifyDown(0);

		return returnvalue;

	}

	/**
	 * 
	 * The algorithm mentioned in Kleinberg and Tardos Textbook (Chapter 2
	 * Pg.63)
	 * 
	 * 
	 * @param iPointer
	 */
	public void heapifyDown(int iPointer) {
		int iTemporary = 0;
		leftChildNode = 2 * iPointer + 1;
		rightChildNode = 2 * (iPointer + 1);
		if (iPointer * 2 < getLength()) {

			if (getHeap()[leftChildNode] != -1) {
				if (getHeap()[rightChildNode] == -1) {

					iTemporary = leftChildNode;
				} else if (getCosts()[getHeap()[leftChildNode]] < getCosts()[getHeap()[rightChildNode]]) {
					iTemporary = leftChildNode;
				} else {
					iTemporary = rightChildNode;

				}
				if (getCosts()[getHeap()[iTemporary]] < getCosts()[getHeap()[iPointer]]) {
					temp = getHeap()[iTemporary];
					heapifyDown(iTemporary);
					getHeap()[iTemporary] = getHeap()[iPointer];
					getHeap()[iPointer] = temp;
					

				}
			}
		}

	}

	/**
	 * Getter method for Heap
	 * 
	 * @return
	 */
	public int[] getHeap() {
		return heap;
	}

	/**
	 * Setter method for Heap
	 * 
	 * @param heap
	 */
	public void setHeap(int heap[]) {
		this.heap = heap;
	}

	/**
	 * Getter method for Heap Length
	 * 
	 * @return
	 */
	public int getLength() {
		return length;
	}

	/**
	 * Setter method for Heap Length
	 * 
	 * @param length
	 */
	public void setLength(int length) {
		this.length = length;
	}

	/**
	 * Getter method for Heap-Cost
	 * 
	 * @return
	 */
	public int[] getCosts() {
		return costs;
	}

	/**
	 * Setter method for Heap-Cost
	 * 
	 * @param costs
	 */
	public void setCosts(int costs[]) {
		this.costs = costs;
	}

	/**
	 * Getter method for getting the cost Length
	 * 
	 * @return
	 */
	public int getCostLength() {
		return costs.length;
	}

}

/**
 * @author Vaikunth Sridharan
 * 
 */
public class TestHeapMain {

	public static void main(String[] args) {
		// Calling constructor with size
		Heap h = new Heap(4);
		// Initialize the Heap with few sample numbers
		h.insert(0);
		h.insert(1);
		h.insert(3);
		h.insert(2);
		Logger loggin = Logger.getLogger("MyLog");
		loggin.log(Level.WARNING,
				"THIS IS JUST TO TEST HEAP!\nDO NOT EXECUTE WHEN YOU WANT TO RUN PRIMS...");
		h.printHeap();
		System.out.println("\n|----> " + h.extractMin());
		System.out.println("Our new Heap is ");

		for (int i = 0; i < h.getLength(); i++) {
			System.out.print(h.getHeap()[i] + "  ");
		}

	}

}
