/**
 * @author      Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version     Version 1
 * @since       1.6
 */

import java.awt.Graphics2D;
import java.awt.Color;
import java.util.List;
import java.util.ArrayList;
import java.lang.Math;

public class Field
{
    // CONSTANTS
    public static int NONE = -1;
    public static int BANK = 0;
    public static int SHOP = 1;
    public static int EVENT = 2;
    public static int VISIT = 3;

    private static int TAX_DIVISOR = 10;
    private static int INVESTMENT_DIVISOR = 5;
    private static int BUYOUT_PRICE = 5;

    // PRIVATE VALUES
    private List<Field> fields;
    private int id;
    private int x, y;
    private int type;
    private int price;
    private int investment;
    private Color color;

    // THIS WILL BE USED AS GARBAGE INT FOR WHATEVER
    public int garbage;

    /*
     * Initializer for the field.
     * Best not to use the default values.
     */
    public Field()
    {
        this.fields = new ArrayList<Field>();
        this.id = 0;
        this.x = 0;
        this.y = 0;
        this.type = NONE;
        this.price = 0;
        this.investment = 0;
        this.color = Color.GRAY;
    }

    /*
     * Creates connection between two supplied fields.
     * Will not work if you try to add field that is already connected to it.
     * Also will not work if you try to add this field to itself.
     * @param field, field object to which to connect this object.
     * @return true if the fields connected, false otherwise.
     */
    public boolean addField(Field field)
    {
        // CHECK TO SEE IF THE FIELD IS NOT ALREADY CONNECTED
        for(int i = 0; i < this.getFieldSize(); i++)
            if(this.fields.get(i).getId() == field.getId())
                return false;
        // CHECK TO SEE IF THE FIELD IS NOT THE SAME FIELD
        if(this.getId() == field.getId())
            return false;
        // FIELDS WERE NOT THE SAME, SO ADD THEM
        this.fields.add(field);
        return true;
    }

    /*
     * Returns field that is connected to this field at position num
     * @param num, connected field are stored in the list, return num position of the field.
     * @return field if it exists, null otherwise.
     */
    public Field getField(int num)
    {
        if(num < this.getFieldSize() || num >= 0)
            return this.fields.get(num);
        else
            return null;
    }

    /*
     * Returns int that represents how many fields are connected to this one.
     * @return int that represents how many fields are connected to this one.
     */
    public int getFieldSize()
    {
        return this.fields.size();
    }

    /*
     * Sets the id of this field to the value passed.
     * Id will not be set if the any of the connected fields have the same id.
     * @param id id to which to set this field to.
     * @return true if the id was set, false otherwise.
     */
    public boolean setId(int id)
    {
        // CHECK TO SEE IF CONNECTED FIELDS HAVE THE SAME ID
        for(int i = 0; i < this.getFieldSize(); i++)
            if (this.fields.get(i).getId() == id)
                return false;
        // CONNECTED FIELDS DID NOT HAVE THE SAME ID, THEREFORE SET ID OF THIS FIELD TO THE VALUE PASSED
        this.id = id;
        return true;
    }

    /*
     * Returns id that this field is set to.
     * @return id that this field is set to.
     */
    public int getId()
    {
        return this.id;
    }

    /*
     * Sets the type of the field to the values passed.
     * Types are the constants defined for the field class.
     * @param type type to which to set the type of this field to.
     */
    public void setType(int type)
    {
        this.type = type;
    }

    /*
     * Returns the type to which this field is set to.
     * @return int value that is representation of what type this field is.
     */
    public int getType()
    {
        return this.type;
    }

    /*
     * Sets the price of this field to whatever you want.
     * Price can be used to represent visit value, if type of field is visit.
     * @param price value to which to set the price of this field to.
     * @return true if the price was set, false otherwise if price is less than 0.
     */
    public boolean setPrice(int price)
    {
        if(price < 0)
            return false;
        this.price = price;
        return true;
    }

    /*
     * Returns the price of this field.
     * @return value that represents price of this field.
     */
    public int getPrice()
    {
        return this.price;
    }

    /*
     * Returns buyout price of this field.
     * It is usually the price of the field times by buyout multiplier.
     * @return how much it would cost to buyout this store from another player.
     */
    public int getBuyoutPrice()
    {
        return ((this.getPrice()+this.getInvestment())*this.BUYOUT_PRICE);
    }

    /*
     * Returns what tax the player that landed on this field has to pay.
     * @return tax that needs to be paid for landing on this field.
     */
    public int getTax()
    {
        return Math.round(this.getPrice()/this.TAX_DIVISOR)+ Math.round(this.getInvestment()/this.INVESTMENT_DIVISOR);
    }

    /*
     * Sets the investment to whatever amount is passed.
     * Investment determines buyout price and the tax of the property.
     * @param amount how much to set investment to.
     */
    public void setInvestment(int amount)
    {
        this.investment = amount;
    }

    /*
     * Increments the investment to whatever amount is passed.
     * Investment determines buyout price and tax of the property.
     * @param how much to increment investments by.
     */
    public void addInvestment(int amount)
    {
        this.investment += amount;
    }

    /*
     * Returns how much is invested in this property.
     * Not sure why you would even want this function.
     * @return how much is invested in this field.
     */
    public int getInvestment()
    {
        return this.investment;
    }

    /*
     * Sets the color of this field to whatever is passed. Used for drawing.
     * For the love of god, if someone uses this code, and has better design skills than me,
     * can you please add better graphics?
     * @param color color to which to set this field to.
     */
    public void setColor(Color color)
    {
        this.color = color;
    }

    /*
     * Returns what color this field is, can be used to see if the field is owned or not.
     * Usually Color.GRAY means the field is not owned, everything else means it is owned.
     * @return color to which this field is set to.
     */
    public Color getColor()
    {
        return this.color;
    }

    /*
     * Returns a boolean value representing whether or not the field is owned.
     * It simply compares the color. Not the best method, but oh well.
     * @return true if field is owned, false otherwise.
     */
    public boolean isOwned()
    {
        return !(this.getColor() == Color.GRAY);
    }

    /*
     * Returns x value where the field will be. Used for drawing.
     * This is just horrible. Please won't someone make better graphics?
     * @return x coordinate of the field.
     */
    public int getX()
    {
        return this.x;
    }

    /*
     * Sets the x coordinate of the field to the passed value.
     * @param x the value to which to set the x coordinate to.
     */
    public void setX(int x)
    {
        this.x = x;
    }

    /*
     * Returns the y coordinate of the field.
     * I just feel terrible about this.
     * @return the y coordinate of this field.
     */
    public int getY()
    {
        return this.y;
    }

    /*
     * Sets the y coordinate of this field to the passed value.
     * @param y value to which to set the y coordinate to.
     */
    public void setY(int y)
    {
        this.y = y;
    }

    /*
     * Draws the field with specified size and outline to the graphics passed to it.
     * @param g graphics to which the field will be drawn to.
     * @param size widht and height of the said field which will be drawn.
     * @param outline which color the outline of the field will be colored in.
     */
    public void draw(Graphics2D g, int size, Color outline)
    {
        g.setColor(this.getColor());
        g.fillRect(this.getX(), this.getY(), size, size);
        g.setColor(outline);
        g.drawRect(this.getX(), this.getY(), size, size);
    }

    /*
     * Draws the info assiciated with this field to the screen.
     * All the info is printed within the square given the minimum field size of 40 pixels.
     * @param g graphics to which the info will be printed to.
     */
    public void drawInfo(Graphics2D g)
    {
        // IF TYPE IS BANK
        if(this.getType() == BANK)
            g.drawString("BANK", this.getX()+5, this.getY()+25);
        // IF TYPE IS SHOP
        else if(this.getType() == SHOP)
        {
            // IF SHOP IS OWNED
            if(this.isOwned())
            {
                if(this.getInvestment() > 0)
                {
                    g.drawString(this.getTax()+"", this.getX()+5, this.getY()+20);
                    g.drawString(this.getInvestment()+"", this.getX()+5, this.getY()+30);
                }
                else
                    g.drawString(this.getTax()+"", this.getX()+5, this.getY()+25);
            }
            // SHOP HAS NO OWNER
            else
                g.drawString(this.getPrice()+"", this.getX()+5, this.getY()+25);
        }
        // IF TYPE IS VISIT
        else if(this.getType() == VISIT)
        {
            g.drawString("VISIT", this.getX()+5, this.getY()+15);
            g.drawString((this.getPrice()+1)+"", this.getX()+15, this.getY()+30);
        }
        // IF TYPE IS EVENT
        else if(this.getType() == EVENT)
            g.drawString("EVENT", this.getX()+1, this.getY()+25);
    }
}
