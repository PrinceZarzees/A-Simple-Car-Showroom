package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import tcpdiff.Car;

public class searchbymm {
    @FXML
    public Button backbutton;
    @FXML
    public TextField carmake;
    @FXML
    public TextField carmodel;
    @FXML
    public Button searchbymm;
    Client client;
    public void setClient(Client client) {
        this.client=client;
    }

    public void back() throws Exception {
        client.showHomePage();
    }

    public void searchbymm(ActionEvent actionEvent){

        String s1=carmake.getText();
        String s2=carmodel.getText();
        if (s1.equals("") || s2.equals(""))
        {
            s1=" ";
            s2=" ";
        }
        client.networkUtil.write("searchbymm,"+s1+","+s2);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Car Info");
        alert.setHeaderText("Car Information");
        s1=(String)client.networkUtil.read();
        if (s1.equals("success")) {
            Car e = (Car) (client.networkUtil.read());
            alert.setContentText("Car Make: "+e.getCar_Make() + "\n" +
                    "Car Model: "+e.getCar_Model() + "\n" +
                    "Colour: "+e.getColour()[0] + " " + e.getColour()[1] + " " + e.getColour()[2] + "\n" +
                    "Year: "+e.getYear_Made() + "\n" +
                    "Price: "+e.getPrice() + "\n" +
                    "Quantity: "+e.getQuantity());
            alert.showAndWait();
        }
        else
        {
            alert.setContentText(s1);
            alert.showAndWait();
        }

    }
}
