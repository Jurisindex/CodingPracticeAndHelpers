package examSources.facebook;

import java.sql.Array;
import java.util.*;

public class Puzzles {
    public double getHitProbability(int R, int C, int[][] G) {
        double probability = 0.0;
        for(int i = 0; i < G.length; i++)
        {
            for(int j = 0; j < G[0].length; j++)
            {
                probability += G[i][j];
            }
        }
        return probability/(R*C);
    }

    public long getMaxAdditionalDinersCount(long N, long K, int M, long[] S) {
        //        N = 15
        //        K = 2
        //        M = 3
        //        S = [11, 6, 14]
        //000001000010010

        // Write your code here
        long twoKGaps = 0L;
        int[] onesRep = new int[(int) N];
        ArrayList<Integer> spaces = new ArrayList<>();

        //xK gap
        //x/k + (x%k > 0 ? 1 : 0);
        //1
        //00000
        int runningCount = 0;
        for(int i = 0; i<M; i++)
        {
            int seatNumber = (int)(S[i]-1L);
            //first-pass S and fill out every K slots to also be filled
            //000000000001000000100000001 // K=3
            //000000001111111111111101111
            //you like this for-loop
            for(int j = (int) Math.min(N-1,seatNumber+K); j >= Math.max(0,seatNumber-K); j--)
            {
                onesRep[j] = 1;
            }
         }

        for(int i = 0; i<onesRep.length; i++)
        {
            //2nd-pass index spaces, and then do simple math to get how many more
            //000000001111111111111101111
            //spaces = [8,1]
            if(onesRep[i] == 0)
            {
                runningCount++;
            }
            else
            {
                if(runningCount > 0)
                {
                    spaces.add(runningCount);
                    runningCount = 0;
                }
            }
        }
        if(runningCount > 0) {
            spaces.add(runningCount);
        }

        for(Integer i : spaces)
        {
            twoKGaps += i/(K+1) + (i%(K+1) > 0 ? 1 : 0);
        }
        return twoKGaps;
    }
}
