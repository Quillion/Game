import java.util.Scanner;

public class Engine
{
    public static void movePiece(Board board, Piece piece, int amount)
    {
        Scanner in = new Scanner(System.in);
        // init
        Field a;
        int user_choice = 0;
        int count = 0;
        // how many spaces to move forward
        for (int i = 0; i < amount; i++)
        {
            // get the field currently standing on
            a = piece.getField();
            System.out.println("Current: "+a.getId()+", Last: "+piece.getLastId());
            // if we can travel in more than one direction
            if(a.getFieldSize() > 2 || piece.getLastId() == -1)
            {
                count = 1;
                // display choices
                System.out.println("\nChoose where to go next:");
                for (int j = 0; j < a.getFieldSize(); j++)
                {
                    if(a.getField(j).getId() != piece.getLastId())
                    {
                        System.out.println(count+") "+a.getField(j).getId());
                        count++;
                    }
                }

                // get the choice
                user_choice = in.nextInt();

                count = 1;
                // move
                for (int j = 0; j < a.getFieldSize(); j++)
                {
                    if(user_choice == count)
                    {
                        piece.setField(a.getField(j));
                        break;
                    }
                    if(a.getField(j).getId() != piece.getLastId())
                    {
                        count++;
                    }
                }
            }
            // we can only move forward
            else
            {
                // move forward
                for (int j = 0; j < a.getFieldSize(); j++)
                {
                    if(a.getField(j).getId() != piece.getLastId())
                    {
                        piece.setField(a.getField(j));
                        break;
                    }
                }
            }
        }
    }
}
