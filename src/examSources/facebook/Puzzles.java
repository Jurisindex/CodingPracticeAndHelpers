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
        Map<Long, Long> spaces = new HashMap<>();
        spaces.put(0L, N - 1L);

        //This is our answer. But we get ahead of ourselves.
        long dinersWecanAdd = 0L;

        //For every diner that exists
        for(int i = 0; i<M; i++)
        {
            long seatNumber = S[i]-1L;
            long floor = Math.max(0L,seatNumber-K);
            long ceil = Math.min(N-1L,seatNumber+K);

            spaces = getNewSpaceAdjustment(spaces, floor, ceil);
        }

        //Add every space's customer potential (one chair at 3. 1 customer. 3 chairs at 6, 2 customers (K=1)
        for (Map.Entry<Long, Long> entry : spaces.entrySet()) {
            long currentLengthSpace = entry.getValue()-entry.getKey() + 1L; //4-5 is 2 spaces, since 5-4 +1 = 2
            dinersWecanAdd += currentLengthSpace/(K+1L) + (currentLengthSpace%(K+1L) > 0L ? 1L : 0L);
        }
        return dinersWecanAdd;
    }

    private Map<Long, Long> getNewSpaceAdjustment(Map<Long, Long> spaces, long floor, long ceil) {
        Map<Long, Long> toRemove = new HashMap<>();
        Map<Long, Long> toAdd = new HashMap<>();

        for (Map.Entry<Long, Long> entry : spaces.entrySet()) {
            long entryFloor = entry.getKey();
            long entryCeil = entry.getValue();
            //If our social distancer is not validly inserted. Error handling gracefully handled
            if(floor > entryCeil || ceil < entryFloor) {
                continue;
            }
            else if(floor > entryFloor && ceil < entryCeil) {
                toRemove.put(entryFloor, entryFloor);
                toAdd.put(entryFloor, floor-1L);
                toAdd.put(ceil+1L, entryCeil);
                break;
            }
            else if(floor <= entryFloor){
                toRemove.put(entryFloor, entryFloor);
                if(ceil < entryCeil) toAdd.put(ceil+1L, entryCeil);
                break;
            }
            else { //ceil >= entryCeil
                toRemove.put(entryFloor, entryFloor);
                if(entryFloor < floor) toAdd.put(entryFloor, floor-1L);
                break;
            }
        }

        for(Map.Entry<Long,Long> entry : toRemove.entrySet()) {
            spaces.remove(entry.getKey());
        }
        for(Map.Entry<Long,Long> entry : toAdd.entrySet()) {
            spaces.put(entry.getKey(), entry.getValue());
        }
        return spaces;
    }
}
