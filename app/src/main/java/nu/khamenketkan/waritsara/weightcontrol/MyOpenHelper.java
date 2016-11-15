package nu.khamenketkan.waritsara.weightcontrol;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Windows 8.1 on 16/7/2559.
 */
public class MyOpenHelper extends SQLiteOpenHelper{

    //Explicit
    public static final String database_name = "Weight.db";

    private static final int database_version = 1;

    //For userTABLE
    private static final String create_user_table = "create table userTABLE (" +
            "_id integer primary key, " +
            "Name text, " +
            "Surname text," +
            "Weight text," +
            "Height text," +
            "Sex text," +
            "Age text," +
            "BMR text);";

    // For caloriesTABLE
    private static final String create_caloriesTABLE = "create table caloriesTABLE (" +
            "_id integer primary key," +
            "Date text," +
            "Food text," +
            "Calories text);";

    //For burnTABLE
    private static final String create_burnTABLE = "create table burnTABLE (" +
            "_id integer primary key," +
            "Date text," +
            "Exercise text," +
            "Burn text);";
    //For foodTABLE
    private static final String create_foodTABLE = "create table foodTABLE (" +
            "_id integer primary key," +
            "Food text," +
            "Unit text," +
            "Calories text);";


    //For exerciseTABLE
    private static final String create_exerciseTABLE = "create table exerciseTABLE (" +
            "_id integer primary key," +
            "Exercise text," +
            "Burn text);";

    public MyOpenHelper(Context context) {
        super(context, database_name, null, database_version);
    } // Constructor

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(create_user_table);
        sqLiteDatabase.execSQL(create_caloriesTABLE);
        sqLiteDatabase.execSQL(create_burnTABLE);
        sqLiteDatabase.execSQL(create_foodTABLE);
        sqLiteDatabase.execSQL(create_exerciseTABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
} // Main Class

