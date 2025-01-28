package ממן_14;

import static Library.Print.p;

/**
 * A class to maintain a chained list represnted by a 2-dim rectangular array,
 * repersented by a matrix, of whole numbers.
 * Each cell in the matrix, represet a node in the list, and it has 4
 * connections for the possible 4 cells around it, in the up/down/left/right
 * directions.
 *
 * @author (Zvi Barak)
 * @version (17.01.2025)
 */
public class MatrixList
{
    IntNodeMat _m00;    // the head pf the list. pointing to the first cell (0,0) 
    
    /**
     * An empty constructor for objects of class MatrixList that initializes
     * the head of the matrix to null
     */
    public MatrixList()
    {
        _m00 = null;
    }
    /**
     * A constructor for objects of class MatrixList that receives a 2-dim
     * array of whole numbers and creates the chained list.
     * The constructor verifies the matrix is not null or empty, otherwise it
     * does not create the list.
     * It also verify the list does not already exists, otherwise it does not
     * create a new list (not overriding an existing list).
     * In addition, it verifies the input matrix is a rectangular, otherwise
     * it does not create the list.
     * 
     * @param   int[][] mat - a 2-dim matrix of whole numbers
     */
    public MatrixList(int[][] mat)
    {
        if (mat == null) return;    // verifies the input is not null
        if (mat.length == 0 || mat[0].length == 0) return; // verifies the input matrix is not empty
        if (_m00 != null) return;   // verifies the chain does not already exists
        
        IntNodeMat last = new IntNodeMat(mat[0][0]);    // gets cell (0,0)
        _m00 = last;
        IntNodeMat prevRow = last; // keep cell above
        IntNodeMat firstInRow = last; // keep first cell in a row
        for (int i = 0; i < mat.length; i++)
        {
            if (i > 0 && mat[0].length != mat[i].length)
            {
                _m00 = null;   // verifies the matrix is rectangular
                return;
            }
            for (int j = 0; j < mat[0].length; j++)
            {
                if (i == 0 && j == 0) continue; // skip cell (0,0) since already taken care of above
                IntNodeMat cell = new IntNodeMat(mat[i][j]); // get cell(i,j)
                if (j > 0)  // not first column so set left & right pointers
                {
                    cell.setPrevCol(last);
                    last.setNextCol(cell);
                }
                else
                    firstInRow = cell;
                if (i > 0)  // not first row so set up & down pointers
                {
                    cell.setPrevRow(prevRow);
                    prevRow.setNextRow(cell);
                    prevRow = prevRow.getNextCol();
                    
                }
                last = cell;
            }
            last = prevRow = firstInRow;
        }
    }
    
    /**
     * A method to get the value of a cell in the matrix.
     * 
     * @param   int i   - the parameter representing the row number of the cell.
     * @param   int j   - the parameter representing the column number of the cell.
     * 
     * @return  the value of the cell, or Integer.MIN_VALUE if no list, or invalid
     *                  input, or row out of bounds, or columd out of bounds.
     */
    public int getData_i_j(int i, int j)
    {
        if (_m00 == null) return Integer.MIN_VALUE;   // verifies the list exists
        if (i < 0 || j < 0) return Integer.MIN_VALUE;   // verifies the input is valid
        IntNodeMat cell = _m00;
        for (int _i = 0; _i < i; _i++)
        {
            cell = cell.getNextRow();
            if (cell == null) return Integer.MIN_VALUE; // out of bounds
        }
        for (int _i = 0; _i < j; _i++)
        {
            cell = cell.getNextCol();
            if (cell == null) return Integer.MIN_VALUE; // out of bounds
        }
        return cell.getData();
    }
    
    /**
     * A method to set the value of a cell in the matrix. If no list or invalid
     *    input, or row out of bounds, or columd out of bounds, it does nothing.
     * 
     * @param   int num - the new value to set in cell.
     * @param   int i   - the parameter representing the row number of the cell.
     * @param   int j   - the parameter representing the column number of the cell.
     * 
     */
    public void setData_i_j(int num, int i, int j)
    {
        if (_m00 == null) return;   // verifies the list exists
        if (i < 0 || j < 0) return;   // verifies the input is valid
        IntNodeMat cell = _m00;
        for (int _i = 0; _i < i; _i++)
        {
            cell = cell.getNextRow();
            if (cell == null) return; // out of bounds
        }
        for (int _i = 0; _i < j; _i++)
        {
            cell = cell.getNextCol();
            if (cell == null) return;  // out of bounds
        }
        //setValue(cell, num);
        cell.setData(num);
    }
    
    /**
     * A recursive method to find the largest value in the matrix. 
     *  If no list or empty list, the method returns Integer.MIN_VALUE.
     * 
     * @param   int j   - the argest value or Integer.MIN_VALUE.
     * 
     */
    public int findMax()
    {
        if (_m00 == null) return Integer.MIN_VALUE;   // verifies the list exists
        return findMax(_m00, _m00, Integer.MIN_VALUE); // starts at cell (0,0)
    }
    // A helper recursive method that finds the max between previou max and the
    // value of the current cell, and then moves right to the next cell in the
    // row, and calls again the method. This way it finds the max of the row.
    // When it reaches the end of the row (the right pointer of the cell is 
    // null) it goes down one row from the saved first cell of the row. It then
    // saves the frist cell of the row and calls again the method to go over
    // this row, as before. 
    // When no more rows, it finishes the matrix and returns the value of max.
    // Initially, the first call is (0,0) and because it is also the first cell
    // of the first row, it is also save in the firstCellCurRow which keeps
    // the node of the first cell in a row.
    private int findMax(IntNodeMat cell, IntNodeMat firstCellCurRow, int max)
    {
        max = Math.max(max, cell.getData());
        cell = cell.getNextCol();  // move right in the row
        if (cell == null)   // end of row
        {
            cell = firstCellCurRow.getNextRow();   // go to begining of next row
            if (cell == null) return max;
            firstCellCurRow = cell;
        }
        return findMax(cell, firstCellCurRow, max);
    }

    /**
     * A method to find how many times a given number appears in the matrix.
     *  If no list or empty list, the method returns Integer.MIN_VALUE.
     *  The matrix must have been sorted such that each row contains increasing
     *  numbers with no duplicates, and also each column contains increasing
     *  numbers with no duplicates.
     *  At the start of the method it verifies the matrix is sorted as specified 
     *  above, using a helper.
     * 
     * @param   int x  - the number to find its count of duplicates in the matrix.
     * @return  int   The number of times the number X is found in matrix or 0 if
     *                not found, or is empty or Integer.MIN_VALUE if the
     *                matrix is not sorted as expected.
     *
     * The worst case is when the number is equal to the value of the last
     * cell in the last row-1, and then it is the first number in the last row,
     * since then the search is over the full first row = n, and then down the
     * next rows from the first to last row-1, where it find the first occurence
     * of x. It then moves left a column and down to the next row, which is the 
     * last row. So it moved down m-2 rows, then moved left and down
     * to the next row, and then left to the begining of the row, for another 
     * n-1 cells. Total of n + m - 2  + n - 1 = 2n + m - 3.
     * 
     * Time complexity of O(2n+m-3) -> O(n) or O(m) if m > 2n.
     * Space complexity of 1+1+1+1+1+1=6 -> O(1)
     */
    
    private final int MOVE_LEFT = 0;
    private final int MOVE_RIGHT = 1;
    private final int MOVE_DOWN = 2;
    
    public int howManyX(int x)
    {
        if (_m00 == null) return 0;   // verifies the list exists
        // cannot do a binary search on the first row because we do know its
        // length and even if we knew the length it would not have save steps
        // since to get to the mid cell requires moving sequentailly from the
        // begining of the row till that cell, and the same for the other 
        // sub-sections.
        IntNodeMat cell = _m00;
        IntNodeMat firstCellCurRow = cell;
        IntNodeMat prevCell = cell;
        int curValue = cell.getData();
          // in order to verify did not visit the cell already and that
          // the matrix is valid (each cell to right or down of current cell
          // has larger value)
        int prevValue = curValue;
        int numX = 0;
        boolean moveLeft = false;   // are we moving left or right in a row
        int direction = -1; // keep the last direction moved
        while (cell != null)
        {
            if (x > curValue)   // need to move right to the next cell
            {
                prevCell = cell;
                cell = (moveLeft ? cell.getPrevCol() : cell.getNextCol());
                direction = (moveLeft ? MOVE_LEFT : MOVE_RIGHT);
                if (cell != null)
                {
                    curValue = cell.getData();
                    if (inValid(curValue, prevValue, direction))
                        return Integer.MIN_VALUE;
                    prevValue = curValue;
                }
                else // end of a row so move to the next row, 
                {   // but first make sure the last number in the next row is 
                    // smaller then x, because otherwise there is no need to
                    // serach this row because of the numbers there are smaller
                    // then x, and thus we move to the next row
                    cell = prevCell.getNextRow();
                    direction = MOVE_DOWN;
                    firstCellCurRow = null;
                    if (cell != null)
                    {
                        curValue = cell.getData();
                        if (inValid(curValue, prevValue, direction))
                            return Integer.MIN_VALUE;
                        prevValue = curValue;
                        while (cell != null && x >= curValue)
                        {
                            if (x == curValue)
                            {
                                numX++;
                                cell = cell.getPrevCol();
                                direction = MOVE_LEFT;
                                if (cell != null)
                                {
                                    curValue = cell.getData();
                                    if (inValid(curValue, prevValue, direction))
                                        return Integer.MIN_VALUE;
                                    prevValue = curValue;
                                }
                            }
                            if (cell != null)
                            {
                                cell = cell.getNextRow();
                                direction = MOVE_DOWN;
                                firstCellCurRow = null;
                                if (cell != null)
                                {
                                    curValue = cell.getData();
                                    if (inValid(curValue, prevValue, direction))
                                        return Integer.MIN_VALUE;
                                    prevValue = curValue;
                                }
                            }
                        // the next cell at the next row but to left of the 
                        // current column because the cell just below the current cell
                        // its value is larger than the value of the current cell
                        }
                    }
                    if (cell == null)
                        return numX; // end of matrix
                    // found a row that x is not larger than all the numbers to the 
                    // left of the current column in that row so now search backward to
                    // find the number that eqaul or less than x
                    moveLeft = true;
                }
            }
            else // x smaller or equal curValue
            {
                if (x == curValue)  // found occurence of x
                {
                    numX++;
                    if (moveLeft) // no more numbers larger equal than x so go to next row
                    {
                        cell = cell.getNextRow();
                        direction = MOVE_DOWN;
                        if (cell != null)
                        {
                            curValue = cell.getData();
                            if (inValid(curValue, prevValue, direction))
                                return Integer.MIN_VALUE;
                            prevValue = curValue;
                        }
                    }
                }                    
                    // on the first row of the matrix,
                    // if the current cell is the first in the row and x <= curValue
                    // then since the value of the cell in the next row is larger than
                    // the value of the current cell, it means it is also larger
                    // than x and there is no use in looking further in the matrix
                if (!moveLeft && cell == firstCellCurRow)
                    break;
                    // x <= curValue so no need to look further in this row
                if (moveLeft)
                {
                    if (cell != null) 
                        cell = cell.getPrevCol();
                    if (cell != null)
                    {
                        curValue = cell.getData();
                        direction = MOVE_LEFT;
                        if (inValid(curValue, prevValue, direction))
                            return Integer.MIN_VALUE;
                        prevValue = curValue;
                    }
                }
                else
                {
                    cell = cell.getNextRow();
                    direction = MOVE_DOWN;
                    firstCellCurRow = null;
                    // look for number less or equal x by moving let, from
                    // large number to a smaller number than x
                    while (cell != null && x <= cell.getData())
                    {
                        curValue = cell.getData();
                        if (inValid(curValue, prevValue, direction))
                            return Integer.MIN_VALUE;
                        prevValue = curValue;
                        if (x == curValue)
                        {
                            numX++;
                            cell = cell.getPrevCol();
                            direction = MOVE_LEFT;
                            if (cell != null)
                            {
                                curValue = cell.getData();
                                if (inValid(curValue, prevValue, direction))
                                    return Integer.MIN_VALUE;
                                prevValue = curValue;
                                cell = cell.getNextRow();
                                direction = MOVE_DOWN;
                            }
                            // the next cell at the next row but to left of the 
                            // current column because the cell just below the current cell
                            // its value is larger than the value of the current cell
                        }
                        else
                        {
                            cell = cell.getPrevCol();
                            direction = MOVE_LEFT;
                            prevValue = curValue;
                        }
                    }
                    if (cell == null)
                        return numX; // end of matrix
                    // found a row that x is not larger than all the numbers to the 
                    // left of the current column in that row so now search backward to
                    // find the number that eqaul or less than x
                    moveLeft = true;
                    curValue = cell.getData();
                    if (inValid(curValue, prevValue, direction))
                        return Integer.MIN_VALUE;
                    prevValue = curValue;
                }
            }
            if (cell != null)
                curValue = cell.getData();
        }
        return numX;
    }
    // A helper method to verify the matrix is built properly such that
    // each move to the right or down moves to a cell whose value is greater
    // than the previous one and moving to the left moves to a cell whose 
    // value is smaller and than the previous one (no check for up because
    // the method does not use that direction).
    // Doing these checks also gaurantees that we do not move to a cell
    // we visited already, becuase it's value will be incosistent with the
    // above checks, because it's value is smaller than the current value.
    private boolean inValid(int curValue, int prevValue, int direction)
    {
        switch (direction)
        {
            case MOVE_LEFT:  // left cell must be smaller
                if (curValue >= prevValue) return true;
                break;
            case MOVE_RIGHT:
            case MOVE_DOWN: // right or down cell must be larger
                if (curValue <= prevValue) return true;
                break;
            default:
                return true;
        }
        return false;
    }
    
    /**
     *  A method that returns a string that represents this matrix.
     *  
     *  @return     a String that represents this matrix in the following 
     *            format: the columns are separated by a tab "\t", thw rows
     *                  are separated by a newline "\n", including after the
     *                  last row.
     *            If the matrix is empty or null, and empty string is returned.
     *              
     */
    public String toString()
    {
        IntNodeMat cell = _m00;
        IntNodeMat firstCellCurRow = cell;
        String s = "";
        while (cell != null)
        {
            s += cell.getData();
            cell = cell.getNextCol();
            if (cell == null)   // end of row
            {
                cell = firstCellCurRow.getNextRow();
                firstCellCurRow = cell;
                s += "\n";
            }
            else
                s += "\t";
        }
        return s;
    }
}// end of class MatrixList
