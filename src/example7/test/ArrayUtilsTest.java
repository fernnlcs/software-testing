package example7.test;

import example7.main.ArrayUtils;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.Size;

import java.util.List;

import org.assertj.core.api.Assertions;

public class ArrayUtilsTest {

    @Property
    void indexOfShouldFindFirstValue(
            @ForAll @Size(value = 100) List<@IntRange(min = -1000, max = 1000) Integer> numbers,
            @ForAll @IntRange(min = 1001, max = 2000) int value,
            @ForAll @IntRange(max = 99) int indexToAddElement,
            @ForAll @IntRange(max = 99) int startIndex) {
        numbers.add(indexToAddElement, value);
        // we convert the list to an array
        int[] array = convertListToArray(numbers);

        /* if element after the start index, it
         returns the corresponding position,
         otherwise -1 */
        int expectedIndex = (indexToAddElement >= startIndex) ? indexToAddElement : -1;

        Assertions.assertThat(ArrayUtils.indexOf(array, value,
                startIndex)).isEqualTo(expectedIndex);
    }

    private int[] convertListToArray(List<Integer> numbers) {
        int[] array = numbers.stream().mapToInt(x -> x).toArray();
        return array;
    }
}
