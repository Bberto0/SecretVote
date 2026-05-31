import java.util.concurrent.ThreadLocalRandom;

public class Voter implements Runnable{

    private final String studentId;
    private final String chosen_candidate;
    private final DigitalUrn urn;

    public Voter(String id, String candidate, DigitalUrn urn)
    {

        // check if the id given is valid
        if(id == null || Integer.parseInt(id) == 0)
            throw new IllegalArgumentException("The id inserted is invalid");
        else
            this.studentId = id;

        // check if the urn given is valid
        if(urn == null)
            throw new IllegalArgumentException("The urn inserted is invalid");
        else
            this.urn = urn;

        this.chosen_candidate = candidate;
    }

    @Override
    public void run()
    {
        try {

            Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 3000));
            VoteOutcome outcome = urn.vote(this);

            if(outcome == VoteOutcome.REGISTERED_VOTE)
                System.out.println("The " + studentId + " student has voted");

        } catch(InterruptedException e) {

            Thread.currentThread().interrupt();
        }
    }

    public String getChosen_candidate() { return chosen_candidate; }

    public String getStudentId() { return studentId; }
}