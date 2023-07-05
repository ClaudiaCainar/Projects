package Model;

public class Client {

    private int ID;
    private int arrivalTime;
    private int serviceTime;

    public Client(int ID, int arrivalTime, int serviceTime)
    {
        this.ID = ID;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
    }

    public int getId()
    {
        return ID;
    }

    public int getServiceTime()
    {
        return serviceTime;
    }

    public void setServiceTime(int serviceTime)
    {
        this.serviceTime = serviceTime;
    }

    public int getArrivalTime()
    {
        return arrivalTime;
    }

    @Override
    public String toString() {
        return "("+ ID + ", " + serviceTime + ", " + arrivalTime + ")";
    }
}
