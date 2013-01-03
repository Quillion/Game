/**
 * @author      Edgar Ghahramanyan <edgarquill@gmail.com>
 * @version     Version 1
 * @since       1.6
 */

import java.util.List;
import java.util.ArrayList;
import java.awt.Color;
import java.lang.Boolean;

public class Piece
{
    // CONSTANTS
    private static int BANK_PAYMENT = 500;
    private static int SHOP_MULTIPLIER = 50;

    // PRIVATE VALUES
    private int last_id;
    private Field field;
    private Color color;
    private int wallet;
    private List<Boolean> visited;
    private List<Field> shops;

    /*
     * Initializer for the piece.
     * Use default values if you would like, doesn't matter.
     */
    public Piece()
    {
        this.field = null;
        this.last_id = -1;
        this.color = Color.BLACK;
        this.wallet = 1500;
        this.visited = new ArrayList<Boolean>();
        this.shops = new ArrayList<Field>();
    }

    /*
     * Sets the last id of the field visited.
     * Used to determine which way the pieces can move.
     * @param id id of the last field the piece was standing on, or -1 for reset.
     */
    public void setLastId(int id)
    {
        this.last_id = id;
    }

    /*
     * Returns the id of the field on which this piece was last on.
     * @return id of the last field this piece was on.
     */
    public int getLastId()
    {
        return this.last_id;
    }

    /*
     * Sets the field on which the player is currently on.
     * Last id becomes the field on which the player was previously on before moving to this one.
     * @param field, the new field to which this piece is moving to.
     */
    public void setField(Field field)
    {
        if(this.getField() != null)
            this.setLastId(this.getField().getId());
        this.field = field;
    }

    /*
     * Returns the field on which this player is currently standing on.
     * @return field on which the player is standing on.
     */
    public Field getField()
    {
        return this.field;
    }

    /*
     * Sets the color of this piece to the passed color.
     * Once again I beg whoever is reading this code.
     * If you can make better graphics I will be very thankful.
     * @param color color to which to set the color of this piece to.
     */
    public void setColor(Color color)
    {
        this.color = color;
    }

    /*
     * Returns the color of this piece. Please there has to be a better way.
     * @return color of this piece.
     */
    public Color getColor()
    {
        return this.color;
    }

    /*
     * Sets the wallet to a certain amount of money.
     * @param wallet the amount to which to set the current money to.
     */
    public void setWallet(int wallet)
    {
        this.wallet = wallet;
    }

    /*
     * Adds a certain amount of money to the wallet.
     * Just pass negative number for substraction.
     * @param amount the amount of money you would like to add or substract from the wallet.
     */
    public void addMoney(int amount)
    {
        this.wallet += amount;
    }

    /*
     * Returns how much money the player currently possesses.
     * @return the amount of money this player has in his wallet.
     */
    public int getWallet()
    {
        return this.wallet;
    }

    /*
     * This piece has visited the bank.
     * If he visited all the neccessary fields then he will money.
     */
    public void bank()
    {
        if(this.allVisited())
            this.wallet += (this.BANK_PAYMENT + this.shopsOwned() * this.SHOP_MULTIPLIER);
    }

    /*
     * Returns the size of the visited fields we have.
     * @return the amount of visited spaces the player has to aquire.
     */
    public int getVisitedSize()
    {
        return this.visited.size();
    }

    /*
     * Sets the size of how many fields the player has to visit.
     * @param size how many visited spaces there are on the field.
     */
    public void setVisitedSize(int size)
    {
        for(int i = 0; i < size; i++)
            this.visited.add(new Boolean(false));
    }

    /*
     * Sets the visited to true.
     * If the visited field does exist then it does nothing and returns false.
     * @param what visited to set true to.
     * @return true if the visited value was changed to true, false otherwise.
     */
    public boolean setVisited(int place)
    {
        if(!(place < this.getVisitedSize()) || (place < 0))
            return false;
        this.visited.set(place, new Boolean(true));
        return true;
    }

    /*
     * Rsets all the visited values to false.
     * Is used once you pass the bank.
     */
    public void resetVisited()
    {
        for(int i = 0; i < this.getVisitedSize(); i++)
            this.visited.add(new Boolean(false));
    }

    /*
     * Returns the value that a certain visited place has.
     * If the field does not exist false is returned.
     * @param place which position of visited space to check.
     * @return true if boolean value for visited is true, and false for any other occasion.
     */
    public boolean getVisited(int place)
    {
        if(!(place < this.getVisitedSize()) || (place < 0))
            return false;
        return this.visited.get(place).booleanValue();
    }

    /*
     * Tells if the user has visited all of the spaces required.
     * @return true if all the spaces have been visited, false otherwise.
     */
    public boolean allVisited()
    {
        for(int i = 0; i < this.getVisitedSize(); i++)
        {
            if(!this.getVisited(i))
                return false;
        }
        return true;
    }

    /*
     * Add the current shop as a shop owned by this piece.
     * @param field field which you would like added as this piece's property.
     */
    public void addShop(Field field)
    {
        this.shops.add(field);
        field.setColor(this.color);
    }

    /*
     * Removes the specified field from being owned by a player.
     * If id is wrong then no shop will be removed.
     * @param id id of the field which you want to remove from the piece's ownership.
     */
    public void removeShop(int id)
    {
        for(int i = 0; i < shops.size(); i++)
            if(this.shops.get(i).getId() == id)
                this.shops.remove(i);
    }

    /*
     * Returns an int which represents how many fields the player owns.
     * @return how many stores the piece owns.
     */
    public int shopsOwned()
    {
        return this.shops.size();
    }
}
