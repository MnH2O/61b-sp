public class OffByN implements CharacterComparator{
    private int Number;

    public OffByN(int N){
        Number = N;
    }

    @Override
    public boolean equalChars(char x, char y){
        int distance = Math.abs(x - y);
        if(distance == Number){
            return true;
        }
        return false;
    }
}
