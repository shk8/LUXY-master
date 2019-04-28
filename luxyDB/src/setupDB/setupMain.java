package setupDB;

/**
 *
 * @author Sherry Zhuang
 */
//Imports
import java.util.InputMismatchException;
import java.util.Scanner;

public class setupMain
{
    //Default variables that can be changed according to user's personal settings
    static final String URL = "jdbc:mysql://luxy.cqqk9altr3km.us-east-1.rds.amazonaws.com:3306/";
    static final String DB = "jdbc:mysql://luxy.cqqk9altr3km.us-east-1.rds.amazonaws.com:3306/luxy";
    static final String USER = "root";
    static final String PW = "rootROOT";

    public static void main(String[] args)
    {
        //Create instances
        setupDB.createDB createDB = new setupDB.createDB();
        setupDB.createTables createTables = new setupDB.createTables();
        setupDB.insertMockData insertMockData = new setupDB.insertMockData();

        //Instantiate scanner
        Scanner sc = new Scanner(System.in);

        //Variable declaration
        int menuChoice;

        //Display menu
        System.out.print("    To setup LUXY Database:\nChoose ONE of the options"
                + " below\n-------------------------------\n"
                + "1. Create Database & Tables\n\n2. Create Database & Tables"
                + "\n\t\t+\n\tInsert Mock Data\n\nYour Selection: ");
        //Switch Case
        try
        {
            menuChoice = sc.nextInt();
            switch (menuChoice)
            {
                //Create database & tables
                case 1:
                    createDB.createDB(URL, USER, PW);
                    createTables.createTables(DB, USER, PW);
                    break;
                //Create database & tables + insert mock data
                case 2:
                    createDB.createDB(URL, USER, PW);
                    createTables.createTables(DB, USER, PW);
                    insertMockData.insertMockData(DB, USER, PW);
                    break;
                //Exit
                default:
                    System.exit(0);
                    break;
            }
        } //Handle errors
        catch (InputMismatchException IME)
        {
            sc.nextLine();
            System.out.println("ERROR: Invalid selection.");
        }
    }
} //End setupMain
