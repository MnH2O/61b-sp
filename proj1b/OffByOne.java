public class OffByOne implements CharacterComparator{
    @Override
    public boolean equalChars(char x, char y){
        int distance = Math.abs(x - y);
        if(distance == 1){
            return true;
        }
        return false;
    }
}
