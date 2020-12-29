package tcpdiff;

import java.io.Serializable;

public class Car implements Serializable {
    String Registration_Number;
    String Car_Make;
    String Car_Model;
    String[] Colour=new String[3];
    int Year_Made,Price,Quantity;
    public void setQuantity(int q)
    {
        Quantity=q;
    }
    public int getQuantity()
    {
        return Quantity;
    }
    public void setRegistration_Number(String CarReg)
    {
        Registration_Number=CarReg;
    }
    public  void setYear_Made(int YearMade)
    {
        Year_Made=YearMade;
    }
    public void setColour(String Colour1,String Colour2,String Colour3)
    {Colour[0]=Colour1;
        Colour[1]=Colour2;
        Colour[2]=Colour3;
    }
    public void setCar_Make(String CarMake)
    {
        Car_Make=CarMake;
    }
    public void setCar_Model(String CarModel)
    {
        Car_Model=CarModel;
    }
    public void setPrice(int Price)
    {
        this.Price=Price;
    }
    public String getRegistration_Number()
    {
        return Registration_Number;
    }
    public String getCar_Make()
    {
        return Car_Make;
    }
    public String getCar_Model()
    {
        return Car_Model;
    }
    public int getYear_Made()
    {
        return Year_Made;

    }
    public String[] getColour()
    {
        return Colour;
    }
    public int getPrice()
    {
        return Price;
    }


    public String toString()
    {
        return Registration_Number+","+Year_Made+","+Colour[0]+","+Colour[1]+","+Colour[2]+","+Car_Make+","+Car_Model+","+Price+","+Quantity;
    }

}