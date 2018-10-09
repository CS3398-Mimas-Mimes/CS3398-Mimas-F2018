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
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author DANH
 */

public class OrderTransaction {
  //public ArrayList<Order> orderList = new ArrayList<Order>();
  private static  ArrayList<Order> orderList = new ArrayList<Order>();
  private static ArrayList<Payment> paymentList = new ArrayList<Payment>();
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
    System.out.println("\nCheck if order with number 1369 is on the list or not");
    if(existOrder(1369))
      System.out.println("Order number 1369 is at position " + searchOrder(1369));
    else
      System.out.println("Order number 1369 is not on the list");
    
    // Testing to change the status of an order
    System.out.println("\nChange the status of the first order on list");
    changeStatus(orderList.get(0).getOrderNum(),2);
    System.out.println(orderList.get(0).toString());
    
    // Testing to add payment to the order 4047
    System.out.println("\nAdd payment to the order 6454, by credit card");
    addPayment(6454, 1, 222, 123456789);
    
    // Display the payment of order 4047
    System.out.println("\nDisplay the payment of the order 6454");
    if(existOrder(6454)){
      int pos = searchOrder(6454);
      int payID = orderList.get(pos).getPayID();
      for(int i = 0; i < paymentList.size(); i++){
        if (paymentList.get(i).getPayID() == payID)
          System.out.println(paymentList.get(i).toString());
      }
    }
    
    // Testing remove an order (1369) from the list
    System.out.println("\n Try to remove the order 1369 from the list");
    removeOrder(1369);
    
    // Save information of order to database
    System.out.println("Save all data to the file");
          
          
    saveToFile();
  }
  
  public static void loadFile() throws Exception{
    // Load information from file .ser to program as format
    int sizeOrder = 0;// 
    try{
      FileInputStream file = new FileInputStream("orderData.ser");
      ObjectInputStream inFile = new ObjectInputStream(file);

      sizeOrder = inFile.readInt();  // 
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
   
    // Load payment database to system
    int sizePayment = 0;// 
    try{
      FileInputStream file = new FileInputStream("paymentData.ser");
      ObjectInputStream inFile = new ObjectInputStream(file);

      sizePayment = inFile.readInt();  // 
      for (int index = 0; index < sizePayment; index++){
          Payment temp = (Payment)inFile.readObject();
          paymentList.add(temp);
      }                     
      file.close();
    }catch(FileNotFoundException e){
      FileOutputStream file = new FileOutputStream("paymentData.ser");
      file.close();
    }catch(EOFException e){
      FileOutputStream file = new FileOutputStream("paymentData.ser");
      file.close();
    } 
 }
  
  public static void saveToFile() throws Exception{
    // Write object from program to file .ser in order
    FileOutputStream file = new FileOutputStream("orderData.ser");
    ObjectOutputStream outFile = new ObjectOutputStream(file);
    outFile.writeInt(new Integer(orderList.size()));  // fourth: number of drum in the file
    // Write object of ORDER type
    for (Order tempt : orderList)
      outFile.writeObject(tempt);
    outFile.close();
    
    // Write object from program to file .ser in order
    file = new FileOutputStream("paymentData.ser");
    outFile = new ObjectOutputStream(file);
    outFile.writeInt(new Integer(paymentList.size()));  // fourth: number of drum in the file
    // Write object of PAYMENT type
    for (Payment tempt : paymentList)
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
    for (int i = 0; i < orderList.size(); i++){
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
      5: Make a payment, but not pick up
      6: Completed 
  */
  public static void changeStatus(int orderNum, int status){
    if(existOrder(orderNum)){
      int pos = searchOrder(orderNum);
      orderList.get(pos).setStatus(status);
    }
  }
  
  public static boolean existPayment (int paymentID){
    for (int i = 0; i < paymentList.size(); i+=2){
      if (paymentList.get(i).getPayID() == paymentID)
        return true;
    }
    return false;
  }
  
  public static int searchPayment(int paymentID){
    for (int i = 0; i < paymentList.size(); i++){
      if (paymentList.get(i).getPayID() == paymentID)
        return i;
    }
    return 0;  // This is not correctly
  }
  
  public static void addPayment(int orderNum, int newMethod, int newCCV, int newCard){
    if(existOrder(orderNum)){
      // Find the posistion of the order 
      int orderPos = searchOrder(orderNum); 
      if (orderList.get(orderPos).getStatus() >= 5){ // Did make a payment?  
        System.out.println("This order is paid");
        return;
      }
      // Fill new random number for the payment ID
      Random numRandom = new Random();
      int paymentID = numRandom.nextInt(10000);
      while (existOrder(orderNum))
        orderNum = numRandom.nextInt(10000);
      
      // Calculate the total price of an order
      float totalPrice = orderList.get(orderPos).getSubPrice() * (float) 1.085;
      
      // Create a payment. Save to the database, and set the payID on the order
      Payment tempPay = new Payment(paymentID, newMethod, newCCV, newCard, totalPrice);
      paymentList.add(tempPay);
      orderList.get(orderPos).setPayID(paymentID); 
    }
  }
}
