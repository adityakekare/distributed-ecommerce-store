public class Coordinator_imp<Task> extends java.rmi.server.UnicastRemoteObject implements Coordinator {

    private Map<String, Integer> servers;
    private Integer port;
    private String name;
    private Map<String, Proposal> currentProposals;
    private int quatrum;

    protected Coordinator_imp() throws RemoteException {
        super();
        // TODO Auto-generated constructor stub
    }

    // coordinator act as proposer and learner here
    /*
     * execute here, paxos here,
     * first get the acceptor list,
     * send promise, to get quaturam
     * if so, get everyone to accept
     *
     */
    public long execute(Proposal proposal) throws RemoteException {
        long proposal_id = proposal.getID();

        // phase 0: get the acceptor list
        List<PaxoServer> acceptors = new ArrayList<>();

        for (String name : this.servers.keySet()) {
            try {
                port = servers.get(name);
                PaxoServer acceptor = (PaxoServer) Naming
                        .lookup("rmi://localhost:" + name + "/" + servers.get(name));
                acceptors.add(acceptor);
            } catch (Exception e) {
                System.out.println("this server" + name + "is not available");
            }
        }

        // phase 1: A proposer selects a proposal number n and sends a prepare
        // request with number n to a majority of acceptors.
        // phase 2: 2. (a) If the proposer receives a response to its prepare requests
        // (numbered n) from a majority of acceptors, then it sends an accept
        // request to each of those acceptors for a proposal numbered n with a
        // value v, where v is the value of the highest-numbered proposal among
        // the responses, or is any value if the responses reported no proposals.

        int yeses = 0;
        for (PaxoServer acceptor : acceptors) {
            long id = acceptor.promise(proposal); // phase 1, A,
            // Phase m2 (a) 6If the proposer receives a response to its prepare requests
            // (numbered n) from a majority of acceptors, then it sends an accept
            // request to each of those acceptors for a proposal numbered n with a
            // value v, where v is the value of the highest-numbered proposal among
            // the responses, or is any value if the responses reported no proposals.

            // count the response
            if (id == proposal.getID()) { // equals to the one, means the one rn is the highest, success
                yeses++;
            }

        }
        int accept_num = 0;
        if (yeses > quatrum) {// got the majority vote here, send the accept request, phase2 (a)
            for (PaxoServer acceptor : acceptors) {
                long accept_id = acceptor.accept(proposal);
                if (accept_id > proposal_id) {
                    return accept_id; // inform the proposal the execution failed because there is a higher id, if any
                    // accept failed

                } else {
                    accept_num++;
                }
            }
        }
        // if everyone successfully accepts, everyone learns
        if (accept_num == acceptors.size()) {
            for (PaxoServer acceptor : acceptors) {
                acceptor.learn(proposal);
            }
            return proposal_id;// return the same id means this is successful
        }
        return proposal_id / 1000; // the execution failed
    }

    /*
     * Given a port and hostname to add the server
     * save the acceptors' port and name into key
     */
    public void addAcceptor(int port, String hostname) throws RemoteException {
        this.servers.put(name, port);
    }

}