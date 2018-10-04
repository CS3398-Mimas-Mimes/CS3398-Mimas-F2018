package menu;

import java.io.Serializable;
/**
 *
 * @author ANDREW
 */
public class FoodItem implements Serializable {
    private int foodNum;
    private String foodName;
    private double foodCost;
    
    public FoodItem() {
    
    }
    
    public FoodItem(int foodNum, String foodName, double foodCost) {
        this.foodNum = foodNum;
        this.foodName = foodName;
        this.foodCost = foodCost;
    }
    
    public int getFoodNum() {
        return this.foodNum;
    }
    
    public String getFoodName() {
        return this.foodName;
    }
    
    public double getFoodCost() {
        return this.foodCost;
    }
    
    public void setFoodNum(int num) {
        this.foodNum = num;
    }
    
    public void setFoodName(String name) {
        this.foodName = name;
    }
    
    public void setFoodCost(double cost) {
        this.foodCost = cost;
    }
    
    @Override
    public String toString() {
        return String.format("\n|%1$10s %2$-2d |%3$12s %4$-20s |%5$8s %6$-6s", 
                         "Number: ", foodNum,
                         "Food Name: ", foodName, 
                         "Cost: ", foodCost);
    }
}
