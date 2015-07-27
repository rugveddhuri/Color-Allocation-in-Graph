import java.util.ArrayList;
import java.util.Scanner;

public class ColorAllocate {

	public static void main(String[] args) {
		int vertexCount;

		ColorAllocate c = new ColorAllocate();
		
		@SuppressWarnings("resource")
		Scanner input = new Scanner(System.in);

		ArrayList<Vertex> vertices = new ArrayList<Vertex>();
		ArrayList<String> colorNames = new ArrayList<String>();
		Boolean[][] AdjacentMatrix;

		System.out.print("Enter Total Vertices:");
		vertexCount = input.nextInt();

		if (vertexCount < 1) {
			System.out.print("Invalid Number of Vertices");
			return;
		}
		
		AdjacentMatrix = new Boolean[vertexCount][vertexCount];

		for (int i = 0; i < vertexCount; i++) {
			vertices.add(new Vertex(-1));
		}

		colorNames.add(new String("Blue"));
		colorNames.add(new String("Green"));
		colorNames.add(new String("Yellow"));
		colorNames.add(new String("Red"));
		colorNames.add(new String("Black"));
		colorNames.add(new String("Orange"));
		colorNames.add(new String("Violet"));

		for (int i = 0; i < vertexCount; i++) {
			for (int j = 0; j < vertexCount; j++) {
				AdjacentMatrix[i][j] = false;
			}
		}

		/*
		 * // Vertex 1 AdjacentMatrix[0][1] = true; AdjacentMatrix[0][2] = true;
		 * 
		 * // Vertex 2 AdjacentMatrix[1][0] = true; AdjacentMatrix[1][2] = true;
		 * AdjacentMatrix[1][3] = true;
		 * 
		 * // Vertex 3 AdjacentMatrix[2][0] = true; AdjacentMatrix[2][1] = true;
		 * AdjacentMatrix[2][3] = true;
		 * 
		 * // Vertex 4 AdjacentMatrix[3][1] = true; AdjacentMatrix[3][2] = true;
		 */

		input.nextLine();
		for (int i = 0; i < vertexCount; i++) {
			for (int j = 0; j < vertexCount; j++) {
				if (i == j) {
					continue;
				}
				if(i < j) {
					AdjacentMatrix[i][j] = AdjacentMatrix[j][i];
					continue;
				}
				System.out.println("Is vertex " + (i + 1) + " adjacent to "	+ (j + 1) + "?");
				
				if (input.nextLine().equalsIgnoreCase("y")) {
					AdjacentMatrix[i][j] = true;
					//System.out.println("ok");
				}
			}
		}
		System.out.println("");

		int colorSlot[] = new int[vertexCount];

		for (int i = 0; i < vertexCount; i++) {

			/* Initialising Color Slot */
			for (int j = 0; j < vertexCount; j++) {
				colorSlot[j] = -1;
			}

			for (int j = 0; j < vertexCount; j++) {
				if (AdjacentMatrix[i][j]) {
					if (vertices.get(j).getColor() >= 0) {
						colorSlot[j] = vertices.get(j).getColor();
					}
				}
			}

			vertices.get(i).setColor(c.nextColor(colorSlot));
			/*
			 * for (int j = 0; j < vertexCount; j++) { if(colorSlot[j] >= 0) {
			 * System.out.print(colorSlot[j] + " "); }else {
			 * System.out.print(colorSlot[j] + " "); } }
			 */

			// System.out.println(" ");
		}

		ArrayList<Integer> cc = new ArrayList<Integer>();

		int i = 1;
		for (Vertex v : vertices) {
			System.out.print("Vertex " + i + ": ");
			if (v.getColor() < colorNames.size()) {
				System.out.println("Color is " + colorNames.get(v.getColor()));
			} else {
				System.out.println("Color code is " + v.getColor());
			}

			if (!cc.contains(v.getColor())) {
				cc.add(new Integer(v.getColor()));
			}
			i++;
		}

		System.out.println("\nThe minimum number of colors required are: "
				+ cc.size());
	}

	public int nextColor(int c[]) {
		int next = 0;
		boolean nextIteration = true;

		while (nextIteration) {
			for (int i : c) {
				if (i == next) {
					next++;
					nextIteration = true;
				} else {
					nextIteration = false;
				}
			}
		}

		return next;
	}
}
