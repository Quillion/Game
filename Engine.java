import java.util.Scanner;
import java.util.Arrays;
import java.util.Queue;
import java.util.LinkedList;
import java.awt.Color;

public class Engine
{
    public static void repo(Piece piece)
    {
        while(piece.getWallet() < 0 && piece.shopsOwned() > 0)
        {
            piece.addMoney(piece.getShop(piece.shopsOwned()-1).getPrice());
            piece.getShop(piece.shopsOwned()-1).setColor(Color.GRAY);
            piece.getShop(piece.shopsOwned()-1).setInvestment(0);
            piece.removeShop(piece.getShop(piece.shopsOwned()-1).getId());
        }
    }

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

    public static void event(Piece piece, Board board)
    {
        int event = board.getRandom(1, 19);
        // GET PAID event * 100
        if(event > 0 && event < 6)
        {
            piece.addMoney(event*100);
        }
        // TELEPORT
        else if(event == 7)
        {
            event = board.getRandom(0, board.getBoardSize()-1);
            piece.setField(board.getField(event));
            piece.setLastId(-1);
        }
        // GET PAID BASED ON WALLET
        else if(event > 7 && event < 13)
        {
            event = Math.round(piece.getWallet()*((event-7)/10));
            piece.addMoney(event);
        }
        // LOSE ONE SHOP
        else if(event == 13)
        {
            piece.removeShop(piece.getShop(piece.shopsOwned()-1).getId());
        }
        // LOSE MONEY BASED ON WALLET
        else if(event > 13 && event < 19)
        {
            event = Math.round(piece.getWallet()*((event-13)/10));
            piece.addMoney(-event);
        }
        // GIVE 7777
        else if(event == 19)
        {
            piece.addMoney(7777);
        }
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
