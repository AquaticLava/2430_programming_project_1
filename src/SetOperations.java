import java.util.ArrayList;
import java.util.List;

public class SetOperations<T extends Comparable<T>>{

	List<T> parentSet;

	public SetOperations(List<T> parentSet) {
		this.parentSet = parentSet;
	}

	/*
	 * BinarySearch is used to find the index of the element of subset in the parentSet.
	 * return the index in the parentSet.
	 */
      
	private int binarySearch(T item) {
		int left = 0;
		int right = parentSet.size();
		int middle;
		while(left <= right) {
			middle = (left + right)/2;
			if(item.compareTo(parentSet.get(middle)) == 0)
				return middle;
			if(item.compareTo(parentSet.get(middle)) < 0)
				right = middle - 1;
			if(item.compareTo(parentSet.get(middle)) > 0)
				left = middle + 1;
		}
		return -1;
		}
	
	
	
	/*
	 * Convert subset to int array
	 * The reason why I choose int[] instead of boolean[] is that int[] is more easier to deal with the multiSet operations.
	 * This method should be a generic method.
	 */
	
	public int[] convertIntoInt(ArrayList<T> subset) {
		int index;
		int[] arr = new int[parentSet.size()];
		for(int i = 0; i < subset.size(); i++) {
			index = binarySearch(subset.get(i));
			if (index < 0){
				continue;
			}
			++arr[index];
			}
		return arr;
		}
	
	/*
	 * Convert int array to subset
	 * 
	 */
	public ArrayList<T> covertIntoSet(int[] subset) {
		ArrayList<T> newSubset = new ArrayList<>();
		for(int i = 0; i < subset.length; i++) {
			if(subset[i] > 0) {
				for(int j = 0; j < subset[i]; ++j) {
					newSubset.add(parentSet.get(i));
				}
			}
		}
		return newSubset;
	}
	
	/*
	 * The method is used to set operation A or B
	 * The parameter can be subset or multiset.
	 * return a subset after operation
	 */
	public ArrayList<T> union(ArrayList<T> a, ArrayList<T> b) {
		 int[] A = convertIntoInt(a);
		 int[] B = convertIntoInt(b);
		 int[] newSet = new int[A.length];
		 for(int i = 0; i < A.length; ++i) {
			 if(A[i] > 0 || B[i] > 0) {
				newSet[i] = greater(A[i], B[i]);
			 }
		 }
		 return covertIntoSet(newSet);
		}
	
	/*
	 * The method is used to set operation not(A)
	 * The parameter can be subset or multiset.
	 * return a subset after operation
	 */
	public ArrayList<T> complement(ArrayList<T> subset) {
		int[] set = convertIntoInt(subset);
		for(int i = 0; i < set.length; ++i) {
			if(!(set[i] > 0)) {
				++set[i];
			}
			else {
				set[i] = 0;
			}
		}
		return covertIntoSet(set);
		}
	
	/*
	 * The method is used to set operation A and B
	 * The parameter can be subset or multiset.
	 * return a subset after operation
	 */
	public ArrayList<T> intersection(ArrayList<T> a, ArrayList<T> b) {
		 int[] A = convertIntoInt(a);
		 int[] B = convertIntoInt(b);
		 int[] newSet = new int[A.length];
		 for(int i = 0; i < A.length; ++i) {
			 if(A[i] > 0 && B[i] > 0) {
				 newSet[i] = smaller(A[i], B[i]);
			 }
		 }
		 return covertIntoSet(newSet);
		}
	
	/*
	 * The method is used to set operation A - B
	 * The parameter can be subset or multiset.
	 * return a subset after operation
	 */
	public ArrayList<T> subtraction(ArrayList<T> a, ArrayList<T> b) {
		 int[] A = convertIntoInt(a);
		 int[] B = convertIntoInt(b);
		 int[] newSet = new int[A.length];
		 for(int i = 0; i < A.length; ++i) {
			 if(A[i] > 0 && B[i] > 0 && A[i] > B[i]) {
				 newSet[i] = A[i] - B[i];
			 }
			 if(A[i] > 0 && B[i] == 0) {
				  newSet[i] = A[i];
			 }
		 }
		 return covertIntoSet(newSet);
		}
	
	
	 /*The method is used to set operation A + B
	 * The parameter can be subset or multiset.
	 * return a subset after operation
	 */
	
			
	public  ArrayList<T> add(ArrayList<T> a, ArrayList<T> b) {
		 int[] A = convertIntoInt(a);
		 int[] B = convertIntoInt(b);
		 int[] newSet = new int[A.length];
		  for(int i = 0; i < A.length; ++i) {
			 if(A[i] > 0 || B[i] > 0) {
				  newSet[i] = A[i] + B[i];
			 }
		 }
		 return covertIntoSet(newSet);
	}
	
	
     
	/*
	 * The method is used to set operation A exclusive or B 
	 * The parameter can be subset or multiset.
	 * return a subset after operation
	 */
	public ArrayList<T> exclusiveOr(ArrayList<T> a, ArrayList<T> b) {
		 return add(subtraction(a, b), subtraction(b, a));
	}
	
	private static int greater(int a, int b) {
		return Math.max(a, b);
	}
	
	private static int smaller(int a, int b) {
		return Math.min(a, b);
	}
	
	
	public static void main(String[] args) {
		ArrayList<String> Set = new ArrayList<>();
		Set.add("A");
		Set.add("B");
		Set.add("C");
		Set.add("D");
		Set.add("E");
		Set.add("F");
		Set.add("G");
		Set.add("H");
		Set.add("I");
		Set.add("J");

		SetOperations<String> integerSetOperations = new SetOperations<>(Set);
		ArrayList<String> aSubSet = new ArrayList<>();
		aSubSet.add("A");
		aSubSet.add("D");
		aSubSet.add("I");
		aSubSet.add("J");

		ArrayList<String> bSubSet = new ArrayList<>();
		bSubSet.add("A");
		bSubSet.add("E");
		bSubSet.add("D");
		bSubSet.add("F");
		bSubSet.add("J");

		
		ArrayList<String> finalSet;
		finalSet = integerSetOperations.exclusiveOr(aSubSet, bSubSet);
		for (String s : finalSet) {
			System.out.print(s + " ");
		}
	}
	
	
}
