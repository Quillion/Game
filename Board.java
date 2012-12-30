import java.util.List;
import java.util.ArrayList;

public class Board
{
    private List<Field> fields;
    private int id_counter;

    public Board()
    {
        this.fields = new ArrayList<Field>();
        this.id_counter = 0;
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

    public boolean connect(int id_a, int id_b)
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
            return true;
        }
        return false;
    }
}
