import java.util.Scanner;

public class BellmanFord {

	private int d[];
	private int aa[];
	private int num = 0;

	public BellmanFord(int num) {
		this.num = num;
		d = new int[num + 1];
		aa = new int[num + 1];
	}

	public void belleval(int s, int adj[][]) {

		for (int i = 1; i <= num; i++)
			d[i] = 999;
		d[s] = 0;

		for (int k = 1; k <= num - 1; k++) {
			for (int i = 1; i <= num; i++) {
				for (int j = 1; j <= num; j++) {
					if (adj[i][j] != 99) {
						if (d[j] > d[i] + adj[i][j]) {
							d[j] = d[i] + adj[i][j];
						}
					}

				}
			}
		}
		/*
		 * for (int i = 1; i <= num; i++) { for (int j = 1; j <= num; j++) { if
		 * (adj[i][j] != 99) { if (d[j] > d[i] + adj[i][j])
		 * System.out.println("Graph has negative edge cycle "); }
		 * 
		 * } }
		 */

		for (int i = 1; i <= num; i++)
			System.out.println(i + "\t" + 'k' + "\t" + d[i]);

	}

	void floyd(int[][] adj, int num) {
		int i, j, k;
		for (k = 1; k <= num; k++) {
			for (i = 1; i <= num; i++) {
				for (j = 1; j <= num; j++)
					adj[i][j] = Math.min(adj[i][j], adj[i][k] + adj[k][j]);
			}
		}
		for (i = 1; i <= num; i++) {
			for (j = 1; j <= num; j++) {
				System.out.print(adj[i][j] + " ");
			}
			System.out.println(" ");
		}

	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = 0;
		int s;
		System.out.println("Enter Number Of vertices ");
		n = sc.nextInt();
		int[][] adj = new int[n + 1][n + 1];

		System.out.println("Enter Adjacency Matrix ");
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				adj[i][j] = sc.nextInt();

				if (i == j) {
					adj[i][j] = 0;
					continue;
				}
				if (adj[i][j] == 0)
					adj[i][j] = 99;

			}
		}
		System.out.println("Enter Source vertex ");
		s = sc.nextInt();
		BellmanFord bell = new BellmanFord(n);

		bell.floyd(adj, n);
		bell.belleval(s, adj);
		sc.close();
	}

}
