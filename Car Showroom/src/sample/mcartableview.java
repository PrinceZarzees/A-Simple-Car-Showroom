package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import tcpdiff.Car;

import java.io.IOException;
import java.util.List;

public class mcartableview {
    public Button back;
    public Button Refresh;
    Client client;
    public TableView tableView;
    ObservableList<mtemp> data;
    mtemp[] arr;
    private boolean init = true;
    private void initializeColumns() {
        TableColumn<mtemp,String> firstCol = new TableColumn<>("Registration Number");
        firstCol.setMinWidth(80);
        firstCol.setCellValueFactory(new PropertyValueFactory<>("Registration_Number"));
        TableColumn<Button,String> secCol = new TableColumn<>("Edit");
        secCol.setMinWidth(80);
        secCol.setCellValueFactory(new PropertyValueFactory<>("Editbutton"));
        TableColumn<Button,String> thirdCol = new TableColumn<>("Delete");
        thirdCol.setMinWidth(80);
        thirdCol.setCellValueFactory(new PropertyValueFactory<>("Deletebutton"));
        TableColumn<Button,String> fourthCol = new TableColumn<>("Image");
        fourthCol.setMinWidth(80);
        fourthCol.setCellValueFactory(new PropertyValueFactory<>("b"));
        tableView.getColumns().addAll(firstCol,secCol,thirdCol,fourthCol);

    }
    public void load(List<Car> Carlist)
    {
        if (init) {
            initializeColumns();
            init = false;
        }
        arr=new mtemp[Carlist.size()];
        for(int i=0;i<Carlist.size();i++)
        {arr[i]=new mtemp();
            Car e=Carlist.get(i);
            arr[i].setClient(client);
arr[i].setB();
            arr[i].setEditbutton();
            arr[i].setDeletebutton();
            arr[i].setRegistration_Number(e.getRegistration_Number());


        }
        data = FXCollections.observableArrayList(arr);

        tableView.setEditable(true);
        tableView.setItems(data);
        tableView.getSelectionModel().selectedItemProperty().addListener(
                (observableValue,oldvalue,newvalue) -> {
                    Alert a = new Alert(Alert.AlertType.INFORMATION);
                    a.setHeaderText("Car Information");
                    mtemp t=(mtemp)newvalue;
                    client.networkUtil.write("info,"+t.getRegistration_Number());
                    String s=(String)client.networkUtil.read();
                    if (s.equals("success")) {
                        Car e = (Car) client.networkUtil.read();
                        a.setContentText("Car Make: "+e.getCar_Make() + "\n" +
                                "Car Model: "+e.getCar_Model() + "\n" +
                                "Colour: "+e.getColour()[0] + " " + e.getColour()[1] + " " + e.getColour()[2] + "\n" +
                                "Year: "+e.getYear_Made() + "\n" +
                                "Price: "+e.getPrice() + "\n" +
                                "Quantity: "+e.getQuantity());
                        a.showAndWait();
                    }
                    else
                    {
                        a.setContentText(s);
                        a.showAndWait();
                    }
                }


        );


    }
    public void setClient(Client client) {
        this.client=client;
    }

    public void back(ActionEvent actionEvent) throws IOException {
        client.showMHomePage();
    }

    public void Refresh() throws Exception {
        client.networkUtil.write("viewallcar");
        List<Car>CarList=(List<Car>)(client.networkUtil.read());
        client.showMviewallcar(CarList);

    }
}
