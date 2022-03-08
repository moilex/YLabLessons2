public class Players {
    private String playerName;
    private char playerElement;

    public Players(int n, String playerName){
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
}
