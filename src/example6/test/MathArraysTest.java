package example6.test;

import example6.main.MathArrays;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.Size;

import java.util.Comparator;
import java.util.List;

import org.assertj.core.api.Assertions;

public class MathArraysTest {
    @Property
    void unique(@ForAll @Size(value = 100) List<@IntRange(min = 1, max = 20) Integer> numbers) {
        int[] doubles = convertListToArray(numbers);
        int[] result = MathArrays.unique(doubles);

        Assertions.assertThat(result)
                .contains(doubles)
                .doesNotHaveDuplicates()
                .isSortedAccordingTo(Comparator.reverseOrder());
    }

    private int[] convertListToArray(List<Integer> numbers) {
        int[] array =numbers.stream().mapToInt(x -> x).toArray();
        return array;
    }
}
