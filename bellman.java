import java.util.Arrays;
import java.util.Scanner;

public class bellman
{
	static int graph[][], V, infinity=999;
	public static void bellmanford(int node, int dist[], int path[], int toConverge[])
	{	
		dist[node]=0;
		path[node]=node;
		int flag=0;

		for(int vertex=1;vertex<=V-1 && flag==0;vertex++)
		{
			for(int i=1;i<=V;i++)
			{
				for(int j=1;j<=V;j++)
				{
					if(graph[i][j]!=infinity)		//if it's false means we have not yet reached this node
					{
						if(dist[j]>graph[i][j]+dist[i])
						{
							dist[j]=graph[i][j]+dist[i];
							path[j]=i;		//keeping path on which node it shud depend on
						}
					}
				}
			}
			
			if(canConverge(toConverge, dist))		//checking if we can converge
				flag=1;
			
			toConverge=dist;		//keeping backup to compare later
		}
		
		System.out.println("\nFor node "+node);
		System.out.println("DEST\t| COST\t| NEXTHOP");
		System.out.println("-------------------------");
		for(int p=1;p<=V;p++)
		{
				System.out.println("| "+p+"\t| "+dist[p]+"\t| "+path[p]+"\t|");
		}
		System.out.print("-------------------------\n");
	}
	
	public static boolean canConverge(int toConverge[], int dist[])		//comparing arrays if they are equal we can converge
	{
		for(int p=1;p<=V;p++)
		{
			if(toConverge[p]!=dist[p])
				return false;
		}
		return true;
	}

	public static void main(String argv[])		//reading input and calling bellman function
	{
		Scanner in = new Scanner(System.in);

		int dist[], toConverge[], path[];
		
		System.out.print("Enter the number of nodes = ");
		V=in.nextInt();
		graph = new int[V+1][V+1];
		dist = new int[V+1];
		path = new int[V+1];
		toConverge = new int[V+1];

		System.out.println("\nEnter the adjacency matrix :");
		for(int i=1;i<=V;i++)
		{
			for(int j=1;j<=V;j++)
			{
				graph[i][j]=in.nextInt();
				if(i==j)
				{
					graph[i][j]=0;
					continue;
				}
				if(graph[i][j]==0)
					graph[i][j]=infinity;
			}
		}
		
		for(int iter=1;iter<=V;iter++)
		{
			Arrays.fill(dist, infinity);
			Arrays.fill(path, 0);
			bellmanford(iter,dist,path,toConverge);		//calling bellman ford for each node
		}
		
		System.out.print("\nEnter soure = ");
		int src=in.nextInt();
		
		System.out.print("Enter destination = ");
		int des=in.nextInt();
		
		findPath(src, des, dist, path, toConverge);
	}
	
	public static void findPath(int s, int d, int dist[], int path[], int toConverge[])
	{
		int src=s;
		Arrays.fill(dist, infinity);
		Arrays.fill(path, 0);
		bellmanford(d,dist,path,toConverge);		//here we are going from destination to source

		System.out.print("\nPath : "+s);
		while(d!=s)
		{
			System.out.print(" --> "+path[s]);
			s=path[s];
		}
		System.out.print("\nCost required : "+dist[src]);
	}
}
