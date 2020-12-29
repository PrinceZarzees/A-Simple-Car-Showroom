package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import tcpdiff.Car;
import util.NetworkUtil;

import java.io.IOException;
import java.util.List;

public class ShowMHomePage {
    public Button logout;
    public Button viewallcar;
    public Button Add;
    Client client;

    public void logout(ActionEvent actionEvent) throws IOException {
        client.showStartPage();
    }

    public void setClient(Client client) {
        this.client=client;
    }

    public void viewallcar(ActionEvent actionEvent) throws IOException {
        NetworkUtil networkUtil=client.networkUtil;
        networkUtil.write("viewallcar");
        List<Car> Carlist;
        Carlist=(List<Car>)networkUtil.read();
        client.showMviewallcar(Carlist);
    }

    public void Add(ActionEvent actionEvent) throws IOException {
        client.add();
    }
}
