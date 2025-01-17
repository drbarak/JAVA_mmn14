package ממן_14;

import static Library.Print.p;

public class MatrixListTest
{
    // method to test the class
    public static void main()
    {
        int [][] m = new int[][]{{1,2,3,4},{5,6,7,8},{9,10,11,12}};
        //m = new int[][]{{1,2,3,4,-1},{5,6,7,8,-2},{90,10,11,12,-100}};
        //m = new int[1][0];
        m = new int[][]{{1,2,3,4},{2,3,4,5},{3,10,11,12}};
        m = new int[][]{{1,2,3,4},{2,4,16,18},{3,5,17,19}};
        m = new int[][]{{1,2,3,4},{2,16,17,18},{3,5,17,19}};
        m = new int[][]{{1,2,3,4},{2,3,17,18},{3,17,18,19}};
        //m = new int[][]{{1,2,3,4},{2,16,17,8},{3,17,-18,-19}};
        //m = new int[][]{{1,2,3,4},{2,16,17,18},{3,17,18}}; // not a rectangular
        //m = new int[][]{};
        int x = 3, y = 10;
        for (int i=0; i<x; i++)
        {
            m[i] = new int[y];
            for (int j=0; j < y; j++)
                m[i][j] = i + j;
        }
        MatrixList ml = new MatrixList(m);
        System.out.print(ml);   // used toString[] of the class MatrixList
        int run = 1;//1000+100+10+1;
        if (run >= 1000)
        {
            p("------ get ------");
            int i = 0, j = 0;
            for (; i < m.length; i++)
            {
                for (j = 0; j < m[0].length; j++)
                {
                    p(i, j, ml.getData_i_j(i,j));
                }
            }
            i = -1; j = 1; p(i, j, ml.getData_i_j(i,j));
            i = 1; j = -1; p(i, j, ml.getData_i_j(i,j));
            i = 10; j = 1; p(i, j, ml.getData_i_j(i,j));
            i = 1; j = 10; p(i, j, ml.getData_i_j(i,j));
        }
        if (run % 1000 >= 100)
        {
            p("\n------ set ------");
            int i = 0, j = 0;
            for (; i < m.length; i++)
            {
                for (j = 0; j < m[0].length; j++)
                {
                    ml.setData_i_j(i * 10 + j, i,j);
                    p(i, j, ml.getData_i_j(i,j));
                }
            }
            i = -1; j = 1; ml.setData_i_j(i * 10 + j, i,j); p(i, j, ml.getData_i_j(i,j));
            i = 1; j = -1; ml.setData_i_j(i * 10 + j, i,j); p(i, j, ml.getData_i_j(i,j));
            i = 10; j = 1; ml.setData_i_j(i * 10 + j, i,j); p(i, j, ml.getData_i_j(i,j));
            i = 1; j = 10; ml.setData_i_j(i * 10 + j, i,j); p(i, j, ml.getData_i_j(i,j));
            
        }
        if (run % 100 >= 10)
        {
            p("\n------ max ------");
            p(ml.findMax());
        }
        if (run % 10 >= 1)
        {
            p("\n------ how many ------");
            p("The number of times the number -1 appears in the matrix is "+ ml.howManyX(-1));
            p("The number of times the number 0 appears in the matrix is "+ ml.howManyX(0));
            p("The number of times the number 1 appears in the matrix is "+ ml.howManyX(1));
/*            
            p(ml.howManyX(2));
            p(ml.howManyX(3));
            p(ml.howManyX(16));
            p(ml.howManyX(15));
*/            
            p("The number of times the number 8 appears in the matrix is "+ ml.howManyX(8));
            p("The number of times the number 20 appears in the matrix is "+ ml.howManyX(20));
        }
    }
    /* method howManyX using recursion and no restrictions on values of the
     * matrix, ie. not sorted in any way. Thus, simply goes over the whole
     * matrix.
     * However, the assignment assumes the matix is sorted and looks for the
     * most effecient way to find the result.
     */
    /*
    int count = 0;
    public int howManyX(int x)
    {
        count = 0;
        if (_m00 == null) return Integer.MIN_VALUE;;   // verifies the list exists
        return howManyX(_m00, x, 0);
    }
    
    private int howManyX_nextCell(IntNodeMat cell, int x, int num)
    {
        // if first column or last row then no need to look further because all
        // numbers are larger: down and to the right number is larger 
        if (getLeft(cell) == null || getDown(cell) == null) return num;
        cell = getDown(cell);  // next row
        // move to the left in the row untill find anumber lower or equal
        while (getLeft(cell) != null && getValue(cell) > x)
            cell = getLeft(cell);
        return howManyX(cell, x, num);
    }
    
    private int howManyX(IntNodeMat cell, int x, int num)
    {
        int val = getValue(cell);
        p("X=" + x + ", val=" + val + ", num=" +  num + ", count=" +  ++count);
        if (x <= val)   // no need to continue looking in the row because no more possible finds
        {
            if (x == val) num++;
            return howManyX_nextCell(cell, x, num);
        }
        if (getRight(cell) == null)   // end of row
            return howManyX_nextCell(cell, x, num);
        cell = getRight(cell);
        return howManyX(cell, x, num);
    }
    */
}
