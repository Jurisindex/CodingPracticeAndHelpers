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

    public long getMinCodeEntryTimeTwoLocks(int N, int M, int[] C) {
        int leftPosition = 0;
        int rightPosition = 0;
        long timeTakenSoFar = 0L;
        for(int i = 0; i < M; i++){
            int nextPos = C[i]-1;
            long ifLeftHandMoved = getTimeWithPositionAtPos(N, C, leftPosition, i);
            long ifRightHandMoved = getTimeWithPositionAtPos(N, C, rightPosition, i);
            if(ifLeftHandMoved <= ifRightHandMoved){
                //make left hand move
                timeTakenSoFar += ifLeftHandMoved;
                leftPosition = nextPos;
            }
            else{
                //make right hand move
                timeTakenSoFar += ifRightHandMoved;
                rightPosition = nextPos;
            }

        }
        return timeTakenSoFar;
    }

    private static long getTimeWithPositionAtPos(int N, int[] C, int positionAt, int positionFrom) {
        long secondsPassed = 0L;
        int indexToGoTo = C[positionFrom]-1;
        int overflowDistance = Math.min(N -indexToGoTo+ positionAt, N - positionAt +indexToGoTo);
        int distance = Math.min(overflowDistance, Math.abs(indexToGoTo- positionAt));
        secondsPassed = secondsPassed + (long) distance;
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

    public int getMinProblemCountGeneralized(int N, int[] S, Map<Integer,Integer> minProblemsForValue) {
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
        int smallestDistance = -1;
        for(Pair<Integer, Integer> endIndex : endIndecies){
            int fromStartToEnd = smallestDistanceBetweenWithBlockers(startIndex, endIndex, blockerChars, G, portalToExits);
            if(fromStartToEnd >= 1){
                if(smallestDistance < 0)
                    smallestDistance = fromStartToEnd;
                else
                    smallestDistance = Math.min(smallestDistance, fromStartToEnd);
            }
        }
        return smallestDistance;
        //All member vars but smallestDistance maps are now populated
//        for(Map.Entry<Character, List<Pair<Integer,Integer>>> portalEntry: portalToExits.entrySet()){
//            Character portalId = portalEntry.getKey();
//            List<Pair<Integer, Integer>> portalExits = portalEntry.getValue();
//            for(Pair<Integer, Integer> exit : portalExits){
//                int currentSmallestDistToStart = startToPortalDistanceSmallest.getOrDefault(portalId, Integer.MAX_VALUE);
//                int distToStart = smallestDistanceBetweenWithBlockers(exit, startIndex, blockerChars, G, portalToExits);
//                if(distToStart > 0)
//                    startToPortalDistanceSmallest.put(portalId, Math.min(currentSmallestDistToStart,distToStart));
//
//                for(Pair<Integer, Integer> endIndex : endIndecies){
//                    int currentSmallestDistToExit = endToPortalDistanceSmallest.getOrDefault(new Pair<>(portalId, endIndex), Integer.MAX_VALUE);
//                    int distToEnd = smallestDistanceBetweenWithBlockers(exit, endIndex, blockerChars, G, portalToExits);
//                    if(distToEnd > 0)
//                        endToPortalDistanceSmallest.put(new Pair<>(portalId, endIndex), Math.min(currentSmallestDistToExit,distToEnd));
//                }
//            }
//        }
//        int smallestTime = Integer.MAX_VALUE;
//        //not using portals
//        for(Pair<Integer, Integer> end : endIndecies){
//            int timeTakenStartToEnd = smallestDistanceBetweenWithBlockers(startIndex, end, blockerChars, G, portalToExits);
//            if(timeTakenStartToEnd > 0)
//                smallestTime = Math.min(smallestTime, timeTakenStartToEnd);
//        }
//        //using portals
//        for(Pair<Character, Pair<Integer, Integer>> characterAndExitPair: endToPortalDistanceSmallest.keySet()) {
//            Character c = characterAndExitPair.getKey();
//            int timeFromStart = startToPortalDistanceSmallest.getOrDefault(c, -1);
//            int timeToEnd = endToPortalDistanceSmallest.getOrDefault(characterAndExitPair,  -1);
//            if(timeToEnd > 0 && timeFromStart > 0){
//                smallestTime = Math.min(timeFromStart+timeToEnd+1, smallestTime);   //takes +1 second to take the portal
//            }
//        }
//        if (smallestTime == Integer.MAX_VALUE || smallestTime < 0){
//            return -1;
//        }
//        return smallestTime;
    }


    private int smallestDistanceBetweenWithBlockers(Pair<Integer, Integer> pointA, Pair<Integer, Integer> pointB,
                                                    Set<Character> blockerChars, char[][] graph, Map<Character, List<Pair<Integer, Integer>>> portalToExits) {
        return smallestDistanceBetweenWithBlockers(pointA, pointB, blockerChars, graph, new HashSet<>(), portalToExits);
    }

    //We only move pointA
    private int smallestDistanceBetweenWithBlockers(Pair<Integer, Integer> pointA, Pair<Integer, Integer> pointB,
                                                    Set<Character> blockerChars, char[][] graph,
                                                    Set<Pair<Integer, Integer>> visited, Map<Character, List<Pair<Integer,Integer>>> portalToExits) {
        if(pointA.equals(pointB)){
            return 0;
        }
        //If impassable terrain is met, a negative number is returned
        //The graph takes in input in [y][x], but I hate that format so it's reverse for everything else
        else if(blockerChars.contains(graph[pointA.getVal()][pointA.getKey()])){
            return -1;
        }

        visited.add(pointA);
        int smallestDistance = Integer.MAX_VALUE;
        int distance = -1;
        boolean found = false;
        //visit left
        Pair<Integer, Integer> moveTo = new Pair<>(Math.max(pointA.getKey()-1,0),pointA.getVal());
        if(!visited.contains(moveTo)) {
            distance = smallestDistanceBetweenWithBlockers(moveTo, pointB, blockerChars, graph, visited, portalToExits);
            if(distance >= 0) {
                smallestDistance = Math.min(smallestDistance, distance+1);
                found=true;
            }
        }
        //visit right
        moveTo = new Pair<>(Math.min(pointA.getKey()+1,graph[0].length-1),pointA.getVal());
        if(!visited.contains(moveTo)) {
            distance = smallestDistanceBetweenWithBlockers(moveTo, pointB, blockerChars, graph, visited, portalToExits);
            if(distance >= 0) {
                smallestDistance = Math.min(smallestDistance, distance+1);
                found=true;
            }
        }
        //visit up
        moveTo = new Pair<>(pointA.getKey(), Math.max(pointA.getVal()-1,0));
        if(!visited.contains(moveTo)) {
            distance = smallestDistanceBetweenWithBlockers(moveTo, pointB, blockerChars, graph, visited, portalToExits);
            if(distance >= 0) {
                smallestDistance = Math.min(smallestDistance, distance+1);
                found=true;
            }
        }
        //visit down
        moveTo = new Pair<>(pointA.getKey(), Math.min(pointA.getVal()+1,graph.length-1));
        if(!visited.contains(moveTo)) {
            distance = smallestDistanceBetweenWithBlockers(moveTo, pointB, blockerChars, graph, visited, portalToExits);
            if(distance >= 0) {
                smallestDistance = Math.min(smallestDistance, distance+1);
                found=true;
            }
        }
        //visit portal exits
        //If the cell is a portalExit, maybe we step through portal?
        List<Pair<Integer, Integer>> portalExitPoints = portalToExits.getOrDefault(graph[pointA.getVal()][pointA.getKey()], new ArrayList<>());
        for(Pair<Integer, Integer> teleportTo: portalExitPoints){
            moveTo = teleportTo;
            if(!visited.contains(moveTo)) {
                distance = smallestDistanceBetweenWithBlockers(moveTo, pointB, blockerChars, graph, visited, portalToExits);
                if(distance >= 0) {
                    smallestDistance = Math.min(smallestDistance, distance+1);
                    found=true;
                }
            }
        }
        visited.remove(pointA);
        if(found)
            return smallestDistance;
        else
            return -1;
    }

    public int getMaxVisitableWebpages(int N, int[] L) {
        ArrayList<LinkedList<Integer>> hopChains = new ArrayList<>();
        int longestSize = 0;
        for(int i = 0; i < L.length; i++){
            int jumpTo = L[i]-1;  //1st cell is 0 index, but we track by index
            boolean added = false;
            for(int j = 0; j < hopChains.size(); j++){
                //is it starting or ending a current chain
                LinkedList<Integer> thisChain = hopChains.get(j);
                if(thisChain.contains(i) && thisChain.contains(jumpTo)){
                    added = true;
                    break;
                }

                if(thisChain.getFirst().equals(jumpTo)){
                    if(!thisChain.contains(i)) {
                        thisChain.addFirst(i);
                        added = true;
                    }
                }
                else if(thisChain.getLast().equals(i)) {
                    if(!thisChain.contains(jumpTo)){
                        thisChain.addLast(jumpTo);
                        added = true;
                    }
                }
            }
            if(!added){
                LinkedList<Integer> newChain = new LinkedList<>();
                newChain.addFirst(i);

                newChain.addLast(jumpTo);
                hopChains.add(newChain);
            }
        }

        for(int i = 0; i < hopChains.size(); i++){
            longestSize = Math.max(longestSize, hopChains.get(i).size());
        }
        return longestSize;
//        int maxVisits = 0;
//        Set<Integer> visitedNumbers = new HashSet<>();
//        //trying starting at every cell
//        Set<Integer> numbersNotAppeared = new HashSet<>();
//        //[1,12,2,6,3,5,6,11,10,7,10,8]
//        //[3,4,7,8,9]
//        //for [4,1,2,1]:
//        //[2,3]
//        for(int i = 0; i < L.length; i++){
//            int linkTo = L[i]-1;  //from i, [4,1,2,1], i=2, linkTo=2(1-indexed so we minus 1)
//            int visitsStartingFromi = 1;
//            visitedNumbers.add(i);
//            visitsStartingFromi++;
//
//            int nextLink = L[linkTo-1];
//            while(!visitedNumbers.contains(nextLink)){
//                visitedNumbers.add(nextLink);
//                visitsStartingFromi++;
//                nextLink = L[nextLink-1];
//            }
//            visitedNumbers = new HashSet<>();
//            maxVisits = Math.max(maxVisits, visitsStartingFromi);
//        }
//        return maxVisits;
    }

    public long getSecondsElapsed(long C, int N, long[] A, long[] B, long K) {
        //Train goes in big circle size C. 1 second to go 1 position
        //There are N tunnels. A[i] is the start, B[i] is the end. B[i]-A[i] is the length of tunnel i, let's say
        //How many seconds until the train spends K seconds in a tunnel?
        Arrays.sort(A);
        Arrays.sort(B);
        long totalTunnelTimeInOneRotation = 0L;
        Map<Long, Long> tunnelEntranceAndTimeSpentAfterTaken = new HashMap<>();
        ArrayList<Long> timeSpentInTunnelAfterTaken = new ArrayList<>();
        for(int i = 0; i < A.length; i++){
            //count the time spent in the tunnels as we go through them
            totalTunnelTimeInOneRotation += B[i] - A[i];
            if(totalTunnelTimeInOneRotation >= K){
                //1st Tunnel ends at 20, starts at 10, K is 5. B[i] is 20, totalTimeInTunnel is 10. 20-(10-5) = 15
                //After 15 seconds (time to get to A[i]:10) we should return
                return B[i] - (totalTunnelTimeInOneRotation-K);
            }
            //every tunnel entrance, mark down in the Map, how long we've spent in tunnels SO FAR
//            tunnelEntranceAndTimeSpentAfterTaken.put(A[i],totalTunnelTimeInOneRotation);
            timeSpentInTunnelAfterTaken.add(totalTunnelTimeInOneRotation);
            //Then we have something that looks like: {1=>0,6=>2
        }
        //totalTunnelTimeInOneRotation will then be used to see how many loops we have to do (just *C)
        long loops = K/totalTunnelTimeInOneRotation;
        long remainder = K%totalTunnelTimeInOneRotation;
        //Then iterate by tunnel entrance, marking down the previous tunnel entry
        long previousIndex = 0L;
        long minusor = 0L;
        if(remainder == 0L){
            //The loops needed plus ended at the end of the last tunnel, cutting off the last loop
            return loops*C - (C-B[B.length-1]);
        }
        for(int i = 0; i < A.length; i++){
            if(timeSpentInTunnelAfterTaken.get(i) >= remainder){
                previousIndex = A[i];
                if(i > 0)
                    minusor = timeSpentInTunnelAfterTaken.get(i-1);
                break;
            }
        }
        //The last train tunnel will reach our goal
        if(previousIndex == 0L){
            System.out.println("Something has gone wrong");
        }
        return (loops*C) + (previousIndex+(remainder-minusor));
    }

    private double getDmg(int war1D, int war1HP, int war2D, int war2HP, int dmgByBoss){
        return Math.max(war1D*((double)war1HP/dmgByBoss) + war2D*((double)war1HP/dmgByBoss + (double)war2HP/dmgByBoss),
                        war2D*((double)war2HP/dmgByBoss) + war1D*((double)war2HP/dmgByBoss + (double)war1HP/dmgByBoss));
    }

    public double getMaxDamageDealt(int N, int[] H, int[] D, int B) {
        class Warrior implements Comparable<Warrior> {
            long health;
            long damage;

            public Warrior(int health, int damage) {
                this.health = health;
                this.damage = damage;
            }

            public int compareTo(Warrior that) {
                return - Long.compare(this.health * this.damage, that.health * that.damage);
            }

            // Calculate the total boss damage with a second warrior
            public double bossDamage(Warrior second, int B) {
                return (double) (this.damage + second.damage) * this.health/B + (double) second.damage * second.health/B;
            }
        }
        //Parse each warrior i. Create a dmgDone number for each one, new double[]
        //timeLived*damage
        double[] dmgDone = new double[N];
        Integer[] warsByDmg = new Integer[N];
        Integer[] warsByHP = new Integer[N];
        double maxDamageForPair = 0;
        for(int i = 0; i < N; i++){
//            dmgDone[i] = ((double)H[i]/(double)B) * (double)D[i];
            warsByHP[i] = i;
            warsByDmg[i] = i;
        }
        Arrays.sort(warsByDmg, (a, b) -> Double.compare(D[b], D[a]));
        Arrays.sort(warsByHP, (a, b) -> Double.compare(H[b], H[a]));
        int dpsWarriorIndex = warsByDmg[0];
        int tankWarriorIndex = warsByHP[0];
        if(dpsWarriorIndex == tankWarriorIndex){
            tankWarriorIndex = warsByHP[1];
        }
        maxDamageForPair = getDmg(D[tankWarriorIndex], H[tankWarriorIndex], D[dpsWarriorIndex], H[dpsWarriorIndex], B);
        double nextChoice = maxDamageForPair;
        double maxChoice = 0.0;
        while(nextChoice > maxChoice){
            maxChoice = nextChoice;
            for(int other = 0; other < N; other++){
                if(other == dpsWarriorIndex || other == tankWarriorIndex){
                    continue;
                }
                int otherWarDmg = D[other];
                int otherWarHP = H[other];
                double replaceTank = getDmg(otherWarDmg, otherWarHP, D[dpsWarriorIndex], H[dpsWarriorIndex], B);
                double replaceDps = getDmg(D[tankWarriorIndex], H[tankWarriorIndex], otherWarDmg, otherWarHP, B);
                if(replaceTank > nextChoice){
                    tankWarriorIndex = other;
                    nextChoice = replaceTank;
                }else if (replaceDps > nextChoice){
                    dpsWarriorIndex = other;
                    nextChoice = replaceDps;
                }
            }
        }
        return maxChoice;
    }

    public double getMinExpectedHorizontalTravelDistance(int N, int[] H, int[] A, int[] B) {
        //A[i],H[i] & B[i],H[i] represent a belt i. There are N belts.
        //Having the option to force 1 belt to go left or right
        //First let's calculate our expected horizontal travel distance if we DON'T get to choose.
        class Belt implements Comparable<Belt>{
            int height;
            int startX;
            int endX;
            double halfLength;
            double areaCovered;
            double timeOptimizedIfLeft;
            double timeOptimizedIfRite;
            double bestTimeSaved;
            double averageDropResponsibility;
            ArrayList<Belt> droppedOntoFrom = new ArrayList<>();
            ArrayList<Belt> droppingOnto = new ArrayList<>();

            Belt(int height, int startX, int endX){
                this.height = height;
                this.startX = startX;
                this.endX = endX;
                this.halfLength = (double)(endX-startX)/2.0;
                this.timeOptimizedIfLeft = 0.0;
                this.timeOptimizedIfRite = 0.0;
                this.averageDropResponsibility = 0.0;
                this.areaCovered = 0.0;
                this.bestTimeSaved = 0.0;
                this.droppedOntoFrom = new ArrayList<>();
                this.droppingOnto = new ArrayList<>();
            }

            void addDroppingOnto(Belt to){
                if(!droppingOnto.contains(to)){
                    droppingOnto.add(to);
                }
            }

            void addDroppedOntoFrom(Belt from){
                if(droppedOntoFrom.contains(from)){
                    return;
                }
                this.droppedOntoFrom.add(from);
                this.averageDropResponsibility = droppedOntoFrom.size() * halfLength;
                calculateDistance(from);
            }

            //Gives the minimum time if left or right is picked here (we don't care which one is actually
            private void calculateDistance(Belt belt){
                double timeTravelledIfForcedLeft = 0.0;
                double timeTravelledIfForcedRite = 0.0;
                //How much distance does it drop it from my top's right?
                //If go left, distance is currentBelt.endX-slaveBelt.startX
                //If go right, distance is slaveBelt.endX-currentBelt.endX

                //Top's left?
                //If go left, currentBelt.startX-slaveBelt.startX
                //If go right, slaveBelt.endX-currentBelt.startX
                double leftLoad = this.calculateRandomDistance(belt.startX);
                double rightLoad = this.calculateRandomDistance(belt.endX);
                double endCoord = 0;
                if(leftLoad > 0 && rightLoad >0){
                    endCoord = ((double)belt.startX+belt.endX)/2;
                    this.areaCovered = this.areaCovered+(belt.endX-belt.startX);
                }
                else if(leftLoad > 0){
                    endCoord = belt.startX;
                    this.areaCovered = this.areaCovered+(this.endX-belt.startX);
                }
                else if(rightLoad > 0)
                {
                    endCoord = belt.endX;
                    this.areaCovered = this.areaCovered+(this.startX-belt.endX);
                }
                double goLeft = endCoord-this.startX;
                double goRight = this.endX-endCoord;
                timeTravelledIfForcedLeft += goLeft;
                timeTravelledIfForcedRite += goRight;
                double localAverage = timeTravelledIfForcedLeft+timeTravelledIfForcedRite/2;
                //avg is 2500, we big place
                //left drops @endCoord=1000 go only 1000. 1000 total
                //rite drops @endCoord=1000 go 4000. 4000 total
                //In total, we account for 2.k if we do avg
                //LeftBetter is 2500-1000
                //RiteBetter is 2500-4000
                this.timeOptimizedIfLeft += localAverage-timeTravelledIfForcedLeft;
                this.timeOptimizedIfRite += localAverage-timeTravelledIfForcedRite;
                this.bestTimeSaved = this.averageDropResponsibility-Math.min(this.timeOptimizedIfLeft, this.timeOptimizedIfRite);
            }
            double calculateRandomDistance(int dropPoint){
                if((areaCovered == (endX-startX)) && (droppedOntoFrom.size() == 0)){
                    return 0.0;
                }

                if(dropPoint <= startX || dropPoint >= endX){
                    return 0.0;
                }

                return this.halfLength + this.averageDropResponsibility;
            }

            double calculateTimeSpentOnThisBeltGivenAllRandom(){
                if((areaCovered == (endX-startX)) && (droppedOntoFrom.size() == 0)){
                    return 0.0;
                }

                //Imagine half area covered. Area 1000, 500 covered. Estimated time on is 500 from dropping, but now its
                //250 from dropping
                double randomlyDroppingOnThisBelt = (endX-startX-areaCovered)/2;
                double sumOfRandomlyBeingOnPriors = 0.0;
                for(int i = 0; i < droppedOntoFrom.size(); i++){
                    //stub
                }
                return randomlyDroppingOnThisBelt + sumOfRandomlyBeingOnPriors;
            }

            @Override
            public int compareTo(Belt that) {
                //highest first via sorting
                return - Integer.compare(this.height, that.height);
            };
        }

        //Create a set of ranges from the belts, ordered by height... No no no.
        //exit point
        ArrayList<Belt> beltsByHeight = new ArrayList<>();
        ArrayList<Belt> beltsByTimeSaved = new ArrayList<>();
        //N = 5
        //H = [2, 8, 5, 9, 4]
        //A = [5000, 2000, 7000, 9000, 0]
        //B = [7000, 8000, 11000, 11000, 4000]
        for(int i = 0; i < N; i++){
            Belt belty = new Belt(H[i],A[i],B[i]);
            beltsByHeight.add(belty);
            beltsByTimeSaved.add(belty);
        }
        Collections.sort(beltsByHeight);
        //belt highest up first
        for(int beltID = 0; beltID < beltsByHeight.size(); beltID++){
            Belt currentBelt = beltsByHeight.get(beltID);
            boolean leftLoaded = false;
            boolean riteLoaded = false;
            //for all lower belts:
            for(int loadBearingBelt = beltID+1; loadBearingBelt < beltsByHeight.size(); loadBearingBelt++){
                Belt slaveBelt = beltsByHeight.get(loadBearingBelt);

                //Check to see if currentBelt is bigger and onTopOf slaveBelt. If so, cover all its area
                if(currentBelt.startX <= slaveBelt.startX && currentBelt.endX >= slaveBelt.endX){
                    slaveBelt.areaCovered = slaveBelt.halfLength*2;
                    continue;
                }

                //Account for where loads will land, rolling off of currentBelt's left/right exits
                double leftLoad, riteLoad;
                if(!leftLoaded){
                    leftLoad = slaveBelt.calculateRandomDistance(currentBelt.startX);
                    if(leftLoad > 0){
                        slaveBelt.addDroppedOntoFrom(currentBelt);
                        currentBelt.addDroppingOnto(slaveBelt);
                        leftLoaded = true;
                    }
                }
                if(!riteLoaded){
                    riteLoad = slaveBelt.calculateRandomDistance(currentBelt.endX);
                    if(riteLoad > 0){
                        slaveBelt.addDroppedOntoFrom(currentBelt);
                        currentBelt.addDroppingOnto(slaveBelt);
                        riteLoaded = true;
                    }
                }
            }
        }
        Collections.sort(beltsByTimeSaved, (b1, b2) -> {
            //descending order
            return Double.compare(b2.bestTimeSaved, b1.bestTimeSaved);
        });
        return 0.0;
    }
}
