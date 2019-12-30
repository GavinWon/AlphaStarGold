
public class UnionFind {
	
	class Edge 
	{ 
	    int src, dest; 
	} 
	  
	// class to represent Subset 
	class subset 
	{ 
	    int parent; 
	    int rank; 
	} 
	  
	// A utility function to find  
	// set of an element i (uses  
	// path compression technique) 
	int find(subset [] subsets , int i) 
	{ 
	if (subsets[i].parent != i) 
	    subsets[i].parent = find(subsets,  
	                             subsets[i].parent); 
	    return subsets[i].parent; 
	} 
	  
	// A function that does union 
	// of two sets of x and y 
	// (uses union by rank) 
	void Union(subset [] subsets,  
	           int x , int y ) 
	{ 
	    int xroot = find(subsets, x); 
	        int yroot = find(subsets, y); 
	  
	    if (subsets[xroot].rank < subsets[yroot].rank) 
	        subsets[xroot].parent = yroot; 
	    else if (subsets[yroot].rank < subsets[xroot].rank) 
	        subsets[yroot].parent = xroot; 
	    else
	    { 
	        subsets[xroot].parent = yroot; 
	        subsets[yroot].rank++; 
	    } 
	} 


}
