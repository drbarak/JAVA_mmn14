
/**
 * Write a description of class MatrixList here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class MatrixList
{
    private IntNodeMat _m00, _tail = null;
    boolean p = false;
    /**
     * Constructor for objects of class MatrixList
     */
    public MatrixList(IntNodeMat m00)
    {
        if (_m00 != null) return;
        _m00 = m00;
    }
    public MatrixList(IntNodeMat m00, IntNodeMat tail)
    {
        if (_m00 != null) return;
        _m00 = m00;
        _tail = tail;
    }
    public MatrixList(int[][] mat)
    {
        if (mat == null) return;
        if (_m00 != null) return;
        if (mat.length == 0 || mat[0].length == 0) return; 
        IntNodeMat last = new IntNodeMat(mat[0][0]);
        _m00 = last;
        IntNodeMat prevRow = last, firstInRow = last;
        for (int i = 0; i < mat.length; i++)
        {
            for (int j = 0; j < mat[0].length; j++)
            {
                if (i == 0 && j == 0) continue;
                IntNodeMat cell = new IntNodeMat(mat[i][j]);
                if (j > 0)
                {
                    cell.setLeft(last);
                    last.setRight(cell);
                }
                else
                    firstInRow = cell;
                if (i > 0)
                {
                    if (p)
                    {
                        Print.p(i, j);
                        System.out.println(cell);
                        System.out.println(prevRow);
                    }
                    cell.setUp(prevRow);
                    prevRow.setDown(cell);
                    prevRow = prevRow.getRight();
                }
                last = cell;
            }
            last = prevRow = firstInRow;
        }
    }
    public int getData_i_j(int i, int j)
    {
        if (i < 0 || j < 0 || _m00 == null) return Integer.MIN_VALUE;
        IntNodeMat cell = _m00;
        for (int _i = 0; _i < i; _i++)
        {
            cell = cell.getDown();
            if (cell == null)
                return Integer.MIN_VALUE;
        }
        for (int _i = 0; _i < j; _i++)
        {
            cell = cell.getRight();
            if (cell == null)
                return Integer.MIN_VALUE;
        }
        return cell.getValue();
    }
    public void setData_i_j(int num, int i, int j)
    {
        if (i < 0 || j < 0 || _m00 == null) return;
        IntNodeMat cell = _m00;
        for (int _i = 0; _i < i; _i++)
        {
            cell = cell.getDown();
            if (cell == null)
                return;
        }
        for (int _i = 0; _i < j; _i++)
        {
            cell = cell.getRight();
            if (cell == null)
                return;
        }
        cell.setValue(num);
    }
    private int _findMax(IntNodeMat cell, IntNodeMat prevRow, int max)
    {
        max = Math.max(max, cell.getValue());
        cell = cell.getRight();
        if (cell == null)   // end of row
        {
            cell = prevRow.getDown();
            if (cell == null) return max;
            prevRow = cell;
        }
        return _findMax(cell, prevRow, max);
    }
    public int findMax()
    {
        return _findMax(_m00, _m00, -1);
    }
    int count = 0;
    private int __howManyX(IntNodeMat cell, int x, int num)
    {
        // if first column  or last row then no need to look further because all
        // numbers are larger: down and to the right number is larger 
        if (cell.getLeft() == null || cell.getDown() == null) return num;
        cell = cell.getLeft();
        cell = cell.getDown();
        return _howManyX(cell, x, num);
    }
    private int _howManyX(IntNodeMat cell, int x, int num)
    {
        int val = cell.getValue();
        System.out.println("" + val + ", " + x + ", " +  num + ", " +  ++count);
        if (x <= val)   // no need to continue looking in the row because no more possible finds
        {
            if (x == val) num++;
            return __howManyX(cell, x, num);
        }
        if (cell.getRight() == null)   // end of row
            return __howManyX(cell, x, num);
        cell = cell.getRight();
        return _howManyX(cell, x, num);
    }
    public int howManyX(int x)
    {
        count = 0;
        return _howManyX(_m00, x, 0);
    }
    public String toString()
    {
        IntNodeMat cell = _m00;
        IntNodeMat prevRow = cell;
        String s = "";
        while (cell != null)
        {
            if (p) System.out.println(cell);
            s += cell.getValue();
            cell = cell.getRight();
            if (cell == null)   // end of row
            {
                cell = prevRow.getDown();
                prevRow = cell;
                s += "\n";
            }
            else
                s += "\t";
        }
        return s;
    }
}
