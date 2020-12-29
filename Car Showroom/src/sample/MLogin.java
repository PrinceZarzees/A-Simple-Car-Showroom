package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import util.NetworkUtil;

public class MLogin {
    public TextField Username;
    public PasswordField Password;
    public Button Login;
    public Button Exit;
    Client client;
    public void setClient(Client client) {
        this.client=client;
    }


    public void Login(ActionEvent actionEvent) throws Exception {
        String s1=Username.getText();
        String s2=Password.getText();
        client.networkUtil.write("MLogin,"+s1+","+s2);
        s2=(String)(client.networkUtil.read());
        if (s2.equals("success"))
            client.showMHomePage();
        else
        {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setHeaderText("Invalid Credentials");
            a.setContentText("Username or Password is incorrect");
            a.showAndWait();
        }
    }

    public void Exit(ActionEvent actionEvent) {
        NetworkUtil networkUtil=client.networkUtil;
        networkUtil.closeConnection();
        client.stage.close();
    }
}
