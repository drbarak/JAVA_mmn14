
/**
 * Write a description of class IntNodeMat here.
 *
 * @author (Zvi Barak ID: 050982479)
 * @version (30.11.2024)
 */
public class IntNodeMat
{
    private int _value;
    // the neighbours of the IntNodeMat object
    private IntNodeMat _up, _down, _left, _right;

    /**
     * Constructors for objects of class IntNodeMat
     */
    public IntNodeMat(IntNodeMat other)   // copy an existing IntNodeMat object
    {
        if (other == null) return;
        _up = other._up;
        _down = other._down;
        _left = other._left;
        _right = other._right;
        _value = other._value;
    }
    public IntNodeMat(int value)   // initialise a default IntNodeMat
    {
        _up = _down = _left = _right = null;
        _value = value;
    }
    public IntNodeMat()   // initialise a default IntNodeMat
    {
        _up = _down = _left = _right = null;
        _value = Integer.MIN_VALUE;
    }
    public int getValue()
    {
        return _value;
    }
    public IntNodeMat getUp()
    {
        return _up;
    }
    public IntNodeMat getDown()
    {
        return _down;
    }
    public IntNodeMat getLeft()
    {
        return _left;
    }
    public IntNodeMat getRight()
    {
        return _right;
    }
    
    /**
     * Method to set the x coordinate of the IntNodeMat, if it is valid
     *
     * @param   x   the new x coordinate to set
     */
    public void setValue(int value)
    {
        _value = value;
    }
    public void setUp(IntNodeMat other)
    {
        _up = other;
    }
    public void setDown(IntNodeMat other)
    {
        _down = other;
    }
    public void setLeft(IntNodeMat other)
    {
        _left = other;
    }
    public void setRight(IntNodeMat other)
    {
        _right = other;
    }
    /**
     * Method to prepare the IntNodeMat for printing
     *
     * @return String the IntNodeMat in the format "(_x,_y)"
     */
    public String toString()
    {
        return "(" + _value + ")";
    }
} // end of class IntNodeMat
