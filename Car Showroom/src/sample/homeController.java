package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import tcpdiff.Car;
import util.NetworkUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class homeController {
    public Client client;
    public Button logoutbutton;
    public Button viewallcar;
    public Button searchreg;
    public Button searchbymm;

    public void setClient(Client client)
    {
        this.client=client;
    }

    public void logout() throws Exception {
        client.showStartPage();
    }

    public void viewallcar() throws Exception{
        NetworkUtil networkUtil=client.networkUtil;
        networkUtil.write("viewallcar");
        List<Car>Carlist;
        Carlist=(List<Car>)networkUtil.read();
        client.showviewallcar(Carlist);
    }

    public void searchreg() throws Exception{
      client.showsearchreg();
    }

    public void searchbymm(ActionEvent actionEvent) throws IOException {
        client.showsearchbymm();
    }
}
