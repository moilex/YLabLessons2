import javax.xml.stream.XMLStreamException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Game {
    public static void main(String[] args) throws XMLStreamException, IOException {
        play();
    }

    public static void play() throws XMLStreamException, IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите 1 чтобы начать игру!");
        System.out.println("Введите 2 чтобы посмотреть прошлые игры.");
        System.out.println("Введите 3 для выхода.");
        switch(sc.nextInt()){
            case 1: startGame();
                sc.close();
                break;
            case 2:
                FileFilter f = new FileFilter();
                f.findFiles();
                System.out.println("Введите название игры(без формата файла)");
                Scanner s = new Scanner(System.in);
                String name = s.nextLine();
                ReplayGame rep = new ReplayGame( name + ".xml");
                rep.replayStep();
                play();
                break;
            case 3:
                break;
        }
    }

    private static void startGame() throws XMLStreamException, IOException {
        Scanner sc = new Scanner(System.in);
        GameField field = new GameField();
        WriterXml xml;
        System.out.println("Введите имя первого игрока: ");
        Players player1 = new Players(1, sc.nextLine());
        System.out.println("Введите имя второго игрока: ");
        Players player2 = new Players(2, sc.nextLine());
        xml = new WriterXml((player1.getPlayerName() + " - " + player2.getPlayerName() + ".xml"));
        xml.writePlayers(player1.getPlayerName(), 'x');
        xml.writePlayers(player2.getPlayerName(), 'o');
        xml.writeStartGame();
        int count = 1;
        char check = player1.getPlayerElement();
        while(!field.checkWinner(check)){
            if(count == 10){
                System.out.println("Ничья!");
                writeRes(player1.getPlayerName(), "NON");
                writeRes(player2.getPlayerName(), "NON");
                xml.writeDraw();
                break;
            }
            check = (count % 2) != 0 ? player1.getPlayerElement() : player2.getPlayerElement();
            System.out.println("Введите число по горизонтали");
            int h = sc.nextInt();
            System.out.println("Введите число по вертикали");
            int v = sc.nextInt();
            field.movePlayer(h,v,check);
            field.displayField();
            xml.writeStep(count,check,h,v);
            count++;
        }
        xml.writeEndTeg();
        if(check == 'x' && count < 10){
            System.out.println(player1.getPlayerName() + " Победил!");
            writeRes(player1.getPlayerName(), "W");
            writeRes(player2.getPlayerName(), "L");
            xml.writeGameResult('x', player1.getPlayerName(), 1);
        }
        if(check == 'o' && count < 10){
            System.out.println(player2.getPlayerName() + " Победил!");
            writeRes(player2.getPlayerName(), "W");
            writeRes(player1.getPlayerName(), "L");
            xml.writeGameResult('o', player1.getPlayerName(), 2);
        }
        xml.endWrite();
        play();
    }

    public static void writeRes(String name, String result){
        String s = name + ": " + result;
        try(FileOutputStream fos = new FileOutputStream("Test.txt", true)){
            byte[] buf = s.getBytes();
            fos.write(buf);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
