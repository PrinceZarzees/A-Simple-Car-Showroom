package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import util.NetworkUtil;

import java.io.FileInputStream;
import java.io.IOException;

public class mtemp
{
    Client client;
    String Registration_Number;

    Button Editbutton;
    Button Deletebutton;
    Button b;
    public void setClient(Client obj)
    {client=obj;
    }
    public void setB()
    {
        this.b = new Button("Image");
        b.setOnAction( e -> {

                    client.networkUtil.write("Image,"+Registration_Number);
                    String s=(String)client.networkUtil.read();

                    try {
                        Image img=new Image(new FileInputStream(s));
                        Alert a=new Alert(Alert.AlertType.INFORMATION);
                        a.setGraphic(new ImageView(img));
                        a.showAndWait();

                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }

                }
        );

    }
    public Button getB()
    {
        return b;
    }

   public void setDeletebutton()
   {
       this.Deletebutton= new Button("Delete");
       Deletebutton.setOnAction( e -> {

                   try {
                       client.networkUtil.write("Delete,"+Registration_Number);
                       String s=(String)client.networkUtil.read();
                       if (s.equals("success"))
                       {
                           Alert a = new Alert(Alert.AlertType.INFORMATION);
                           a.setHeaderText("Information");
                           a.setContentText("Successfully Deleted."+"\n"+"Please Refresh");
                           a.showAndWait();
                           client.showMHomePage();
                       }
                       else
                       {
                           Alert a = new Alert(Alert.AlertType.ERROR);
                           a.setHeaderText("Information");
                           a.setContentText("No such car");
                           a.showAndWait();
                           client.showMHomePage();
                       }
                   } catch (Exception e1) {
                       e1.printStackTrace();
                   }
               }
       );

   }

    public void setEditbutton()
    {this.Editbutton = new Button("Edit");
        Editbutton.setOnAction( e -> {

                    try {
                        client.showMedit(Registration_Number);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
        );

    }
    public Button getDeletebutton() {return Deletebutton;}
    public Button getEditbutton()
    {
        return Editbutton;
    }

    public void setRegistration_Number(String CarReg)
    {
        Registration_Number=CarReg;
    }


    public String getRegistration_Number()
    {
        return Registration_Number;
    }



}