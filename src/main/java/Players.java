public class Players {
    private String playerName;
    private char playerElement;
    private int id;

    public Players(int n, String playerName){
        this.id = n;
        if(n == 1){
            this.playerElement = 'x';
        }
        if(n == 2){
            this.playerElement = 'o';
        }
        this.playerName = playerName;
    }

    public char getPlayerElement() {
        return playerElement;
    }

    public String getPlayerName() {
        return playerName;
    }
    public int getId() {
        return id;
    }
}
