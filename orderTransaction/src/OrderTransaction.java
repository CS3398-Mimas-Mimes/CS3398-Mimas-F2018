/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ordertransaction;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author DANH
 */

public class OrderTransaction {
  //public ArrayList<Order> orderList = new ArrayList<Order>();
  private static  ArrayList<Order> orderList ;
  /**
   * @param args the command line arguments
   * @throws java.lang.Exception
   */
  public static void main(String[] args) throws Exception {
    // TODO code application logic here
    orderList = new ArrayList<Order>();
    
    loadFile();
    
    int sizeList = orderList.size();
    
    orderList.get(2).foodDisplay();
    
    ArrayList<Integer> temp = new ArrayList<Integer>();
    temp.add(1);
    temp.add(2);
    temp.add(4);
    
    
    orderList.add(new Order(2, 3, 1, (float) 5.5, temp));
    
    System.out.println("Size of  list = " + sizeList);
    
    sizeList = orderList.size();
    System.out.println("Size of  list = " + sizeList);
    
    saveToFile();
  }
  
  public static void loadFile() throws Exception{
    // Load information from file .ser to program as format
    int sizeOrder = 0;// fourth: number of drum in the file
    try{
        FileInputStream file = new FileInputStream("orderData.ser");
        ObjectInputStream inFile = new ObjectInputStream(file);
        //int i = inFile.readInt();
        //sizeDrum = (Integer)inFile.readObject();  // Read the number of drum object
        sizeOrder = inFile.readInt();  // Read the number of drum object         
        for (int index = 0; index < sizeOrder; index++){
            Order temp = (Order)inFile.readObject();
            orderList.add(temp);
            //database.add(temp);
        }                     
        file.close();
    }catch(FileNotFoundException e){
        FileOutputStream file = new FileOutputStream("orderData.ser");
        file.close();
    }
  }
  
  public static void saveToFile() throws Exception{
    // Write object from program to file .ser in order
    FileOutputStream file = new FileOutputStream("orderData.ser");
    ObjectOutputStream outFile = new ObjectOutputStream(file);
    //outFile.writeInt(new Integer(drumList.size()));  // fourth: number of drum in the file
    outFile.writeInt(orderList.size());  // fourth: number of drum in the file
    // Write object of drum type
    for (Order tempt : orderList)
        outFile.writeObject(tempt);
    outFile.close();
  }
}
