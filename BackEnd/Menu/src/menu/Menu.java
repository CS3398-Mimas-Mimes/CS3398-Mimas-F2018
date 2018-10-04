package menu;

import java.io.EOFException;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Random;

/**
 *
 * @author ANDREW
 */
public class Menu {
    private static ArrayList<FoodItem> menu = new ArrayList<FoodItem>();
    
    public static void main(String[] args) throws Exception {
        System.out.println("Before load.");
        // Load menu database to system
        loadFile();
        
        System.out.println("Contents of menu: ");
        for (int i = 0; i < menu.size(); i++)
            displayMenuItem(menu.get(i));
        
        //System.out.println("After load, before FoodItem creation.");
        FoodItem burger = new FoodItem(1,"Burger",6.00);
        FoodItem fries = new FoodItem(2,"Fries",2.00);
        FoodItem salad = new FoodItem(3,"Salad",8.00);
        
        
        // Testing addMenuItem()
        boolean menuItemExists = existMenuItem(burger);
        if (menuItemExists) {
            int index = getMenuItemIndex(burger);
            System.out.printf("%s already exists, is menu item %d. Won't add.\n", 
                               menu.get(index).getFoodName(),
                               menu.get(index).getFoodNum());
            setMenuItemEqual(burger, index);
        }
        else
            menu.add(burger);
        // reset flag
        menuItemExists = false;
        
        menuItemExists = existMenuItem(fries);
        if (menuItemExists) {
            int index = getMenuItemIndex(fries);
            System.out.printf("%s already exist(s), is menu item %d. Won't add.\n", 
                               menu.get(index).getFoodName(),
                               menu.get(index).getFoodNum());
            setMenuItemEqual(fries, index);
        }
        else
            menu.add(fries);
        
        // reset flag
        menuItemExists = false;
        
        menuItemExists = existMenuItem(salad);
        if (menuItemExists) {
            int index = getMenuItemIndex(salad);
            System.out.printf("%s already exist(s), is menu item %d. Won't add.\n", 
                               menu.get(index).getFoodName(),
                               menu.get(index).getFoodNum());
            setMenuItemEqual(salad, index);
            displayMenuItem(menu.get(index));
        }
        else
            menu.add(salad);
        
        
        // Testing display customer information
        System.out.println("After adding burgers and fries, current menu: ");
        for (int j = 0; j < menu.size();j++)
            displayMenuItem(menu.get(j));
       
        
        // Testing editing customer information
        System.out.println("Salad prices have changed.");
        //calculatle random value for updated price
        double newPrice = getRandomDouble();
        System.out.printf("New price of salad: %f\n", newPrice);
        editMenuItem(salad, newPrice);
        System.out.println("Displaying updated info for salads:");
        int indexOfSalad = getMenuItemIndex(salad);
        displayMenuItem(menu.get(indexOfSalad));
        
        // Testing removeMenuItem()
        System.out.println("Removing burgers and fries cuz they're boring.");
        removeMenuItem(burger);
        removeMenuItem(fries);
        System.out.println("Displaying menu after removing some items: ");
        for (int k = 0; k < menu.size();k++)
            displayMenuItem(menu.get(k));
    
        // Save information of order to database
        saveToFile();
        
        System.out.println("Running complete.");
    }
    
    /*
    Function to load existing customers in DB
    */
    public static void loadFile() throws Exception{
        // Load information from file .ser to program as format
        int numFoodItems = 0;// fourth: number of drum in the file
        
        try{
            FileInputStream file = new FileInputStream("menuData.ser");
            ObjectInputStream inFile = new ObjectInputStream(file);
            numFoodItems = inFile.readInt();  // Read the number of drum object         
            for (int index = 0; index < numFoodItems; index++){
                FoodItem temp = (FoodItem)inFile.readObject();
                menu.add(temp);
            }                     
            file.close();
        }catch(FileNotFoundException e){
            FileOutputStream file = new FileOutputStream("menuData.ser");
            file.close();
        }catch(EOFException e){
            FileOutputStream file = new FileOutputStream("menuData.ser");
            file.close();
        }catch(Exception e) {
            FileOutputStream file = new FileOutputStream("menuData.ser");
            file.close();
        }
    }
    
    /*
    Function to save new customer(s) to DB
    */
    public static void saveToFile() throws Exception{
        // Write object from program to file .ser in order
        FileOutputStream file = new FileOutputStream("menuData.ser");
        ObjectOutputStream outFile = new ObjectOutputStream(file);
        //outFile.writeInt(new Integer(drumList.size()));  // fourth: number of drum in the file
        outFile.writeInt(menu.size());  // fourth: number of drum in the file
        // Write object of drum type
        for (FoodItem tempt : menu)
            outFile.writeObject(tempt);
        outFile.close();
    }
    
    /*
    Function to add a new customer to set of existing customers (if any)
    */
    public static void addMenuItem(FoodItem newFood){
        menu.add(newFood);  
    }
    
    /*
    Function to check if a food item is already on the menu (compares by name)
    Return True: exists
    Return False: does not exist
    */
    public static boolean existMenuItem (FoodItem food){
        for (int i = 0; i < menu.size(); i++){
            if (menu.get(i).getFoodName().equals(food.getFoodName()))
                return true;
        }
        return false;
    }
    
    /*
    Function to utilize Customer's toString()
    */
    public static void displayMenuItem(FoodItem food) {
        System.out.println(food.toString());
    }
    
    public static int getMenuItemIndex(FoodItem food) {
        int i;
        for (i = 0; i < menu.size(); i++) {
            if (menu.get(i).getFoodName().equals(food.getFoodName()))
                break;
        }
        return i;
    }
    
    /*
    Function to edit existing menu item's price (name/number won't change).
    Note: If customer does not exist, adds customer
    */
    public static void editMenuItem(FoodItem food, double newCost) {
        if (existMenuItem(food)) {
            // find location of existing food item
            int index = getMenuItemIndex(food);
            if (menu.get(index).getFoodCost() == newCost) {
                System.out.println("Nothing to update. No change in price.");
            }
            else {
                System.out.printf("Price of %s changed, was %f and now is %f.\n",
                                  menu.get(index).getFoodName(),
                                  menu.get(index).getFoodCost(),
                                  newCost);
                menu.get(index).setFoodCost(newCost);
            }
        }
        else
        {
            System.out.println("Menu item does not currently exist, adding now.\n");
            addMenuItem(food);
        }
    }
    
    /*
    Function to remove an existing customer.
    Note: If customer does not exist, does nothing.
    */
    public static void removeMenuItem(FoodItem food) {
        if(existMenuItem(food)){
            for (int i = 0; i < menu.size(); i++) {
                if (menu.get(i).getFoodNum() == food.getFoodNum())
                    menu.remove(i);
            }
        }
    }
    
    public static double getRandomDouble() {
        double min = 5.0;  //  Set To Your Desired Min Value
        double max = 10.0; //    Set To Your Desired Max Value
        double x = (Math.random()*((max-min)+1))+min;
        double xrounded = Math.round(x * 100.0) / 100.0;
        return xrounded;
    }
    
    public static void setMenuItemEqual(FoodItem food, int index) {
        food.setFoodNum(menu.get(index).getFoodNum());
        food.setFoodName(menu.get(index).getFoodName());
        food.setFoodCost(menu.get(index).getFoodCost());
    }
}
