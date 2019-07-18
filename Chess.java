import java.util.Scanner;
public class Chess {
    public static void main(String[] args) {
        Board board = new Board();
        Scanner input = new Scanner(System.in);
        board.printBoard();

        while (true) {
            board.printBoard();
            System.out.printf("Choose a piece: \n");
            System.out.printf("First Coord: ");
            int oldX = input.nextInt();
            System.out.printf("Second Coord: ");
            int oldY = input.nextInt();
            System.out.printf("Choose a space to move to: \n");
            System.out.printf("First Coord: ");
            int newX = input.nextInt();
            System.out.printf("Second Coord: ");
            int newY = input.nextInt();

            boolean moved = board.movePiece(oldX, oldY, newX, newY);
            if (!moved) {
                System.out.println("INVALID MOVE");
            }
        }
    }

}