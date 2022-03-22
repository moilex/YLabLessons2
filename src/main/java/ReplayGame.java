import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Iterator;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class ReplayGame implements Parser{
    GameField field = new GameField();
    private final String fileName;
    XMLInputFactory inputFactory = XMLInputFactory.newInstance();

    public ReplayGame(String fileName){
        this.fileName = fileName;

    }

    public void displayStep() {
        try {
            XMLEventReader eventReader = inputFactory.createXMLEventReader(new FileInputStream(fileName));
            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();
                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();
                    if (startElement.getName().getLocalPart().equals("Step")) {
                        Iterator attributes = startElement.getAttributes();
                        while (attributes.hasNext()) {
                            Attribute attribute = (Attribute) attributes.next();
                            if (attribute.getName().toString().equals("playerId")) {
                                if (attribute.getValue().equals("1")) {
                                    String val = eventReader.getElementText();
                                    int h = Integer.parseInt(val.substring(0, 1));
                                    int v = Integer.parseInt(val.substring(1));
                                    field.movePlayer(h, v, 'x');
                                    field.displayField();
                                    System.out.println("......");
                                } else {
                                    String val = eventReader.getElementText();
                                    int h = Integer.parseInt(val.substring(0, 1));
                                    int v = Integer.parseInt(val.substring(1));
                                    field.movePlayer(h, v, 'o');
                                    field.displayField();
                                    System.out.println("......");
                                }
                            }
                        }
                    }else if(startElement.getName().getLocalPart().equals("GameResult")){
                        XMLEvent event1 = eventReader.nextEvent();
                        if(event1.isStartElement()){
                            String name = "";
                            String id = "";
                            String symbol = "";
                            startElement = event1.asStartElement();
                            Iterator attributes = startElement.getAttributes();
                            while ((attributes.hasNext())){
                            Attribute atre = (Attribute) attributes.next();
                                if(atre.getName().toString().equals("name")){
                                    name = atre.getValue();
                                }else if(atre.getName().toString().equals("id")){
                                    id = atre.getValue();
                                }else{
                                    symbol = atre.getValue();
                                }
                            }
                            System.out.println("PLayer " + id + " -> " + name + " is winner as " + "'" + symbol + "'");

                        }else{
                            System.out.println("Draw!");
                        }
                    }
                }
            }
        } catch (XMLStreamException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}