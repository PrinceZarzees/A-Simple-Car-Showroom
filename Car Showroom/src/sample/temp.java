package sample;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.IOException;

public class temp
{
        Client client;
        String Registration_Number;
        Button button;
        Button b;
        public void setClient(Client client)
        {
                this.client=client;
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
        public void setButton()
        {this.button = new Button("Buy");
                button.setOnAction( e -> {

                                Alert a = new Alert(Alert.AlertType.INFORMATION);
                                 client.networkUtil.write("buy,"+Registration_Number);
                                 a.setHeaderText((String)client.networkUtil.read());
                                a.showAndWait();
                        }
                );

        }
        public Button getB() {return b;}
         public Button getButton()
         {
             return button;
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