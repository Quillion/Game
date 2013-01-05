import java.util.Scanner;
import java.util.Arrays;
import java.util.Queue;
import java.util.LinkedList;

public class Engine
{
    public static int buy_decision(Piece piece)
    {
        if(piece.getField().isOwned())
        {
            if((piece.getField().getBuyoutPrice() * 1.3) < piece.getWallet())
                return 1;
            else
                return 2;
        }
        else
        {
            if((piece.getField().getPrice() * 2.3) < piece.getWallet())
                return 1;
            else
                return 2;
        }
    }

    public static int move_direction(Piece piece, Board board)
    {
        // INTIALIZE THINGS
        boolean visited[] = new boolean[board.getBoardSize()];
        Arrays.fill(visited, false);
        Field temp;
        int answer = 0;
        Queue<Field> queue = new LinkedList<Field>();

        // SET CURRENT AND LAST FIELD AS VISITED
        temp = piece.getField();
        visited[piece.getField().getId()] = true;
        if(piece.getLastId() != -1)
            visited[piece.getLastId()] = true;
        // SET CHOICES USING FIELD GARBAGE VALUE
        for(int i = 0; i < temp.getFieldSize(); i++)
        {
            if(!visited[temp.getField(i).getId()])
            {
                answer++;
                temp.getField(i).garbage = answer;
            }
        }

        // INSERT THINGS IN FRONT OR END ORDER
        answer = board.getRandom(0, 1);
        // FRONT FIRST
        if(answer == 0)
        {
            for(int i = 0; i < temp.getFieldSize(); i++)
            {
                if(!visited[temp.getField(i).getId()])
                {
                    queue.add(temp.getField(i));
                    visited[temp.getField(i).getId()] = true;
                }
            }
        }
        // END FIRST
        else
        {
            for(int i = temp.getFieldSize()-1; i >= 0; i--)
            {
                if(!visited[temp.getField(i).getId()])
                {
                    queue.add(temp.getField(i));
                    visited[temp.getField(i).getId()] = true;
                }
            }
        }

        // SIMPLE BFS
        while(!queue.isEmpty())
        {
            temp = queue.remove();
            // MAKE A DECISION IF POSSIBLE
            if(temp.getType() == Field.VISIT)
            {
                if(!piece.getVisited(temp.getPrice()))
                {
                    answer = temp.garbage;
                    break;
                }
            }
            else if(temp.getType() == Field.BANK)
            {
                if(piece.allVisited())
                {
                    answer = temp.garbage;
                    break;
                }
            }
            // SAME THING AGAIN. EITHER GO FROM FRONT OR BACK
            answer = board.getRandom(0, 1);
            // FRONT FIRST
            if(answer == 0)
            {
                for(int i = 0; i < temp.getFieldSize(); i++)
                {
                    if(!visited[temp.getField(i).getId()])
                    {
                        temp.getField(i).garbage = temp.garbage;
                        queue.add(temp.getField(i));
                        visited[temp.getField(i).getId()] = true;
                    }
                }
            }
            // BACK FIRST
            else
            {
                for(int i = temp.getFieldSize()-1; i >= 0; i--)
                {
                    if(!visited[temp.getField(i).getId()])
                    {
                        temp.getField(i).garbage = temp.garbage;
                        queue.add(temp.getField(i));
                        visited[temp.getField(i).getId()] = true;
                    }
                }
            }
        }
        queue = null;
        return answer;
    }

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
