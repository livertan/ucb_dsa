import org.junit.jupiter.api.*;

import java.util.Comparator;
import deque.MaxArrayDeque61B;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class MaxArrayDeque61BTest {
    private static class StringLengthComparator implements Comparator<String> {
        public int compare(String a, String b) {
            return a.length() - b.length();
        }
    }

    @Test
    public void basicTest() {
        MaxArrayDeque61B<String> mad = new MaxArrayDeque61B<>(new StringLengthComparator());
        mad.addFirst("");
        mad.addFirst("2");
        mad.addFirst("fury road");
        assertThat(mad.max()).isEqualTo("fury road");
        assertThat(mad.max(new StringLengthComparator())).isEqualTo("fury road");
    }

    @Test void naturalOrderTest() {
        MaxArrayDeque61B<Integer> m = new MaxArrayDeque61B<Integer>(Comparator.naturalOrder());
        m.addFirst(9);
        m.addFirst(10);
        m.addFirst(8);
        m.addFirst(2);
        assertThat(m.max()).isEqualTo(10);
        assertThat(m.max(Comparator.naturalOrder())).isEqualTo(10);
    }
}
