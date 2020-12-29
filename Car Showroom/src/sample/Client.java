package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import tcpdiff.Car;
import util.NetworkUtil;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Client extends Application {
    NetworkUtil networkUtil=new NetworkUtil("127.0.0.1", 33333);
    Stage stage;
    @Override
    public void start(Stage primaryStage) throws Exception{
            stage = primaryStage;
            showStartPage();

    }
    public void showMedit(String reg) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("medit.fxml"));
        Parent root = loader.load();
        Medit controller = loader.getController();
        controller.setClient(this);
        controller.setreg(reg);
        stage.setTitle("Edit");
        stage.setScene(new Scene(root, 500, 300));
        stage.show();
    }
    public void showStartPage() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("showStartPage.fxml"));
        Parent root = loader.load();
        showStartPage controller = loader.getController();
        controller.setClient(this);

        stage.setTitle("Start");
        stage.setScene(new Scene(root, 500, 300));
        stage.show();

    }
    public void showsearchreg() throws Exception
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("showsearchreg.fxml"));
        Parent root = loader.load();
        Showsearchreg controller = loader.getController();
        controller.setClient(this);

        stage.setTitle("Search");
        stage.setScene(new Scene(root, 500, 300));
        stage.show();
    }
    public void showviewallcar(List<Car> CarList) throws Exception
    {FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("cartableview.fxml"));
        Parent root = loader.load();
       cartableviewcontroller controller = loader.getController();
        controller.setClient(this);

        controller.load(CarList);
        stage.setTitle("Table View");
        stage.setScene(new Scene(root, 500, 300));
        stage.show();


    }
    public void showMLoginPage() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Mlogin.fxml"));
        Parent root = loader.load();

        // Loading the controller
        MLogin controller = loader.getController();
        controller.setClient(this);

        // Set the primary stage
        stage.setTitle("Login");
        stage.setScene(new Scene(root, 400, 250));
        stage.show();
    }
    public void showMHomePage() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("showMHomePage.fxml"));
        Parent root = loader.load();

        // Loading the controller
        ShowMHomePage controller = loader.getController();
        controller.setClient(this);

        // Set the primary stage
        stage.setTitle("Home");
        stage.setScene(new Scene(root, 400, 250));
        stage.show();
    }
    public void showLoginPage() throws Exception
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("viewerlogin.fxml"));
        Parent root = loader.load();

        // Loading the controller
        Controller controller = loader.getController();
        controller.setClient(this);

        // Set the primary stage
        stage.setTitle("Login");
        stage.setScene(new Scene(root, 400, 250));
        stage.show();


    }
    public void showHomePage() throws Exception
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("home.fxml"));
        Parent root = loader.load();

        // Loading the controller
        homeController controller = loader.getController();
        controller.setClient(this);

        // Set the primary stage
        stage.setTitle("Home");
        stage.setScene(new Scene(root, 400, 250));
        stage.show();

    }


    public static void main(String[] args) {
        //String serverAddress = "127.0.0.1";
        //int serverPort = 33333;
        //Client client = new Client(serverAddress, serverPort);
        launch(args);
    }

    public void showsearchbymm() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("searchbymm.fxml"));
        Parent root = loader.load();

        // Loading the controller
        searchbymm controller = loader.getController();
        controller.setClient(this);

        // Set the primary stage
        stage.setTitle("Search");
        stage.setScene(new Scene(root, 400, 250));
        stage.show();
    }

    public void showMviewallcar(List<Car>CarList) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("mcartableview.fxml"));
        Parent root = loader.load();
        mcartableview controller = loader.getController();
        controller.setClient(this);

        controller.load(CarList);
        stage.setTitle("Table View");
        stage.setScene(new Scene(root, 500, 300));
        stage.show();
    }


    public void add() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("maddview.fxml"));
        Parent root = loader.load();
        maddview controller = loader.getController();
        controller.setClient(this);
        stage.setTitle("Add View");
        stage.setScene(new Scene(root, 500, 500));
        stage.show();
    }


}
