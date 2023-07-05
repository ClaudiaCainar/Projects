package BusinessLogic;

import Model.Client;
import Model.Server;

import java.util.List;

public class TimeStrategy implements Strategy{

    @Override
    public void addClient(List<Server> servers, Client client) {
        Server selectedServer = null;
        int minTime = Integer.MAX_VALUE; // luam cea mai mare valoare pentru a compara cu timpul de la celelalte servere si a gasi cel mai mic timp
        for(Server server : servers)
        {
            int serverTime = server.getWaitingPeriod().intValue();
            if(serverTime < minTime)
            {
                selectedServer = server;
                minTime = serverTime;
            }
        }
        selectedServer.addClient(client);
    }
}
