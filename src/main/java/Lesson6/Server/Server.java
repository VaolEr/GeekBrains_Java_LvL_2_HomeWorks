package Lesson6.Server;

import Lesson6.Network.Network;
import Lesson6.Network.NetworkRole;

public class Server {

    public static void main(String[] args) {
        Network server = new Network(NetworkRole.SERVER);
        server.waitMessage();
        server.sendMessageConsoleInputMode();
        while(true){
            if(server.getInputMessage().equals("Client: /end")){
                server.sendMessage("Connection is closing...");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
        System.out.println("Server has been closed.");
    }
}
