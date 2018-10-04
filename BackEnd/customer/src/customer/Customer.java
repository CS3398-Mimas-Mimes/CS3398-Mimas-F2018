package customer;

import java.io.Serializable;

/**
 * Customer Class: This class defines a customer.
 * @author ANDREW
 */
public class Customer implements Serializable {
    private int customerId;
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String phone;
    private String email;
    
    public Customer() {      
    }
    
    public Customer(int id,
                    String firstName,
                    String lastName,
                    String userName,
                    String password,
                    String phone,
                    String email) {
        this.customerId = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.phone = phone;
        this.email = email;
    }
    
    // Getter Methods
    public int getCustomerId() {
        return this.customerId;
    }
    
    public String getFirstName() {
        return this.firstName;
    }
    
    public String getLastName() {
        return this.lastName;
    }
    
    public String getUserName() {
        return this.userName;
    }
    
    // DON'T WANT TO RETURN A USER'S PASSWORD
    
    public String getPhone() {
        return this.phone;
    }
    
    public String getEmail() {
        return this.email;
    }
    
    // Setter Methods (customerId is immutable)
    public void setFirstName(String name) {
        this.firstName = name;
    }
    
    public void setLastName(String name) {
        this.lastName = name;
    }
    
    public void setUserName(String user) {
        this.userName = user;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public void setPhone(String phoneNum) {
        this.phone = phoneNum;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    @Override
    public String toString() {
        return String.format("\n|%1$5s %2$-5d |%3$12s %4$-15s |%5$12s %6$-15s |%7$12s %8$-15s |%9$7s %10$-12s |%11$10s %12$-20s", 
                         "ID: ", customerId,
                         "First Name: ", firstName, 
                         "Last Name: ", lastName,
                         "User Name: ", userName,
                         "Phone: " , phone,
                         "Email: " , email);
    }
}
