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

    public long getMaxAdditionalDinersCountLinear(long N, long K, int M, long[] S) {
        ArrayList<Long> spaces = new ArrayList<>();

        //This is our answer. But we get ahead of ourselves.
        long dinersWecanAdd = 0L;
        long currentSeat = 0L;
        Arrays.sort(S);
        //For every diner that exists
        for(int i = 0; i<M; i++)
        {
            long seatNumber = S[i]-1L;
            long floor = Math.max(0L,seatNumber-K);
            long ceil = Math.min(N-1L,seatNumber+K);
            spaces.add(Math.max(0L, floor-currentSeat));
            currentSeat = ceil+1L;
        }
        spaces.add(Math.max(0L, N-currentSeat));

        //Add every space's customer potential (one chair at 3. 1 customer. 3 chairs at 6, 2 customers (K=1)
        for (Long seats: spaces) {
            if(seats <= 0){
                continue;
            }
            long currentLengthSpace = seats; //4-5 is 2 spaces, since 5-4 +1 = 2
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
            for(int i = Math.max(index-X,-1); i >= Math.max(index-Y,0); i--){
                if (S.charAt(i) == c) resultIndecies.add(i);
            }
        }
        return resultIndecies;
    }

    public int getArtisticPhotographCountOnePass(int N, String C, int X, int Y) {
        int artisticShots = 0;
        ArrayList<Integer> pIndex = new ArrayList<>();
        ArrayList<Integer> aIndex = new ArrayList<>();
        ArrayList<Integer> bIndex = new ArrayList<>();

        for(int i = 0; i < C.length(); i++){
            if(C.charAt(i) == 'P') {
                pIndex.add(i);
            }
            if(C.charAt(i) == 'A') {
                aIndex.add(i);
            }
            if(C.charAt(i) == 'B') {
                bIndex.add(i);
            }
        }
        for(int i = 0; i < pIndex.size(); i++)
        {
            int producerIndex = pIndex.get(i);
            ArrayList<Integer> relevantActors = getListOfCharactersInRange(aIndex, X, Y, producerIndex, null);
            for(Integer actorIndex : relevantActors)
                if(actorIndex < producerIndex)
                    artisticShots += getListOfCharactersInRange(bIndex, X, Y, actorIndex, false).size();
                else
                    artisticShots += getListOfCharactersInRange(bIndex, X, Y, actorIndex, true).size();
        }
        return artisticShots;
    }

    private ArrayList<Integer> getListOfCharactersInRange(ArrayList<Integer> indexOccurrences, int X, int Y, int index, Boolean direction) {
        ArrayList<Integer> resultIndecies = new ArrayList<>();
        for(int i = 0; i < indexOccurrences.size(); i++) {
            int nextChainIndex = indexOccurrences.get(i);
            if((direction == null || !direction) && nextChainIndex >= index-Y && nextChainIndex <= index-X){
                resultIndecies.add(nextChainIndex);
            }
            else if((direction == null || direction) && nextChainIndex <= index+Y && nextChainIndex >= index+X){
                resultIndecies.add(nextChainIndex);
            }
        }
        return resultIndecies;
    }


    public int getMaximumEatenDishCount(int N, int[] D, int K) {
        int dishesEaten = 0;
        LinkedList<Integer> dishIDs = new LinkedList<>();
        Set<Integer> dishCache = new HashSet<>(K, 1.0f);

        for (int i = 0; i < D.length; i++) {
            int dishId = D[i];
            if(!dishCache.contains(dishId)) {
                if(dishCache.size() == K) {
                    //forget the oldest dish
                    dishCache.remove(dishIDs.removeFirst());
                }
                //sample the new dish!
                dishIDs.add(dishId);
                dishCache.add(dishId);
                dishesEaten++;
            }
        }
        return dishesEaten;
    }

    public long getMinCodeEntryTime(int N, int M, int[] C) {
        int currentPosition = 0;
        long secondsPassed = 0L;
        for(int i = 0; i < C.length; i++) {
            int indexToGoTo = C[i]-1;
            int overflowDistance = Math.min(N-indexToGoTo+currentPosition, N-currentPosition+indexToGoTo);
            int distance = Math.min(overflowDistance, Math.abs(indexToGoTo-currentPosition));
            currentPosition = indexToGoTo;
            secondsPassed = secondsPassed + (long) distance;
        }
        return secondsPassed;
    }

    public int getMinProblemCount(int N, int[] S) {
        boolean oddValue = false;
        int highestScore = 0;
        for(int i = 0; i < S.length; i++) {
            highestScore = Math.max(highestScore, S[i]);
            if(S[i]%2 == 1) oddValue = true;
        }
        return highestScore/2 + (oddValue ? 1 : 0);
    }

    public int getMinimumDeflatedDiscCount(int N, int[] R) {
        //Count backwards, deflate minimally needed, if >=, as we iterate backwards.
        //If deflated and need to deflate again, return -1
        boolean deflated = false;
        int latestRingSize = R[R.length-1];
        int amountDeflated = 0;
        //From R.length-2 => 1:
        for(int i = R.length-2; i >= 0; i-- ) {
            int ringEntrySize = R[i];
            if(ringEntrySize >= latestRingSize)
            {
                latestRingSize = latestRingSize-1;
                if(latestRingSize < 1) return -1;
                amountDeflated++;
            }
            else{
                latestRingSize = ringEntrySize;
            }
        }
        return amountDeflated;
    }

    public int getUniformIntegerCountInInterval(long A, long B) {
        int uniformNumbers = 0;
        int offsets = 2;

        //determine if A is uniform or under//67 no, 66 yes, 65 yes
        //determine if B uniform will be reached //67 yes, 65 no, 66 no
        ArrayList<Integer> powerOfTensDigitsA = new ArrayList<>();
        ArrayList<Integer> powerOfTensDigitsB = new ArrayList<>();
        while(A > 0){
            powerOfTensDigitsA.add((int) (A%10L));
            A = A/10L;
        }
        while(B > 0){
            powerOfTensDigitsB.add((int) (B%10L));
            B = B/10L;
        }
        //is Uniform or will reach uniformity
        int digitA = powerOfTensDigitsA.get(powerOfTensDigitsA.size()-1);
        for(int i = powerOfTensDigitsA.size()-2; i >= 0; i--){
            if(powerOfTensDigitsA.get(i).equals(digitA))
            {
                continue;
            }
            if(powerOfTensDigitsA.get(i) > digitA){
                offsets--;

            }
            break;
        }
        int digitB = powerOfTensDigitsB.get(powerOfTensDigitsB.size()-1);
        for(int i = powerOfTensDigitsB.size()-2; i >= 0; i--){
            if(powerOfTensDigitsB.get(i).equals(digitB))
            {
                continue;
            }
            if(powerOfTensDigitsB.get(i) < digitB){
                offsets--;
            }
            break;
        }
        //are A and B of same 10s power and largest is same #? Minus 1.
        if(offsets == 2 && powerOfTensDigitsA.size() == powerOfTensDigitsB.size() && digitA == digitB) {
            offsets--;  //we're double counting the same one (ex 665=>667. We'd count 666 twice here)
        }

        //Create data structure like this: ex 7 => 665
        //{7}, {5,6,6}
        //Here we see 7 has 2 more it can reach. 8 and 9. For the tens part, it's not the last digit, so we count all 9
        //At the hundreds part, since we dont subtract it, we count 5, and the offset takes care of the current 100s (600s) uniform check
        //If we did {7,0,6}, {5,6,6}, for 607 => 665, we consider 665's 5 reached in the 100s already to be subtracted from 607's 5 reached, equalling 0. If it was =>667, offset would be positive.

        int powerDiff = powerOfTensDigitsB.size() - powerOfTensDigitsA.size();
        //what we want to do is: powerDiff*9 tbh. Then, we also do lastIndex Analysis if different
        //81 => 1005: 4 - 2 [1,8]vs[5,0,0,1]
        if(powerDiff > 0) {
            uniformNumbers += (9-digitA)+(digitB-1)+offsets+((powerDiff-1)*9);
        }
        // 1005 => 2281: 4 - 2 [5,0,0,1]vs[1,8,2,2]
        else {
            uniformNumbers += Math.max(digitB-digitA-1, 0)+offsets;
        }
        return uniformNumbers;
    }

    public long getSecondsRequired(long N, int F, long[] P) {
        Arrays.sort(P);
        ArrayList<Long> frogIndecies = new ArrayList<>(Arrays.asList(Arrays.stream(P).boxed().toArray(Long[]::new)));
//        ArrayList<Long> frogIndecies = new ArrayList<>();
//        //insertion-sort the frogs
//        for(int i = 0; i < P.length; i++) {
//            long frogPos = P[i];
//            int indexInsertion = 0;
//            for(int j = 0; j < frogIndecies.size(); j++) {
//                if(frogPos > frogIndecies.get(j)){
//                    indexInsertion = j+1;
//                }
//                else break;
//            }
//            frogIndecies.add(indexInsertion, frogPos);
//        }
        long secondsRequired = 0L;
        for(int i = 0; i < frogIndecies.size()-1; i++){
            // F1 => F2, F2-F1-1 hops.
            long frogPos1 = frogIndecies.get(i);
            long frogPos2 = frogIndecies.get(i+1);
            //index [99], wants to get to [999], next frog. 999-99 = 900. But, she has 100 frogs around em. Still takes 900 leaps, just all frogs move too
            long secondsNeededForDistanceClosing = (frogPos2 - frogPos1) - 1L; //6 and 5 are distance closed, but 1L still
            secondsRequired += secondsNeededForDistanceClosing;
        }
        secondsRequired += (N-frogIndecies.get(frogIndecies.size()-1)) - 1L + (long) F;
        return secondsRequired;
    }

    public double getMaxExpectedProfit(int N, int[] V, int C, double S) {
        Stack<Integer> daysEntered = new Stack<>();
        int lastPackageDelivered = N-1;
        for(int i = N-1; i >= 0; i--){
            if(V[i] > 0) {
                daysEntered.add(i);
                lastPackageDelivered = i;
                break;
            }
        }
        //starting from the back, assume we go in last day
        double lastDayEnteredProfit = V[lastPackageDelivered]-C;

        for(int i = lastPackageDelivered-1; i >= 0; i--) {
            int earliestCurrentDayEntered = daysEntered.peek();
            //Seeing previous day
            //What if we entered that day
            int packageValue = V[i];
            if(packageValue == 0) continue;
            double profitIfEntered = packageValue-C;
            double expectedValIfSleptOn = V[i]*Math.pow((1.0-S), (earliestCurrentDayEntered-i));
            if(profitIfEntered < 0.0){
                //tack on expected value to the next day we enter
                lastDayEnteredProfit += expectedValIfSleptOn;
            }
            else {
                //if not worth it to enter on Day i. Risk the theft.
                if(Math.max(0.0,lastDayEnteredProfit)+profitIfEntered < lastDayEnteredProfit+expectedValIfSleptOn){
                    lastDayEnteredProfit += expectedValIfSleptOn;
                }
                //It actually IS worth it to go in on Day i, and not risk theft.
                else {
                    if(daysEntered.size() == 1) {
                        lastDayEnteredProfit = Math.max(0,lastDayEnteredProfit);
                    }
                    lastDayEnteredProfit +=  profitIfEntered;
                    daysEntered.add(i);
                }
            }
        }
        return Math.max(0.0,lastDayEnteredProfit);

        //Iterate from the back

//        Map<Integer, Double> itemsAndChanceStillThere = new HashMap();
//        double expectedValueOfItemsInRoom = 0.0;
//        double currentProfit = 0.0;
//        Map<Integer, Integer> dayAndItemValueAdded = new HashMap<>();
//        for(int i = 0; i < V.length; i++)
//        {
//            if(V[i] == 0)
//                continue;
//            //day starts
//            expectedValueOfItemsInRoom += V[i];
//            itemsAndChanceStillThere.put(V[i], 1.0);
//            //should I enter and take profit?
//            if(S > 0.0 && expectedValueOfItemsInRoom > C){
//                currentProfit += expectedValueOfItemsInRoom-C;
//                //Reset tracking vars
//                expectedValueOfItemsInRoom = 0.0;
//                itemsAndChanceStillThere = new HashMap<>();
//            }
//            //The thief may come
//            Map<Integer, Double> updatedItemChances = new HashMap();
//            for(Map.Entry<Integer,Double> item : itemsAndChanceStillThere.entrySet()){
//                int itemBaseValue = item.getKey();
//                double chanceStillThere = item.getValue();
//                double currentItemEV = itemBaseValue*chanceStillThere;
//                chanceStillThere *= (1.0 - S);
//                double newItemEV = itemBaseValue*chanceStillThere;
//                expectedValueOfItemsInRoom -= currentItemEV - newItemEV;
//                if(chanceStillThere > 0.0)
//                    updatedItemChances.put(itemBaseValue, chanceStillThere);
//            }
//            itemsAndChanceStillThere = updatedItemChances;
//        }
//        if(expectedValueOfItemsInRoom > C)
//            currentProfit += expectedValueOfItemsInRoom-C;
//        return currentProfit;
    }

    public int getSecondsRequired(int R, int C, char[][] G) {
        List<Pair<Integer, Integer>> endIndecies = new ArrayList<>();
        Pair<Integer, Integer> startIndex = null;
        Set<Character> blockerChars = new HashSet<>();
        blockerChars.add('#');
        Set<Pair<Integer, Integer>> blockedSpots = new HashSet<>();
        Map<Character, List<Pair<Integer,Integer>>> portalToExits = new HashMap<>();
        Map<Character, Integer> startToPortalDistanceSmallest = new HashMap<>();
        Map<Pair<Character, Pair<Integer, Integer>>, Integer> endToPortalDistanceSmallest = new HashMap<>();

        for(int x = 0; x < G[0].length; x++){
            for(int y = 0; y < G.length; y++){
                char charAtCoord = G[y][x];
                if(charAtCoord == 'E'){
                    endIndecies.add(new Pair<>(x,y));
                }
                else if(charAtCoord == 'S'){
                    startIndex = new Pair<>(x,y);
                }
                else if(charAtCoord == '#'){
                    blockedSpots.add(new Pair<>(x,y));
                }
                else if(Character.isLowerCase(charAtCoord)){
                    List<Pair<Integer,Integer>> currentExitsForPortal = portalToExits.getOrDefault(charAtCoord, new ArrayList<>());
                    currentExitsForPortal.add(new Pair<>(x,y));
                    portalToExits.put(charAtCoord, currentExitsForPortal);
                }
            }
        }
        //All member vars but smallestDistance maps are now populated
        for(Map.Entry<Character, List<Pair<Integer,Integer>>> portalEntry: portalToExits.entrySet()){
            Character portalId = portalEntry.getKey();
            List<Pair<Integer, Integer>> portalExits = portalEntry.getValue();
            for(Pair<Integer, Integer> exit : portalExits){
                int currentSmallestDistToStart = startToPortalDistanceSmallest.getOrDefault(portalId, Integer.MAX_VALUE);
                int distToStart = smallestDistanceBetweenWithBlockers(exit, startIndex, blockerChars, G);
                if(distToStart > 0)
                    startToPortalDistanceSmallest.put(portalId, Math.min(currentSmallestDistToStart,distToStart));

                for(Pair<Integer, Integer> endIndex : endIndecies){
                    int currentSmallestDistToExit = endToPortalDistanceSmallest.getOrDefault(new Pair<>(portalId, endIndex), Integer.MAX_VALUE);
                    int distToEnd = smallestDistanceBetweenWithBlockers(exit, endIndex, blockerChars, G);
                    if(distToEnd > 0)
                        endToPortalDistanceSmallest.put(new Pair<>(portalId, endIndex), Math.min(currentSmallestDistToExit,distToEnd));
                }
            }
        }
        int smallestTime = Integer.MAX_VALUE;
        //not using portals
        for(Pair<Integer, Integer> end : endIndecies){
            int timeTakenStartToEnd = smallestDistanceBetweenWithBlockers(startIndex, end, blockerChars, G);
            if(timeTakenStartToEnd > 0)
                smallestTime = Math.min(smallestTime, timeTakenStartToEnd);
        }
        //using portals
        for(Pair<Character, Pair<Integer, Integer>> characterAndExitPair: endToPortalDistanceSmallest.keySet()) {
            Character c = characterAndExitPair.getKey();
            int timeFromStart = startToPortalDistanceSmallest.getOrDefault(c, -1);
            int timeToEnd = endToPortalDistanceSmallest.getOrDefault(characterAndExitPair,  -1);
            if(timeToEnd > 0 && timeFromStart > 0){
                smallestTime = Math.min(timeFromStart+timeToEnd+1, smallestTime);   //takes +1 second to take the portal
            }
        }
        if (smallestTime == Integer.MAX_VALUE || smallestTime < 0){
            return -1;
        }
        return smallestTime;
    }


    private int smallestDistanceBetweenWithBlockers(Pair<Integer, Integer> pointA, Pair<Integer, Integer> pointB,
                                                    Set<Character> blockerChars, char[][] graph) {
        return smallestDistanceBetweenWithBlockers(pointA, pointB, blockerChars, graph, new HashSet<>());
    }

    //We only move pointA
    private int smallestDistanceBetweenWithBlockers(Pair<Integer, Integer> pointA, Pair<Integer, Integer> pointB,
                                                    Set<Character> blockerChars, char[][] graph,
                                                    Set<Pair<Integer, Integer>> visited) {
        if(pointA.equals(pointB)){
            return 0;
        }
        //If impassable terrain is met, a negative number is returned
        //The graph takes in input in [y][x], but I hate that format so it's reverse for everything else
        else if(blockerChars.contains(graph[pointA.getVal()][pointA.getKey()])){
            return -1;
        }

        visited.add(pointA);
        int smallestDistance = -1;
        int distance = -1;
        //visit up
        Pair<Integer, Integer> moveTo = new Pair<>(Math.max(pointA.getKey()-1,0),pointA.getVal());
        if(!visited.contains(moveTo)) {
            distance = smallestDistanceBetweenWithBlockers(moveTo, pointB, blockerChars, graph, visited);
            if(distance >= 0) smallestDistance = distance+1;
        }
        //visit down
        moveTo = new Pair<>(Math.min(pointA.getKey()+1,graph[0].length-1),pointA.getVal());
        if(!visited.contains(moveTo)) {
            distance = smallestDistanceBetweenWithBlockers(moveTo, pointB, blockerChars, graph, visited);
            if(distance >= 0) smallestDistance = distance+1;
        }
        //visit left
        moveTo = new Pair<>(pointA.getKey(), Math.max(pointA.getVal()-1,0));
        if(!visited.contains(moveTo)) {
            distance = smallestDistanceBetweenWithBlockers(moveTo, pointB, blockerChars, graph, visited);
            if(distance >= 0) smallestDistance = distance+1;
        }
        //visit right
        moveTo = new Pair<>(pointA.getKey(), Math.min(pointA.getVal()+1,graph.length-1));
        if(!visited.contains(moveTo)) {
            distance = smallestDistanceBetweenWithBlockers(moveTo, pointB, blockerChars, graph, visited);
            if(distance >= 0) smallestDistance = distance+1;
        }
        visited.remove(pointA);
        return smallestDistance;
    }
}
