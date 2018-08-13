import java.util.*;
public class Crc {
	
	static int[] divide(int[] gen,int[] data,int[] rem) {
		int cur=0;
		while(true)
		{
			for(int i=0;i<gen.length;i++) {
				rem[cur+i]=(rem[cur+i]^gen[i]);
			}
				while(rem[cur]==0 && cur!=rem.length-1)
					cur++;
				if((rem.length-cur)<gen.length)
					break;
		}
			return rem;	
	}

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Size of data");
		int data_len  = sc.nextInt();
		
		System.out.println("Enter data bits");
		int[] data = new int[data_len];
				for(int i=0;i<data_len;i++)
					data[i]=sc.nextInt();
				
		//System.out.println("Enter Generator Length");
		int gen_len = 17;
		//int gen[]=new int[gen_len];
		System.out.println(" \nGenerator\n ");
			/*	for(int i=0;i<gen_len;i++)
				{
					gen[i]=sc.nextInt();
				} */
		 int gen[]={1,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,1};
		 for(int i=0;i<gen_len;i++)
			{
				System.out.print(gen[i]+" ");
			} 
		int tot_len=data_len+gen_len-1;
		int[] div = new int[tot_len];
		int[] rem = new int[tot_len];
		int[] crc = new int[tot_len];
		
		System.arraycopy(data, 0, div, 0, data_len);
		System.arraycopy(div, 0, rem, 0, tot_len);
		
		crc=divide(gen,div,rem);
		
		//append remainder
				for(int i=0;i<data_len;i++)
				{
					crc[i]=(data[i]^crc[i]);
				}
		
		System.out.println("\nGenerated Codeword ");
			for(int i=0;i<tot_len;i++)
			{
			System.out.print(crc[i]+" ");
			}
		
			//CRC checker
			//System.out.println("\n Enter "+tot_len+"CRC bits");
			int[] var= new int[tot_len];
			for(int i=0;i<tot_len;i++)
				var[i]=crc[i];
			System.out.println("\nInduce ERROR(1) OR NOT(0)");
			int ch = sc.nextInt();
			if(ch==1)
			{
				System.out.println("Enter Position");
				int pos= sc.nextInt();
				if((var[pos])==1)
					var[pos]=0;
				else
					var[pos]=1;
			}
			System.out.println("\nRECEIVED CODEWORD\n ");
			for(int i=0;i<tot_len;i++)
				System.out.print(var[i]);
			System.out.println(" ");
			System.out.println("Remainder\n");
			for(int i=0;i<tot_len;i++)
				System.out.print(rem[i]);
			System.out.println(" ");
			
			System.arraycopy(var, 0, rem, 0, tot_len);
			rem=divide(gen,var,rem);
			
			for(int i=0;i<rem.length;i++) {
				if(rem[i]!=0) {
					System.out.println("ERROR");
					break;
				}
				
				if(i==rem.length-1) {
					System.out.println("NO ERROR");
					break;
				}
				
			}			
		
				
		sc.close();

	}

}

