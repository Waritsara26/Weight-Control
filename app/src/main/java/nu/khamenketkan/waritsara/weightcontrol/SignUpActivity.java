package nu.khamenketkan.waritsara.weightcontrol;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {
    //Explicit
    private EditText nameEditText, surnameEditText, weightEditText, heightEditText,
    ageEditText;
    private RadioGroup radioGroup;
    private RadioButton maleRadioButton , femaleRadioButton ;
    private String nameString, surnameString, weightString, heightString, ageString,
    sexString;
    private int indexAnInt = 0;
    private String[] sexStrings = new  String[]{"Male","Female"};
    private double douBMR = 0;

    //การประกาศตัวแปร

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Bind Widget
        nameEditText = (EditText) findViewById(R.id.editText);
        surnameEditText = (EditText) findViewById(R.id.editText2);
        weightEditText = (EditText) findViewById(R.id.editText3);
        heightEditText = (EditText) findViewById(R.id.editText4);
        ageEditText = (EditText) findViewById(R.id.editText5);
        radioGroup = (RadioGroup) findViewById(R.id.ragSex);
        maleRadioButton = (RadioButton) findViewById(R.id.radioButton);
        femaleRadioButton = (RadioButton) findViewById(R.id.radioButton2);


        //Radio Controller
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.radioButton:
                        indexAnInt = 0;
                        break;
                    case R.id.radioButton2:
                        indexAnInt = 1;
                        break;
                }
            }
        });


    } //Main Method

    public void clickSignUp(View view) {

        //Get Value From Edit Text
        nameString = nameEditText.getText().toString().trim();
        surnameString = surnameEditText.getText().toString().trim();
        weightString = weightEditText.getText().toString().trim();
        heightString = heightEditText.getText().toString().trim();
        ageString = ageEditText.getText().toString().trim();


        // CheckSpace เช็คว่ากรอกครบทุกช่องไหม
        if (nameString.equals("") ||
                surnameString.equals("")||
                weightString.equals("") ||
                heightString.equals("") ||
                ageString.equals("")) {
            //Have Space
            Toast.makeText(this, "กรุณากรอกทุกช่องคะ",Toast.LENGTH_SHORT).show();
        } else {
            //No Space
            ConfirmData();

        }  // if

    } //clickSignUp

    private void ConfirmData() {
        // Calculate BMR
        double[] maleFactorDoubles = new double[]{66, 13.7, 5, 6.8};
        double[] femaleFactorDoubles = new double[]{665, 9.6, 1.8, 4.7};


        switch (indexAnInt) {
            case 0: //ForMale
                douBMR = maleFactorDoubles[0]
                        + (maleFactorDoubles[1] * Double.parseDouble(weightString)) +
                        (maleFactorDoubles[2] * Double.parseDouble(heightString)) -
                        (maleFactorDoubles[3] * Double.parseDouble(ageString));
                break;
            case 1: //ForFemale
                douBMR = femaleFactorDoubles[0]
                        + (femaleFactorDoubles[1] * Double.parseDouble(weightString)) +
                        (femaleFactorDoubles[2] * Double.parseDouble(heightString)) -
                        (femaleFactorDoubles[3] * Double.parseDouble(ageString));
                break;
        }


        //Create Alert
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setIcon(R.drawable.doremon48);
        builder.setTitle("Please Confirm Data");
        builder.setMessage("Name = " + nameString + "\n" +
        "Surname = " + surnameString + "\n" +
        "Weight = " + weightString + "Kg." + "\n" +
        "Height = " + heightString + "Cm." + "\n"+
        "Age = " + ageString + "Year." + "\n"+
        "Sex = " + sexStrings[indexAnInt] + "\n" + "\n" +
        "BMR ==> " + Double.toString(douBMR));
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyManage myManage = new MyManage(SignUpActivity.this);
                myManage.addUser(nameString, surnameString, weightString,
                        heightString,sexStrings[indexAnInt], ageString, Double.toString(douBMR));
                dialogInterface.dismiss();
                finish();
      // my manage add เดต้าใหม่เข้าไปข้างใน ถ้าคอนเฟิมแล้วเสดแล้วจะไปที่หน้าแรกเลย
            }
        });
        builder.show();
    } //confirmData

} //Main class
