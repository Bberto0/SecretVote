import javax.crypto.spec.PSource;
import java.util.*;
import java.util.Set;

/*
  import java.util.HashMap;
  import java.util.Map;
  import java.util.HashSet;
  import java.util.Set;
*/

public class DigitalUrn {

    private final Set<String> voters = new HashSet<>();
    private final Map<String, Integer> candidates;

    public DigitalUrn(String[] candidatesNames)
    {

        if(candidatesNames == null || candidatesNames.length == 0)
            throw new IllegalArgumentException();

        this.candidates = new HashMap<>(candidatesNames.length);

        // adding the key and value to the map, initializing the number of votes to 0
        for(String c : candidatesNames)
            this.candidates.put(c, 0);

    }

    public boolean candidateExists(String c)
    {
        return candidates.containsKey(c);
    }

    public synchronized VoteOutcome vote(Voter v) {

        // checking if the voter already voted
        if(voters.contains(v.getStudentId()))
            return VoteOutcome.ALREADY_VOTED;

        //checking if the candidate exists
        if(!candidateExists(v.getChosen_candidate()))
            return VoteOutcome.INVALID_CANDIDATE;

        voters.add(v.getStudentId());

        // using Integer::sum instead of the full lambda expression
        candidates.merge(v.getChosen_candidate(), 1, Integer::sum);

        return VoteOutcome.REGISTERED_VOTE;
    }

    public String findWinner()
    {
        int maxVotes = -1;
        String winner = "";

        for(Map.Entry<String, Integer> c : candidates.entrySet())
        {
            if(c.getValue() > maxVotes)
            {
                maxVotes = c.getValue();
                winner = c.getKey();
            }
        }

        return winner;
    }

    public void printPollResults()
    {

        System.out.println("\nRESULTS:");
        for(Map.Entry<String, Integer> c : candidates.entrySet())
        {
            if(c.getKey().equalsIgnoreCase(findWinner()))

                System.out.println("The winner is " + c.getKey() + " with " + c.getValue() + " votes");
            else

                System.out.println("Candidate: " + c.getKey() + " with " + c.getValue() + " votes");
        }
    }
}
