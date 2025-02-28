package com.pickcomplete.main.model;

import com.corundumstudio.socketio.SocketIOServer;
import com.pickcomplete.main.server_components.PickServer;
import com.pickcomplete.main.server_components.SocketClient;
import io.socket.client.Socket;

import java.net.URISyntaxException;
import java.util.ArrayList;


// Хранит локальные значения сервера
public class Room {

    private final ArrayList<Team> teamList = new ArrayList<Team>();

    private final ArrayList<Jury> juryList = new ArrayList<Jury>();

    private final PickServer pickServer = new PickServer();

    private final SocketClient socket = new SocketClient();

    public Room(String url, int teamNum, int JuryNum) {

        //Инициализируем сервер
        setServer(url);
        //Заполняем команды
        fillTeams(teamNum);
    }


    private void fillTeams(int number) {
        for (int i = 0; i < number; i++) {
            teamList.add(new Team());
        }
    }

    public ArrayList<Team> getTeams() {
        return teamList;
    }

    public ArrayList<Jury> getJury() {
        return juryList;
    }

    private void setServer(String url) {
        pickServer.init();
        try {
            socket.socketInitialization(url);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public SocketIOServer getServer() {
        return pickServer.getServer();
    }

    public Socket getSocketClient() {
        return socket.getSocket();
    }

}
