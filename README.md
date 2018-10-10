# CS3398-Mimas-F2018
An Android restaurant ordering app allows the user to check various categories of foods available in the restaurant and place orders. This system provides a categorized list of food products. After installing this application, the user have to login into the system and can start placing an order. If they like any product they can add to cart and purchase the products once they stop surfing for more. Users can make payment with secure online payment via credit card. After the payment is successful, the user receives a conformation of payment email. This application makes it easy for the restaurant owner and the customer to make easy to buy products. The products in the application will be the products that are stored in the database. This application is very easy to use and helps the customer to buy product at the user’s convenience.

# Code Branches
<a href="https://github.com/CS3398-Mimas-Mimes/CS3398-Mimas-F2018/tree/orderTransaction">OrderTransaction Branch.</a><br>    
- Contains codes of Order and Transaction Database

<a href="https://github.com/CS3398-Mimas-Mimes/CS3398-Mimas-F2018/tree/master/BackEnd/customer">Customer Branch.</a><br>    
- Contains codes of Customer Database
- Currently having issues building with Travis-CI, however it builds on local machine. In process of figuring out why incorporation does not successfully build (ticket in-progress, see for details). 

<a href="https://github.com/CS3398-Mimas-Mimes/CS3398-Mimas-F2018/tree/master/BackEnd/Menu">Menu Branch.</a><br>    
- Contains codes of Menu Database
- Currently having issues building with Travis-CI, however it builds on local machine. In process of figuring out why incorporation does not successfully build (ticket in-progress, see for details).

# Travis-CI
Auto builds with Travis-CI (Running Code gets Automatic)

<a href="https://travis-ci.com/CS3398-Mimas-Mimes/CS3398-Mimas-F2018/builds/86799670">OrderTransaction Branch.</a><br>
<a href="https://travis-ci.com/CS3398-Mimas-Mimes/CS3398-Mimas-F2018/builds/87372068">Create_Menu_Class Branch.</a><br>

*We are currently working on integrating Android Studio with GitHub so in the future, we can download and run our app directly from code on GitHub. At the moment, code does not download and run on local machine, there are several dependencies that are in the works. Our goal is to have this issue addressed in the next sprint.

# Status of Project
Currently, we have a frontend (which is built using Android Studio), that displays a basic login/signup/homepage for our restaurant ordering app. This frontend is not yet connected with our backend (which includes java code for Customer, Menu items, Orders, and Transactions). Our goal is to connect these two ends together in the next sprint. Next steps for our team members include:
* Andrew Hyatt: Research and set up database and server, possibly using Herokuapp (discussed in class).
* Danh Pham: 
* Le Cuong: Design and format Homepage
* Zak King:
* Alexander Muyshondt:

# Individual (Artifact References and Proof of Work)
Andrew Hyatt

* <a href="https://github.com/CS3398-Mimas-Mimes/CS3398-Mimas-F2018/tree/master/BackEnd/customer">Customer Code.</a><br>
* One issue that I can improve is how I create new issues (and my branching strategy for each issue). 
*

Danh Pham

* <a href="https://github.com/CS3398-Mimas-Mimes/CS3398-Mimas-F2018/tree/orderTransaction">OrderTransaction Branch.</a><br>


Le Cuong



Zak King



Alexander Muyshondt



# Retroscpective (Sprint 1)
Mimas-Mimes:

What went well?
* We effectively split the work into two subgroups (frontend web development, and backend code in java). 
* Backend developers (Danh and Andrew) communicated well together.
* Frontend developers (Le, Zak, and Alexander) became much more comfortable using Android Studios.
* As a group, we are all more comfortable with using GitHub and the processes for branching and pushing code.


What can we do to improve?
* We can improve our communication by meeting up as a group more often. This would ensure that we are all on the same page.


What Might Be Impeding Us from Performing Better?
* Communication between the frontend developers and backend developers impedes work as understanding is not shared in terms of the functionality of our app.
* A lack of Android studio integration with GitHub led backend developers to not understand how the two ends should meet. This also impedes graders from downloading and running code directly (as-is) from GitHub.

