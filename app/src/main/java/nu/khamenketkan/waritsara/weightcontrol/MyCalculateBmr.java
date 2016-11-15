package nu.khamenketkan.waritsara.weightcontrol;

import android.content.Context;

/**
 * Created by masterUNG on 11/15/2016 AD.
 */

public class MyCalculateBmr {

    //Explicit
    private Context context;
    private int sexAnInt; // 0 ==> male, 1 ==> female
    private double weightADouble, heightADouble, ageADouble;

    public MyCalculateBmr(Context context,
                          int sexAnInt,
                          double weightADouble,
                          double heightADouble,
                          double ageADouble) {
        this.context = context;
        this.sexAnInt = sexAnInt;
        this.weightADouble = weightADouble;
        this.heightADouble = heightADouble;
        this.ageADouble = ageADouble;
    }

    public String myBMR() {

        double BMR = 0;
        double[] maleFactorDoubles = new double[]{66, 13.7, 5, 6.8};
        double[] femaleFactorDoubles = new double[]{665, 9.6, 1.8, 4.7};

        switch (sexAnInt) {
            case 0:

                BMR = maleFactorDoubles[0]
                        + (maleFactorDoubles[1] * weightADouble) +
                        (maleFactorDoubles[2] * heightADouble) -
                        (maleFactorDoubles[3] * ageADouble);

                break;
            case 1:

                BMR = femaleFactorDoubles[0]
                        + (femaleFactorDoubles[1] * weightADouble) +
                        (femaleFactorDoubles[2] * heightADouble) -
                        (femaleFactorDoubles[3] * ageADouble);

                break;
        }


        return Double.toString(BMR);
    }

}   // Main Class