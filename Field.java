import java.util.List;
import java.util.ArrayList;

public class Field
{
    private List<Field> fields;
    private int id;

    public Field()
    {
        this.fields = new ArrayList<Field>();
        this.id = 0;
    }

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

    public Field getField(int num)
    {
        return this.fields.get(num);
    }

    public int getFieldSize()
    {
        return this.fields.size();
    }

    public boolean setId(int id)
    {
        for(int i = 0; i < fields.size(); i++)
            if (fields.get(i).getId() == id)
                return false;
        this.id = id;
        return true;
    }

    public int getId()
    {
        return this.id;
    }
}
