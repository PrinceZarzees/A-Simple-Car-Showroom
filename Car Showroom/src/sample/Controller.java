package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import util.NetworkUtil;

public class Controller {

    public Client client;
    public TextField textfield;
    public Button loginbutton;
    public Button Exit;


    public void setClient(Client client)
    {
        this.client=client;
    }


    public void loginbutton() throws Exception{
        NetworkUtil networkUtil=client.networkUtil;
        if (textfield.getText().equals(""))
        networkUtil.write("login page viewer,"+" ");
        else
            networkUtil.write("login page viewer,"+textfield.getText());
        String s=(String)networkUtil.read();
        if (s.equals("success"))
            client.showHomePage();
        else if (s.equals("fail"))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Incorrect Credentials");
            alert.setHeaderText("Incorrect Credentials");
            alert.setContentText("The username  is not correct.");
            alert.showAndWait();

        }

    }


    public void Exit(ActionEvent actionEvent) {
        NetworkUtil networkUtil=client.networkUtil;
        networkUtil.closeConnection();
        client.stage.close();
    }
}
