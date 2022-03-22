import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class ReadJson implements Parser{
    private final Object obj;

    public ReadJson(String path) throws IOException, ParseException {
        obj = new JSONParser().parse(new FileReader(path));
    }

    public void displayStep(){
        GameField gameField = new GameField();
        JSONObject jo = (JSONObject) obj;
        JSONObject gamePlay = (JSONObject) jo.get("Gameplay");
        JSONObject game = (JSONObject) gamePlay.get("Game");
        JSONArray step = (JSONArray) game.get("Step");
        Iterator steps = step.iterator();
        while(steps.hasNext()){
            JSONObject move = (JSONObject) steps.next();
            String text = (String) move.get("#text");
            String playerId = (String) move.get("-playerId");
            char symbol;
            if(playerId.equals("1")){
                symbol = 'x';
            }else{
                symbol = 'o';
            }
            int h = Integer.parseInt(text.substring(0,1));
            int v = Integer.parseInt(text.substring(1));
            gameField.movePlayer(h,v,symbol);
            gameField.displayField();
            System.out.println("...........");
        }
    }


    private void parseGameResult(){
        JSONObject jo = (JSONObject) obj;
        JSONObject gamePlay = (JSONObject) jo.get("Gameplay");
        JSONObject result = (JSONObject) gamePlay.get("GameResult");
        if(result.containsKey("")){
            System.out.println("Draw!");
            return;
        }
        JSONObject player = (JSONObject) result.get("Player");
        System.out.println("PLayer " + player.get("-id") + " -> " + player.get("-name") + " is winner as " + "'" + player.get("-symbol") + "'");
    }

    public void displayGame(){
        displayStep();
        parseGameResult();
    }

}