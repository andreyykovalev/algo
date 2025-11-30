package org.algo;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MainTest {
    private Main underTest;

    @BeforeEach
    void setUp() {
        underTest = new Main();
    }

    @Test
    public void findIndex_emptyArray() {
        int[] input = new int[]{};
        int result = underTest.findIndex(input, 5);

        assertEquals(-1, result);
    }

    @Test
    public void findIndex() {
        int[] input = new int[]{1, 3, 5, 7, 9};
        int result = underTest.findIndex(input, 5);

        assertEquals(2, result);
    }

    @Test
    public void findIndex_nAtTheEnd() {
//        [1, 3, 5, 7, 9], x = 5
        int[] input = new int[]{1, 3, 5, 7, 9};
        int result = underTest.findIndex(input, 9);

        assertEquals(4, result);
    }

    @Test
    public void findIndex_nIsNotInArray() {
        int[] input = new int[]{2, 4, 6, 8, 10};
        int result = underTest.findIndex(input, 7);

        assertEquals(-1, result);
    }

    @Test
    public void findIndex_startEqualsEnd() {
        int[] input = new int[]{2, 4, 6, 8, 10};
        int result = underTest.findIndex(input, 8);

        assertEquals(3, result);
    }

}