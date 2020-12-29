package tcpdiff;

import util.NetworkUtil;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Server {
    private static final String INPUT_FILE_NAME = "cars.txt";
    private static final String OUTPUT_FILE_NAME = "cars.txt";
    private static final String LOGIN_FILE_NAME="login.txt";
    List<Car> CarList = new ArrayList();
    HashMap<String,String>loginlist=new HashMap<String,String>();
    private ServerSocket serverSocket;
    Server(List<Car> CarList,HashMap<String,String>loginlist) {
        try {
            this.CarList=CarList;
            this.loginlist=loginlist;
            serverSocket = new ServerSocket(33333);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                serve(clientSocket);

            }
        } catch (Exception e) {
            System.out.println("Server starts:" + e);
        }
    }

    public void serve(Socket clientSocket) {
        NetworkUtil networkUtil = new NetworkUtil(clientSocket);

        new clienthandle(networkUtil,CarList,loginlist,OUTPUT_FILE_NAME);

    }

    public static void main(String args[]) {
        List<Car> CarList = new ArrayList();
        HashMap<String,String>loginlist=new HashMap<String,String>();
        try {
            String line;
            BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE_NAME));
            while (true) {
                line = br.readLine();
                if (line == null) break;
                String[] car;
                car = line.split(",");
                Car e = new Car();
                e.setRegistration_Number(car[0]);
                e.setYear_Made(Integer.valueOf(car[1]));
                e.setColour(car[2], car[3], car[4]);
                e.setCar_Make(car[5]);
                e.setCar_Model(car[6]);
                e.setPrice(Integer.valueOf(car[7]));
                e.setQuantity(Integer.valueOf(car[8]));
                CarList.add(e);

            }
            br=new BufferedReader(new FileReader(LOGIN_FILE_NAME));
            while (true) {
                line = br.readLine();
                if (line == null) break;
                String[] a;
                a= line.split(",");
               loginlist.put(a[0],a[1]);

            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Server server = new Server(CarList,loginlist);
    }
}
