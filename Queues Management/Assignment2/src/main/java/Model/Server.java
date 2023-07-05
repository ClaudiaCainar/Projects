package Model;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.lang.Thread;

public class Server implements Runnable{

    private BlockingQueue<Client> clients;
    private AtomicInteger waitingPeriod;

    public Server(int maxClientsPerServer)
    {
        this.clients = new ArrayBlockingQueue<>(maxClientsPerServer);
        this.waitingPeriod = new AtomicInteger(0);
    }

    public void run() {
        while (!clients.isEmpty()) {
            Client client = clients.peek();
            if (client != null) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                client.setServiceTime(client.getServiceTime() - 1);
                if (client.getServiceTime() == 0) {
                    clients.poll();
                }
                waitingPeriod.getAndIncrement();
            }
        }
    }

    public AtomicInteger getWaitingPeriod()
    {
        return waitingPeriod;
    }

    public BlockingQueue<Client> getClients() {
        return clients;
    }

    public void addClient(Client client) {
        try {
            clients.put(client);
            waitingPeriod.addAndGet(client.getServiceTime());
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    public void serveClients() {
        if (!clients.isEmpty()) {
            Client client = clients.peek();
            int serviceTime = client.getServiceTime();
            if (serviceTime > 0) {
                client.setServiceTime(serviceTime - 1);
            }
            if (serviceTime == 0) {
                clients.remove();
            }
        }
    }

    public String toString()
    {
        if(!clients.isEmpty())
            return clients + ", ";
        else
            return "closed";
    }
}
