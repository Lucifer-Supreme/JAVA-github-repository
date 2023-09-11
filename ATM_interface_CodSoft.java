import java.sql.*;
import java.util.Scanner;

//This is ATM interface where we can withdraw money from , change password , check bank balance along with 2 additional features
//that is deposit money and create account

// this ATM interface is connected to mysql database so we can store balance and other account details permanently
// and retrieve it any time

//below is the structure of the database and table that you need to create before using this code in your own device

//        Database name -> bank
//        Table name -> user_data
//        +-----------------+-------------+------+-----+---------+-------+
//        | Field           | Type        | Null | Key | Default | Extra |
//        +-----------------+-------------+------+-----+---------+-------+
//        | userID          | int         | NO   | PRI | NULL    |       |
//        | username        | varchar(50) | NO   |     | NULL    |       |
//        | password        | int         | NO   |     | NULL    |       |
//        | current_account | int         | YES  |     | NULL    |       |
//        | savings_account | int         | YES  |     | NULL    |       |
//        | total_amount    | int         | YES  |     | NULL    |       |
//        +-----------------+-------------+------+-----+---------+-------+

//to create this you need to run these following querries in you mysql command line
// 1 -> create database bank;
// 2 -> CREATE TABLE user_data ( userID INT NOT NULL PRIMARY KEY, username VARCHAR(50) NOT NULL, password INT(4) NOT NULL, current_account INT, savings_account INT, total_amount INT );

// (NOTE : change the user and password of the mysql in line number 37 according to your user and password)

public class ATM_interface_CodSoft {

    Connection con;//your password for mysql
    Scanner sc = new Scanner(System.in);
    {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root", "13243546");//your mysql credentials
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    void create_account() throws SQLException {
        String insertSQL = "INSERT INTO user_data  VALUES (?, ?, ?,?,?,?)";

        PreparedStatement preparedStatement = con.prepareStatement(insertSQL);
        System.out.println("Enter new account ID -");
        int id = sc.nextInt();
        System.out.println("Enter new account holder's name -");
        sc.nextLine();
        String name = sc.nextLine();

        System.out.println("Enter password -");
        int pass = sc.nextInt();
        System.out.println("Deposit in current account -");
        int ca = sc.nextInt();
        System.out.println("Deposit in savings account -");
        int sa = sc.nextInt();

        int total = ca + sa;

        preparedStatement.setInt(1, id);
        preparedStatement.setString(2, name);
        preparedStatement.setInt(3, pass);
        preparedStatement.setInt(4, ca);
        preparedStatement.setInt(5, sa);
        preparedStatement.setInt(6, total);

        // Execute the INSERT statement
        preparedStatement.execute();

        System.out.println("\nAccount created successfully....");

//                    if (successStatus > 0) {
//                        System.out.println("Data inserted successfully.");
//                    } else {
//                        System.out.println("No rows were inserted.");
//                    }
    }

    void check_balance(){
        String selectSQL = "SELECT * FROM user_data where userID = ?";

        try (
                PreparedStatement preparedStatement = con.prepareStatement(selectSQL)
        ) {

            System.out.println("Enter the account number you want to check balance -");
            int accno = sc.nextInt();
            preparedStatement.setInt(1, accno);

            ResultSet rs = preparedStatement.executeQuery();

            int id = 0;
            String uname = "";
            int pass = 0;
            int ca = 0;
            int sa = 0;


            if (rs.next()) {
                id = rs.getInt("userID");
                uname = rs.getString("username");
                pass = rs.getInt("password");
                ca = rs.getInt("current_account");
                sa = rs.getInt("savings_account");

            }


            int count = 3;

            while (count >= 1) {
                System.out.println("Enter your account password \n(press 1 if incorrect account number)");
                int password = sc.nextInt();


                if (pass == password) {
                    System.out.println("Account Number -" + id + "\nHolder name -" + uname + "\nBalance in current account -" + ca + "\nBalance in savings account -" + sa);
                    System.out.println("\nBank balance fetched successfully....");
                    break;

                } else if (password == 1) {
                    break;

                } else {
                    System.out.println("Inncorrect Password or Account number\nPlease verify both and try again...");
                    count--;

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void deposit_amount(){
        System.out.println("Enter the account number you want to deposit -");
        int accno = sc.nextInt();
        System.out.println("Enter you account password -");
        int pass = sc.nextInt();
        System.out.println("You want to deposit in current account(1) or savings account(2) -");
        int acc = sc.nextInt();
        System.out.println("Enter the ammount you want to deposit");
        int depo = sc.nextInt();

        String selectSQL = "SELECT * FROM user_data where userID = ?";

        try (
                PreparedStatement preparedStatement = con.prepareStatement(selectSQL)
        ) {

            preparedStatement.setInt(1, accno);

            ResultSet rs = preparedStatement.executeQuery();

            int id = 0;
            String uname = "";
            int password = 0;
            int ca = 0;
            int sa = 0;


            if (rs.next()) {
                id = rs.getInt("userID");
                uname = rs.getString("username");
                password = rs.getInt("password");
                ca = rs.getInt("current_account");
                sa = rs.getInt("savings_account");

            }


            if (pass == password) {
                if (acc == 1) {

                    String deposit = "UPDATE user_data set current_account = ? where userID = ?";
                    String deposittotal = "UPDATE user_data set total_amount = ? where userID = ?";
                    int current_update = ca + depo;
                    int total_update = current_update+sa;
                    PreparedStatement prepareStatement = con.prepareStatement(deposit);
                    PreparedStatement prepareeStatement = con.prepareStatement(deposittotal);


                    prepareStatement.setInt(1, current_update);
                    prepareStatement.setInt(2, id);
                    prepareeStatement.setInt(1, total_update);
                    prepareeStatement.setInt(2, id);


                    // Execute the INSERT statement
                    prepareStatement.execute();
                    prepareeStatement.execute();
                    System.out.println("\nAmount deposited successfully....");

                } else {
                    String deposit = "UPDATE user_data set savings_account = ? where userID = ?";
                    String deposittotal = "UPDATE user_data set total_amount = ? where userID = ?";

                    int savings_update = sa + depo;
                    int total_update = savings_update+ca;
                    PreparedStatement prepareStatement = con.prepareStatement(deposit);
                    PreparedStatement prepareeStatement = con.prepareStatement(deposittotal);


                    prepareStatement.setInt(1, savings_update);
                    prepareStatement.setInt(2, id);
                    prepareeStatement.setInt(1, total_update);
                    prepareeStatement.setInt(2, id);


                    // Execute the INSERT statement
                    prepareStatement.execute();
                    prepareeStatement.execute();
                    System.out.println("\nAmount deposited successfully....");

                }
            } else {
                System.out.println("password or account number is incorrect");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    void withdraw_amount(){
        System.out.println("Enter the account number you want to withdraw  -");
        int accno = sc.nextInt();
        System.out.println("Enter you account password -");
        int pass = sc.nextInt();
        System.out.println("You want to withdraw in current account(1) or savings account(2) -");
        int acc = sc.nextInt();
        System.out.println("Enter the ammount you want to withdraw");
        int withdraw = sc.nextInt();

        String selectSQL = "SELECT * FROM user_data where userID = ?";

        try (
                PreparedStatement preparedStatement = con.prepareStatement(selectSQL)
        ) {

            preparedStatement.setInt(1, accno);

            ResultSet rs = preparedStatement.executeQuery();

            int id = 0;
            String uname = "";
            int password = 0;
            int ca = 0;
            int sa = 0;


            if (rs.next()) {
                id = rs.getInt("userID");
                uname = rs.getString("username");
                password = rs.getInt("password");
                ca = rs.getInt("current_account");
                sa = rs.getInt("savings_account");

            }


            if (pass == password) {
                if (acc == 1) {
                    if (ca>=withdraw) {

                        String deposit = "UPDATE user_data set current_account = ? where userID = ?";
                        String deposittotal = "UPDATE user_data set total_amount = ? where userID = ?";
                        int current_update = ca - withdraw;
                        int total_update = current_update + sa;
                        PreparedStatement prepareStatement = con.prepareStatement(deposit);
                        PreparedStatement prepareeStatement = con.prepareStatement(deposittotal);


                        prepareStatement.setInt(1, current_update);
                        prepareStatement.setInt(2, id);
                        prepareeStatement.setInt(1, total_update);
                        prepareeStatement.setInt(2, id);


                        // Execute the INSERT statement
                        prepareStatement.execute();
                        prepareeStatement.execute();
                        System.out.println("\nAmount withdrawn successfully....");

                    }

                    else {
                        System.out.println("Insufficient balance...");
                    }

                }
                else {
                    if (sa>=withdraw) {
                        String deposit = "UPDATE user_data set savings_account = ? where userID = ?";
                        String deposittotal = "UPDATE user_data set total_amount = ? where userID = ?";

                        int savings_update = sa - withdraw;
                        int total_update = savings_update + ca;
                        PreparedStatement prepareStatement = con.prepareStatement(deposit);
                        PreparedStatement prepareeStatement = con.prepareStatement(deposittotal);


                        prepareStatement.setInt(1, savings_update);
                        prepareStatement.setInt(2, id);
                        prepareeStatement.setInt(1, total_update);
                        prepareeStatement.setInt(2, id);


                        // Execute the INSERT statement
                        prepareStatement.execute();
                        prepareeStatement.execute();
                        System.out.println("\nAmount withdrawn successfully....");

                    }
                    else {
                        System.out.println("Insufficient balance...");
                    }

                }
            } else {
                System.out.println("password or account number is incorrect");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    void change_password(){
        System.out.println("Enter the account number you change password  -");
        int accno = sc.nextInt();
        System.out.println("Enter you account existing password -");
        int pass = sc.nextInt();
        System.out.println("Enter the new password -");
        int newPass = sc.nextInt();

        String selectSQL = "SELECT * FROM user_data where userID = ?";

        try (
                PreparedStatement preparedStatement = con.prepareStatement(selectSQL)
        ) {

            preparedStatement.setInt(1, accno);

            ResultSet rs = preparedStatement.executeQuery();

            int id = 0;
            int password = 0;


            if (rs.next()) {
                id = rs.getInt("userID");
                password = rs.getInt("password");

            }


            if (pass == password) {

                String change = "UPDATE user_data set password = ? where userID = ?";

                PreparedStatement prepareStatement = con.prepareStatement(change);


                prepareStatement.setInt(1, newPass);
                prepareStatement.setInt(2, id);


                // Execute the INSERT statement
                prepareStatement.execute();
                System.out.println("\nPassword changed successfully....");
            }
            else {
                System.out.println("password or account number is incorrect");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    void exit(){

    }
    public static void main(String[] args) {
        System.out.println("<><><<><>>>><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><<");
        System.out.println("                      WELCOME TO CHAPRA BANK ATM                        ");
        System.out.println("<><><<><>>>><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><<");
        try {
            while (true) {
                System.out.println("<><><<><>>>><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><<\n");
                System.out.println("  Select an action\n");
                System.out.println("1-> create account\n2-> check balance\n3-> Add ammount\n4-> Withdraw money\n5-> Change Password\n6-> Exit\n");
                System.out.println("<><><<><>>>><><><><><><><><><><><><><><><><><><><><><><><><><><><><><><<");
                Scanner sc = new Scanner(System.in);
                int choice = sc.nextInt();

                if (choice == 1) {

                    ATM_interface_CodSoft obj = new ATM_interface_CodSoft();

                    obj.create_account();

                } else if (choice == 2) {

                    ATM_interface_CodSoft obj = new ATM_interface_CodSoft();
                    obj.check_balance();

                } else if (choice == 3) {

                    ATM_interface_CodSoft obj = new ATM_interface_CodSoft();
                    obj.deposit_amount();

                }
                else if (choice==4) {

                    ATM_interface_CodSoft obj = new ATM_interface_CodSoft();
                    obj.withdraw_amount();

                } else if (choice==5) {

                    ATM_interface_CodSoft obj = new ATM_interface_CodSoft();
                    obj.change_password();


                } else if (choice==6) {
                    break;

                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}