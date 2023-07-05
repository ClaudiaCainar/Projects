package GUI;

import Model.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class SimulationFrame {

    private List<JLabel> serverLabels;
    private JLabel timeLabel;

    private JFrame frame;
    private JTextField clients;
    private JTextField servers;
    public SimulationFrame(int numServers)
    {
        serverLabels = new ArrayList<>();
        for(int i = 0; i < numServers; i++)
        {
            JLabel label = new JLabel();
            serverLabels.add(label);
        }
        timeLabel = new JLabel();

    }

    public void update(List<Server> servers, int currentTime)
    {
        int serverCount = Math.min(serverLabels.size(), servers.size()); // Use the minimum size
        for(int i = 0; i < serverCount; i++)
        {
            Server server = servers.get(i);
            JLabel label = serverLabels.get(i);
            if(server.getClients().isEmpty())
            {
                label.setText("Server " + (i+1) + " - idle");
            }
            else
            {
                Client currentClient = server.getClients().peek();
                label.setText("Server " + (i+1) + " - Client " + currentClient.getId());
            }
        }
        timeLabel.setText("Time: " + currentTime);
    }
}
