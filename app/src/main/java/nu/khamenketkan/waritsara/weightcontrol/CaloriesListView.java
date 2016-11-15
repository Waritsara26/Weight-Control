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

    // Click QRcode
    public void clickQRcode(View view) {

        //เมื่อมีการเรียกใช้การอ่าน QRcode ให้เรียกใช้ libary ที่อยู่ในแอพของ zxing client
        // แล้วเอาผลลัพธ์ที่อ่านได้มาประมวลในแอพของเรา โดยการผ่าน intent
        try {

            Intent intent = new Intent("com.google.zxing.client.android.SCAN");
            intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
            startActivityForResult(intent, 0);

        } catch (Exception e) {
            //แต่ถ้าในมือถือไม่มี zxing client จะขึ้นข้อความขอร้องให้ติดตั้ง Barcode scanner ก่อน
            Toast.makeText(this, "Please Install Barcode Scanner",
                    Toast.LENGTH_SHORT).show();
        }

    }   // clickQRcode

    //นี่คือ method ที่จะทำงานหลังจาก zxing client ทำงานเสร็จแล้ว
    // จะส่งสิ่งที่อ่านได้มาเป็น Data
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if ((requestCode ==0) && (resultCode == RESULT_OK)) {


            String strQRcode = data.getStringExtra("SCAN_RESULT");
            Log.d("1novV1", "QR code ==> " + strQRcode);
            //คือการเอา String ที่อ่านได้จาก QRcode มาทำการแยกโดยใช้เครื่องหมาย / เป็นตัวแยก
            String[] QRStrings = strQRcode.split("/");
            Log.d("1novV1", "array0 ==> " + QRStrings[0]); //ชื่อขนม
            Log.d("1novV1", "array1 ==> " + QRStrings[1]); //ค่าตัวแคลอรี่

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
