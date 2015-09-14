public class Reliability_Network {
	private static double sum = 0;
	private static double Row_Probabaility = -1;
	private static int flip_flag = 0;
	private static final int Count_50 = 50;
	private static final int Count_1000 = 1000;
	private static final int Count_1024 = 1024; // total possible combinations of the components
	private static final int Count_10 = 10;
	private static int[] random_Generator = new int[Count_50];
	private static int[] array = new int[Count_1000];
	private static int[][] edge_Matrix = new int[Count_1024][Count_10];
	private static int[][] Matrix_One = new int[Count_1024][Count_10];
	/*
	 * This method will be used to assign the edge or the connection between the two
	 * nodes as up or down
	 */
	public static int[][] SetLinks(int[][] link_Mat) {
		for (int i = 0; i < Count_1024; i++) {
			String currentString = String.format("%010d", Integer.parseInt(Integer.toBinaryString(i)));
			for(int j = 0; j < 10; j++)
			{
				if(currentString.charAt(j) == '0')
				{ 

					link_Mat[i][j] = 0;
				}
				else
				{
					link_Mat[i][j] = 1; 
				}
			}
		}
		return link_Mat;
	}

	/*
	 * This function will round off upto n places
	 */
	public static double round(double value, int places) {

		if (places < 0)
		{
			throw new IllegalArgumentException();
		}

		long factor = (long) Math.pow(10, places);
		value = value * factor;
		long tmp = Math.round(value);
		return (double) tmp / factor;
	}

	/*
	 * This method will find if the nodes are up or down
	 */

	public static int[] UP_DOWN_Search(int[][] matrix) {
		int[] temp = new int[Count_1024]; 
		int counter = 0;
		for (int i = 0; i < Count_1024; i++)
		{
			temp[i] = 0;
		}
		for (int i = 0; i < Count_1024; i++)
		{
			int[] matrix_chk = new int[5];
			for (int k = 0; k < 5; k++)
			{
				matrix_chk[k] = -1;
			}
			if (matrix[i][0] == 1)
			{
				matrix_chk[0] = 0;
				matrix_chk[1] = 1;
			}
			if (matrix[i][1] == 1)
			{
				matrix_chk[0] = 0;
				matrix_chk[2] = 2;
			}
			if (matrix[i][2] == 1)
			{
				matrix_chk[0] = 0;
				matrix_chk[3] = 3;
			}
			if (matrix[i][3] == 1)
			{
				matrix_chk[0] = 0;
				matrix_chk[4] = 4;
			}
			if (matrix[i][4] == 1)
			{
				matrix_chk[1] = 1;
				matrix_chk[2] = 2;
			}
			if (matrix[i][5] == 1)
			{
				matrix_chk[1] = 1;
				matrix_chk[3] = 3;
			}
			if (matrix[i][6] == 1)
			{
				matrix_chk[1] = 1;
				matrix_chk[4] = 4;
			}
			if (matrix[i][7] == 1)
			{
				matrix_chk[2] = 2;
				matrix_chk[3] = 3;
			}
			if (matrix[i][8] == 1)
			{
				matrix_chk[2] = 2;
				matrix_chk[4] = 4;
			}
			if (matrix[i][9] == 1)
			{
				matrix_chk[3] = 3;
				matrix_chk[4] = 4;
			} 
			int count = 0;

			for (int j = 0; j < 5; j++)
			{
				count = count + matrix_chk[j];
			}

			if (count == Count_10)
			{
				temp[counter] = i;
				counter++;
			}
		}
		return temp;
	}
	/*
	 *
	 */
	public static void main(String[] args) {
		int k, i;

		for (i = 0; i < Count_50; i++)
		{
			random_Generator[i] = -1;
		}

		sum = 0;
		float q = (float) 1.0;
		float p = (float) 0.0;

		if (flip_flag == 0)
		{
			edge_Matrix = SetLinks(edge_Matrix);
		}

		array = UP_DOWN_Search(edge_Matrix);



		/*
		 *   This case will handle variable p value which will be incremented in steps of 0.02
		 */

		if (flip_flag != 0)
		{ 
			p = (float) 0.9;
			q = (float) 0.9;
		}

		System.out.println("P\t\tReliability");

		for (double id = p; id <= q;)
		{
			for (int j = 0; j < Count_1000; j++) { if (array[j] != 0)
			{
				for (int ik = 0; ik < Count_10; ik++)
				{
					if (edge_Matrix[array[j]][ik] == 1)
					{
						if (Row_Probabaility == -1)
						{
							Row_Probabaility = id;
						} 					
						else
						{
							Row_Probabaility = Row_Probabaility * id; 
						}
					}
					else
					{
						if (Row_Probabaility == -1)
						{ 
							Row_Probabaility = (1 - id);
						}
						else
						{
							Row_Probabaility = Row_Probabaility * (1- id); 
						}
					}
				}


				sum = sum + Row_Probabaility;
				Row_Probabaility = -1;
			}
			}
			System.out.println(id + "\t\t" + sum);
			id = round(id + 0.02, 2);
			sum = 0;
		}

		Matrix_One = edge_Matrix;


		/*
		 * This case will handle constant k value
		 */
		flip_flag = 1;
		System.out.println("\n******** Constant Value of p *********\n");
		System.out.println("K\t\tReliability");
		for (k = 0; k <= Count_50; k++)
		{
			for (i = 0; i < k; i++)
				random_Generator[i] = (int) (Math.random() * Count_1024);
			int j = 0;
			for (i = 0; i < Count_50; i++) ;
			for (i = 0; i < Count_50; i++)
			{
				if (random_Generator[i] != -1)
				{
					for (j = 0; j < Count_10; j++)
					{
						if (edge_Matrix[random_Generator[i]][j] == 0)
						{ 
							edge_Matrix[random_Generator[i]][j] = 1;
						}
						else
						{
							edge_Matrix[random_Generator[i]][j] = 0; 
						}
					}
				}
			}

			sum = 0;
			q = (float) 1.0 * 1;
			p = (float) 0.0 * 1;
			if (flip_flag == 0)
			{
				edge_Matrix = SetLinks(edge_Matrix); 
			}

			array = UP_DOWN_Search(edge_Matrix);

			if (flip_flag != 0)
			{
				p = (float) 0.96 * 1;
				q = (float) 0.96 * 1;
			}
			for (double di = p; di <= q;)
			{
				for (j = 0; j < Count_1000; j++)
				{
					if (array[j] != 0)
					{
						for (int ki = 0; ki < Count_10; ki++)
						{
							if (edge_Matrix[array[j]][ki] == 1)
							{
								if (Row_Probabaility == -1)
								{ 
									Row_Probabaility = di;
								}
								else
								{
									Row_Probabaility = Row_Probabaility * di;

								}
							}
							else
							{

								if (Row_Probabaility == -1)
								{ 
									Row_Probabaility = (1 - di);
								}
								else
								{
									Row_Probabaility = Row_Probabaility * (1-di);

								}
							}
						}
						sum = sum + Row_Probabaility;
						Row_Probabaility = -1;
					}
				}

				System.out.println(k + "\t\t" + sum);
				di = round(di + 0.02, 2);
				sum = 0;
			}

			edge_Matrix = Matrix_One;
			for (i = 0; i < k; i++)
				random_Generator[i] = -1;
		}
	}
} 
