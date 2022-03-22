import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.File;
import java.io.IOException;

public class WriterJson implements Writer{
    private final ObjectMapper mapper = new ObjectMapper();
    private final ObjectNode gamePlay = mapper.createObjectNode();;
    private final ArrayNode stepArr = mapper.createArrayNode();
    private final ArrayNode players = mapper.createArrayNode();
    private final ObjectNode mainNode = mapper.createObjectNode();
    private final ObjectNode gameNode = mapper.createObjectNode();
    private final ObjectNode playerNode1 = mapper.createObjectNode();
    private final ObjectNode playerNode2 = mapper.createObjectNode();

    public void writePlayers(Players player1, Players player2) throws IOException {
        playerNode1.put("-id", String.valueOf(player1.getId()));
        playerNode1.put("-name", String.valueOf(player1.getPlayerName()));
        playerNode1.put("-symbol", String.valueOf(player1.getPlayerElement()));
        players.add(playerNode1);
        playerNode2.put("-id", String.valueOf(player2.getId()));
        playerNode2.put("-name", String.valueOf(player2.getPlayerName()));
        playerNode2.put("-symbol", String.valueOf(player2.getPlayerElement()));
        players.add(playerNode2);
    }

    public void writeStep(int count, char symbol, int h,int v){
        int id;
        if(symbol == 'x'){
            id = 1;
        }else{
            id = 2;
        }
        ObjectNode stepNode = mapper.createObjectNode();
        stepNode.put("-num", String.valueOf(count));
        stepNode.put("-playerId", String.valueOf(id));
        stepNode.put("#text", h + "" + v);
        stepArr.add(stepNode);
    }
    public ObjectNode writeResult(char win){
        if(win == 'x'){
            return mapper.createObjectNode().set("Player", playerNode1);
        }else if(win == 'o'){
            return mapper.createObjectNode().set("Player", playerNode2);
        }else{
            return mapper.createObjectNode().put("", "Draw!");
        }
    }

    public void writeGameplay(ObjectNode result, String path) throws IOException {
        gamePlay.set("Player", players);
        gameNode.set("Step", stepArr);
        gamePlay.set("Game", gameNode);
        gamePlay.set("GameResult", result);
        mainNode.set("Gameplay", gamePlay);
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(path + ".json"), mainNode);
    }
}