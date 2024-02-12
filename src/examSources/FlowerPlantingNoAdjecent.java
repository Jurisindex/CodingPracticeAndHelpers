package examSources;
/*
    You have n gardens, labeled from 1 to n, and an array paths where paths[i] = [xi, yi] describes a bidirectional path between garden xi to garden yi. In each garden, you want to plant one of 4 types of flowers.

    All gardens have at most 3 paths coming into or leaving it.

    Your task is to choose a flower type for each garden such that, for any two gardens connected by a path, they have different types of flowers.

    Return any such a choice as an array answer, where answer[i] is the type of flower planted in the (i+1)th garden. The flower types are denoted 1, 2, 3, or 4. It is guaranteed an answer exists.



    Example 1:

    Input: n = 3, paths = [[1,2],[2,3],[3,1]]
    Output: [1,2,3]
    Explanation:
    Gardens 1 and 2 have different types.
    Gardens 2 and 3 have different types.
    Gardens 3 and 1 have different types.
    Hence, [1,2,3] is a valid answer. Other valid answers include [1,2,4], [1,4,2], and [3,2,1].

    Example 2:

    Input: n = 4, paths = [[1,2],[3,4]]
    Output: [1,2,1,2]

    Example 3:

    Input: n = 4, paths = [[1,2],[2,3],[3,4],[4,1],[1,3],[2,4]]
    Output: [1,2,3,4]



    Constraints:

        1 <= n <= 104
        0 <= paths.length <= 2 * 104
        paths[i].length == 2
        1 <= xi, yi <= n
        xi != yi
 */
import java.util.*;

public class FlowerPlantingNoAdjecent
{
    public static int[] gardenNoAdj(int n, int[][] paths)
    {
        //Map<Integer, Integer> flowerMap = new HashMap<>();
        //Create a map with GardenInt => List<ConnectedGardenInt>
        //for each entry, I do the following: Plant flower 1 in the first one, and flowers 2-4 on its neighbors,
        //depending on connection (1=>2,6,7), gets flowers 1,2,3,4 planted at => [1,2,6,7] respectively.
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            graph.put(i, new ArrayList<>());
        }

        for (int[] path : paths) {
            int garden1 = path[0];
            int garden2 = path[1];
            graph.get(garden1).add(garden2);
            graph.get(garden2).add(garden1);
        }

        Map<Integer, Integer> flowerMap = new HashMap<>();
        Set<Integer> flowerIDs = new HashSet<>();
        flowerIDs.addAll(Arrays.asList(1,2,3,4));
        for(Integer gardenInt : graph.keySet()) {
            //0-4. 0 is unset. 1-4 are flower types
            int flowerId = flowerMap.getOrDefault(gardenInt, 0);
            if (flowerId != 0) {
                continue;
            }
            Set<Integer> gardenFlowerChoices = new HashSet<>();
            gardenFlowerChoices.addAll(flowerIDs);
            for (Integer neighbourGarden : graph.get(gardenInt)) {
                gardenFlowerChoices.remove(flowerMap.getOrDefault(neighbourGarden, 0));
            }
            Iterator<Integer> iterator = gardenFlowerChoices.iterator();
            flowerMap.put(gardenInt, iterator.next());
        }
        int[] result = new int[n];
        for (int i = 0; i < n; i++)
        {
            result[i] = flowerMap.get(i + 1); // Adjust index
        }
        return result;
    }
}
//Accepted