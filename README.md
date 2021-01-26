# Inventory System In Java

I have created this repository for creating my **own customized Inventory System**. I have used **Java, JDBC** for the project. The database used here is **MySQL**.

## Workflow

> In the next few lines, I would like to summarize the general workflow of my project for easy understanding.

1. Fetching information from text files
2. With the fetched information, I have done the following operations in the same order:

- Creating customers and updating customer information.
- Creating stock items and setting the values of stock items.
- Adding those stock items into order items and updating the stocks left.
- Clubbing the order items into purchase orders.
- Setting the purchase orders for each customer with appropiate values.
- Implementing various methods related to the functionality of project.

3. Now, after everything, I have connected my project with the database.
4. Before storing into database, I am initializing the complete database and setting up the database as per my current project needs.

   > This has made my project free from all sorts of prior database setup including creating databases and tables and constraints etc. Any one can also customize this behaviour as per their requirement 😊

5. Finally, each and every information is stored into the database.

## Project Structure

📦inventory

┣ 📂dao

┃ ┣ 📜CustomerDao.java

┃ ┣ 📜OrderItemDao.java

┃ ┣ 📜PurchaseOrderDao.java

┃ ┗ 📜StockItemDao.java

┣ 📂database

┃ ┗ 📜DatabaseUtil.java

┣ 📂invoices

┃ ┣ 📜BILL - INVOICE.txt

┃ ┣ 📜JAMIE - INVOICE.txt

┃ ┣ 📜JOE - INVOICE.txt

┃ ┗ 📜SOMRAJ - INVOICE.txt

┣ 📂model

┃ ┣ 📜Customer.java

┃ ┣ 📜InsufficientDataException.java

┃ ┣ 📜OrderItem.java

┃ ┣ 📜PurchaseOrder.java

┃ ┗ 📜StockItem.java

┣ 📂service

┃ ┣ 📜CustomerService.java

┃ ┣ 📜DatabaseService.java

┃ ┣ 📜FileService.java

┃ ┗ 📜StockItemService.java

┣ 📂tester

┃ ┣ 📜CustomerTest.java

┃ ┗ 📜InventoryUtil.java

┣ 📜customer-info.txt

┗ 📜data.txt

## Developer

LinkedIn : [iamsomraj](https://www.linkedin.com/in/iamsomraj/) 😊

HackerRank: [iamsomraj](https://www.hackerrank.com/iamsomraj?hr_r=1) 😊

Portfolio: [Somraj Mukherjee](https://iamsomraj.github.io/) 😊

Google Play Store: [Somraj Mukherjee](https://play.google.com/store/apps/developer?id=Somraj+Mukherjee) 😊

## Show Your Support

Give me a star ⭐

if this project helped you 👦 👧

## Contributing

Pull requests are welcome. 🤝 For major changes, please open an issue first to discuss what you would like to change. 🙏

Please make sure to update tests as appropriate. ✌

## License

[MIT](https://choosealicense.com/licenses/mit/) 📰
