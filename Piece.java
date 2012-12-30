public class Piece
{
    private int last_id;
    private Field field;

    public Piece()
    {
        field = null;
        this.last_id = -1;
    }

    public void setLastId(int id)
    {
        this.last_id = id;
    }

    public int getLastId()
    {
        return this.last_id;
    }

    public void setField(Field field)
    {
        if(this.field != null)
            this.last_id = this.field.getId();
        this.field = field;
    }

    public Field getField()
    {
        return this.field;
    }
}
