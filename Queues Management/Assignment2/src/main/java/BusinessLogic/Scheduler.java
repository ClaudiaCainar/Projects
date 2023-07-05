package BusinessLogic;

import Model.Client;
import Model.Server;

import java.util.ArrayList;
import java.util.List;

public class Scheduler {

    private List<Server> servers;
    private List<Thread> threads;
    private int maxNoServers;
    private int maxClientPerServer;
    private Strategy strategy;

    public Scheduler(int maxNoServers, int maxClientPerServer, List<Server> servers)
    {
        this.servers = servers;
        this.maxNoServers = maxNoServers;
        this.maxClientPerServer = maxClientPerServer;
        this.strategy = new TimeStrategy();
        this.threads = new ArrayList<>();

        for (int i = 0; i < maxNoServers; i++) {
            Server server = new Server(maxClientPerServer);
            this.servers.add(server);
            Thread t = new Thread(server);
            threads.add(t);
            t.start();
        }
    }

    void changeStrategy(Strategy.SelectionPolicy policy)
    {
        if(policy == Strategy.SelectionPolicy.SHORTEST_QUEUE)
            strategy = new ShortestQueueStrategy();
        else
            if(policy == Strategy.SelectionPolicy.SHORTEST_TIME)
                strategy = new TimeStrategy();
    }

    public void dispatchClient(Client client)
    {
        strategy.addClient(servers, client);
    }

    public void dispatchWaitingClients()
    {
        for(Server server : servers)
        {
            server.serveClients();
        }
    }

    public String toString()
    {
        StringBuilder s = new StringBuilder();
        int count = 1;
        for(Server server: servers)
        {
            s.append("Queue " + count + ":" + server.toString() + ";" + "\n");
            count = count + 1;
        }
        return s.toString();
    }
}
