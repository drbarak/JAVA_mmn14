public class MatrixListTest
{
    // method to test the class
    public static void main(String[] args)
    {
        int [][] m = new int[][]{{1,2,3,4},{5,6,7,8},{9,10,11,12}};
        //m = new int[][]{{1,2,3,4,-1},{5,6,7,8,-2},{90,10,11,12,-100}};
        //m = new int[1][0];
        m = new int[][]{{1,2,3,4},{2,3,4,5},{3,10,11,12}};
        m = new int[][]{{1,2,3,4},{2,4,16,18},{3,5,17,19}};
        MatrixList ml = new MatrixList(m);
        System.out.print(ml);
        int run = 1;//1000+100+10+1;
        if (run >= 1000)
        {
            Print.p("------ get ------");
            for (int i = 0; i <= m.length; i++)
            {
                for (int j = 0; j <= m[0].length; j++)
                {
                    Print.p(ml.getData_i_j(i,j));
                }
            }
        }
        if (run % 1000 >= 100)
        {
            Print.p("\n------ set ------");
            for (int i = 0; i <= m.length; i++)
            {
                for (int j = 0; j <= m[0].length; j++)
                {
                    ml.setData_i_j(i * 10 + j, i,j);
                    Print.p(ml.getData_i_j(i,j));
                }
            }
        }
        if (run % 100 >= 10)
        {
            Print.p("\n------ max ------");
            Print.p(ml.findMax());
        }
        if (run % 10 >= 1)
        {
            Print.p("\n------ how many ------");
            Print.p(ml.howManyX(0));
            Print.p(ml.howManyX(1));
            Print.p(ml.howManyX(2));
            Print.p(ml.howManyX(3));
            Print.p(ml.howManyX(16));
        }
    }
}
