# Twitter Clone 

## About

Spring Boot Java-based backend for Social Media App - Twitter, using Gradle, leveraging JPA for ORM and H2 as an in-memory database. Supporting core features such as posting, commenting, reactions, and comprehensive user authentication with BCrypt algorithm, ensuring robust password encoding and secure user experience.

## Key Features

- Supports posting, commenting, reactions, and comprehensive user authentication
- Has an in-memory database h2 for storage
- Has BCryptEncoded user password authentication and JPA for ORM

## Getting started

Developed a robust backend for a social media clone using Spring Boot, leveraging JPA for ORM and H2 as an
in-memory database, validated through comprehensive API testing using Postman for reliability and correctness
• Implemented core features such as posting, commenting, reactions, and comprehensive user authentication with BCrypt
algorithm, ensuring robust password encoding and secure user experience

### Navigating to root directory
```sh
cd /path/to/the/root
```
Download the jar file in the root directory.

### Running the jar file
```sh
table.out
waiter.out
admin.out
hotelmanager.out
```
## How to run?

### For table process
Upon executing the table process, enter a positive integer when prompted :
```sh
Enter Table Number:
Enter Number of Customers at Table:
```

The table process will read the menu from the pre-created menu.txt file, and each customer process orders when prompted :
```sh
Enter the serial number(s) of the item(s) to order from the menu. Enter -1 when done:
```
### For waiter process
Upon executing the waiter process, enter a positive integer when prompted :
```sh
Enter Waiter ID:
```

Once the correct order is received by the waiter process, it calculates the total bill amount of the table and displays :
```sh
Bill Amount for Table X: Y
```
### For hotel manager process
Upon executing the hotel-manager process, enter a positive integer when prompted :
```sh
Enter the Total Number of Tables at the Hotel:
```

On receiving intimation of termination from admin process and only when there are no customers at the hotel, the hotel manager process calculates the total earnings :
```sh
Total Earnings of Hotel: X
Total Wages of Waiters: Y
Total Profit: Z
```
### For admin process
Admin process will keep running along with the table, customer, waiter and hotel manager processes and only one instance of this program is executed when prompted :
```sh
Do you want to close the hotel? Enter Y for Yes and N for No.
```
