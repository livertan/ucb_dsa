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
    //
    @Test
    void isEmptyAndSizeTest() {
        Deque61B<Integer> array = new ArrayDeque61B();
        array.addLast(6);
        array.addFirst(7);
        array.addFirst(8);
        array.addFirst(9);
        assertThat(array.isEmpty()).isEqualTo(false);
        assertThat(array.size()).isEqualTo(4);

    }

    @Test
    void getTest() {
        Deque61B<Integer> array = new ArrayDeque61B();
        array.addFirst(0);
        array.addFirst(1);
        array.addLast(5);
        array.addFirst(-1);
        array.addLast(6);
        array.addFirst(7);
        array.addFirst(8);
        assertThat(array.get(4)).isEqualTo(0);
        array.addFirst(9);
        assertThat(array.get(0)).isEqualTo(9);
    }

    @Test
    void removeFirstAndremoveLastTest() {
        Deque61B<Integer> array = new ArrayDeque61B();
        array.addFirst(0);
        array.addFirst(1);
        array.addLast(5);
        array.addFirst(-1);
        array.addLast(6);
        array.addFirst(7);
        array.addFirst(8);
        array.removeFirst();
        assertThat(array.toList()).containsExactly(7,-1,1,0,5,6).inOrder();
        array.removeLast();
        assertThat(array.toList()).containsExactly(7,-1,1,0,5).inOrder();
        array.addLast(11);
        assertThat(array.toList()).containsExactly(7,-1,1,0,5,11).inOrder();
        //
        Deque61B<Integer> array1 = new ArrayDeque61B();
        //check items added equally from both ends
        for (int i = 0; i < 24; i++) {
            array1.addFirst(2*i+1);
            array1.addLast(2*i);
        }
        for (int i = 0; i < 23; i++) {
            array1.removeFirst();
            array1.removeLast();
        }
        assertThat(array1.toList()).containsExactly(1,0).inOrder();
        //
        //check more items added from ending end
        Deque61B<Integer> array2 = new ArrayDeque61B();
        for (int i = 0; i < 12; i++){
            array2.addFirst(2*i+1);
        }
        for (int i = 0; i < 36; i++){
            array2.addLast(2*i);
        }
        for (int i = 0; i < 47; i++) {
            array2.removeFirst();
            //array2.removeLast();
        }
        assertThat(array2.toList()).containsExactly(70).inOrder();
        //
        //check more items added from ending end
        Deque61B<Integer> array3 = new ArrayDeque61B();
        for (int i = 0; i < 36; i++){
            array3.addFirst(2*i+1);
        }
        for (int i = 0; i < 12; i++){
            array3.addLast(2*i);
        }
        for (int i = 0; i < 47; i++) {
            //array3.removeFirst();
            array3.removeLast();
        }
        assertThat(array3.toList()).containsExactly(71).inOrder();

    }

    @Test
    void ResizingUpTest() {
        Deque61B<Integer> array = new ArrayDeque61B();
        //check items added equally from both ends
        for (int i = 0; i < 8; i++){
            array.addFirst(2*i+1);
            array.addLast(2*i);
        }
        assertThat(array.toList()).containsExactly(15,13,11,9,7,5,3,1,0,2,4,6,8,10,12,14).inOrder();
        array.addFirst(17);
        array.addLast(16);
        assertThat(array.toList()).containsExactly(17,15,13,11,9,7,5,3,1,0,2,4,6,8,10,12,14,16).inOrder();
        //check more items added from starting end
        Deque61B<Integer> array1 = new ArrayDeque61B();
        for (int i = 0; i < 6; i++){
            array1.addFirst(2*i+1);
        }
        for (int i = 0; i < 2; i++){
            array1.addLast(2*i);
        }
        assertThat(array1.toList()).containsExactly(11,9,7,5,3,1,0,2).inOrder();
        array1.addFirst(13);
        array1.addLast(4);
        assertThat(array1.toList()).containsExactly(13,11,9,7,5,3,1,0,2,4).inOrder();
        //check more items added from ending end
        Deque61B<Integer> array2 = new ArrayDeque61B();
        for (int i = 0; i < 2; i++){
            array2.addFirst(2*i+1);
        }
        for (int i = 0; i < 6; i++){
            array2.addLast(2*i);
        }
        assertThat(array2.toList()).containsExactly(3,1,0,2,4,6,8,10).inOrder();
        array2.addFirst(5);
        array2.addLast(12);
        assertThat(array2.toList()).containsExactly(5,3,1,0,2,4,6,8,10,12).inOrder();
        //
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
