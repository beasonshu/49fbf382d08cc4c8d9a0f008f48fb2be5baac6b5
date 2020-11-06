package com.lv.test;

public class Testss {
    public static String parseStr(Object objIn)
    {
        if (objIn == null)
        {
            return "";
        } else
        {
            return objIn.toString().trim();
        }
    }

    public static String jiemi(Object value)
    {
        String f = String.valueOf(value);
        if (!f.equals("") && !f.equals("null"))
        {
            double num1 = Double.valueOf(f);
            byte[] byt = ResSecurty.decodeBytes(ResSecurty.doubleToBytes(num1));
            //row.setValue(colName, String.valueOf(ResSecurty.bytesToDouble(byt)));
            // double 会出现科学计数法，这里改成String
            return ResSecurty.bytesToString(byt);
        }
        return null;
    }

    public static String jiami(Object value)
    {
        String f = String.valueOf(value);
        if (!f.equals("") && !f.equals("null"))
        {
            Double.valueOf(f);
            byte[] byt = ResSecurty.encodeBytes(ResSecurty.doubleToBytes(Double.valueOf(f)));

            return parseStr(Double.valueOf(ResSecurty.bytesToString(byt)));
        }
        return null;
    }

}
