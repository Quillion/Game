/**
 * @author      Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version     Version 1
 * @since       1.6
 */

import java.awt.Graphics2D;
import java.awt.Color;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class Board
{
    // CONSTANTS
    public static int TOP = 1;
    public static int TOP_RIGHT = 2;
    public static int RIGHT = 3;
    public static int BOTTOM_RIGHT = 4;
    public static int BOTTOM = 5;
    public static int BOTTOM_LEFT = 6;
    public static int LEFT = 7;
    public static int TOP_LEFT = 8;

    public static int FIRST = 0;
    public static int SECOND = 1;
    public static int THIRD = 2;
    public static int FOURTH = 3;

    // PRIVATE VALUES
    private List<Field> fields;
    private int id_counter;
    private int field_size;
    private Random fate;

    /*
     * Initializer for the Board.
     * It is not enough to just initialize, do stuff.
     */
    public Board()
    {
        this.fields = new ArrayList<Field>();
        this.fate = new Random();
        this.id_counter = 0;
        this.field_size = 40;
    }

    /*
     * Adds a field to the board.
     * Board will manage the field id,
     * but you should know that field ids are incremental starting from 0.
     */
    public void addField()
    {
        Field field = new Field();
        field.setId(this.id_counter);
        this.fields.add(field);
        this.id_counter++;
    }

    /*
     * Will add whatever field you pass to it.
     * The id of the field will be owerwritten with what board choses.
     * @param field field that you want to add to the board.
     */
    public void addField(Field field)
    {
        field.setId(this.id_counter);
        this.fields.add(field);
        this.id_counter++;
    }

    /*
     * In all honesty it is pretty much the same as using getFieldById,
     * But if you decide to change the id of the field after you add it,
     * and you forgot the id, then use position I guess.
     * I really don't see how you would use it.
     * @param num position of the field in the list.
     * @return field object which was at the position you asked for, null if position you asked for is wrong.
     */
    public Field getField(int num)
    {
        if(num < 0 || num >= this.getBoardSize())
            return null;
        return this.fields.get(num);
    }

    /*
     * Returns the last field added to the list. Not sure why you would want it, but here ya go.
     * @return last field object in the list.
     */
    public Field getLastField()
    {
        return this.fields.get(this.fields.size() - 1);
    }

    /*
     * Returns the current number of fields on the board.
     * @return the size of the board.
     */
    public int getBoardSize()
    {
        return this.fields.size();
    }

    /*
     * Returns the field that has the id passed.
     * If the id does not exist however, you will receive null in return for the field object.
     * @param id the id of the field you are searching for in the board.
     * @return the field if found, null otherwise.
     */
    public Field getFieldById(int id)
    {
        for(int i = 0; i < this.getBoardSize(); i++)
            if(this.fields.get(i).getId() == id)
                return this.fields.get(i);
        return null;
    }

    /*
     * Connects two fields together.
     * The direction also is used for the calculations of the x and y coordinates.
     * If someone awesome out there could only make better graphics,
     * the stupid direction thing would be unnecessary.
     * The fields must exist, otherwise they will not be connected.
     * @param id_a id of the first field.
     * @param id_b id of the second field to which field one will be connected to.
     * @param direction relative position of second field to first field.
     * @return true if the fields were connected, false otherwise.
     */
    public boolean connect(int id_a, int id_b, int direction)
    {
        // SOME INIT VALUES
        Field a, b;
        a = b = new Field();
        boolean a_found, b_found;
        a_found = b_found = false;
        // SEARCH FOR THE FIELDS ON THE BOARD
        for(int i = 0; i < this.getBoardSize(); i++)
        {
            if(this.fields.get(i).getId() == id_a)
            {
                a = this.fields.get(i);
                a_found = true;
            }
            else if(this.fields.get(i).getId() == id_b)
            {
                b_found = true;
                b = this.fields.get(i);
            }
        }
        // IF BOTH FIELDS WERE FOUND
        if(a_found && b_found)
        {
            // IF ADDING IS IMPOSSIBLE DUE TO SOME WIRD ERROR
            // IF IT IS THEN IT IS YOUR FAULT! FIX IT AND STOP MESSING UP!!! >:(
            if(!a.addField(b))
                return false;
            if(!b.addField(a))
                return false;
            // SET THE COORDINATES ACCORDING TO DIRECTION
            if(direction == TOP)
            {
                b.setX(a.getX());
                b.setY(a.getY() - this.field_size);
            }
            else if(direction == TOP_RIGHT)
            {
                b.setX(a.getX() + this.field_size);
                b.setY(a.getY() - this.field_size);
            }
            else if(direction == RIGHT)
            {
                b.setX(a.getX() + this.field_size);
                b.setY(a.getY());
            }
            else if(direction == BOTTOM_RIGHT)
            {
                b.setX(a.getX() + this.field_size);
                b.setY(a.getY() + this.field_size);
            }
            else if(direction == BOTTOM)
            {
                b.setX(a.getX());
                b.setY(a.getY() + this.field_size);
            }
            else if(direction == BOTTOM_LEFT)
            {
                b.setX(a.getX() - this.field_size);
                b.setY(a.getY() + this.field_size);
            }
            else if(direction == LEFT)
            {
                b.setX(a.getX() - this.field_size);
                b.setY(a.getY());
            }
            else if(direction == TOP_LEFT)
            {
                b.setX(a.getX() - this.field_size);
                b.setY(a.getY() - this.field_size);
            }
            return true;
        }
        return false;
    }

    /*
     * Draws the game board to the graphics passed to it. Only draws the squares of fields, and nothing else.
     * @param g the graphics to which to draw the game board.
     */
    public void drawFields(Graphics2D g)
    {
        // WILL EVENTUALLY CHANGE THESE CONSTANT NUMBERS
        for(int i = 0; i < this.getBoardSize(); i++)
        {
            if(i < 7)
                this.fields.get(i).draw(g, field_size, Color.WHITE);
            else if(i < 16)
                this.fields.get(i).draw(g, field_size, Color.CYAN);
            else if(i < 24)
                this.fields.get(i).draw(g, field_size, Color.MAGENTA);
            else if(i < 33)
                this.fields.get(i).draw(g, field_size, Color.YELLOW);
            else
                this.fields.get(i).draw(g, field_size, Color.PINK);
        }
    }

    /*
     * Draws the info of the fields on the graphics.
     * Call this after you called drawFields().
     * @param g graphics to which the fields info will be drawn on.
     */
    public void drawInfo(Graphics2D g)
    {
        g.setColor(Color.BLACK);
        for(int i = 0; i < this.getBoardSize(); i++)
            this.fields.get(i).drawInfo(g);
    }

    /*
     * Draws the piece on the board. First player goes to top left,
     * second player goes to top right,
     * third to the bottom left,
     * and fourth to the bottom right.
     * @param g graphics to which the piece will be drawn on.
     * @param piece the piece which you want to draw on the graphics.
     * @param spot which place the player is in.
     */
    public void drawPiece(Graphics2D g, Piece piece, int spot)
    {
        if(spot == FIRST)
        {
            g.setColor(piece.getColor());
            g.fillRect(piece.getField().getX(), piece.getField().getY(), this.field_size/2, this.field_size/2);
            g.setColor(Color.DARK_GRAY);
            g.drawRect(piece.getField().getX(), piece.getField().getY(), this.field_size/2, this.field_size/2);
        }
        else if(spot == SECOND)
        {
            g.setColor(piece.getColor());
            g.fillRect(piece.getField().getX()+this.field_size/2, piece.getField().getY(), this.field_size/2, this.field_size/2);
            g.setColor(Color.DARK_GRAY);
            g.drawRect(piece.getField().getX()+this.field_size/2, piece.getField().getY(), this.field_size/2, this.field_size/2);
        }
        else if(spot == THIRD)
        {
            g.setColor(piece.getColor());
            g.fillRect(piece.getField().getX(), piece.getField().getY()+this.field_size/2, this.field_size/2, this.field_size/2);
            g.setColor(Color.DARK_GRAY);
            g.drawRect(piece.getField().getX(), piece.getField().getY()+this.field_size/2, this.field_size/2, this.field_size/2);
        }
        else if(spot == FOURTH)
        {
            g.setColor(piece.getColor());
            g.fillRect(piece.getField().getX()+this.field_size/2, piece.getField().getY()+this.field_size/2, this.field_size/2, this.field_size/2);
            g.setColor(Color.DARK_GRAY);
            g.drawRect(piece.getField().getX()+this.field_size/2, piece.getField().getY()+this.field_size/2, this.field_size/2, this.field_size/2);
        }
    }

    /*
     * Rolls the dice.
     * @return random number from 1 to 6.
     */
    public int roll()
    {
        return (this.fate.nextInt(6)+1);
    }

    /*
     * Returns a random number from min(inclusive) to max(inclusive).
     * @return a random number within the range of min max (both inclusive).
     */
    public int getRandom(int min, int max)
    {
        return (this.fate.nextInt(max - min+1)+min);
    }

    /*
     * Draws the dice on the game board.
     * Will not draw anything if dice is not within range of 1 to 6.
     * @param g graphics to which to draw the dice to.
     * @param dice what is the current dice value.
     */
    public void drawDice(Graphics2D g, int dice)
    {
        if(dice == 1)
        {
            g.setColor(Color.WHITE);
            g.fillRect(550, 400, field_size, field_size);
            g.setColor(Color.BLACK);
            g.fillOval(567, 417, 6, 6);
        }
        else if(dice == 2)
        {
            g.setColor(Color.WHITE);
            g.fillRect(550, 400, field_size, field_size);
            g.setColor(Color.BLACK);
            g.fillOval(557, 407, 6, 6);
            g.fillOval(577, 427, 6, 6);
        }
        else if(dice == 3)
        {
            g.setColor(Color.WHITE);
            g.fillRect(550, 400, field_size, field_size);
            g.setColor(Color.BLACK);
            g.fillOval(577, 407, 6, 6);
            g.fillOval(567, 417, 6, 6);
            g.fillOval(557, 427, 6, 6);
        }
        else if(dice == 4)
        {
            g.setColor(Color.WHITE);
            g.fillRect(550, 400, field_size, field_size);
            g.setColor(Color.BLACK);
            g.fillOval(557, 407, 6, 6);
            g.fillOval(557, 427, 6, 6);
            g.fillOval(577, 407, 6, 6);
            g.fillOval(577, 427, 6, 6);
        }
        else if(dice == 5)
        {
            g.setColor(Color.WHITE);
            g.fillRect(550, 400, field_size, field_size);
            g.setColor(Color.BLACK);
            g.fillOval(557, 407, 6, 6);
            g.fillOval(557, 427, 6, 6);
            g.fillOval(567, 417, 6, 6);
            g.fillOval(577, 407, 6, 6);
            g.fillOval(577, 427, 6, 6);
        }
        else if(dice == 6)
        {
            g.setColor(Color.WHITE);
            g.fillRect(550, 400, field_size, field_size);
            g.setColor(Color.BLACK);
            g.fillOval(557, 407, 6, 6);
            g.fillOval(557, 427, 6, 6);
            g.fillOval(557, 417, 6, 6);
            g.fillOval(577, 417, 6, 6);
            g.fillOval(577, 407, 6, 6);
            g.fillOval(577, 427, 6, 6);
        }
    }
}
