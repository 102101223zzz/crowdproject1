package com.kknb.crowdproject.util;


import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;

import java.nio.charset.StandardCharsets;

public class PasswordUtils {



    //通过huTool工具包进行加密
    //key为自定义密钥
    public  String encrypt(String key,String encryptPassword){
        //生成密钥
        byte[] bytes = key.getBytes(StandardCharsets.UTF_8);
        //在密钥生成时必须为128/192/256 bits（位），本案例中使用256位
        //故需进行判断
        //byte，一字节，8位，故需要达到256位，需要32字节
        if (bytes.length!=32){
            //创建32字节的byte数组
            byte[] b = new byte[32];
            if (bytes.length<32){
                //将自定义密钥添加到b数组
                /**
                 * 方法：System.arraycopy
                 * 参数：
                 * src：the source array要插入的数组
                 * srcPos：starting position in the source array插入数组的起始位置
                 * dest：the destination array被插入的数组
                 * destPos：starting position in the destination data被插入数组插入时的起始位置
                 * length：the number of array elements to be copied要插入的数组的长度
                 */
                System.arraycopy(bytes,0,b,0,bytes.length);
            }
            bytes=b;
        }
        //构建
        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, bytes);
        //加密为16进制表示
        String encryptHex = aes.encryptHex(encryptPassword);
        return encryptHex;
    }


    //通过huTool工具包进行解密
    //key为自定义密钥
    public  String decrypt(String key,String decryptPassword){
        //生成密钥
        byte[] bytes = key.getBytes(StandardCharsets.UTF_8);
        if (bytes.length!=32){
            byte[] b = new byte[32];
            if (bytes.length<32){
                System.arraycopy(bytes,0,b,0,bytes.length);
            }
            bytes=b;
        }
        //构建
        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, bytes);
        //解密为字符串
        String decryptStr = aes.decryptStr(decryptPassword, CharsetUtil.CHARSET_UTF_8);
        return decryptStr;
    }

}