package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import tcpdiff.Car;

import java.io.IOException;

public class maddview {
    public Button Back;
    public Button Add;
    public TextField price;
    public TextField quantity;
    public TextField year;
    public TextField carmodel;
    public TextField carmake;
    public TextField Reg;
    public TextField color1;
    public TextField color2;
    public TextField color3;
    Client client;
    public void setClient(Client client) {
        this.client=client;
    }

    public void Back() throws IOException {
        client.showMHomePage();

    }

    public void Add(ActionEvent actionEvent) {
        Car e=new Car();
        e.setPrice(Integer.valueOf(price.getText()));
        e.setQuantity(Integer.valueOf(quantity.getText()));
        e.setRegistration_Number(Reg.getText());
        e.setColour(color1.getText(),color2.getText(),color3.getText());
        e.setYear_Made(Integer.valueOf(year.getText()));
        e.setCar_Make(carmake.getText());
        e.setCar_Model(carmodel.getText());
        client.networkUtil.write("Add");
        client.networkUtil.write(e);
        String s=(String)(client.networkUtil.read());
        if (s.equals("fail"))
        {
            Alert a=new Alert(Alert.AlertType.ERROR);
            a.setContentText("Duplicate Registration Number");
            a.showAndWait();
        }
        else
        {Alert a=new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Successfully added");
            a.showAndWait();

        }

    }
}
