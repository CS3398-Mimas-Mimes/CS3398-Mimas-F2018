/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ordertransaction;

import java.util.Date;
import java.io.Serializable; 
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author DANH
 */
public class Payment implements Serializable{
  private int paymentID;
  private int method; // 0: Cash ;  1: Credit Card
  private int ccv;
  private int creditCard; 
  private float totalPrice;
  private Date day;
  
  public Payment(){}
    
  public Payment(int newPayID, int newMethod, int newCCV, int newCard, float newPrice){
    paymentID = newPayID;
    method = newMethod;
    ccv= newCCV;
    creditCard = newCard;
    totalPrice = newPrice;
    Calendar time = Calendar.getInstance();
    day = time.getTime();
  }
  
  @Override
  public String toString(){
    if (method == 0) // Pay by cash
      return String.format("\n|%1$10s |%2$10d |%3$10s |%4$-10s |", 
                         "Method: ", "Cash", 
                         "Total Price:", totalPrice);
    else // Pay by credit card
      return String.format("\n|%1$10s |%2$10s |%3$10s |%4$-10s |%5$-6s %6$-12s |", 
                         "Method: ", "Credit card", 
                         "Total Price:", totalPrice, 
                         "Card  " , creditCard);    
  }
  
  public int getCard(){
    return creditCard;
  }
  
  public float getTotalPrice(){
    return totalPrice;
  }
  
  public int getPayID(){
    return paymentID;
  }
  
}
