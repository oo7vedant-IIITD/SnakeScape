package sample;

public class Ladders implements Objects
{
    final private int start;
    final private int end;
    Ladders(int start, int end)
    {
        this.start = start;
        this.end = end;
    }
    public int getStart(){
        return start;
    }
    public int getEnd(){
        return end;
    }
}
