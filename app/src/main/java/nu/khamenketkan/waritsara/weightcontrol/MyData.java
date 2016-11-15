package nu.khamenketkan.waritsara.weightcontrol;

/**
 * Created by Windows 8.1 on 16/7/2559.
 */
public class MyData {

    //Explicit
    String[] foodStrings = new String[]{
            "ข้าวผัดกะเพราหมู",
            "ข้าวผัดกะเพราไก่", "ข้าวผัดกะเพรากุ้ง",
            "ข้าวผัดกะเพราเนื้อ", "ข้าวผัดกะเพราไก่ไข่ดาว",
            "ก๋วยจั๊บ", "ก๋วยเตี๋ยวคั่วไก่",
            "ก๋วยเตี๋ยวต้มยำกุ้ง", "ก๋วยเตี๋ยวผัดไทยใส่ไข่", "ก๋วยเตี๋ยวเรือน้ำตก", };
    String[] unitStrings = new String[]{
            "จาน",
            "จาน","จาน",
            "จาน","จาน",
            "ชาม", "จาน",
            "ถ้วย","จาน","ชาม", };
    String[] caloriesStrings = new String[]{
            "580",
            "554", "540",
            "622", "630",
            "240", "435",
            "320", "577", "180",};
    String[] exerciseStrings = new String[]{"วิ่งเร็ว","วิ่งเหยาะๆ","ว่ายน้ำ","เต้น Zumba","เดินธรรมดา",
            "เดินเร็ว","เดินลงบันได","แอโรบิค","แบดมินตัน","บาสเก็ตบอล",};
    String[] burnStrings = new String[]{"1200",
            "750",
            "750",
            "500",
            "300",
            "480",
            "425","600","350","660",};

    int[] iconInts = new int[]{R.drawable.p1, R.drawable.p2, R.drawable.p3,
            R.drawable.p4, R.drawable.p5, R.drawable.p6, R.drawable.p7};

    public int[] getIconInts() {
        return iconInts;
    }

    public String[] getFoodStrings() {
        return foodStrings;
    }

    public String[] getUnitStrings() {
        return unitStrings;
    }

    public String[] getCaloriesStrings() {
        return caloriesStrings;
    }

    public String[] getExerciseStrings() {
        return exerciseStrings;
    }

    public String[] getBurnStrings() {
        return burnStrings;
    }

} // MyData Class
