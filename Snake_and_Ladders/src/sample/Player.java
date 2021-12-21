package sample;

public class Player {
    private String name;
    private double pos_x;
    private double pos_y;
    private boolean status=false;
    private boolean winner=false;
    private String role;
    private int tile=1;
    // check if player has won
    public boolean isWinner()
    {
        if (this.tile==100)
        {
            winner=true;
        }
        return winner;
    }
    String getRole(){return this.role;}
    Player(String name,double x,double y,String role){
        this.name=name;
        this.pos_x=x;
        this.pos_y=y;
        this.role=role;
    }
    double[] getCords(){
        double[] cords=new double[2];
        cords[0]=pos_x;
        cords[1]=pos_y;
        return cords;
    }
    void setCords(double x,double y){
        pos_x=x;
        pos_y=y;
    }
    void setStatus(boolean status){
        this.status=status;
    }
    String getName() { return this.name;}
    void setName(String name) {this.name=name;}
    void setWinner(boolean winner){
        this.winner=winner;
    }
    boolean getWinner(){
        return winner;
    }
    boolean getStatus(){
        return status;
    }
    void setTile(int tile){
        this.tile=tile;
    }
    int getTile(){
        return tile;
    }
}
