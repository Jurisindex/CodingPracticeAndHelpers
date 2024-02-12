package test;

import examSources.FlowerPlantingNoAdjecent;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FlowerPlantingNoAdjecentTest
{

    //int[] gardenNoAdj(int n, int[][] paths)

    @Test
    void gardenNoAdjTest1()
    {
        int n = 4;
        int[][] paths = {
                {1, 2},
                {2, 3},
                {3, 4},
                {4, 1},
                {1, 3},
                {2, 4}
        };
        int[] output = FlowerPlantingNoAdjecent.gardenNoAdj(n, paths);
        //This is hard to test, and will have to be a little manually verified. Unfortunately.
        //But it is a clear sign of fast failure, if the length is not as expected.
        assertEquals(4, output.length);
    }

    @Test
    void gardenNoAdjTest2()
    {
        int n = 5;
        int[][] paths = {
                {3, 4},
                {4, 5},
                {3, 2},
                {5, 1},
                {1, 3},
                {4, 2}
        };
        int[] output = FlowerPlantingNoAdjecent.gardenNoAdj(n, paths);
        //This is hard to test, and will have to be a little manually verified. Unfortunately.
        //But it is a clear sign of fast failure, if the length is not as expected.
        assertEquals(5, output.length);
    }
}