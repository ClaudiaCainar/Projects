package Main;

import BusinessLogic.*;
import Model.*;
import GUI.*;

import java.util.ArrayList;
import java.util.List;

public class MainClass {
    public static void main( String[] args )
    {
        //SimulationManager simulationManager1 = new SimulationManager(4, 2, 60, 2, 30, 2, 4);
        //simulationManager1.generateClients();
        //simulationManager1.createServers();
        //Thread thread1 = new Thread(simulationManager1);
        //thread1.start();
        SimulationManager simulationManager2 = new SimulationManager(50, 5, 60, 2, 40, 1, 7);
        simulationManager2.generateClients();
        Thread thread2 = new Thread(simulationManager2);
        thread2.start();
        //SimulationManager simulationManager3 = new SimulationManager(1000, 20, 200, 10, 100, 3, 9);
        //simulationManager3.generateClients();
        //Thread thread3 = new Thread(simulationManager3);
        //thread3.start();
    }

}
