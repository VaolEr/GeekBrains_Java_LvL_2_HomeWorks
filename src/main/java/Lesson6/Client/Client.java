package Lesson6.Client;

import Lesson6.Network.Network;
import Lesson6.Network.NetworkRole;

public class Client {

    public static void main(String[] args) {
        Network client = new Network(NetworkRole.CLIENT);
        client.waitMessage();
        client.sendMessageConsoleInputMode();
        while(true){
            if(client.getInputMessage().equals("Server: Connection is closing...")){
                break;
            }
        }
        System.out.println("Server has been closed.");
    }
}
