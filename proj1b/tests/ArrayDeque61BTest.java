import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDeque61BTest {
    @Test
    void addFirstAndaddLastTest() {
        Deque61B<Integer> array = new ArrayDeque61B();
        array.addFirst(0);
        array.addFirst(1);
        array.addLast(5);
        array.addFirst(-1);
        array.addLast(6);
        array.addFirst(7);
        array.addFirst(8);
        array.addFirst(9);
        assertThat(array.toList()).containsExactly(9,8,7,-1,1,0,5,6).inOrder();
    }

//     @Test
//     @DisplayName("ArrayDeque61B has no fields besides backing array and primitives")
//     void noNonTrivialFields() {
//         List<Field> badFields = Reflection.getFields(ArrayDeque61B.class)
//                 .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(Object[].class) || f.isSynthetic()))
//                 .toList();
//
//         assertWithMessage("Found fields that are not array or primitives").that(badFields).isEmpty();
//     }

}
