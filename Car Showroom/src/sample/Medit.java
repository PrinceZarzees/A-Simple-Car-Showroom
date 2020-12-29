package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import tcpdiff.Car;

import java.io.IOException;
import java.util.List;

public class Medit {
    public Button Edit;
    public TextField Quantity;
    public TextField Price;
    public TextField Colour1;
    public TextField Colour2;
    public TextField Colour3;
    public Button Back;
    Client client;
    String reg;
    public void setClient(Client client) {
        this.client=client;
    }
    public void setreg(String reg)
    {
        this.reg=reg;
    }

    public void Edit() throws IOException {
        client.networkUtil.write("info"+","+reg);
        String s=(String)client.networkUtil.read();
        if (s.equals("success"))
        {Car e=(Car)client.networkUtil.read();
        e.setColour(Colour1.getText(),Colour2.getText(),Colour3.getText());
        e.setQuantity(Integer.valueOf(Quantity.getText()));
        e.setPrice(Integer.valueOf(Price.getText()));
        client.networkUtil.write("Edit"+","+reg);
         client.networkUtil.write(e);
        List<Car> CarList=(List<Car>)client.networkUtil.read();
        client.showMviewallcar(CarList);}
        else
        {
            Alert a=new Alert(Alert.AlertType.ERROR);
            a.setContentText(s);
            a.showAndWait();
        }

    }

    public void Back(ActionEvent actionEvent) throws IOException {
        client.showMHomePage();
    }
}
