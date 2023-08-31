import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.function.Consumer;

public class Client extends Thread{
    Socket socketClient;
    ObjectOutputStream out;
    ObjectInputStream in;
    private Consumer<Serializable> callback;
    private boolean connectedToServer;
    private boardController controller;

    Client(Consumer<Serializable> call){

        callback = call;
        this.controller = controller;
    }

    public void run() {

        try {
            this.connectedToServer = true;
            socketClient= new Socket("127.0.0.1",5555);
            out = new ObjectOutputStream(socketClient.getOutputStream());
            in = new ObjectInputStream(socketClient.getInputStream());
            socketClient.setTcpNoDelay(true);
        }
        catch(Exception e) {
            this.connectedToServer = false;
            System.out.println("What are you doing");
        }

        while(true) {

            try {
                String message = in.readObject().toString();
                callback.accept(message);

            }
            catch(Exception e) {}
        }

    }

    public boolean isConnectedToServer(){
        return this.connectedToServer;
    }

    /*public static boolean isServerOpen(String ipAddress, int port) {
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(ipAddress, port), 1000);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

     */

    public void send(MessageData data) {

        try {
            out.writeObject(data);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


}
