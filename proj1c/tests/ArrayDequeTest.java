import deque.ArrayDeque61B;
import deque.Deque61B;
import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDequeTest {
    @Test
    void IteratorTestArrayContent () {
        Deque61B<String> array = new ArrayDeque61B<>();
        array.addLast("front"); // after this call we expect: ["front"]
        array.addLast("middle"); // after this call we expect: ["front", "middle"]
        array.addLast("back"); // after this call we expect: ["front", "middle", "back"]
        assertThat(array).containsExactly("front", "middle", "back");
    }

    @Test
    void IteratorForLoopTest () {
        Deque61B<Integer> array1 = new ArrayDeque61B<>();
        for (int i = 0; i < 24; i++) {
            array1.addFirst(2*i+1);
            //array1.addLast(2*i);
        }
        int k = 23;
        for (int j : array1) {
            assertThat(j).isEqualTo(2*k + 1);
            k--;
        }
        Deque61B<Integer> array2 = new ArrayDeque61B<>();
        for (int i = 0; i < 24; i++) {
            //array1.addFirst(2*i+1);
            array2.addLast(2*i);
        }
        k = 0;
        for (int j : array2) {
            assertThat(j).isEqualTo(2*k);
            k++;
        }
    }

    @Test
    void equalsTest () {
        Deque61B<String> lld1 = new ArrayDeque61B<>();
        Deque61B<String> lld2 = new ArrayDeque61B<>();

        lld1.addLast("front");
        lld1.addLast("middle");
        lld1.addLast("back");

        lld2.addLast("front");
        lld2.addLast("middle");
        lld2.addLast("back");

        assertThat(lld1).isEqualTo(lld2);
    }

    @Test
    void toStringTest () {
        Deque61B<String> lld1 = new ArrayDeque61B<>();

        lld1.addLast("front");
        lld1.addLast("middle");
        lld1.addLast("back");

        assertThat(lld1.toString()).isEqualTo("[front,middle,back]");
    }

}
