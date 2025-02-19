package com.cdzeroly.wvp.utils;

/**
 * @Description CRC-8/MAXIM  x8+x5+x4+1
 * @Author MGARY
 * @Date 2020/05/26 15:26
 **/
public class CRC8Utils {



    /******************************************************************************
     * Name:    CRC-8/MAXIM         x8+x5+x4+1
     * Poly:    0x31
     * Init:    0x00
     * Refin:   True
     * Refout:  True
     * Xorout:  0x00
     * Alias:   DOW-CRC,CRC-8/IBUTTON
     * Use:     Maxim(Dallas)'s some devices,e.g. DS18B20
     *****************************************************************************/
    public static byte getCrc(byte[] data, int offset, int length){
        byte i;
        //校验
        byte crc = 0;
        length += offset;
        for(int j=offset;j<length;j++) {
            crc ^= data[j];
            for ( i = 0; i < 8; i++ ){
                crc = (crc & 1) == 0 ? (byte) ((crc & 0xff) >> 1) : (byte) (((crc & 0xff) >> 1) ^ 0x8C);
            }
        }
        return crc;
    }

    public static byte getCrc(byte[] data) {
        return getCrc(data, 0, data.length);
    }

}
