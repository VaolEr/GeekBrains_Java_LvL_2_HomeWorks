package Lesson6.Network;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;
import java.util.Scanner;

public class Network {

    private static final int SERVER_PORT = 8189;
    private static final String HOST = "localhost";
    private static final int PORT = 8189;

    private String host;
    private int port;

    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    private volatile String  inputMessage = "";
    private volatile String outputMessage;

    private String networkID;

    public Network(@org.jetbrains.annotations.NotNull NetworkRole role) {
        Socket socket;
        switch (role) {
            case SERVER -> {
                networkID = "Server";
                try {
                    ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
                    System.out.println("Waiting for new connection...");
                    socket = serverSocket.accept();
                    System.out.println("Client has been connected!");
                    inputStream = new DataInputStream(socket.getInputStream());
                    outputStream = new DataOutputStream(socket.getOutputStream());
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("Connection broken!");
                }
            }
            case CLIENT -> {
                networkID = "Client";
                try {
                    System.out.println("Connecting to server...");
                    socket = new Socket(HOST, PORT);
                    System.out.println("Connection successful!");
                    inputStream = new DataInputStream(socket.getInputStream());
                    outputStream = new DataOutputStream(socket.getOutputStream());
                } catch (IOException e) {
                    System.err.println("Connection was not set!");
                    e.printStackTrace();
                }
            }
        }
    }

    public Network(@org.jetbrains.annotations.NotNull NetworkRole role, String host, int port) {
        Socket socket;
        switch (role) {
            case SERVER -> {
                networkID = "Server";
                try {
                    ServerSocket serverSocket = new ServerSocket(port);
                    System.out.println("Waiting for new connection...");
                    socket = serverSocket.accept();
                    System.out.println("Client has been connected!");
                    inputStream = new DataInputStream(socket.getInputStream());
                    outputStream = new DataOutputStream(socket.getOutputStream());
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("Connection broken!");
                }
            }
            case CLIENT -> {
                networkID = "Client";
                this.host = host;
                this.port = port;
                try {
                    System.out.println("Connecting to server...");
                    socket = new Socket(this.host, this.port);
                    System.out.println("Connection successful!");
                    inputStream = new DataInputStream(socket.getInputStream());
                    outputStream = new DataOutputStream(socket.getOutputStream());
                } catch (IOException e) {
                    System.err.println("Connection was not set!");
                    e.printStackTrace();
                }
            }
        }
    }

    public String getInputMessage() {
        return inputMessage;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getNetworkID() {
        return networkID;
    }

    public void setNetworkID(String networkID) {
        this.networkID = networkID;
    }

    public void waitMessage() {
        Thread getMessageThread = new Thread(() -> {
            try {
                while (true) {
                    inputMessage = inputStream.readUTF();
                    System.out.println(inputMessage);
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Connection broken!");
            }
        });
        getMessageThread.setDaemon(true);
        getMessageThread.start();
    }

    public void sendMessageConsoleInputMode(){
        Thread writeMessageThread = new Thread(() -> {
            Scanner in = new Scanner(System.in);
            while(true) {
                try {
                    outputStream.writeUTF(networkID + ": " + in.nextLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        writeMessageThread.setDaemon(true);
        writeMessageThread.start();
    }

    public void sendMessage(String message){
        Thread writeMessageThread = new Thread(() -> {
            int i = 0;
            while(i < 1) {
                try {
                    outputStream.writeUTF(networkID + ": " + message);
                    i = 0;
                } catch (IOException e) {
                    e.printStackTrace();
                    i = 0;
                }
                i++;
            }
        });
        writeMessageThread.setDaemon(true);
        writeMessageThread.start();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Network network = (Network) o;
        return port == network.port &&
                Objects.equals(host, network.host) &&
                Objects.equals(inputStream, network.inputStream) &&
                Objects.equals(outputStream, network.outputStream) &&
                Objects.equals(inputMessage, network.inputMessage) &&
                Objects.equals(outputMessage, network.outputMessage) &&
                networkID.equals(network.networkID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(host, port, inputStream, outputStream, inputMessage, outputMessage, networkID);
    }
}
