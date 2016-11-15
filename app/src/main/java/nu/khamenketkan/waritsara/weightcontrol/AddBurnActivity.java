package nu.khamenketkan.waritsara.weightcontrol;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class AddBurnActivity extends AppCompatActivity {

    //Explicit
    private String dateString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_burn);

        dateString = getIntent().getStringExtra("Date");
        TextView textView = (TextView) findViewById(R.id.txtBurnAdd);
        textView.setText("Date = " + dateString);

        //Create ListView
        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.database_name,
                MODE_PRIVATE, null);
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM exerciseTABLE", null);
        cursor.moveToFirst();
        final String[] exerciseStrings = new String[cursor.getCount()];
        final String[] burnStrings = new String[cursor.getCount()];

        for (int i=0;i<cursor.getCount();i+=1) {
            exerciseStrings[i] = cursor.getString(cursor.getColumnIndex(MyManage.column_exercise));
            burnStrings[i] = cursor.getString(cursor.getColumnIndex(MyManage.column_burn));
            cursor.moveToNext();

        } //for
        cursor.close();
        MyAdapter myAdapter = new MyAdapter(this, exerciseStrings, burnStrings);
        ListView listView = (ListView) findViewById(R.id.livBurnAdd);
        listView.setAdapter(myAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                chooseAmount(exerciseStrings[i], burnStrings[i]);

            } //onItem
        });

    } //Main Method



    private void chooseAmount(final String exerciseString, final String burnString) {

        CharSequence[] charSequences = new CharSequence[]{"30 นาที", "60 นาที","90 นาที","120 นาที","150 นาที","180 นาที"};
        final double[] factorDoubles = new double[]{0.5, 1, 1.5, 2, 2.5, 3};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.drawable.doremon48);
        builder.setTitle(exerciseString);
        builder.setSingleChoiceItems(charSequences, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                double douBurn = Double.parseDouble(burnString) * factorDoubles[i];

                MyManage myManage = new MyManage(AddBurnActivity.this);
                myManage.addBurn(dateString, exerciseString, Double.toString(douBurn));
                dialogInterface.dismiss();
                finish();
                //คำสั่งพอเลือก popup เสดแล้วมันจะหายไป
            }// onClick
        });
        builder.show();

        // คำสั่งทำ popup

    } //chooseAmount


} //Main Class
