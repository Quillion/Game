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
    private static int CHOICE = 2;

    private int WIDTH, HEIGHT;

    private Board board;
    private List<Piece> pieces;

    private int current_player;
    private int timer;
    private int dice;
    private int choice;

    private int step;

    private int temp;

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

        board.getFieldById(0).setType(Field.BANK);
        board.getFieldById(1).setType(Field.SHOP);
        board.getFieldById(1).setPrice(100);
        board.getFieldById(2).setType(Field.SHOP);
        board.getFieldById(2).setPrice(110);
        board.getFieldById(3).setType(Field.SHOP);
        board.getFieldById(3).setPrice(210);
        board.getFieldById(4).setType(Field.VISIT);
        board.getFieldById(5).setType(Field.SHOP);
        board.getFieldById(5).setPrice(150);
        board.getFieldById(6).setType(Field.SHOP);
        board.getFieldById(6).setPrice(190);
        board.getFieldById(7).setType(Field.SHOP);
        board.getFieldById(7).setPrice(200);
        board.getFieldById(8).setType(Field.SHOP);
        board.getFieldById(8).setPrice(300);
        board.getFieldById(9).setType(Field.SHOP);
        board.getFieldById(9).setPrice(190);
        board.getFieldById(10).setType(Field.VISIT);
        board.getFieldById(11).setType(Field.SHOP);
        board.getFieldById(11).setPrice(250);
        board.getFieldById(12).setType(Field.SHOP);
        board.getFieldById(12).setPrice(90);
        board.getFieldById(13).setType(Field.SHOP);
        board.getFieldById(13).setPrice(290);
        board.getFieldById(14).setType(Field.EVENT);
        board.getFieldById(15).setType(Field.SHOP);
        board.getFieldById(15).setPrice(110);
        board.getFieldById(16).setType(Field.SHOP);
        board.getFieldById(16).setPrice(170);
        board.getFieldById(17).setType(Field.SHOP);
        board.getFieldById(17).setPrice(100);
        board.getFieldById(18).setType(Field.SHOP);
        board.getFieldById(18).setPrice(210);
        board.getFieldById(19).setType(Field.VISIT);
        board.getFieldById(20).setType(Field.SHOP);
        board.getFieldById(20).setPrice(370);
        board.getFieldById(21).setType(Field.EVENT);
        board.getFieldById(22).setType(Field.SHOP);
        board.getFieldById(22).setPrice(220);
        board.getFieldById(23).setType(Field.SHOP);
        board.getFieldById(23).setPrice(150);
        board.getFieldById(24).setType(Field.SHOP);
        board.getFieldById(24).setPrice(200);
        board.getFieldById(25).setType(Field.SHOP);
        board.getFieldById(25).setPrice(70);
        board.getFieldById(26).setType(Field.VISIT);
        board.getFieldById(27).setType(Field.SHOP);
        board.getFieldById(27).setPrice(180);
        board.getFieldById(28).setType(Field.SHOP);
        board.getFieldById(28).setPrice(270);
        board.getFieldById(29).setType(Field.SHOP);
        board.getFieldById(29).setPrice(380);
        board.getFieldById(30).setType(Field.VISIT);
        board.getFieldById(31).setType(Field.SHOP);
        board.getFieldById(31).setPrice(130);
        board.getFieldById(32).setType(Field.SHOP);
        board.getFieldById(32).setPrice(110);
        board.getFieldById(33).setType(Field.EVENT);
        board.getFieldById(34).setType(Field.SHOP);
        board.getFieldById(34).setPrice(390);
        board.getFieldById(35).setType(Field.SHOP);
        board.getFieldById(35).setPrice(450);
        board.getFieldById(36).setType(Field.SHOP);
        board.getFieldById(36).setPrice(480);
        board.getFieldById(37).setType(Field.VISIT);
        board.getFieldById(38).setType(Field.SHOP);
        board.getFieldById(38).setPrice(180);
        board.getFieldById(39).setType(Field.SHOP);
        board.getFieldById(39).setPrice(280);
        board.getFieldById(40).setType(Field.SHOP);
        board.getFieldById(40).setPrice(380);
        board.getFieldById(41).setType(Field.SHOP);
        board.getFieldById(41).setPrice(480);

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
        choice = 0;
        step = ROLL;
        temp = 0;
    }

    public void draw(Graphics2D g)
    {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        board.draw(g);

        for(int i = 0; i < pieces.size(); i++)
            board.draw(g, pieces.get(i), i);

        board.drawDice(g, dice);

        if(dice != 0)
        {
            temp = 0;
            for(int i = 0; i < pieces.get(current_player).getField().getFieldSize(); i++)
            {
                if(pieces.get(current_player).getField().getField(i).getId() != pieces.get(current_player).getLastId())
                {
                    temp++;
                    g.setColor(Color.YELLOW);
                    if(pieces.get(current_player).getField().getField(i).getType() == Field.BANK)
                        g.drawString(temp+") BANK", 550, 350 + temp*10);
                    else if(pieces.get(current_player).getField().getField(i).getType() == Field.SHOP)
                        g.drawString(temp+") "+pieces.get(current_player).getField().getField(i).getPrice(), 550, 350 + temp*10);
                    else if(pieces.get(current_player).getField().getField(i).getType() == Field.VISIT)
                        g.drawString(temp+") VISIT", 550, 350 + temp*10);
                    else if(pieces.get(current_player).getField().getField(i).getType() == Field.EVENT)
                        g.drawString(temp+") EVENT", 550, 350 + temp*10);
                }
            }
        }

        g.setColor(Color.BLUE);
        g.fillRect(570, 250, 10, 10);
        g.setColor(Color.RED);
        g.fillRect(570, 265, 10, 10);
        g.setColor(Color.GREEN);
        g.fillRect(570, 280, 10, 10);
        g.setColor(Color.YELLOW);
        g.fillRect(570, 295, 10, 10);
        g.setColor(Color.GRAY);
        g.fillPolygon(new int[]{555, 565, 555}, new int[]{(250+current_player*15), (255+current_player*15), (260+current_player*15)}, 3);
    }

    public void update()
    {
        if(step == MOVE)
        {
            if(pieces.get(current_player).getField().getFieldSize() > 2 || pieces.get(current_player).getLastId() == -1)
            {
                if(choice != 0)
                {
                    for(int i = 0; i < pieces.get(current_player).getField().getFieldSize(); i++)
                    {
                        if(pieces.get(current_player).getField().getField(i).getId() == pieces.get(current_player).getLastId())
                        {
                            choice++;
                        }
                        if(choice == (i+1))
                        {
                            pieces.get(current_player).setField(pieces.get(current_player).getField().getField(i));
                            dice--;
                            if(dice == 0)
                            {
                                step = ROLL;
                                current_player++;
                                if(current_player > 3)
                                    current_player = 0;
                            }
                            break;
                        }
                    }
                    choice = 0;
                }
                else
                    step = CHOICE;
            }
            else
            {
                if(timer < 50)
                    timer++;
                else
                {
                    timer = 0;
                    for(int i = 0; i < pieces.get(current_player).getField().getFieldSize(); i++)
                    {
                        if(pieces.get(current_player).getField().getField(i).getId() != pieces.get(current_player).getLastId())
                        {
                            pieces.get(current_player).setField(pieces.get(current_player).getField().getField(i));
                            dice--;
                            if(dice == 0)
                            {
                                step = ROLL;
                                current_player++;
                                if(current_player > 3)
                                    current_player = 0;
                            }
                            break;
                        }
                    }
                }
            }
        }
    }

    public void keyPressed(KeyEvent e)
    {
        if(step == ROLL)
        {
            if(e.getKeyCode() == KeyEvent.VK_SPACE)
            {
                step = MOVE;
                dice = board.roll();
            }
        }
        if(step == CHOICE)
        {
            int number = e.getKeyCode();
            if(number >= KeyEvent.VK_1 && number <= KeyEvent.VK_4)
            {
                choice = number - KeyEvent.VK_0;
                step = MOVE;
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
