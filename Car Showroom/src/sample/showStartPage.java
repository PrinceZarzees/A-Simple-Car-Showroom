package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import java.io.IOException;

public class showStartPage {
    public Button viewer;
    public Button Manufacturer;
    Client client;
    public void setClient(Client client) {
        this.client=client;
    }

    public void viewer() throws Exception {
        client.showLoginPage();

    }

    public void manufacturer(ActionEvent actionEvent) throws IOException {
        client.showMLoginPage();
    }
}
