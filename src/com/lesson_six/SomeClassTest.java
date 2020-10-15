package com.lesson_six;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.lang.reflect.Array;
import java.util.stream.Stream;

public class SomeClassTest {


    @ParameterizedTest
    @MethodSource("getArrayAfterFour")
    public void doTestGetLastAfterFour(int[] testArray, int[] expected) {
        Assertions.assertArrayEquals(expected, SomeClass.getLastAfterFour(testArray));
    }

    private static Stream<Arguments> getArrayAfterFour() {
        int[] ints = new int[] {3,6,4,1,0};
        int[] ans = new int[] {1,0};

        return Stream.of(
                Arguments.arguments(ints, ans),
                Arguments.arguments(new int[] {1,2,3,4,1}, new int[] {1}),
                Arguments.arguments(new int[] {3,6,7,1,0}, new int[] {1, 0} ),
                Arguments.arguments(new int[] {3,6,7,1,4}, null)
        );
    }

    @Test
    public void doTestGetLastAfterFourException() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            SomeClass.getLastAfterFour(new int[] {1,2,3,6,7,8});
        });
    }



    @ParameterizedTest
    @MethodSource("getCheckOneFour")
    public void doTestCheckOneFour(int[] testArray) {
        Assertions.assertTrue(SomeClass.checkOneFour(testArray));
    }

    private static Stream<Arguments> getCheckOneFour() {

        return Stream.of(
                Arguments.arguments(new int[] {1, 2, 4, 3}),
                Arguments.arguments(new int[] {2,3,4,}),
                Arguments.arguments(new int[] {1, 9, 8 ,7}),
                Arguments.arguments(new int[] {9, 8, 8 ,7})
        );
    }




}
