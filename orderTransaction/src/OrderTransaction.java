/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ordertransaction;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author DANH
 */

public class OrderTransaction {
  //public ArrayList<Order> orderList = new ArrayList<Order>();
  private static  ArrayList<Order> orderList = new ArrayList<Order>();
  /**
   * @param args the command line arguments
   * @throws java.lang.Exception
   */
  public static void main(String[] args) throws Exception {
    // TODO code application logic here
    //orderList = new ArrayList<Order>();
    ArrayList<Integer> foodList = new ArrayList<Integer>();
    
    /* 
      Create a sample list of foods for an order
        1: Burger
        2: Fries
      The first element is the ID of food, the second element is quantity of the food
    */
    foodList.add(1); // Add buger to the list
    foodList.add(2); // Quantity is 2 burgers
    
    // Load order database to system
    loadFile();
    
    // Testing add new order to database
    addOrder(1, 5, foodList);
            
    // Testing display a order on a list
    System.out.println("Display the first order on the list");
    System.out.println(orderList.get(0).toString());
    System.out.println("List of Food     "  + "Quantity");
    orderList.get(0).foodDisplay();
    
    // Testing if test an order is on the list, and return the position on the list
    System.out.println("Check if order with number 1369 is on the list or not");
    if(existOrder(1369))
      System.out.println("Order number 1369 is at position " + searchOrder(1369));
    else
      System.out.println("Order number 1369 is not on the list");
    
    // Testing to change the status of an order
    System.out.println("Change the status of the first order on list");
    changeStatus(orderList.get(0).getOrderNum(),2);
    System.out.println(orderList.get(0).toString());
    
    // Testing remove an order (1369) from the list
    removeOrder(1369);
    
    
    // Save information of order to database
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
        }                     
        file.close();
    }catch(FileNotFoundException e){
        FileOutputStream file = new FileOutputStream("orderData.ser");
        file.close();
    }catch(EOFException e){
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

  /*
    Add a new order to order list
      customerID: ID of cusotomer;
      status: current status of order(wailist, ready, paid, or complete)
      price: the price of the order
      list: list of foodID and quantity
  */
  public static void addOrder(int customerID,
                              float price,
                              ArrayList<Integer> listFood){
    // Create a order number randomly
    Random numRandom = new Random();
    int orderNum = numRandom.nextInt(10000);
    while (existOrder(orderNum)){
      orderNum = numRandom.nextInt(10000);
    }
    //int status = 1; // 1: Order in wailist
    Order temp = new Order(orderNum, customerID, price, listFood);
    orderList.add(temp);  
  }
  
  /*
    Fucntion to check if an order exists or not
    Return True: exist
    Return False: does not exist
  */
  public static boolean existOrder (int orderNum){
    for (int i = 0; i < orderList.size(); i+=2){
      if (orderList.get(i).getOrderNum() == orderNum)
        return true;
    }
    return false;
  }
  
  public static int searchOrder(int orderNum){
    for (int i = 0; i < orderList.size(); i+=2){
      if (orderList.get(i).getOrderNum() == orderNum)
        return i;
    }
    return 0;  // This is not correctly
  }
  
  /*
    Remove an order form the list
      orderNum: number of order needs to be removed
  */
  public static void removeOrder(int orderNum){
    if(existOrder(orderNum)){
      int pos = searchOrder(orderNum);
      orderList.remove(pos);
    }
  }
  
  /*
    Set the status of the transaction: 
      1: wailist
      2: ready
      3: picked up
      4: cancelled
  */
  public static void changeStatus(int orderNum, int status){
    if(existOrder(orderNum)){
      int pos = searchOrder(orderNum);
      orderList.get(pos).setStatus(status);
    }
  }
}
