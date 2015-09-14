public class DjikstraShortestPath {

	final int Temp=0; 
	final int PERM= 1; 
	final int NODE= 36; 
	final int Value=299; 
	int NumberOfHops;
	int path[];
	int distance;
	int Count[][]= new int[36][36];
	public int[][] getCount() {
		return Count;
	}
	public void setC(int[][] NewCount) {
		this.Count = NewCount;
	}
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	public int getNumberOfHops() {
		return NumberOfHops;
	}
	public void setNumberOfHops(int numberOfHops) {
		this.NumberOfHops = numberOfHops;
	}
	public int[] getPath() {
		return path;
	}
	public void setPath(int[] path) {
		this.path = path;
	}
	public DjikstraShortestPath()
	{
		for(int x=0; x<Count.length; x++)
		{
			for(int y=0; y<Count.length; y++)
			{Count[x][y]=0;
			}
		}
	}
	void findpath(int s,int d,int a[][])
	{
		int i,min,count=0;
		int current,newdist,u,v,n=NODE;
		int setdist=0; 
		int path[]=new int[n];

		NodeGenerator[] node=new NodeGenerator[n];
		/* Make all nodes temporary */
		for(i=0; i<n; i++)
		{
			node[i] = new NodeGenerator(); 
			node[i].setPredecessor(-1); 
			node[i].setDistance(Value);
			node[i].setStatus(Temp);
		}
		/*Source node should be permanent*/
		node[s].setPredecessor(-1); 
		node[s].setDistance(0); 
		node[s].setStatus(PERM);
		/*Starting from source node until destination is found*/
		current=s;
		while(current!=d)
		{
			for(i=0; i<n; i++)
			{ 
				/*Checks for adjacent temporary nodes */
				if ( a[current][i] > 0 && node[i].getStatus() == Temp)	
				{
					newdist=node[current].getDistance() + a[current][i];

					/*Checks for Relabeling*/
					if( newdist < node[i].getDistance() )
					{ 
						node[i].setPredecessor(current);
						node[i].setDistance(newdist);
					}
				}
			}/*End of for*/
			/*Search for temporary node with minimum distance make it current node*/ 
			min=Value; current=-1;
			for(i=1; i<n; i++)
			{ 
				if(node[i].getStatus() == Temp &&
						node[i].getDistance() < min)
				{
					min = node[i].getDistance();
					current=i;
				}
			}/*End of for*/
			if(current==-1) /*If Source or Sink node is isolated*/
				return ;
			node[current].setStatus(PERM);
		}/*End of while*/
		/* Getting full path in array from destination to source */
		while( current!=-1 )
		{
			//   count++; 
			path[count++]=current; 
			current=node[current].getPredecessor();
		}
		/*Getting distance from source to destination*/
		for( i=count-1; i>0; i--)
		{
			u=path[i];
			v=path[i-1];
			setdist= setdist + a[u][v];
			Count[u][v]=Count[u][v]+1;
		} 
		this.setNumberOfHops(count); 
		this.setPath(path); 
		this.setDistance(setdist); 
		this.setC(Count);

	}/*End of findpath()*/
}

