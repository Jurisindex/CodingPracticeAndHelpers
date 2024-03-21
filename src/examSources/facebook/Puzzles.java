package examSources.facebook;

import helperClasses.Pair;

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
        ArrayList<Pair<Long, Long>> spaces = new ArrayList<>();
        spaces.add(new Pair<>(0L, N - 1L));

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
        for (Pair<Long, Long> entry : spaces) {
            long currentLengthSpace = entry.getVal()-entry.getKey() + 1L; //4-5 is 2 spaces, since 5-4 +1 = 2
            dinersWecanAdd += currentLengthSpace/(K+1L) + (currentLengthSpace%(K+1L) > 0L ? 1L : 0L);
        }
        return dinersWecanAdd;
    }

    private ArrayList<Pair<Long, Long>> getNewSpaceAdjustment(ArrayList<Pair<Long, Long>> spaces, long floor, long ceil) {
        Set<Pair<Long, Long>> toRemove = new HashSet<>();
        Set<Pair<Long, Long>> toAdd = new HashSet<>();

        for (Pair<Long, Long> entry : spaces) {
            long entryFloor = entry.getKey();
            long entryCeil = entry.getVal();
            //If our social distancer is not validly inserted. Error handling gracefully handled
            if(floor > entryCeil || ceil < entryFloor) {
                continue;
            }
            else if(floor > entryFloor && ceil < entryCeil) {
                toRemove.add(new Pair<>(entryFloor, entryCeil));
                toAdd.add(new Pair<>(entryFloor, floor-1L));
                toAdd.add(new Pair<>(ceil+1L, entryCeil));
            }
            else if(floor <= entryFloor){
                toRemove.add(new Pair<>(entryFloor, entryCeil));
                if(ceil < entryCeil) toAdd.add(new Pair<>(ceil+1L, entryCeil));
            }
            else { //ceil >= entryCeil
                toRemove.add(new Pair<>(entryFloor, entryCeil));
                if(entryFloor < floor) toAdd.add(new Pair<>(entryFloor, floor-1L));
            }
        }

        for(Pair<Long, Long> entry : toRemove) {
            spaces.remove(entry);
        }
        for(Pair<Long, Long> entry : toAdd) {
            spaces.add(new Pair<>(entry.getKey(), entry.getVal()));
        }
        return spaces;
    }

    public int getArtisticPhotographCount(int N, String C, int X, int Y) {
        int artisticShots = 0;

        for(int i = 0; i < C.length(); i++){
            if(C.charAt(i) == 'P') {
                ArrayList<Integer> inRangeActors = getListOfCharactersInRange(C, X, Y, i, 'A', true, true);
                for(Integer actorIndex : inRangeActors) {
                    if(actorIndex < i){
                        artisticShots += getListOfCharactersInRange(C, X, Y, actorIndex, 'B', false, true).size();
                    }
                    else{
                        artisticShots += getListOfCharactersInRange(C, X, Y, actorIndex, 'B', true, false).size();
                    }
                }
            }
        }
        return artisticShots;
    }

    private ArrayList<Integer> getListOfCharactersInRange(String S, int X, int Y, int index, Character c, boolean lookForward, boolean lookBack)
    {
        ArrayList<Integer> resultIndecies = new ArrayList<>();
        if(lookForward){
            for(int i = Math.min(index+X,S.length()); i <= Math.min(index+Y,S.length()-1); i++){
                if (S.charAt(i) == c) resultIndecies.add(i);
            }
        }
        if(lookBack){
            for(int i = Math.max(index-X,0); i >= Math.max(index-Y,0); i--){
                if (S.charAt(i) == c) resultIndecies.add(i);
            }
        }
        return resultIndecies;
    }
}
