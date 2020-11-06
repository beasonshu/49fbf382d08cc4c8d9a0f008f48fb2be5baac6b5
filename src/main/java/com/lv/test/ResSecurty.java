package com.lv.test;

import java.math.BigDecimal;

public class ResSecurty {
    /**
     * 对字节数组编码,8位
     * @param bits
     * @return
     */
    public static byte[] encodeBytes(byte[] bits)
    {
        byte[] result = new byte[bits.length];

        byte bit;
        int a, b, c;
        for (int i = 0; i < bits.length; i++)
        {
            bit = bits[i];
            a = bit << 4;
            a = a & 0xf0;
            b = bit >> 4;
            b = b & 0x0f;
            c = a | b;
            c = c ^ 0xf0;
            result[i] = (byte) c;
        }

        return result;
    }

    /**
     * 对字节数组编码,8位
     * @param bits
     * @return
     */
    public static int encodeBytes(byte bit)
    {
        int a, b, c;
        a = bit << 4;
        a = a & 0xf0;
        b = bit >> 4;
        b = b & 0x0f;
        c = a | b;
        return c;
    }

    /**
     * 对字节数组解码
     * @param bits
     * @return
     */
    public static void decodeReplaceBytes(byte[] bits)
    {
        byte bit;
        int a, b, c;
        for (int i = 0; i < bits.length; i++)
        {
            bit = bits[i];
            a = bit ^ 0xf0;
            b = a << 4;
            b = b & 0xf0;
            c = a >> 4;
            c = c & 0x0f;
            c = c | b;
            bits[i] = (byte) c;
        }
    }

    /**
     * 对字节数组解码
     * @param bits
     * @return
     */
    public static byte[] decodeBytes(byte[] bits)
    {
        byte[] result = new byte[bits.length];
        byte bit;
        int a, b, c;
        for (int i = 0; i < bits.length; i++)
        {
            bit = bits[i];
            a = bit ^ 0xf0;
            b = a << 4;
            b = b & 0xf0;
            c = a >> 4;
            c = c & 0x0f;

            c = c | b;
            result[i] = (byte) c;
        }

        return result;
    }

    /**
     * 对字节数组解码
     * @param bits
     * @return
     */
    public static int decodeBytes(byte bit)
    {
        int a, b, c;
        a = bit ^ 0xf0;
        b = a << 4;
        b = b & 0xf0;
        c = a >> 4;
        c = c & 0x0f;
        c = c | b;
        return c;
    }

    public static byte[] doubleToBytes(double d)
    {
        byte writeBuffer[] = new byte[8];
        long v = Double.doubleToLongBits(d);
        writeBuffer[0] = (byte) (v >>> 56);
        writeBuffer[1] = (byte) (v >>> 48);
        writeBuffer[2] = (byte) (v >>> 40);
        writeBuffer[3] = (byte) (v >>> 32);
        writeBuffer[4] = (byte) (v >>> 24);
        writeBuffer[5] = (byte) (v >>> 16);
        writeBuffer[6] = (byte) (v >>> 8);
        writeBuffer[7] = (byte) (v >>> 0);
        return writeBuffer;
    }

    public static double bytesToDouble(byte[] readBuffer)
    {
        return Double
                .longBitsToDouble((((long) readBuffer[0] << 56) + ((long) (readBuffer[1] & 255) << 48)
                        + ((long) (readBuffer[2] & 255) << 40) + ((long) (readBuffer[3] & 255) << 32)
                        + ((long) (readBuffer[4] & 255) << 24) + ((readBuffer[5] & 255) << 16)
                        + ((readBuffer[6] & 255) << 8) + ((readBuffer[7] & 255) << 0)));
    }

    /**
     * 加密
     * @param value
     * @return
     */
    public static String jiaMi(Object value)
    {
        String f = String.valueOf(value);
        if (!f.equals("") && !f.equals("null"))
        {
            Double.valueOf(f);
            byte[] byt = ResSecurty.encodeBytes(ResSecurty.doubleToBytes(Double.valueOf(f)));

            return String.valueOf(Double.valueOf(ResSecurty.bytesToString(byt)));
        }
        return f;
    }
    /**
     * 字节转String  double会出现科学计数法，这里转成String
     * @param readBuffer 字节
     * @return String
     */
    public static String bytesToString(byte[] readBuffer)
    {
        double tempValue = Double
                .longBitsToDouble((((long) readBuffer[0] << 56) + ((long) (readBuffer[1] & 255) << 48)
                        + ((long) (readBuffer[2] & 255) << 40) + ((long) (readBuffer[3] & 255) << 32)
                        + ((long) (readBuffer[4] & 255) << 24) + ((readBuffer[5] & 255) << 16)
                        + ((readBuffer[6] & 255) << 8) + ((readBuffer[7] & 255) << 0)));
        String StringValue = BigDecimal.valueOf(tempValue).toPlainString();
        return StringValue;
    }

}
