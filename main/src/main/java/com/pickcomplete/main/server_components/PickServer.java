package com.pickcomplete.main.server_components;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;

public class PickServer {

    public SocketIOServer getServer() {
        return server;
    }

    private SocketIOServer server;

    public void init() {
        Configuration config = new Configuration();
        config.setPort(8092);
        config.setHostname("localhost");

        server = new SocketIOServer(config);

        server.addConnectListener(
                (client) -> {
                    System.out.println("Client has Connected!");
                });

        server.addEventListener("MESSAGE", String.class,
                (client, message, ackRequest) -> {
                    System.out.println("Client said: " + message);
                });

        server.start();
    }
}
