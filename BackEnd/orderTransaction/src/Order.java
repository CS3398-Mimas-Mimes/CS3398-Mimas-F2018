/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ordertransaction;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author DANH
 */
public class Order implements Serializable {
  private int orderNum;
  private int customerID;
  private int status;
  private float subPrice;
  private ArrayList<Integer> foodList;
  
  public Order(){
  }
  
  public Order(int newNum, 
               int newCustomer, 
               float newPrice, 
               ArrayList<Integer> list){
    orderNum = newNum;
    customerID = newCustomer;
    status = 1;  // Set wailist as default status
    subPrice = newPrice;
    foodList = new ArrayList<>(list);
  }
  
  @Override
  public String toString(){
    return String.format("\n|%1$10s |%2$10d |%3$10s |%4$-10s |%5$-6s %6$-12s %7$-10s %8$-46s |", 
                         "Order: ", orderNum, 
                         "CustomerID:", customerID, 
                         "Status  " , status,
                         "subPrice " , subPrice);    
  }
  
  public void foodDisplay(){
    for(int i = 0; i < foodList.size(); i += 2){
      System.out.println(foodList.get(i) + "   "  + foodList.get(i+1));
    }
  }
  
  public int getOrderNum(){
    return orderNum;
  }
  
  public void setStatus(int newStatus){
    status = newStatus;
  }
}