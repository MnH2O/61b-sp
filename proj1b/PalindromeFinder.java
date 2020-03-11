/** This class outputs all palindromes in the words file in the current directory. */
public class PalindromeFinder {

    public static void main(String[] args) {
        int minLength = 4;
        In in = new In("../library-sp18/data/words.txt");
        Palindrome palindrome = new Palindrome();

      //  while (!in.isEmpty()) {
      //      String word = in.readString();
      //      if (word.length() >= minLength && palindrome.isPalindrome(word)) {
      //          System.out.println(word);
      //      }
      //  }

        Palindrome palindrome1 = new Palindrome();
        OffByN obn = new OffByN(2);
        while (!in.isEmpty()) {
            String word = in.readString();
            if (word.length() >= minLength && palindrome.isPalindrome(word, obn)) {
                System.out.println(word);
            }
        }
    }
}
