
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.NotBoundException;
 
public class Server 
{
    private static final String SERVER_NAME = "Server";
 
    private String  port;
    public Server(String port)    
    {
        this.port = port;
    }
 
    public void runServer() throws IllegalArgumentException, NotBoundException, RemoteException 
    {
        DAOImpl daoImpl = new DAOImpl();
        Registry registry = LocateRegistry.createRegistry(Integer.parseInt(port, 10));
        registry.rebind(SERVER_NAME, daoImpl);
    }
}
