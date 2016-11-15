package nu.khamenketkan.waritsara.weightcontrol;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Windows 8.1 on 16/7/2559.
 */
public class MyManage {
    //Explicit
    private MyOpenHelper myOpenHelper;
    private SQLiteDatabase sqLiteDatabase;

    //For foodTABLE
    public static final String food_table = "foodTABLE";
    public static final String column_food = "Food";
    public static final String column_unit = "Unit";
    public static final String column_calories = "Calories";

    //For exerciseTABLE
    public static final String exercise_table = "exerciseTABLE";
    public static final String column_exercise = "Exercise";
    public static final String column_burn = "Burn";

    //For userTABLE
    public static final String user_table = "userTABLE";
    public static final String column_Name = "Name";
    public static final String column_Surname = "Surname";
    public static final String column_Weight = "Weight";
    public static final String column_Height = "Height";
    public static final String column_Sex = "Sex";
    public static final String column_Age = "Age";
    public static final String column_BMR = "BMR";

    //For caloriesTABLE
    public static final String calories_table = "caloriesTABLE";
    public static final String column_date = "Date";

    //For burnTABLE
    public static final String burn_table = "burnTABLE";

    public MyManage(Context context) {

        myOpenHelper = new MyOpenHelper(context);
        sqLiteDatabase = myOpenHelper.getWritableDatabase();

    } //Constructor

    //Add Burn
    public long addBurn(String strDate,
                        String strExercise,
                        String strBurn) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(column_date, strDate);
        contentValues.put(column_exercise, strExercise);
        contentValues.put(column_burn, strBurn);

        return sqLiteDatabase.insert(burn_table, null, contentValues);
    }

    //Add calories
    public long addCalories(String strDate,
                            String strFood,
                            String strCalories) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(column_date, strDate);
        contentValues.put(column_food, strFood);
        contentValues.put(column_calories, strCalories);

        return sqLiteDatabase.insert(calories_table, null, contentValues);
    }

    //Add User
    public long addUser(String strName,
                        String strSurname,
                        String strWeight,
                        String strHeight,
                        String strSex,
                        String strAge,
                        String strBMR) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(column_Name, strName);
        contentValues.put(column_Surname, strSurname);
        contentValues.put(column_Weight, strWeight);
        contentValues.put(column_Height, strHeight);
        contentValues.put(column_Sex, strSex);
        contentValues.put(column_Age, strAge);
        contentValues.put(column_BMR, strBMR);
        //เอาข้อมูลในหน้าsign upไปเก็บไว้ในหน้า usertable
        return sqLiteDatabase.insert(user_table, null, contentValues);
    }

    //Add Exercise
    public long addExercise(String strExercise,
                            String strBurn) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(column_exercise, strExercise);
        contentValues.put(column_burn, strBurn);

        return sqLiteDatabase.insert(exercise_table, null, contentValues);
    }

    // Add Food
    public long addFood(String strFood,
                        String strUnit,
                        String strCalories) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(column_food, strFood);
        contentValues.put(column_unit, strUnit);
        contentValues.put(column_calories, strCalories);

        return sqLiteDatabase.insert(food_table, null, contentValues);
    }
} // Main Class
