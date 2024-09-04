import java.util.ArrayList;
import java.util.List;

public class JavaExercises {

    /**
     * Returns an array [1, 2, 3, 4, 5, 6]
     */
    public static int[] makeDice() {
        // TODO: Fill in this function.
        int[] Dice = new int[6];
        for (int i = 0; i < 6; i++) {
            Dice[i] = i + 1;
        }
        return Dice;
    }

    /**
     * Returns the order depending on the customer.
     * If the customer is Ergun, return ["beyti", "pizza", "hamburger", "tea"].
     * If the customer is Erik, return ["sushi", "pasta", "avocado", "coffee"].
     * In any other case, return an empty String[] of size 3.
     */
    public static String[] takeOrder(String customer) {
        // TODO: Fill in this function.
        String[] order;
        if (customer.equals("Ergun")) {
            order = new String[]{"beyti", "pizza", "hamburger", "tea"};
        } else if (customer.equals("Erik")) {
            order = new String[]{"sushi", "pasta", "avocado", "coffee"};
        } else {
            order = new String[]{null, null, null};
        }
        return order;
    }

    /**
     * Returns the positive difference between the maximum element and minimum element of the given array.
     * Assumes array is nonempty.
     */
    public static int findMinMax(int[] array) {
        // TODO: Fill in this function.
        int Min=array[0], Max=array[0];
        int result;
        for (int i = 1; i < array.length; i++) {
            if (array[i] <= Min) {
                Min = array[i];
            }
            if (array[i] >= Max) {
                Max = array[i];
            }
        }
        result = Math.abs(Max) - Math.abs(Min);
        return result;
    }

    /**
     * Uses recursion to compute the hailstone sequence as a list of integers starting from an input number n.
     * Hailstone sequence is described as:
     * - Pick a positive integer n as the start
     * - If n is even, divide n by 2
     * - If n is odd, multiply n by 3 and add 1
     * - Continue this process until n is 1
     */
    public static List<Integer> hailstone(int n) {
        return hailstoneHelper(n, new ArrayList<>());
    }

    private static List<Integer> hailstoneHelper(int x, List<Integer> list) {
        // TODO: Fill in this function.
        if(list.isEmpty()){
            list.add(x);
        }
        if (x  == 1) {
            return list;
        } else if (x%2 == 0) {
            list.add(x/2);
            hailstoneHelper(x/2, list);
            return list;
        } else {
            list.add(3 * x + 1);
            hailstoneHelper(3 * x + 1, list);
            return list;
        }
    }
}