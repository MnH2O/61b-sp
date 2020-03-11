public class Palindrome {

    public Deque<Character> wordToDeque(String word){
        Deque<Character> result = new LinkedListDeque<>();
        int wordLength = word.length();

        for(int i = 0; i < wordLength; i += 1){
            result.addLast(word.charAt(i));
        }

        return result;
    }

    public boolean isPalindrome(String word){
        Deque<Character> wordDeque = wordToDeque(word);
        while(!wordDeque.isEmpty()){
            char front = wordDeque.removeFirst();
            if(!wordDeque.isEmpty()){
                if(front != wordDeque.removeLast())
                    return false;
            }
        }
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc){
        Deque<Character> wordDeque = wordToDeque(word);
        while(!wordDeque.isEmpty()){
            char front =  wordDeque.removeFirst();
            if(!wordDeque.isEmpty()){
                if(!cc.equalChars(front, wordDeque.removeLast()))
                    return false;
            }
        }
        return true;
    }
}
