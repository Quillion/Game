public class Main
{
    public static void main(String [] args)
    {
        Board board = new Board();
        Piece piece = new Piece();

        board.addField(); // 0
        board.addField(); // 1
        board.addField(); // 2
        board.addField(); // 3
        board.addField(); // 4
        board.addField(); // 5
        board.addField(); // 6
        board.addField(); // 7
        board.addField(); // 8
        board.addField(); // 9

        board.connect(0, 1);
        board.connect(0, 2);
        board.connect(2, 3);
        board.connect(1, 3);
        board.connect(3, 4);
        board.connect(3, 5);
        board.connect(4, 6);
        board.connect(5, 6);
        board.connect(6, 7);
        board.connect(6, 8);
        board.connect(7, 9);
        board.connect(8, 9);

        piece.setField(board.getFieldById(0));

        Engine.movePiece(board, piece, 9);
    }
}
