package customer;

import java.io.EOFException;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * CustomerDB Class: This class is in charge of accessing the customer database
 *                   and either reading from it or saving to it.
 * @author ANDREW
 */
public class CustomerDB {
    private static ArrayList<Customer> customerList = new ArrayList<Customer>();;
    
    public static void main(String[] args) throws Exception {
        
        // Load order database to system
        loadFile();
    
        Customer customer1 = new Customer(1, "Little", "Timmy", "Lil' Timmy",
                                            "123", "1112223333", "lil_T@gmail.com");
        Customer customer2 = new Customer(2, "Big", "Timmy", "Big' Timmy",
                                            "456", "4445556666", "big_T@gmail.com");

        // Add Little Timmy to the list of existing customers
        customerList.add(customer1);
        
        // Testing addCustomer()
        boolean customerExists = existCustomer(customer1);
        if (customerExists) {
            System.out.printf("%s %s already exists, won't add.\n", 
                               customer1.getFirstName(),
                               customer1.getLastName());
        }
        else
            customerList.add(customer1);
        // reset flag
        customerExists = false;
        
        customerExists = existCustomer(customer2);
        if (customerExists) {
            System.out.printf("%s %s already exists, won't add.\n", 
                               customer2.getFirstName(),
                               customer2.getLastName());
        }
        else
            customerList.add(customer2);
            
        // Testing display customer information
        System.out.println("Display the first customer in the list: \n");
        displayCustomer(customerList.get(0));
    
        // Testing editing customer information
        System.out.println("Little Timmy wants to change his email. \n");
        editCustomer(customer1, 6, "best_T@gmail.com");
        System.out.println("Displaying updated info for Little Timmy:");
        displayCustomer(customer1);
    
        // Testing removeCustomer()
        System.out.println("We want to remove Little Timmy. He is not old enough.");
        removeCustomer(customer1);
        System.out.println("Displaying new first customer:");
        displayCustomer(customerList.get(0));
    
        // Save information of order to database
        saveToFile();
        
        System.out.println("Testing successful.");
  }
    
    /*
    Function to load existing customers in DB
    */
    public static void loadFile() throws Exception{
        // Load information from file .ser to program as format
        int numCustomers = 0;// fourth: number of drum in the file
        
        try{
            FileInputStream file = new FileInputStream("customerData.ser");
            ObjectInputStream inFile = new ObjectInputStream(file);
            numCustomers = inFile.readInt();  // Read the number of drum object         
            for (int index = 0; index < numCustomers; index++){
                Customer temp = (Customer)inFile.readObject();
                customerList.add(temp);
            }                     
            file.close();
        }catch(FileNotFoundException e){
            FileOutputStream file = new FileOutputStream("customerData.ser");
            file.close();
        }catch(EOFException e){
            FileOutputStream file = new FileOutputStream("customerData.ser");
            file.close();
        }
    }
    
    /*
    Function to save new customer(s) to DB
    */
    public static void saveToFile() throws Exception{
        // Write object from program to file .ser in order
        FileOutputStream file = new FileOutputStream("customerData.ser");
        ObjectOutputStream outFile = new ObjectOutputStream(file);
        //outFile.writeInt(new Integer(drumList.size()));  // fourth: number of drum in the file
        outFile.writeInt(customerList.size());  // fourth: number of drum in the file
        // Write object of drum type
        for (Customer tempt : customerList)
            outFile.writeObject(tempt);
        outFile.close();
    }
    
    /*
    Function to add a new customer to set of existing customers (if any)
    */
    public static void addCustomer(Customer newCustomer){
        customerList.add(newCustomer);  
    }
    
    /*
    Fucntion to check if a customer exists or not
    Return True: exist
    Return False: does not exist
    */
    public static boolean existCustomer (Customer customer){
        for (int i = 0; i < customerList.size(); i++){
            if (customerList.get(i).getCustomerId() == customer.getCustomerId())
                return true;
        }
        return false;
    }
    
    /*
    Function to utilize Customer's toString()
    */
    public static void displayCustomer(Customer customer) {
        System.out.println(customer.toString());
    }
    
    /*
    Function to edit existing customer's information
    Note: If customer does not exist, adds customer
    */
    public static void editCustomer(Customer customer, int option, String newValue) {
        if (existCustomer(customer)) {
            System.out.printf("%s %s wants to edit their information.\n", 
                              customer.getFirstName(),
                              customer.getLastName());
            if (option == 1) // Customer wants to change their first name
                customer.setFirstName(newValue);
            else if (option == 2) // Customer wants to change their last name
                customer.setLastName(newValue);
            else if (option == 3) //Customer wants to change their username
                customer.setUserName(newValue);
            else if (option == 4) //Customer wants to change their password
                customer.setPassword(newValue);
            else if (option == 5) //Customer wants to change their phone
                customer.setPhone(newValue);
            else // Customer wants to change their email
                customer.setEmail(newValue);
        }
        else
        {
            System.out.println("Customer does not currently exist, adding now.\n");
            addCustomer(customer);
        }
    }
    
    /*
    Function to remove an existing customer.
    Note: If customer does not exist, does nothing.
    */
    public static void removeCustomer(Customer customer) {
        if(existCustomer(customer)){
            for (int i = 0; i < customerList.size(); i++) {
                if (customerList.get(i).getCustomerId() == customer.getCustomerId())
                    customerList.remove(i);
            }
        }
    }
    
}