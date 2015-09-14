import java.util.ArrayList; 
import java.util.Collections; 
import java.util.List;
public class RandomGenerator {

	//

	public List<Integer> createRandomIndices(int[] indices,int k,int i,int n)

	{

		//creating a list to store 0 to n-1

		List<Integer> list = new ArrayList<Integer>(); 
		Integer arr[] = new Integer[36];
		//creating list to store random k values 
		List<Integer> randomList = new ArrayList<Integer>(); 
		for(int j=0;j<n;j++)
		{

			// System.out.println("In for loop"); 
			arr[j] = new Integer(indices[j]); 
			list.add(arr[j]);
		}

		//System.out.println("Done adding to list\n");

		//removing the possibility of selecting j=i 
		list.remove(i);
		//shuffling the list to facilitate randomization

		Collections.shuffle(list);

		for(int j=0; j<k; j++)

		{

			{

				//Retrieving the first k elements one by one 
				int z= (int) list.get(j);
				//Adding retrieved element to randomList 
				randomList.add(z);

			}

		}
		//return randomlist to main method 

		return randomList;

	}

}
