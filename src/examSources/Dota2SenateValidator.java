package examSources;

public class Dota2SenateValidator
{
    public static String predictPartyVictory(String senate)
    {
        boolean allSame = false;
        while(!allSame)
        {
            for(int i = 0; i < senate.length(); i++)
            {
                char currentParty = senate.charAt(i);
                int nextOppositionIndex = getNextOppositePartyIndex(senate, currentParty, i);
                if(nextOppositionIndex == -1)
                {
                    return String.valueOf(currentParty);
                }
                else if(nextOppositionIndex < i)
                {
                    i--;
                }
                //.remove(nextOppositionIndex);
            }
        }
        return String.valueOf(senate.charAt(0));
    }

    public static int getNextOppositePartyIndex(String senate, char currentParty, int index)
    {
        for(int i = index; i < senate.length(); i++)
        {
            if(senate.charAt(i) != currentParty)
            {
                return i;
            }
        }
        for(int i = 0; i < index; i++)
        {
            if(senate.charAt(i) != currentParty)
            {
                return i;
            }
        }
        return -1;
    }
}
