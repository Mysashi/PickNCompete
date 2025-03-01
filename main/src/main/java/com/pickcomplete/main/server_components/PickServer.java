package com.pickcomplete.main.server_components;

import com.corundumstudio.socketio.*;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import com.pickcomplete.main.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.UUID;


@Component
public class PickServer {

    private Room room;

    private int administator = 0;

    private ArrayList<UUID> localUsers = new ArrayList<>();

    private UUID adminId = null;

    @Autowired
    public PickServer(SocketIOServer server) {
        System.out.println(server.getConfiguration().getPort());
        System.out.println(server.getConfiguration().getHostname());
        System.out.println("SocketIO server started");
        server.addConnectListener(this.onConnected());
        server.addDisconnectListener(this.onDisconnected());
        server.addEventListener("JoinTeam", String.class, new DataListener<String>() {
            @Override
            public void onData(SocketIOClient socketIOClient, String s, AckRequest ackRequest) throws Exception {
                System.out.println("DATA" + s);
                server.getBroadcastOperations().sendEvent("JoinTeam", s);
            }
        });
    }

    private ConnectListener onConnected() {
        return client -> {
            administator += 1;

            if (administator == 1) {
                adminId = client.getSessionId();
            }
            client.sendEvent("setAdmin", adminId);
            localUsers.add(client.getSessionId());
        };
    }

    private DisconnectListener onDisconnected() {
        return client -> {
            System.out.println(("Client[{}] - Disconnected from chat module." + client.getSessionId().toString()));
        };
    }


}
