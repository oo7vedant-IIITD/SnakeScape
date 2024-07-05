package sample;

public class Dice
{
    private int val;
    Dice()
    {}
    //get new dice value
    public int getVal()
    {
        val = (int)(Math.random()*6)+1;
        return val;
    }
}
