import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Vaikunth Sridharan PrimsAlgoMain class
 */
public class PrimsAlgoMain {

	private static int numberOfVertices;
	private static List<Integer> vertices;
	static int k = 0;
	public static int outputArr[];

	/**
	 * This is the core algorithm login implemented by referring Kleinberg and
	 * Tardos Slides & Textbook
	 * 
	 * @param numberoflines
	 * @param vertices
	 * @param inputMat
	 */
	public static void mstPrimsCore(int numberoflines, List<Integer> vertices,
			int[][] inputMat) {

		outputArr = new int[vertices.size()];
		int root = 0;
		Heap h = new Heap(vertices.size());
		h.getCosts()[root] = 0;
		h.heapClear();
		List<Integer> visitedNodes = new ArrayList<Integer>();
		h.initializeHeap(vertices.size());

		int v, w, u;

		while (!h.isEmpty()) {

			u = h.extractMin();
			visitedNodes.add(u);

			if (u != -1) {
				for (int k = 0; k < numberoflines; k++) {
					if (!visitedNodes.contains(inputMat[k][0])
							|| !visitedNodes.contains(inputMat[k][1])) {
						if (inputMat[k][0] == u) {

							w = inputMat[k][2];
							int nextAdjacentNode = inputMat[k][1];

							if (w < h.getCosts()[nextAdjacentNode]) {
								int flag = h.changeKey(w, nextAdjacentNode, u);
								if (flag != -2)
									outputArr[nextAdjacentNode] = flag;

							}

						} else if (inputMat[k][1] == u) {

							w = inputMat[k][2];
							int nextAdjacentNode = inputMat[k][0];

							if (w < h.getCosts()[nextAdjacentNode]) {

								int flag = h.changeKey(w, nextAdjacentNode, u);
								if (flag != -2)
									outputArr[nextAdjacentNode] = flag;

							}
						}
					}
				}
			}

		}
		outputDisplay(h);
	}

	/**
	 * Function to display the output, format is simple starting node to ending
	 * node and the equivalent weighted cost. NODE IS IGNORED IF INFINITY IS
	 * PRESENT. Just maintaining the same input format in output generation too.
	 * 
	 * @param h
	 */
	public static void outputDisplay(Heap h) {
		int totalSum = 0;
		System.out
				.println("---------------------MINIMUM SPANNING OUTPUT---------------------");
		System.out.println("The start node, end node and weighted cost");
		for (int m = 1; m < h.getCostLength(); m++) {
			if (h.printcosts(m) != -1) {
				System.out.print(m + "  " + outputArr[m] + "  ");
				System.out.print(h.getCosts()[m] + "	");
				totalSum += h.printcosts(m);
				System.out.println();
			}
		}
		System.out
				.println("The total cost of the spanning tree is " + totalSum);
	}

	/**
	 * Function to display the input file (store it in a 2d array) and display
	 * 
	 * @param sz
	 * @param mat
	 */
	public static void inputDisplay(int sz, int[][] mat) {
		System.out
				.println("---------------------INPUT FILE 2-D matrix----------------------");
		for (int j = 0; j < sz; j++) {
			for (int i = 0; i < 3; i++) {
				System.out.print(mat[j][i] + "	");
			}
			System.out.print("\n");
		}

	}

	public static void main(String[] args) {
		BufferedReader inputFile = null;
		try {
			if (args.length > 0)
				inputFile = new BufferedReader(new FileReader(args[0]));
			else {
				System.out
						.println("OOPS! ERROR: NO ARGUMENTS PROVIDED\nUSAGE : PrimsAlgoMain.java <FILE_NAME>\nNOTE  : Text file Necessary");

			}

			vertices = new ArrayList<Integer>();
			numberOfVertices = Integer.parseInt(inputFile.readLine());
			int[][] mat = new int[numberOfVertices * 2][4];
			String line[];
			while (inputFile.ready()) {

				line = inputFile.readLine().split("\\s+");

				for (int l = 0; l < 3; l++) {

					mat[k][l] = Integer.valueOf(line[l]);
					if (!vertices.contains(mat[k][l])) {
						if (l < 2) {
							vertices.add(Integer.parseInt(line[l]));
						}
					}
				}

				k++;

			}
			System.out.println(vertices.size());
			inputDisplay(k, mat);
			double stime = System.currentTimeMillis();
			mstPrimsCore(k, vertices, mat);
			double interval = System.currentTimeMillis() - stime;
			System.out.println("The Algorithm was executed for " + interval
					+ " milliseconds");
			inputFile.close();

		} catch (FileNotFoundException e) {
			System.out.println("The file has not been found!");
		} catch (IOException e) {

			System.out.println("IO Exception found!");
		} catch (NullPointerException e) {
		

		}
	}

}
