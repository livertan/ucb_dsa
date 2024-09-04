import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class MapExercises {
    /** Returns a map from every lower case letter to the number corresponding to that letter, where 'a' is
     * 1, 'b' is 2, 'c' is 3, ..., 'z' is 26.
     */
    public static Map<Character, Integer> letterToNum() {
        // TODO: Fill in this function.
        Map<Character, Integer> result = new HashMap<>();
        for(int i=0;i<26;i++){
            result.put((char)(i+'a'),i+1);
        }
        return result;
    }

    /** Returns a map from the integers in the list to their squares. For example, if the input list
     *  is [1, 3, 6, 7], the returned map goes from 1 to 1, 3 to 9, 6 to 36, and 7 to 49.
     */
    public static Map<Integer, Integer> squares(List<Integer> nums) {
        // TODO: Fill in this function.
        Map<Integer, Integer> result = new HashMap<>();
        for(int elm:nums){
            result.put(elm,elm*elm);
        }
        return result;
    }

    /** Returns a map of the counts of all words that appear in a list of words. */
    public static Map<String, Integer> countWords(List<String> words) {
        // TODO: Fill in this function.
        Map<String, Integer> result = new HashMap<>();
        int count;
        String temp="";
        for(int i=0;i<words.size();i++){
            if(temp.equals(words.get(i))){
                continue;
            }
            temp=words.get(i);
            count=1;
            for(int j=i+1;j<words.size();j++){
                if(temp.equals(words.get(j))){
                    count++;
                }
            }
            result.put(temp,count);
        }
        return result;
    }
}
