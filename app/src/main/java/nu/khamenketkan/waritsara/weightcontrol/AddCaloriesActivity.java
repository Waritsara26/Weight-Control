package nu.khamenketkan.waritsara.weightcontrol;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.content.PermissionChecker;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class AddCaloriesActivity extends AppCompatActivity {
    // Explicit
    private TextView textView;
    private ListView listView;
    private String dateString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_calories);
        //Bind Widget
        textView = (TextView) findViewById(R.id.textView15);
        listView = (ListView) findViewById(R.id.listView2);

        // get & show date
        dateString = getIntent().getStringExtra("Date");
        //textView.setText("Date = " + dateString);
        textView.setText("รายการอาหาร");
// Read All foodTABLE ==> create listview
        readAnCreateListView();

    } //Main Method

    private void readAnCreateListView() {

        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.database_name,
                MODE_PRIVATE, null);
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM foodTABLE", null);
        cursor.moveToFirst();
        final String[] foodStrings = new String[cursor.getCount()];
        final String[] calStrings = new String[cursor.getCount()];
        String[] unitStrings = new String[cursor.getCount()];
        String[] valueStrings = new String[cursor.getCount()];
        // getcount อ่านค่าฐานข้อมูลอัติโนมัต


        for (int i = 0; i < cursor.getCount(); i += 1) {

            foodStrings[i] = cursor.getString(cursor.getColumnIndex(MyManage.column_food));
            calStrings[i] = cursor.getString(cursor.getColumnIndex(MyManage.column_calories));
            unitStrings[i] = cursor.getString(cursor.getColumnIndex(MyManage.column_unit));
            valueStrings[i] = calStrings[i];

            cursor.moveToNext();
        } // for
        cursor.close();

        MyAdapter myAdapter = new MyAdapter(this, foodStrings, valueStrings);
        listView.setAdapter(myAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                chooseAmount(foodStrings[i], calStrings[i]);
                    //เช็คว่ากี่จาน
            } // onItemClick คลิกแล้วโยนสิ่งที่คลิ๊กไปที่ฐานข้อมูล
        });


    } //read foodTable

    private void chooseAmount(final String foodString, final String calString) {

        CharSequence[] charSequences = new CharSequence[]{"1", "2","3","4","5",};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.doremon48);
        builder.setTitle(foodString);
        builder.setSingleChoiceItems(charSequences, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                double douCalories = Double.parseDouble(calString) * (i + 1);

                Log.d("WeightV2", "Amount (" + (i + 1) + ") = " + douCalories);

                MyManage myManage = new MyManage(AddCaloriesActivity.this);
                myManage.addCalories(dateString, foodString, Double.toString(douCalories));
                dialogInterface.dismiss();
                finish();
                //คำสั่งพอเลือก popup เสดแล้วมันจะหายไป
            }// onClick
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        builder.show();

        // คำสั่งทำ popup

    } //chooseAmount
    public void Backfood(View view) {
        Intent intent = new Intent(AddCaloriesActivity.this, CaloriesListView.class);
        intent.putExtra("Date", dateString);
        startActivity(intent);
        finish();
    }


} //Main Class
