public class GameField {
    private char[][] field;
    public GameField(){
        field = new char[3][3];
        createField();
    }

    private void createField(){
        for(int i = 0; i < field.length;i++){
            for( int a = 0; a < field[0].length; a++){
                field[i][a] = '-';
            }
        }
    }

    public void displayField(){
        for(int i = 0; i < field.length;i++){
            for( int a = 0; a < field[0].length; a++){
                System.out.print(field[i][a]);
            }
            System.out.println();
        }
    }
    public void movePlayer(int horizontal, int vertical, char playerChar){
        field[horizontal - 1][vertical - 1] = playerChar;
    }

    public boolean checkWinner(char check){
        return checkDiagonal(check) || checkVertical(check) || checkHorizontal(check);
    }

    private boolean checkHorizontal(char check){
        return field[0][0] == check && field[0][1] == check && field[0][2] == check ||
                field[1][0] == check && field[1][1] == check && field[1][2] == check ||
                field[2][0] == check && field[2][1] == check && field[2][2] == check;
    }

    private boolean checkVertical(char check){
        return field[0][0] == check && field[1][0] == check && field[2][0] == check ||
                field[0][1] == check && field[1][1] == check && field[2][1] == check ||
                field[0][2] == check && field[1][2] == check && field[2][2] == check;
    }

    private boolean checkDiagonal(char check){
        return field[0][0] == check && field[1][1] == check && field[2][2] == check ||
                field[0][2] == check && field[1][1] == check && field[2][0] == check;
    }
}
