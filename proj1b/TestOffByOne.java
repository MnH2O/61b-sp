import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {

    static CharacterComparator offByOne = new OffByOne();

    // Your tests go here.
    @Test
    public void testOffByOne(){
        assertTrue(offByOne.equalChars('f', 'e'));
        assertTrue(offByOne.equalChars('d', 'c'));
        assertFalse(offByOne.equalChars('a', 'z'));
    }


}
