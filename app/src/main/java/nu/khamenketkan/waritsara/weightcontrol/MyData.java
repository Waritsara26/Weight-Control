package nu.khamenketkan.waritsara.weightcontrol;

/**
 * Created by Windows 8.1 on 16/7/2559.
 */

//ข้อมูลดิบที่จะเอาไปเก็บไว้ใน SQLite
public class MyData {

    //Explicit
    String[] foodStrings = new String[]{
            "ข้าวผัดกะเพราหมู",
            "ข้าวผัดกะเพราไก่",
            "ข้าวผัดกะเพราหมูกรอบ",
            "ข้าวผัดกะเพรากุ้ง",
            "ข้าวผัดกะเพราเนื้อ",
            "ข้าวไข่เจียว",
            "ข้าวหมูทอดกระเทียม" ,
            "ข้าวมันไก่",
            "ข้าวมันไก่ทอด",
            "ข้าวหมกไก่",
            "ข้าวหมูแดง",
            "ข้าวหน้าเป็ด",
            "ข้าวผัดแหนม",
            "ข้าวผัดกุนเชียง",
            "ข้าวผัดหมูใส่ไข่",
            "ข้าวผัดปูใส่ไข่",
            "ข้าวผัดไส้กรอก",
            "ข้าวผัดอเมริกัน",
            "ข้าวผัดคะน้าหมูกรอบ",
            "ข้าวผัดน้ำพริกกุ้งสด",
            "ข้าวผัดน้ำพริกเผาหมู",
            "ข้าวผัดน้ำพริกลงเรือ",
            "ข้าวซอยไก่",
            "ข้าวซอยหมู",
};
    String[] unitStrings = new String[]{
            "จาน",
            "จาน",
            "จาน",
            "จาน",
            "จาน",
            "ชาม",
            "จาน",
            "ถ้วย",
            "จาน",
            "ชาม",
            "ชาม",
            "ชาม",
            "ชาม",
            "ชาม",
            "ชาม",
            "ชาม",
            "ชาม",
            "ชาม",
            "ชาม",
            "ชาม",
            "ชาม",
            "ชาม",
            "ชาม",
            "ชาม",
            "ชาม",};
    String[] caloriesStrings = new String[]{
            "580",
            "554",
            "650",
            "540",
            "622",
            "445",
            "525",
            "596",
            "695",
            "534",
            "541",
           "495",
            "610",
            "590",
            "557",
            "610",
            "520",
            "790",
            "670",
            "460",
            "665",
            "605",
            "395",
            "395",
};
    String[] exerciseStrings = new String[]{"วิ่งเร็ว",
            "วิ่งเหยาะๆ",
            "ว่ายน้ำ",
            "เต้น Zumba",
            "เดินธรรมดา",
            "เดินเร็ว",
            "เดินลงบันได",
            "แอโรบิค",
            "แบดมินตัน",
            "บาสเก็ตบอล",};
    String[] burnStrings = new String[]{"1200",
            "750",
            "750",
            "500",
            "300",
            "480",
            "425",
            "600",
            "350",
            "660",};

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
