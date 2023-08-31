import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Objects;
import java.util.function.Consumer;

public class Server {
    int count = 1;
    ArrayList<ClientThread> clients = new ArrayList<ClientThread>(); //array of all the clients that are currently connected to the server
    TheServer server;
    private Consumer<Serializable> callback;

    Server(Consumer<Serializable> call){

        callback = call;
        server = new TheServer();
        server.start();
    }

    public class TheServer extends Thread{

        public void run() {

            try(ServerSocket mysocket = new ServerSocket(5555);){
                System.out.println("Server is waiting for a client!");


                while(true) {

                    ClientThread c = new ClientThread(mysocket.accept(), count);
                    callback.accept("client has connected to server: " + "client #" + count);
                    clients.add(c);
                    c.start();

                    count++;

                }
            }//end of try
            catch(Exception e) {
                callback.accept("Server socket did not launch");
            }
        }//end of while
    }
    class ClientThread extends Thread{


        Socket connection;
        int count; //The count for a specific Client Thread. Meaning if two clients were made the second client would have the count value as 2 while the first one is 1
        ObjectInputStream in;
        ObjectOutputStream out;

        ClientThread(Socket s, int count){
            this.connection = s;
            this.count = count;
        }

        //Function used to send a message to all clients
        public synchronized void updateClients(String message) {
            for(int i = 0; i < clients.size(); i++) { //Loop through all clients sending the message to all clients
                ClientThread t = clients.get(i);
                if(t.count != this.count){
                    try {
                        t.out.writeObject(message);
                    }
                    catch(Exception e) {}
                }
            }
        }

        //Function used to find if the value c is in the clients arrayList. The ClientThread is returned if found
        private synchronized ClientThread isInClients(int c){
            for(ClientThread ct : clients){
                if(c == ct.count){
                    return ct;
                }
            }
            return null;
        }

        //Function used to check if a String can be converted to an integer. the Integer is returned if it can and -1 is retuned if it cant
        private synchronized int checkInt(String s ){
            try{
                int c = Integer.parseInt(s);
                return c;
            }
            catch(Exception e){
                return -1;
            }
        }

        //Function used to show every online client
        public synchronized void showListOfClients(){
            for(int i = 0; i < clients.size(); i++){

                try{
                    ClientThread t = clients.get(i);
                    this.out.writeObject("clients on server: client #" + t.count);
                }
                catch(Exception e) {}

            }
        }

        public void run(){
            try {
                in = new ObjectInputStream(connection.getInputStream());
                out = new ObjectOutputStream(connection.getOutputStream());
                connection.setTcpNoDelay(true);
            }
            catch(Exception e) {
                System.out.println("Streams not open");
            }
            //showListOfClients();
            //updateClients("new client on server: client #"+count);

            while(true) {
                try {

                    MessageData data2 = (MessageData) in.readObject();
                    String dataMessage = data2.getMessage();
                    String sendTo = data2.getSendTo();

                    if(Objects.equals(sendTo, "all")){
                        callback.accept("client: " + count + " sent: " + dataMessage + " to " + "all clients");
                        updateClients("client #"+count+" said: "+dataMessage);
                    }

                    updateClients(dataMessage);


                }
                catch(Exception e) {
                    callback.accept("OOOOPPs...Something wrong with the socket from client: " + count + "....closing down!");
                    updateClients("Client #"+count+" has left the server!");
                    clients.remove(this);
                    break;
                }
            }
        }//end of run


    }

}
