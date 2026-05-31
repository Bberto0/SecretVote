public enum VoteOutcome {

    ALREADY_VOTED("You already voted"),
    INVALID_CANDIDATE("The candidate you voted does not exist"),
    REGISTERED_VOTE("The vote has been registered correctly");

    VoteOutcome(String msg) {}
}
