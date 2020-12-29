package tcpdiff;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import util.NetworkUtil;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.List;

public class clienthandle implements Runnable {
    private Thread thr;
    private NetworkUtil networkUtil;
    List<Car> CarList;
    HashMap<String,String>loginlist;
    String OUTPUT_FILE_NAME;
    public clienthandle(NetworkUtil networkUtil, List<Car>CarList, HashMap<String,String> loginlist,String OUTPUT_FILE_NAME) {
        this.CarList=CarList;
        this.loginlist=loginlist;
        this.OUTPUT_FILE_NAME=OUTPUT_FILE_NAME;
        this.networkUtil = networkUtil;
        this.thr = new Thread(this);
        thr.start();
    }
    public void updatefile(String OUTPUT_FILE_NAME)
    {try {
        BufferedWriter bw = new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME));
        for (Car e : CarList) {
            bw.write(e.toString());
            bw.write("\n");
        }
        bw.close();
    } catch (Exception e) {
        e.printStackTrace();
    }

    }
    public void loginoperation(String s)
    {
        if (s.equals("viewer"))
        networkUtil.write("success");
    else
        networkUtil.write("fail");

    }
    public void viewallcar()
    {
        networkUtil.write(CarList);

    }
    public void carinfo(String s)
    {
        for(int i=0;i<CarList.size();i++) {
            Car e = CarList.get(i);
            if (e.getRegistration_Number().equalsIgnoreCase(s))
            {networkUtil.write("success");
                networkUtil.write(e);
            return;
            }
        }
        networkUtil.write("No Car Here");
    }
    public synchronized void buy(String s)
    {
        for(int i=0;i<CarList.size();i++)
        {Car e=CarList.get(i);
        if (e.getRegistration_Number().equalsIgnoreCase(s)) {
            if (e.getQuantity() > 0) {
                e.setQuantity(e.getQuantity() - 1);

                networkUtil.write("Successfully Bought");
            } else {
                networkUtil.write("Currently not any car");
            }
            CarList.set(i, e);
            updatefile(OUTPUT_FILE_NAME);
            return;
        }
        }
        networkUtil.write("No Car Here");

    }
    public void searchbymm(String s1,String s2)
    {
        for(int i=0;i<CarList.size();i++) {
            Car e = CarList.get(i);
            if (e.getCar_Make().equalsIgnoreCase(s1) && e.getCar_Model().equalsIgnoreCase(s2))
            { networkUtil.write("success");
                networkUtil.write(e);
                return;
            }
        }
        networkUtil.write("No Car Here");
    }
    public void MLogin(String s1,String s2)
    {if (loginlist.containsKey(s1))
    {if (loginlist.get(s1).equals(s2))
            networkUtil.write("success");
        else
            networkUtil.write("fail");}
            else
                networkUtil.write("fail");

    }
    private synchronized void MEdit(String s) {
        for(int i=0;i<CarList.size();i++) {
            Car e = CarList.get(i);
            if (e.getRegistration_Number().equalsIgnoreCase(s))
            {e=(Car)networkUtil.read();
              CarList.set(i,e);
              updatefile(OUTPUT_FILE_NAME);
              break;
            }
        }
        networkUtil.write(CarList);
    }
    private synchronized void MDelete(String s)
    { for(int i=0;i<CarList.size();i++) {
        Car e = CarList.get(i);
        if (e.getRegistration_Number().equalsIgnoreCase(s))
        {
           CarList.remove(i);
            updatefile(OUTPUT_FILE_NAME);
            networkUtil.write("success");
            return;
        }
    }
        networkUtil.write("fail");

    }
    public void image(String s)
    {
            networkUtil.write("C:\\Users\\MD YASIN\\Pictures\\"+s);



    }
    private synchronized void Add(Car e) {
        for(int i=0;i<CarList.size();i++) {
            Car obj= CarList.get(i);
            if (obj.getRegistration_Number().equalsIgnoreCase(e.getRegistration_Number()))
            {
                networkUtil.write("fail");
                return;
            }
        }
        CarList.add(e);
        updatefile(OUTPUT_FILE_NAME);
        networkUtil.write("success");

    }


    public void run() {
        try {
             while(true) {
                 String s = (String) networkUtil.read();
                 if (s.equals("Add"))
                 {Car e=(Car)networkUtil.read();
                  Add(e);
                 }
                 if (s.equals("viewallcar"))
                 {
                   viewallcar();
                 }
                 String []arr;
                 arr=s.split(",");
                 if (arr[0].equals("login page viewer"))
                     loginoperation(arr[1]);
                 if (arr[0].equals("info"))
                 {
                     carinfo(arr[1]);
                 }
                 if (arr[0].equals("buy"))
                     buy(arr[1]);
                 if (arr[0].equals("Searchbyreg"))
                     carinfo(arr[1]);
                 if (arr[0].equals("searchbymm"))
                     searchbymm(arr[1],arr[2]);
                 if (arr[0].equals("MLogin"))
                     MLogin(arr[1],arr[2]);
                 if (arr[0].equals("Edit"))
                     MEdit(arr[1]);
                 if (arr[0].equals("Delete"))
                     MDelete(arr[1]);
                 if (arr[0].equals("Image"))
                     image(arr[1]+".jpg");
             }
        } catch (Exception e) {
            //System.out.println(e);
        }
    }




}



