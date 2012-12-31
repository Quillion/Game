/**
 * @author      Edgar Quillion <edgarquill@gmail.com>
 * @version     Version 1
 * @since       1.6
 */

import java.util.List;
import java.util.ArrayList;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;

public class Game
{
    private static int ROLL = 0;
    private static int MOVE = 1;

    private int WIDTH, HEIGHT;

    private Board board;
    private List<Piece> pieces;

    private int current_player;
    private int timer;
    private int dice;

    private int step;

    public Game(int WIDTH, int HEIGHT)
    {
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;

        board = new Board();
        pieces = new ArrayList<Piece>();
        pieces.add(new Piece());
        pieces.get(0).setColor(Color.BLUE);
        pieces.add(new Piece());
        pieces.get(1).setColor(Color.RED);
        pieces.add(new Piece());
        pieces.get(2).setColor(Color.GREEN);
        pieces.add(new Piece());
        pieces.get(3).setColor(Color.YELLOW);

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
        board.addField(); // 10
        board.addField(); // 11
        board.addField(); // 12
        board.addField(); // 13
        board.addField(); // 14
        board.addField(); // 15
        board.addField(); // 16
        board.addField(); // 17
        board.addField(); // 18
        board.addField(); // 19
        board.addField(); // 20
        board.addField(); // 21
        board.addField(); // 22
        board.addField(); // 23
        board.addField(); // 24
        board.addField(); // 25
        board.addField(); // 26
        board.addField(); // 27
        board.addField(); // 28
        board.addField(); // 29
        board.addField(); // 30
        board.addField(); // 31
        board.addField(); // 32
        board.addField(); // 33
        board.addField(); // 34
        board.addField(); // 35
        board.addField(); // 36
        board.addField(); // 37
        board.addField(); // 38
        board.addField(); // 39
        board.addField(); // 40
        board.addField(); // 41

        board.getFieldById(0).setX(45);
        board.getFieldById(0).setY(135);

        board.connect(0, 1, Board.TOP_RIGHT);
        board.connect(0, 29, Board.BOTTOM_RIGHT);
        board.connect(1, 2, Board.TOP_RIGHT);
        board.connect(2, 3, Board.TOP_RIGHT);
        board.connect(3, 4, Board.BOTTOM_RIGHT);
        board.connect(4, 5, Board.RIGHT);
        board.connect(5, 6, Board.TOP_RIGHT);
        board.connect(5, 7, Board.BOTTOM_RIGHT);
        board.connect(6, 8, Board.BOTTOM_RIGHT);
        board.connect(7, 8, Board.TOP_RIGHT);
        board.connect(7, 9, Board.BOTTOM_RIGHT);
        board.connect(8, 10, Board.TOP_RIGHT);
        board.connect(9, 15, Board.BOTTOM_RIGHT);
        board.connect(9, 32, Board.BOTTOM_LEFT);
        board.connect(10, 11, Board.BOTTOM_RIGHT);
        board.connect(11, 12, Board.BOTTOM_RIGHT);
        board.connect(12, 13, Board.BOTTOM_RIGHT);
        board.connect(13, 14, Board.BOTTOM_LEFT);
        board.connect(14, 16, Board.BOTTOM_LEFT);
        board.connect(14, 41, Board.BOTTOM_RIGHT);
        board.connect(16, 15, Board.TOP_LEFT);
        board.connect(16, 17, Board.BOTTOM_LEFT);
        board.connect(17, 18, Board.BOTTOM_LEFT);
        board.connect(18, 19, Board.BOTTOM_LEFT);
        board.connect(19, 20, Board.LEFT);
        board.connect(19, 33, Board.BOTTOM_RIGHT);
        board.connect(20, 21, Board.TOP_LEFT);
        board.connect(20, 22, Board.BOTTOM_LEFT);
        board.connect(21, 27, Board.TOP_LEFT);
        board.connect(22, 23, Board.BOTTOM_LEFT);
        board.connect(23, 24, Board.TOP_LEFT);
        board.connect(24, 25, Board.TOP);
        board.connect(25, 26, Board.TOP);
        board.connect(26, 27, Board.TOP_RIGHT);
        board.connect(27, 28, Board.TOP_LEFT);
        board.connect(27, 30, Board.TOP_RIGHT);
        board.connect(28, 29, Board.TOP_LEFT);
        board.connect(30, 31, Board.TOP_RIGHT);
        board.connect(31, 32, Board.RIGHT);
        board.connect(33, 34, Board.BOTTOM_RIGHT);
        board.connect(34, 35, Board.RIGHT);
        board.connect(35, 36, Board.RIGHT);
        board.connect(36, 37, Board.TOP_RIGHT);
        board.connect(37, 38, Board.TOP);
        board.connect(38, 39, Board.TOP);
        board.connect(39, 40, Board.TOP);
        board.connect(40, 41, Board.TOP);

        for(int i = 0; i < pieces.size(); i++)
            pieces.get(i).setField(board.getFieldById(0));

        current_player = 0;
        timer = 0;
        dice = 0;
        step = ROLL;
    }

    public void draw(Graphics2D g)
    {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        board.draw(g);

        for(int i = 0; i < pieces.size(); i++)
            board.draw(g, pieces.get(i), i);

        board.drawDice(g, 6);
    }

    public void update()
    {
        //if(roll)
    }

    public void keyPressed(KeyEvent e)
    {
        if(e.getKeyCode() == KeyEvent.VK_SPACE)
        {
            if(step == ROLL)
            {
                step = MOVE;
                dice = board.roll();
            }
            else if (step == MOVE)
            {
            }
        }
    }

    public void keyReleased(KeyEvent e)
    {
    }

    public void mouseEntered(MouseEvent e) 
    {
    }

    public void mousePressed(MouseEvent e)
    {
    }

    public void mouseMoved(MouseEvent e)
    {
    }

    public int getWIDTH ()
    {
        return WIDTH;
    }

    public int getHEIGHT ()
    {
        return HEIGHT;
    }
}
