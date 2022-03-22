import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriterXml implements Writer {
    private XMLOutputFactory output;
    private XMLStreamWriter writer;
    public WriterXml(String path) throws IOException, XMLStreamException {
       output = XMLOutputFactory.newInstance();
       writer = output.createXMLStreamWriter(new FileWriter(path));
       writer.writeStartDocument();
       writer.writeStartElement("Gameplay");
       writer.writeDTD("\n");
    }
    public void writePlayers(String playerName, char playerSymbol) throws XMLStreamException {
        String id;
        if(playerSymbol == 'x'){
            id = Integer.toString(1);
        }else{
            id = Integer.toString(2);
        }
            writer.writeStartElement("Player");
            writer.writeAttribute("id", id);
            writer.writeAttribute("name", playerName);
            writer.writeAttribute("symbol", Character.toString(playerSymbol));
            writer.writeEndElement();
            writer.writeDTD("\n");
    }
    public void endWrite() throws XMLStreamException {
        writer.writeEndDocument();
        writer.flush();
    }

    public void writeStep(int count, char playerSymbol, int h,int v){
        String playerId;
        if(playerSymbol == 'x'){
            playerId = Integer.toString(1);
        }else{
            playerId = Integer.toString(2);
        }
        try {
            writer.writeStartElement("Step");
            writer.writeAttribute("num", Integer.toString(count));
            writer.writeAttribute("playerId", playerId);
            writer.writeCharacters((Integer.toString(h)));
            writer.writeCharacters(Integer.toString(v));
            writer.writeEndElement();
            writer.writeDTD("\n");
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }

    public void writeStartGame() throws XMLStreamException {
        writer.writeStartElement("Game");
        writer.writeDTD("\n");
    }

    public void writeGameResult(char symbolWin, String name, int id) throws XMLStreamException {
        writer.writeStartElement("GameResult");
        writer.writeStartElement("Player");
        writer.writeAttribute("id", Integer.toString(id));
        writer.writeAttribute("name", name);
        writer.writeAttribute("symbol", Character.toString(symbolWin));
        writer.writeEndElement();
        writer.writeEndElement();
        writer.writeDTD("\n");
    }

    public void writeDraw() throws XMLStreamException {
        writer.writeStartElement("GameResult");
        writer.writeCharacters("Draw!");
        writer.writeEndElement();
        writer.writeDTD("\n");
    }
    public void writeEndTeg() throws XMLStreamException {
        writer.writeEndElement();
        writer.writeDTD("\n");
    }
}
