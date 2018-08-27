import java.util.Random;
import java.util.Scanner;

public class LeakyBucket {
	static int bucketsize, timing;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		LeakyBucket b = new LeakyBucket();

		System.out.println(" Enter the Bucket Size ");
		bucketsize = in.nextInt();
		System.out.println("Enter Output Rate < Bucket Size ");
		int psend = in.nextInt();
		int temp=psend;
		int rem = bucketsize;
try {
		while (timing < 10) {
			System.out.println("\n\n\nAt time interval = " + (timing + 1));

			System.out.print("\nBucket size = " + rem);

			int packet_size = b.generator();
			System.out.print("\nPackets recieved = " + packet_size);

			if (packet_size > rem) {
				System.out.print("\nPackets dropped = " + (packet_size - rem));
				rem = 0;
				System.out.print("\nRemaining bucket size = " + rem);
			} else {
				System.out.print("\nPackets dropped = 0");
				rem -= packet_size;
				System.out.print("\nRemaining bucket size = " + rem);
			}
			
			System.out.print("\nPackets available = " + (bucketsize - rem));
			if((bucketsize - rem )<psend)
				psend=( bucketsize - rem);
			System.out.print("\nPackets sent = " + psend +" *** ");
			rem += psend;
			System.out.print("\nRemaining bucket size = " + rem);
			System.out.print("\nPackets available = " + (bucketsize - rem));
			timing++;
			Thread.sleep(1000);
			psend=temp;
		}
		in.close();
	}catch(Exception e)
		{
			e.printStackTrace();
		}

	}
	int generator() {
		Random r = new Random();
		return r.nextInt(bucketsize) + 1;
	}

}
