import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        play();
    }

    public static void play(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите 1 чтобы начать игру!");
        System.out.println("Введите 2 выйти.");
        switch(sc.nextInt()){
            case 1: startGame();
                break;
            case 2:
                break;
        }
        sc.close();
    }

    private static void startGame() {
        Scanner sc = new Scanner(System.in);
        GameField field = new GameField();
        System.out.println("Введите имя первого игрока: ");
        Players player1 = new Players(1, sc.nextLine());
        System.out.println("Введите имя второго игрока: ");
        Players player2 = new Players(2, sc.nextLine());
        int count = 1;
        char check = player1.getPlayerElement();
        while(!field.checkWinner(check)){
            if(count == 10){
                System.out.println("Ничья!");
                writeRes(player1.getPlayerName(), "NON");
                writeRes(player2.getPlayerName(), "NON");
                break;
            }
            count++;
            check = (count % 2) == 0 ? player1.getPlayerElement() : player2.getPlayerElement();
            System.out.println("Введите число по горизонтали");
            int h = sc.nextInt();
            System.out.println("Введите число по вертикали");
            int v = sc.nextInt();
            field.movePlayer(h,v,check);
            field.displayField();
        }
        if(check == 'x' && count < 10){
            System.out.println(player1.getPlayerName() + " Победил!");
            writeRes(player1.getPlayerName(), "W");
            writeRes(player2.getPlayerName(), "L");
        }
        if(check == 'o' && count < 10){
            System.out.println(player2.getPlayerName() + " Победил!");
            writeRes(player2.getPlayerName(), "W");
            writeRes(player1.getPlayerName(), "L");
        }
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
