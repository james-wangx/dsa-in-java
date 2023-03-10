package com.codicefun.kmp;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ViolenceMatchTest {

    @Test
    public void shouldMath() {
        String str1 = "What does James like? Dose James like Java?";
        String str2 = "James like Java";
        assertEquals(ViolenceMatch.violenceMatch(str1, str2), 27);
    }

    @Test
    public void shouldNotMath() {
        String str1 = "What does James like? Dose James like Java?";
        String str2 = "James like girls";
        assertEquals(ViolenceMatch.violenceMatch(str1, str2), -1);
    }
}
