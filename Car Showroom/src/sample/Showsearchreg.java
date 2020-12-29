package sample;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import tcpdiff.Car;

public class Showsearchreg {
    public Button backbutton;
    public Button Searchbutton;
    public TextField Reg;
    Client client;

    public void setClient(Client client)
    {
        this.client=client;
    }

    public void back() throws Exception{
        client.showHomePage();
    }

    public void Searchbyreg() {
      String s=Reg.getText();
      if (s.equals(""))
          s=" ";
      client.networkUtil.write("Searchbyreg,"+s);
      s=(String)client.networkUtil.read();
      if (s.equals("success")) {
          Car e = (Car) (client.networkUtil.read());
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
          alert.setTitle("Car Info");
          alert.setHeaderText("Car Information");
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
          Alert alert = new Alert(Alert.AlertType.INFORMATION);
          alert.setTitle("Car Info");
          alert.setHeaderText("Car Information");
          alert.setContentText(s);
          alert.showAndWait();
      }


    }
}
