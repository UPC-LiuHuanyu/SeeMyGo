package com.example.lhy.util;

import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Serena
 * @time 2016/7/18  0:38
 * @desc ${TODD}
 */

public class MD5Util {
    private static final char[] HEX_DIGITS = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70};

    // ERROR //
    public static String getFileMD5String(File paramFile) {
        // Byte code:
        //   0: ldc 35
        //   2: invokestatic 41	java/security/MessageDigest:getInstance	(Ljava/lang/String;)Ljava/security/MessageDigest;
        //   5: astore 4
        //   7: new 43	java/io/FileInputStream
        //   10: dup
        //   11: aload_0
        //   12: invokespecial 46	java/io/FileInputStream:<init>	(Ljava/io/File;)V
        //   15: astore 5
        //   17: aload 4
        //   19: aload 5
        //   21: invokevirtual 50	java/io/FileInputStream:getChannel	()Ljava/nio/channels/FileChannel;
        //   24: getstatic 56	java/nio/channels/FileChannel$MapMode:READ_ONLY	Ljava/nio/channels/FileChannel$MapMode;
        //   27: lconst_0
        //   28: aload_0
        //   29: invokevirtual 62	java/io/File:length	()J
        //   32: invokevirtual 68	java/nio/channels/FileChannel:map	(Ljava/nio/channels/FileChannel$MapMode;JJ)Ljava/nio/MappedByteBuffer;
        //   35: invokevirtual 72	java/security/MessageDigest:update	(Ljava/nio/ByteBuffer;)V
        //   38: aload 4
        //   40: invokevirtual 76	java/security/MessageDigest:digest	()[B
        //   43: invokestatic 80	com/detu/vr/libs/MD5Util:toHexString	([B)Ljava/lang/String;
        //   46: astore 6
        //   48: aload 6
        //   50: astore_3
        //   51: aload 5
        //   53: ifnull +8 -> 61
        //   56: aload 5
        //   58: invokevirtual 83	java/io/FileInputStream:close	()V
        //   61: aload_3
        //   62: areturn
        //   63: astore_1
        //   64: aload_1
        //   65: astore_2
        //   66: ldc 85
        //   68: astore_3
        //   69: aload_2
        //   70: invokevirtual 88	java/lang/Exception:printStackTrace	()V
        //   73: aload_3
        //   74: areturn
        //   75: astore_2
        //   76: goto -7 -> 69
        //
        // Exception table:
        //   from	to	target	type
        //   0	48	63	java/lang/Exception
        //   56	61	75	java/lang/Exception
        return null;//???
    }

    //加密
    public static String getMD5(String paramString) {
        try {
            MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
            localMessageDigest.update(paramString.getBytes());
            String str = toHexString(localMessageDigest.digest());
            return str;
        } catch (NoSuchAlgorithmException localNoSuchAlgorithmException) {
            localNoSuchAlgorithmException.printStackTrace();
        }
        return "";
    }

    public static String toHexString(byte[] paramArrayOfByte) {
        StringBuilder localStringBuilder = new StringBuilder(2 * paramArrayOfByte.length);
        for (int i = 0; i < paramArrayOfByte.length; i++) {
            localStringBuilder.append(HEX_DIGITS[((0xF0 & paramArrayOfByte[i]) >>> 4)]);
            localStringBuilder.append(HEX_DIGITS[(0xF & paramArrayOfByte[i])]);
        }
        return localStringBuilder.toString();
    }
}
