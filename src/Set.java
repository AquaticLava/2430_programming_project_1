/**
 * Given subsets A and B of a set with n elements,
 * use bit strings or an array of Booleans to find
 * not(A), A∪B, A∩B, A – B, and A ⊕ B
 * (the symmetric difference of A and B defined as (A – B) ∪ (B – A)
 */
public class Set {
	
	/*
	 * returns the inverse of the set 
	 * e.g. [T T F] -> [F F T]
	 */
    public static boolean[] notSet(boolean[] set){
   	 boolean[] returnedSet = set.clone();
   	 for (int i = 0; i < returnedSet.length; i++) {
   		 returnedSet[i] = !returnedSet[i];
   	 }
        return returnedSet;
    }

    public boolean[] orSets(boolean[] setA, boolean[] setB){
        if (setA.length != setB.length){
            throw new IllegalArgumentException("Sets must be the same length");
        }

        boolean[] outputSet = new boolean[setA.length];

        for (int i = 0; i < setA.length; i++) {
            outputSet[i] = setA[i] | setB[i];
        }

        return outputSet;//TODO add comments
    }

    public boolean[] andSets(boolean[] setA, boolean[] setB){
        if (setA.length != setB.length){
            throw new IllegalArgumentException("Sets must be the same length");
        }

        boolean[] outputSet = new boolean[setA.length];

        for (int i = 0; i < setA.length; i++) {
            outputSet[i] = setA[i] & setB[i];
        }

        return outputSet;//TODO add comments
    }

    public boolean[] subtractSets(boolean[] setA, boolean[] setB){
        return null; //TODO
    }

    public boolean[] xorSets(boolean[] setA, boolean[] setB){
        return null; //TODO
    }
}


