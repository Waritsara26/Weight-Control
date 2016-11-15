package nu.khamenketkan.waritsara.weightcontrol;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by Windows 8.1 on 17/7/2559.
 */

//loopวนนำค่าของ String ไปแสดงผลบน listview
public class MyAdapter extends BaseAdapter{

    //Explicit
    private Context context;
    private String[] titleStrings, valueStrings;

    public MyAdapter(Context context,
                     String[] titleStrings,
                     String[] valueStrings) {
        this.context = context;
        this.titleStrings = titleStrings;
        this.valueStrings = valueStrings;
    } //constructor คือ กำหนดค่าให้กับตัวแปร ถ้ามี 20 ตัวก็ขอ 20 ตัว

    @Override
    public int getCount() {
        return titleStrings.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view1 = layoutInflater.inflate(R.layout.my_listview, viewGroup, false);

        // string 2 ตัวรวมกัน viewgroup เอาไปโชว์บนหน้า listview
        // การสร้าง layout เสมือนไปอยู่บน listview เสมือน = visual layout

        //Bind Widget
        TextView titleTextView = (TextView) view1.findViewById(R.id.textView16);
        TextView valueTextView = (TextView) view1.findViewById(R.id.textView17);

        //Set text
        titleTextView.setText(titleStrings[i]);
        valueTextView.setText(valueStrings[i]);
        return view1;
    }
} //Main Class
