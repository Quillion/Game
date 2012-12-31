import java.awt.Graphics2D;
import java.awt.Color;
import java.util.List;
import java.util.ArrayList;

public class Field
{
    private List<Field> fields;
    private int id;
    private int x, y;

    public Field()
    {
        this.fields = new ArrayList<Field>();
        this.id = 0;
        this.x = 0;
        this.y = 0;
    }

    // creates connection between this field and another field
    public boolean addField(Field field)
    {
        for(int i = 0; i < fields.size(); i++)
            if(fields.get(i).getId() == field.getId())
                return false;
        if(this.id == field.getId())
            return false;
        fields.add(field);
        return true;
    }

    // returns instance of this field
    public Field getField(int num)
    {
        return this.fields.get(num);
    }

    // returns number of fields connected to this one
    public int getFieldSize()
    {
        return this.fields.size();
    }

    // sets the id for this field
    public boolean setId(int id)
    {
        for(int i = 0; i < fields.size(); i++)
            if (fields.get(i).getId() == id)
                return false;
        this.id = id;
        return true;
    }

    // returns id of this field
    public int getId()
    {
        return this.id;
    }

    public int getX()
    {
        return this.x;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public int getY()
    {
        return this.y;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public void draw(Graphics2D g, int size, Color outline, Color fill)
    {
        g.setColor(fill);
        g.fillRect(this.x, this.y, size, size);
        g.setColor(outline);
        g.drawRect(this.x, this.y, size, size);
        g.drawString(this.id+"", this.x, this.y);
    }
}
