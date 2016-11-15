package nu.khamenketkan.waritsara.weightcontrol;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class CaloriesListView extends AppCompatActivity {

    //Explicit การประกาศตัวแปร
    private String dateString;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calories_list_view);


        //Get Value From Intent
        dateString = getIntent().getStringExtra("Date");
        // รับค่ามาจากหน้าแรก

        //Show date
        TextView textView = (TextView) findViewById(R.id.textView14);
        textView.setText("Date = " + dateString);


        //Create Listview
        createListView();


    } //Main Method

    public void clickQRcode(View view) {

        try {

            Intent intent = new Intent("com.google.zxing.client.android.SCAN");
            intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
            startActivityForResult(intent, 0);

        } catch (Exception e) {
            Toast.makeText(this, "Please Install Barcode Scanner",
                    Toast.LENGTH_SHORT).show();
        }

    }   // clickQRcode

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if ((requestCode ==0) && (resultCode == RESULT_OK)) {


            String strQRcode = data.getStringExtra("SCAN_RESULT");
            Log.d("1novV1", "QR code ==> " + strQRcode);
            String[] QRStrings = strQRcode.split("/");
            Log.d("1novV1", "array0 ==> " + QRStrings[0]);
            Log.d("1novV1", "array1 ==> " + QRStrings[1]);

            MyManage myManage = new MyManage(this);
            myManage.addCalories(dateString, QRStrings[0], QRStrings[1]);
            createListView();



        }   // if

    }   // onActivity

    @Override
    protected void onRestart() {
        super.onRestart();
        createListView();
        // คำสั่งให้มันทำการีเซ็ตตัวเอง
    }

    private void createListView() {

    SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.database_name,
            MODE_PRIVATE, null);
    Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM caloriesTABLE WHERE Date = " + "'" + dateString + "'", null);
    cursor.moveToFirst();
    String[] foodStrings = new String[cursor.getCount()];
    String[] caloriesStrings = new String[cursor.getCount()];

    for (int i=0;i<cursor.getCount();i +=1) {
        foodStrings[i] = cursor.getString(cursor.getColumnIndex(MyManage.column_food));
        caloriesStrings[i] = cursor.getString(cursor.getColumnIndex(MyManage.column_calories));
        cursor.moveToNext();

    } //For
    cursor.close();
    ListView listView = (ListView) findViewById(R.id.listView);
    MyAdapter myAdapter = new MyAdapter(this, foodStrings, caloriesStrings);
    listView.setAdapter(myAdapter);


} // createListView

    public void clickAddCalories(View view) {
        Intent intent = new Intent(CaloriesListView.this, AddCaloriesActivity.class);
        intent.putExtra("Date", dateString);
        startActivity(intent);
        // หน้า listview ไป add และ คำสั่ง โชว์วันที่

    }

    public void clickBackCalories(View view) {
        finish();
        //คำสั่งปุ่ม back
    }
} //Main Class
