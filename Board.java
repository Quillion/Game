import java.awt.Graphics2D;
import java.awt.Color;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class Board
{
    private List<Field> fields;
    private int id_counter;
    private int field_size;
    private Random fate;

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

    public Board()
    {
        this.fields = new ArrayList<Field>();
        this.fate = new Random();
        this.id_counter = 0;
        this.field_size = 40;
    }

    public void addField()
    {
        Field field = new Field();
        field.setId(this.id_counter);
        this.fields.add(field);
        this.id_counter++;
    }

    public void addField(Field field)
    {
        field.setId(this.id_counter);
        this.fields.add(field);
        this.id_counter++;
    }

    public Field getField(int num)
    {
        return this.fields.get(num);
    }

    public Field getLastField()
    {
        return this.fields.get(this.fields.size() - 1);
    }

    public int getBoardSize()
    {
        return this.fields.size();
    }

    public Field getFieldById(int id)
    {
        for(int i = 0; i < this.fields.size(); i++)
            if(this.fields.get(i).getId() == id)
                return this.fields.get(i);
        return null;
    }

    // connect two fields together
    public boolean connect(int id_a, int id_b, int direction)
    {
        Field a, b;
        a = b = new Field();
        boolean a_found, b_found;
        a_found = b_found = false;
        for(int i = 0; i < this.fields.size(); i++)
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
        if(a_found && b_found)
        {
            a.addField(b);
            b.addField(a);
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

    public void draw(Graphics2D g)
    {
        for(int i = 0; i < this.fields.size(); i++)
        {
            if(i < 7)
                this.fields.get(i).draw(g, field_size, Color.WHITE, Color.GRAY);
            else if(i < 16)
                this.fields.get(i).draw(g, field_size, Color.CYAN, Color.GRAY);
            else if(i < 24)
                this.fields.get(i).draw(g, field_size, Color.MAGENTA, Color.GRAY);
            else if(i < 33)
                this.fields.get(i).draw(g, field_size, Color.ORANGE, Color.GRAY);
            else
                this.fields.get(i).draw(g, field_size, Color.PINK, Color.GRAY);
        }
    }

    public void draw(Graphics2D g, Piece piece, int spot)
    {
        if(spot == FIRST)
        {
            g.setColor(piece.getColor());
            g.fillRect(piece.getField().getX(), piece.getField().getY(), field_size/2, field_size/2);
        }
        else if(spot == SECOND)
        {
            g.setColor(piece.getColor());
            g.fillRect(piece.getField().getX()+field_size/2, piece.getField().getY(), field_size/2, field_size/2);
        }
        else if(spot == THIRD)
        {
            g.setColor(piece.getColor());
            g.fillRect(piece.getField().getX(), piece.getField().getY()+field_size/2, field_size/2, field_size/2);
        }
        else if(spot == FOURTH)
        {
            g.setColor(piece.getColor());
            g.fillRect(piece.getField().getX()+field_size/2, piece.getField().getY()+field_size/2, field_size/2, field_size/2);
        }
    }

    public int roll()
    {
        return (fate.nextInt(5)+1);
    }

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
