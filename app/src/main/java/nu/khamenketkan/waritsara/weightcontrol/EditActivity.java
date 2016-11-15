package nu.khamenketkan.waritsara.weightcontrol;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {

    //Explicit
    private EditText nameEditText, surEditText,
            weihtEditText, heihEditText,
            ageEditText, sexEditText;
    private Button button;
    private MyCalculateBmr myCalculateBmr;
    private String nameString, surnameString, weightString,
            heightString, ageString, sexString, bmrString;
    private String[] sexStrings = new String[]{"Male", "Female"};
    private int index = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);


        //Bind widget เอา Explicit มาผูกกับ widget
        nameEditText = (EditText) findViewById(R.id.editText8);
        surEditText = (EditText) findViewById(R.id.editText9);
        weihtEditText = (EditText) findViewById(R.id.editText10);
        heihEditText = (EditText) findViewById(R.id.editText11);
        ageEditText = (EditText) findViewById(R.id.editText12);
        sexEditText = (EditText) findViewById(R.id.editText13);
        button = (Button) findViewById(R.id.button8);

        //load value from SQLite to Display on Edit Text โหลดข้อมูลจากฐานข้อมูลมาโชว์
        final SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.database_name,
                MODE_PRIVATE, null);
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM userTABLE", null);
        cursor.moveToFirst();

        nameEditText.setText(cursor.getString(cursor.getColumnIndex(MyManage.column_Name)));
        surEditText.setText(cursor.getString(cursor.getColumnIndex(MyManage.column_Surname)));
        weihtEditText.setText(cursor.getString(cursor.getColumnIndex(MyManage.column_Weight)));
        heihEditText.setText(cursor.getString(cursor.getColumnIndex(MyManage.column_Height)));
        ageEditText.setText(cursor.getString(cursor.getColumnIndex(MyManage.column_Age)));
        sexEditText.setText(cursor.getString(cursor.getColumnIndex(MyManage.column_Sex)));


        //Button controller
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nameString = nameEditText.getText().toString().trim();
                surnameString = surEditText.getText().toString().trim();
                weightString = weihtEditText.getText().toString().trim();
                heightString = heihEditText.getText().toString().trim();
                ageString = ageEditText.getText().toString().trim();
                sexString = sexEditText.getText().toString().trim();

                if (sexString.equals(sexStrings[1])) {
                    index = 1;
                } //if

                myCalculateBmr = new MyCalculateBmr(EditActivity.this,
                        index,
                        Double.parseDouble(weightString),
                        Double.parseDouble(heightString),
                        Double.parseDouble(ageString));
                String strBMR = myCalculateBmr.myBMR();

                //Delete All SQLite ลบข้อมูลเก่าทั้งหมด
                sqLiteDatabase.delete(MyManage.user_table, null, null);

                MyManage myManage = new MyManage(EditActivity.this);
                myManage.addUser(nameString, surnameString, weightString, heightString,
                        sexString, ageString, strBMR);
                Toast.makeText(EditActivity.this, "แก้ไขเรียบร้อย", Toast.LENGTH_SHORT).show();
                finish();

            } // onclick ถ้ามีการคลิ๊กจะทำงานตรงนี้
        });

    } // Main method
} //Main Class
