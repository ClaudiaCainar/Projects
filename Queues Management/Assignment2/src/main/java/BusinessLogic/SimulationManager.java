package BusinessLogic;

import Model.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SimulationManager implements Runnable {

    private int N; // nr clients
    private int Q; // nr queues
    private int maxClientPerServer;
    private int maxSimulationTime;
    private int minArrivalTime;
    private int maxArrivalTime;
    private int minServiceTime;
    private int maxServiceTime;
    private Scheduler scheduler;
    private List<Client> clients;
    private List<Server> servers;
    private FileWriter fileWriter;
    private BufferedWriter bufferedWriter;

    public SimulationManager(int n, int q, int maxSimulationTime, int minArrivalTime, int maxArrivalTime, int minServiceTime, int maxServiceTime) {
        N = n;
        Q = q;
        this.maxSimulationTime = maxSimulationTime;
        this.minArrivalTime = minArrivalTime;
        this.maxArrivalTime = maxArrivalTime;
        this.minServiceTime = minServiceTime;
        this.maxServiceTime = maxServiceTime;
        this.clients = new ArrayList<>();
        this.servers = new ArrayList<>();
        this.scheduler = new Scheduler(Q, N, servers);
        scheduler.changeStrategy(Strategy.SelectionPolicy.SHORTEST_TIME);
    }

    public void generateClients() {
        for (int i = 0; i < N; i++) {
            Random rand = new Random();
            int arrivalTime = rand.nextInt(maxArrivalTime - minArrivalTime) + minArrivalTime;
            int serviceTime = rand.nextInt(maxServiceTime - minServiceTime) + minServiceTime;
            Client client = new Client(i + 1, arrivalTime, serviceTime);
            clients.add(client);
        }
    }

    @Override
    public void run() {
        // initializam toate variabilele de care avem nevoie
        int currentTime = 0;
        float averageWaitingTime = 0;
        float averageServiceTime = 0;
        int nrMaxClientsPeakHour = 0;
        int peakHour = 0;
        boolean isEmpty = false;

        for (Client client : clients) {
            averageServiceTime += client.getServiceTime(); //calculam totalul timpului de servire al clientilor
        }

        try {
            fileWriter = new FileWriter("logevents.txt");
            bufferedWriter = new BufferedWriter(fileWriter);

            while (currentTime <= maxSimulationTime && isEmpty == false) { //cat timp timpul curent e mai mic sau egal decat timpul de simulare
            // si cat timp simularea nu este goala
                while (currentTime <= maxSimulationTime) {
                    List<Client> clientsToDispatch = new ArrayList<>(); //adaugam in lista toti clientii cu acelasi timp de sosire
                    for (Client client : clients) { // cautam clientii cu acelasi arrivalTime curent
                        if (client.getArrivalTime() == currentTime) {
                            clientsToDispatch.add(client);
                        }
                    }
                    for (Client client : clientsToDispatch) { // asignam toti clientii cu acelasi timp de sosire curent unor cozi
                        scheduler.dispatchClient(client);
                        clients.remove(client);
                    }

                    bufferedWriter.write("Time " + currentTime + "\n");
                    if(clients.isEmpty())
                        bufferedWriter.write("Waiting clients: none\n");
                    else
                        bufferedWriter.write("Waiting clients: " + clients.toString() + "\n");
                    bufferedWriter.write(scheduler.toString() + "\n\n");

                    int maxClientsPerServer = 0;
                    for (Server server : servers) {
                        averageWaitingTime += server.getWaitingPeriod().get();
                        maxClientsPerServer += server.getClients().size();
                    }

                    if (maxClientsPerServer >= nrMaxClientsPeakHour) {
                        nrMaxClientsPeakHour = maxClientsPerServer;
                        peakHour = currentTime;
                    }

                    if (clients.isEmpty()) {
                        isEmpty = true;
                        for (Server server : servers) {
                            if (!server.getClients().isEmpty()) {
                                isEmpty = false;
                                break;
                            }
                        }
                    }

                    scheduler.dispatchWaitingClients();

                    if (isEmpty == true) {
                        break;
                    }

                    currentTime++;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    bufferedWriter.flush();
                }
            }

            bufferedWriter.write("Average waiting time: " + averageWaitingTime / (Q * maxSimulationTime) + "\n");
            bufferedWriter.write("Average service time: " + (averageServiceTime / N) + "\n");
            bufferedWriter.write("Peak hour: " + peakHour + " with " + nrMaxClientsPeakHour + " clients");

            bufferedWriter.close();
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
