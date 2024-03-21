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
        //Java doesn't like it when we modify things in-memory as we're iterating over them
        Map<Long, Long> spaces = new HashMap<>();
        Map<Long, Long> breakpointsCopy = new HashMap<>();
        breakpointsCopy.put(0L, N-1L);
        spaces.put(0L, N-1L);
        //We take care of that part here and make a deep-copy to re-deep-copy later.

        //This is our answer. But we get ahead of ourselves.
        long dinersWecanAdd = 0L;

        //For every diner that exists
        for(int i = 0; i<M; i++)
        {
            //Where's he sitting? Don't give me that "Oh but seat 1 means index 0 if this is an array" business. -1L
            long seatNumber = S[i]-1L;
            //See how double wide we gotta care for them
            long floor = Math.max(0L,seatNumber-K);
            long ceil = Math.min(N-1L,seatNumber+K);
            //Make it kinda like a zone <----XXX---> that can't exceed max <XX-------->. S[i]=5L/1L respectively. K=1

            //For every currently remaining spot: //on first run, it's {<0L,N-1L>}
            for(Map.Entry<Long,Long> entry: breakpointsCopy.entrySet()) {
                long entryFloor = entry.getKey();
                long entryCeil = entry.getValue();
                //If our social distancer is not validly inserted. Error handling gracefully handled
                if(floor > entryCeil || ceil < entryFloor) {
                    continue;
                }
                else{
                    //This bisects a space, and makes 2 new open slots, assuming social-distance rules followed
                    if(floor > entryFloor && ceil < entryCeil) {
                        spaces.remove(entryFloor);
                        //make new entries from the old one
                        spaces.put(entryFloor, floor-1L);
                        spaces.put(ceil+1L, entryCeil);
                    }
                    else {
                        //make1 new entry from old, taking up space 'from the left' if you will
                        if(floor <= entryFloor){
                            spaces.values().remove(entryCeil);
                            if(ceil < entryCeil) spaces.put(ceil+1L, entryCeil);
                        }
                        else{ //ceil >= entryCeil-1. takes up space 'from the right' if you will
                            spaces.remove(entryFloor);
                            if(entryFloor < floor) spaces.put(entryFloor, floor-1L);
                        }
                    }
                }
            }
            //Our deep copy is now made of the new spaces. Once an insertion M.
            breakpointsCopy = new HashMap<>();
            for (Map.Entry<Long, Long> entry : spaces.entrySet()) {
                breakpointsCopy.put(entry.getKey(), entry.getValue());
            }
        }

        //Add every space's customer potential (one chair at 3. 1 customer. 3 chairs at 6, 2 customers (K=1)
        for (Map.Entry<Long, Long> entry : spaces.entrySet()) {
            long currentLengthSpace = entry.getValue()-entry.getKey() + 1L; //4-5 is 2 spaces, since 5-4 +1 = 2
            dinersWecanAdd += currentLengthSpace/(K+1L) + (currentLengthSpace%(K+1L) > 0L ? 1L : 0L);
        }
        return dinersWecanAdd;
    }
}
