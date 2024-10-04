import deque.ArrayDeque61B;
import deque.LinkedListDeque61B;
import deque.Deque61B;
import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class LinkedListDequeTest {
    @Test
    public void ForLoopTest() {
        Deque61B<Integer> lld = new LinkedListDeque61B<>();
        for (int i = 0; i < 20; i++) {
            lld.addLast(i);
        }
        //
        int k = 0;
        for (int j : lld) {
            assertThat(j).isEqualTo(k);
            k++;
        }
        //
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        for (int i = 0; i < 20; i++) {
            lld1.addFirst(i);
        }
        //
        k = 19;
        for (int j : lld1) {
            assertThat(j).isEqualTo(k);
            k--;
        }
    }

    @Test
    void equalsTest () {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();
        Deque61B<String> lld2 = new LinkedListDeque61B<>();

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
        Deque61B<String> lld1 = new LinkedListDeque61B<>();

        lld1.addLast("front");
        lld1.addLast("middle");
        lld1.addLast("back");

        assertThat(lld1.toString()).isEqualTo("[front,middle,back]");
    }
}
