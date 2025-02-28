package com.pickcomplete.main.server_components;

import com.pickcomplete.main.properties.PropertiesClass;
import io.socket.client.IO;

import io.socket.client.Socket;
import io.socket.emitter.Emitter;

import java.net.URISyntaxException;

public class SocketClient {

    private Socket socket;

    public void socketInitialization(String url) throws URISyntaxException {
        IO.Options options = new IO.Options();
        options.reconnection= true;
        socket = IO.socket(url);

        //socket.on

        socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
                    @Override
                    public void call(Object... args) {
                        System.out.println("Connected");
                    }

                })
                .on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {
                    @Override
                    public void call(Object... args) {
                        System.out.println("Disonnected");
                    }

                });
        socket.connect();
    }

    public Socket getSocket() {
        return socket;
    }
}
