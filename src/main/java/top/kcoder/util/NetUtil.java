package top.kcoder.util;

import java.math.BigInteger;

/**
 * NetUtil
 *
 * @author xiejinjie
 * @date 2023/3/28
 */
public class NetUtil {
    /**
     * int转字节数组 大端模式
     */
    public static byte[] intToByteArrayBigEndian(int x) {
        byte[] bytes = new byte[4];
        bytes[0] = (byte) (x >> 24);
        bytes[1] = (byte) (x >> 16);
        bytes[2] = (byte) (x >> 8);
        bytes[3] = (byte) x;
        return bytes;
    }

    /**
     * int转字节数组 小端模式
     */
    public static byte[] intToByteArrayLittleEndian(int x) {
        byte[] bytes = new byte[4];
        bytes[0] = (byte) x;
        bytes[1] = (byte) (x >> 8);
        bytes[2] = (byte) (x >> 16);
        bytes[3] = (byte) (x >> 24);
        return bytes;
    }

    /**
     * 字节数组转int 大端模式
     */
    public static int byteArrayToIntBigEndian(byte[] bytes) {
        int x = 0;
        for (int i = 0; i < 4; i++) {
            x <<= 8;
            int b = bytes[i] & 0xFF;
            x |= b;
        }
        return x;
    }

    /**
     * 字节数组转int 小端模式
     */
    public static int byteArrayToIntLittleEndian(byte[] bytes) {
        int x = 0;
        for (int i = 0; i < 4; i++) {
            int b = (bytes[i] & 0xFF) << (i * 8);
            x |= b;
        }
        return x;
    }

    /**
     * 字节数组转int 大端模式
     */
    public static int byteArrayToIntLittleEndian(byte[] bytes, int byteOffset, int byteCount) {
        int intValue = 0;
        for (int i = byteOffset; i < (byteOffset + byteCount); i++) {
            intValue |= (bytes[i] & 0xFF) << (8 * (i - byteOffset));
        }
        return intValue;
    }

    /**
     * 字节数组转int 小端模式
     */
    public static int byteArrayToIntBigEndian(byte[] bytes, int byteOffset, int byteCount) {
        int intValue = 0;
        for (int i = byteOffset; i < (byteOffset + byteCount); i++) {
            intValue <<= 8;
            int b = bytes[i] & 0xFF;
            intValue |= b;
        }
        return intValue;
    }

    /**
     * ipv4数组转字符串
     */
    public static String byteArrayToIpv4String(byte[] bytes, int offset) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            int tmp = bytes[offset + i] & 0xff;
            sb.append(tmp);
            if (i < 3) {
                sb.append(".");
            }
        }
        return sb.toString();
    }

    /**
     * ipv6数组转字符串
     */
    public static String byteArrayToIpv6String(byte[] bytes, int offset) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            int tmp = (bytes[offset+(i<<1)]<<8 & 0xff00) | (bytes[offset+(i<<1)+1] & 0xff);
            sb.append(Integer.toHexString(tmp));
            if (i < 7) {
                sb.append(":");
            }
        }
        return sb.toString();
    }

    public static boolean isSameIpv6(String ip1, String ip2) {
        if (CommonUtil.isNotEmpty(ip1) && CommonUtil.isNotEmpty(ip2)) {
            return padIpv6(ip1).equals(padIpv6(ip2));
        }
        return false;
    }

    public static String padIpv6(String ipv6) {
        String[] split = ipv6.split(":");
        int empty = -1;
        for (int i = 0; i < split.length; i++) {
            String s = split[i];
            if (s.length() == 0 && empty ==-1) {
                empty = i;
            } else if (s.length() < 4){
                StringBuilder sb = new StringBuilder(4);
                for (int j = 0; j < 4 - s.length(); j++) {
                    sb.append('0');
                }
                sb.append(s);
                split[i] = sb.toString();
            }
        }
        if (empty != -1) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 8 - split.length + 1; i++){
                sb.append("0000:");
            }
            split[empty] = sb.substring(0, sb.length() - 1);
        }
        return String.join(":", split);
    }

    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    public static String bytesToHexString(byte[] src, int offset, int count) {
        StringBuilder stringBuilder = new StringBuilder();
        if (src == null || src.length == 0) {
            return null;
        }
        for (int i = offset; i < offset + count; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    /**
     * Convert hex string to byte[]
     *
     * @param hexString the hex string
     * @return byte[]
     */
    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    /**
     * Convert char to byte
     *
     * @param c char
     * @return byte
     */
    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    public static String binary(byte[] bytes, int radix) {
        return new BigInteger(1, bytes).toString(radix);// 这里的1代表正数
    }

    public static void printHexString(String hint, byte[] b) {
        System.out.print(hint);
        for (int i = 0; i < b.length; i++) {
            String hex = Integer.toHexString(b[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            System.out.print(hex.toUpperCase() + " ");
        }
        System.out.println("");
    }

    public static String byteToBit(byte b) {
        return "" + (byte) ((b >> 7) & 0x1) + (byte) ((b >> 6) & 0x1) + (byte) ((b >> 5) & 0x1)
                + (byte) ((b >> 4) & 0x1) + (byte) ((b >> 3) & 0x1) + (byte) ((b >> 2) & 0x1) + (byte) ((b >> 1) & 0x1)
                + (byte) ((b >> 0) & 0x1);
    }

    public static byte[] subBytes(byte[] src, int begin, int count) {
        byte[] bs = new byte[count];
        System.arraycopy(src, begin, bs, 0, count);
        return bs;
    }

    public static byte[] addBytes(byte[] data1, byte[] data2) {
        byte[] data3 = new byte[data1.length + data2.length];
        System.arraycopy(data1, 0, data3, 0, data1.length);
        System.arraycopy(data2, 0, data3, data1.length, data2.length);
        return data3;

    }

    public static byte BitToByte(String byteStr) {
        int re, len;
        if (null == byteStr) {
            return 0;
        }
        len = byteStr.length();
        if (len != 4 && len != 8) {
            return 0;
        }
        if (len == 8) {// 8 bit处理
            if (byteStr.charAt(0) == '0') {// 正数
                re = Integer.parseInt(byteStr, 2);
            } else {// 负数
                re = Integer.parseInt(byteStr, 2) - 256;
            }
        } else {// 4 bit处理
            re = Integer.parseInt(byteStr, 2);
        }
        return (byte) re;
    }

    public static byte[] SumCheck(byte[] msg, int length) {
        long mSum = 0;
        byte[] mByte = new byte[length];

        /** 逐Byte添加位数和 */
        for (byte byteMsg : msg) {
            long mNum = ((long) byteMsg >= 0) ? (long) byteMsg : ((long) byteMsg + 256);
            mSum += mNum;
        } /** end of for (byte byteMsg : msg) */

        /** 位数和转化为Byte数组 */
        for (int liv_Count = 0; liv_Count < length; liv_Count++) {
            mByte[length - liv_Count - 1] = (byte) (mSum >> (liv_Count * 8) & 0xff);
        } /** end of for (int liv_Count = 0; liv_Count < length; liv_Count++) */

        return mByte;
    }
}
