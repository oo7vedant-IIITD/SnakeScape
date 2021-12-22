package sample;

public class Snakes implements Objects
{
    final private int start;
    final private int end;
    Snakes(int start, int end)
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
