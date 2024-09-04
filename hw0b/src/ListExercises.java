import java.util.List;
import java.util.ArrayList;

public class ListExercises {

    /** Returns the total sum in a list of integers */
	public static int sum(List<Integer> L) {
        // TODO: Fill in this function.
        int sums=0;
        if(L.isEmpty()) {
            return 0;
        }
        else {
            for(int elm:L) {
                sums=sums+elm;
            }
            return sums;
        }
    }

    /** Returns a list containing the even numbers of the given list */
    public static List<Integer> evens(List<Integer> L) {
        // TODO: Fill in this function.
        List<Integer> result = new ArrayList<>();
        for(int elm:L){
            if(elm%2==0){
                result.add(elm);
            }
        }
        return result;
    }

    /** Returns a list containing the common item of the two given lists */
    public static List<Integer> common(List<Integer> L1, List<Integer> L2) {
        // TODO: Fill in this function.
        List<Integer> result = new ArrayList<>();
        for(int elm1:L1){
            for(int elm2:L2){
                if(elm1==elm2){
                    result.add(elm1);
                }
            }
        }
        return result;
    }


    /** Returns the number of occurrences of the given character in a list of strings. */
    public static int countOccurrencesOfC(List<String> words, char c) {
        // TODO: Fill in this function.
        int count=0;
        for(String elm:words){
            for(int i=0;i<elm.length();i++){
                if(elm.charAt(i)==c){
                    count++;
                }
            }
        }
        return count;
    }
}
