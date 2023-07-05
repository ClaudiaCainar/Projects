package BusinessLogic;

import Model.*;

import java.util.List;

public interface Strategy {

    public void addClient(List<Server> servers, Client client);

    public enum SelectionPolicy{
        SHORTEST_QUEUE, SHORTEST_TIME;
    }

}
