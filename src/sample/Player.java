package sample;

public class Player
{
    private String name;
    final private double pos_x;
    final private double pos_y;
    private boolean status = false;
    private boolean winner = false;
    final private String role;
    private int tile = 1;
    // check if player has won
    public boolean isWinner()
    {
        if (this.tile==100)
        {
            winner=true;
        }
        return winner;
    }

    Player(String name,double x,double y,String role)
    {
        this.name=name;
        this.pos_x=x;
        this.pos_y=y;
        this.role=role;
    }

    String getRole() {return this.role;}

    void setStatus(boolean status)
    {
        this.status=status;
    }

    String getName() { return this.name;}

    void setName(String name) {this.name=name;}

    void setWinner(boolean winner)
    {
        this.winner = winner;
    }

    boolean getStatus()
    {
        return status;
    }

    void setTile(int tile){
        this.tile=tile;
    }

    int getTile(){
        return tile;
    }
}
