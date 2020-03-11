import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    /*// You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset. */
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome(){
        boolean actual1 = palindrome.isPalindrome("racecar");
        boolean actual2 = palindrome.isPalindrome("horse");
        boolean actual3 = palindrome.isPalindrome("a");

        assertTrue(actual1);
        assertFalse(actual2);
        assertTrue(actual3);
    }

    @Test
    public void testIsPalindromeNew(){
        OffByOne obo = new OffByOne();
        OffByN obn = new OffByN(2);
        assertFalse(palindrome.isPalindrome("horse", obo));
        assertTrue(palindrome.isPalindrome("a", obo));
        assertTrue(palindrome.isPalindrome("", obo));
        assertTrue(palindrome.isPalindrome("flake", obo));
        assertTrue(palindrome.isPalindrome("flabke", obo));  // flabke is not a word...
        assertTrue(palindrome.isPalindrome("abedc", obn));
        assertFalse(palindrome.isPalindrome("abedd", obn));
    }
}
