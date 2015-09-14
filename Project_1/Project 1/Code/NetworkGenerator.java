import java.util.ArrayList; 
import java.util.List;
import java.util.Random;

public class NetworkGenerator {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int NODE=36; //number of nodes given
		int b[][]=new int[NODE][NODE]; //traffic demand between pairs of nodes
		int a[][]=new int[NODE][NODE]; //unit cost of traffic between pairs of nodes



		//possible values to choose from for demand
		int GivenArray[]={0,1,2,3}; //array to store possible values for bij
		Random generator = new Random(); //creating object of Random library class

		try
		{ 
			for(int i=0;i<NODE;i++)
			{
				for(int j=0;j<NODE;j++)
				{
					b[i][j] = generator.nextInt(GivenArray.length);
					//generating a random value from GivenArray
				}
			}
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			System.out.println("Array out of bounds");
		}//iterating through each and every value of k - 3,4,5, ..... ,15
		for(int k=3; k<16; k++)
		{
			int MaxCost=0; 
			float density;
			int count=0;
			//Creating an object of ShortestPath class
			DjikstraShortestPath sp=new DjikstraShortestPath();
			//iterating through every value from 0 to n-1
			for(int i=0;i<NODE;i++)
			{
				//creating an array to store 0 to n-1
				int indices[]=new int[NODE];
				for(int l=0;l<NODE;l++)
				{
					indices[l]=l;
				}
				//creating an array list called randomList
				List<Integer> randomList = new ArrayList<Integer>();
				//creating an object of RandomGeneration
				RandomGenerator r=new RandomGenerator();
				//retrieve k random indices from RandomGeneration class 
				randomList=r.createRandomIndices(indices,k,i,NODE);
				//System.out.println("Back to Main");
				//iterating through 0 to NODE-1 
				for(int j=0;j<NODE;j++)
				{
					// System.out.println("In printing method\n");
					//setting diagonal elements to zero
					if(i==j)
					{
						a[i][j]=0;
					} 					

					//set cost to 1 for indices that are picked in randomlist

					if(randomList.contains(j)&& i!=j)
					{ 
						a[i][j]=1; 
					}
					else					

					//setting a[i][j] to 299 if j is not contained in randomlist

					{
						if(i!=j){
							a[i][j]=299; 
						}
					}
					//System.out.print(a[i][j]);
					//System.out.print("\t\t\t");
					// System.out.println("Done printing");
					//Invoking findpath method for every pair i,j
					sp.findpath(i,j,a);
					int hopCount=sp.getNumberOfHops();
					int path[]=sp.getPath();
					//retrieve distance to get from i to j
					int distance=sp.getDistance();

					//computing demand times unit cost for each link
					MaxCost+=b[i][j]*distance;
				}
			} 		

			//counter variable to store number of edges making up shortest 	path

			count=0;
			//retrieving path matrix
			int c[][]=sp.getCount();
			//incrementing that link in matrix if it exists
			for(int x=0;x<c.length;x++)
			{ 
				for(int y=0;y<c.length;y++)
				{
					if(c[x][y]>0)
						count++;
				}
			}
			for (int i = 0; i < c.length; i++)
			{
				System.out.print(" ");
				for (int j = 0; j < c.length; j++)
				{
					if(c[i][j]!=0)
					{
						System.out.print("1");
						if(j!=c.length) 
							System.out.print(" ");

					}
					else
					{
						System.out.print("0");
						if(j!=c.length) 
							System.out.print(" ");
					} 
				}
				System.out.println(" ");
				if(i!=c.length)
					System.out.print(" ");
			}
			System.out.println("Count is "+ count+" NODE ="+NODE); 
			density=(float)(count)/(NODE*(NODE-1)); 
			System.out.println("k = "+k+"\n"+"MaxCost(Zopt) = "+MaxCost+"\n"+"Density = "+density);
			System.out.println("\n");
		}
	}
}
