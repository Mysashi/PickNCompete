package com.pickcomplete.main.model;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;
import com.pickcomplete.main.server_components.PickServer;
import io.socket.client.Socket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URISyntaxException;
import java.util.ArrayList;


// Хранит локальные значения сервера
public class Room {

    private final ArrayList<Team> teamList = new ArrayList<Team>();

    private final ArrayList<Jury> juryList = new ArrayList<Jury>();




    public Room(int teamNum, int JuryNum) {

        //Инициализируем сервер
        //Заполняем команды
        fillTeams(teamNum);
    }


    public void setModule(SocketIOServer server) {
        System.out.println(server);
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


//    public Configuration getServer() {
//        return server.getConfiguration();
//    }


}
