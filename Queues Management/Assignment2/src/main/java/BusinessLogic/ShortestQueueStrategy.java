package BusinessLogic;

import Model.Client;
import Model.Server;

import java.util.List;

public class ShortestQueueStrategy implements Strategy{

    @Override
    public void addClient(List<Server> servers, Client client) {
        Server shortestQueueServer = servers.get(0);
        int shortestQueueSize = shortestQueueServer.getClients().size();

        for(int i = 1; i < servers.size(); i++)
        {
            Server server = servers.get(i);
            int queueSize = server.getClients().size();
            if(queueSize < shortestQueueSize)
            {
                shortestQueueSize = queueSize;
                shortestQueueServer = server;
            }
        }

        try {
            shortestQueueServer.getClients().put(client);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
