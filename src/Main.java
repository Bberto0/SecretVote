
public class Main {
    public static void main(String[] args) {

        // Creating an array of 20 voters
        Voter[] voters = new Voter[20];
        Thread[] threads = new Thread[20];

        String[] candidates = {"Emily", "Paolo", "Adam"};

        DigitalUrn myUrn = new DigitalUrn(candidates);

        // creating every voter with a random chosen candidate
        for(int i = 0; i < voters.length; i++)
        {
            // calculating a random index to assign different candidates to each voter
            int candidate_index = (int)(Math.random() * candidates.length);

            voters[i] = new Voter(String.valueOf(i + 1), candidates[candidate_index], myUrn);

            // associating each voter to a thread
            threads[i] = new Thread(voters[i]);

            threads[i].start();
        }

        // waiting until every thread has finished
        for(Thread t : threads)
        {
            try {

                t.join();

            } catch(InterruptedException e)
            {
                Thread.currentThread().interrupt();
            }
        }

        // printing the final results
        myUrn.printPollResults();
    }
}
