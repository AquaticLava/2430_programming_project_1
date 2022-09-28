import java.util.ArrayList;

public class SetOperations {
	
	/*
	 * If the parentSet is not sorted, implement this method.
	 * This sorting method is used to sort the elements of the parentSet.
	 * 
	 */
	private static <T> void sort(Comparable<T>[] subset) {
		
	}
	
	/*
	 * BinarySearch is used to find the index of the element of subset in the parentSet.
	 * return the index in the parentSet.
	 */
      
	private static <T> int binarySearch(ArrayList<Comparable<T>> parentSet, Comparable<T> item) {
		int left = 0;
		int right = parentSet.size();
		int middle;
		while(left <= right) {
			middle = (left + right)/2;
			if(item.compareTo((T) parentSet.get(middle)) == 0)
				return middle;
			if(item.compareTo((T) parentSet.get(middle)) < 0) 
				right = middle - 1;
			if(item.compareTo((T) parentSet.get(middle)) > 0)
				left = middle + 1;
		}
		return -1;
		}
	
	
	
	/*
	 * Convert subset to int array
	 * The reason why I choose int[] instead of boolean[] is that int[] is more easier to deal with the multiSet operations.
	 * This method should be a generic method.
	 */
	
	public static <T> int[] convertIntoInt(ArrayList<Comparable<T>> parentSet, ArrayList<Comparable<T>> subset) {
		int index;
		int[] arr = new int[parentSet.size()];
		for(int i = 0; i < subset.size(); i++) {
			index = binarySearch(parentSet, subset.get(i));
			++arr[index];
			}
		return arr;
		}
	
	/*
	 * Convert int array to subset
	 * 
	 */
	public static <T> ArrayList<Comparable<T>> covertIntoSet(ArrayList<Comparable<T>> parentSet, int[] subset) {
		ArrayList<Comparable<T>> newSubset = new ArrayList<Comparable<T>>();
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
	public static <T> ArrayList<Comparable<T>> union(ArrayList<Comparable<T>> parentSet, ArrayList<Comparable<T>> a, ArrayList<Comparable<T>> b) {
		 int[] A = convertIntoInt(parentSet, a);
		 int[] B = convertIntoInt(parentSet, b);
		 int[] newSet = new int[A.length];
		 for(int i = 0; i < A.length; ++i) {
			 if(A[i] > 0 || B[i] > 0) {
				newSet[i] = greater(A[i], B[i]);
			 }
		 }
		 return covertIntoSet(parentSet, newSet);
		}
	
	/*
	 * The method is used to set operation not(A)
	 * The parameter can be subset or multiset.
	 * return a subset after operation
	 */
	public static <T> ArrayList<Comparable<T>> complement(ArrayList<Comparable<T>> parentSet, ArrayList<Comparable<T>> subset) {
		int[] set = convertIntoInt(parentSet, subset);
		for(int i = 0; i < set.length; ++i) {
			if(!(set[i] > 0)) {
				++set[i];
			}
			else {
				set[i] = 0;
			}
		}
		return covertIntoSet(parentSet, set);
		}
	
	/*
	 * The method is used to set operation A and B
	 * The parameter can be subset or multiset.
	 * return a subset after operation
	 */
	public static <T> ArrayList<Comparable<T>> intersection(ArrayList<Comparable<T>> parentSet, ArrayList<Comparable<T>> a, ArrayList<Comparable<T>> b) {
		 int[] A = convertIntoInt(parentSet, a);
		 int[] B = convertIntoInt(parentSet, b);
		 int[] newSet = new int[A.length];
		 for(int i = 0; i < A.length; ++i) {
			 if(A[i] > 0 && B[i] > 0) {
				 newSet[i] = smaller(A[i], B[i]);
			 }
		 }
		 return covertIntoSet(parentSet, newSet);
		}
	
	/*
	 * The method is used to set operation A - B
	 * The parameter can be subset or multiset.
	 * return a subset after operation
	 */
	public static <T> ArrayList<Comparable<T>> subtraction(ArrayList<Comparable<T>> parentSet, ArrayList<Comparable<T>> a, ArrayList<Comparable<T>> b) {
		 int[] A = convertIntoInt(parentSet, a);
		 int[] B = convertIntoInt(parentSet, b);
		 int[] newSet = new int[A.length];
		 for(int i = 0; i < A.length; ++i) {
			 if(A[i] > 0 && B[i] > 0 && A[i] > B[i]) {
				 newSet[i] = A[i] - B[i];
			 }
			 if(A[i] > 0 && B[i] == 0) {
				  newSet[i] = A[i];
			 }
		 }
		 return covertIntoSet(parentSet, newSet);
		}
	
	
	 /*The method is used to set operation A + B
	 * The parameter can be subset or multiset.
	 * return a subset after operation
	 */
	
			
	public static <T> ArrayList<Comparable<T>> add(ArrayList<Comparable<T>> parentSet, ArrayList<Comparable<T>> a, ArrayList<Comparable<T>> b) {
		 int[] A = convertIntoInt(parentSet, a);
		 int[] B = convertIntoInt(parentSet, b);
		 int[] newSet = new int[A.length];
		  for(int i = 0; i < A.length; ++i) {
			 if(A[i] > 0 || B[i] > 0) {
				  newSet[i] = A[i] + B[i];
			 }
		 }
		 return covertIntoSet(parentSet, newSet);
	}
	
	
     
	/*
	 * The method is used to set operation A exclusive or B 
	 * The parameter can be subset or multiset.
	 * return a subset after operation
	 */
	public static <T> ArrayList<Comparable<T>> exclusiveOr(ArrayList<Comparable<T>> parentSet, ArrayList<Comparable<T>> a, ArrayList<Comparable<T>> b) {
		 return add(parentSet, subtraction(parentSet, a, b), subtraction(parentSet, b, a));
	}
	
	private static int greater(int a, int b) {
		if(a > b)
			return a;
		else
			return b;
	}
	
	private static int smaller(int a, int b) {
		if(a < b)
			return a;
		else
			return b;
	}
	
	
	public static void main(String[] args) {
		ArrayList<Comparable<Integer>> parentSet = new ArrayList<>();
		ArrayList<Comparable<Integer>> aSet = new ArrayList<>();
		ArrayList<Comparable<Integer>> bSet = new ArrayList<>();
		for(int i = 1; i < 101; ++i) {
			parentSet.add(i);
		}
		for(int i = 4; i < 60; ++i) {
			aSet.add(i);
			aSet.add(i);
		}
		for(int i = 34; i < 65; ++i) {
			bSet.add(i);
			bSet.add(i);
		    bSet.add(i);
		}
		
		ArrayList<Comparable<Integer>> finalSet = new ArrayList<>();
		finalSet = exclusiveOr(parentSet, aSet, bSet);
		for(int i = 0; i < finalSet.size(); ++i) {
			System.out.print(finalSet.get(i) + " ");
		}
	}
	
	
}
