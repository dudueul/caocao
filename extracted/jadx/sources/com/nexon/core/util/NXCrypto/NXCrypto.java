package com.nexon.core.util.NXCrypto;

import com.nexon.core.log.ToyLog;
import com.nexon.core.requestpostman.constants.NXToyCryptoType;
import com.nexon.core.util.NXByteUtil;
import com.nexon.core.util.NXCrypto.interfaces.NXToyDecrypt;
import com.nexon.core.util.NXCrypto.interfaces.NXToyEncrypt;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.util.Locale;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.Mac;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NXCrypto {
    private static final int AES_KEY_SIZE = 16;
    private static final String ALGORITHM_AES = "AES";
    private static final String ALGORITHM_HMAC_SHA_256 = "HmacSHA256";
    private static final String ALGORITHM_RSA = "RSA";
    private static final String CHARSET_UTF_8 = "UTF-8";
    public static final int CIPHER_DECRYPT_MODE = 2;
    public static final int CIPHER_ENCRYPT_MODE = 1;
    private static final String CIPHER_TRANSFORMATION_AES = "AES/ECB/PKCS5Padding";
    private static final String CIPHER_TRANSFORMATION_RSA = "RSA/ECB/PKCS1Padding";
    private static final String COMMON_AES_KEY = "dd4763541be100910b568ca6d48268e3";
    private static final int RSA_EXPONENT_RADIX = 16;
    private static final int RSA_MODULUS_RADIX = 16;

    private static byte[] cipherWithAES128(String str, byte[] bArr, int i) throws Exception {
        return cipherWithAES128(str.getBytes("UTF-8"), bArr, i);
    }

    private static byte[] cipherWithAES128(byte[] bArr, byte[] bArr2, int i) throws Exception {
        if (bArr == null || bArr2 == null || !(i == 1 || i == 2)) {
            return null;
        }
        try {
            byte[] bArr3 = new byte[16];
            System.arraycopy(bArr, 0, bArr3, 0, Math.min(bArr.length, 16));
            SecretKeySpec secretKeySpec = new SecretKeySpec(bArr3, ALGORITHM_AES);
            Cipher cipher = Cipher.getInstance(CIPHER_TRANSFORMATION_AES);
            cipher.init(i, secretKeySpec);
            return cipher.doFinal(bArr2);
        } catch (InvalidKeyException e) {
            throw e;
        } catch (NoSuchAlgorithmException e2) {
            throw e2;
        } catch (BadPaddingException e3) {
            throw e3;
        } catch (IllegalBlockSizeException e4) {
            throw e4;
        } catch (NoSuchPaddingException e5) {
            throw e5;
        }
    }

    public static byte[] decryptWithAES128(String str, byte[] bArr) throws Exception {
        return cipherWithAES128(str, bArr, 2);
    }

    public static byte[] decryptWithAES128(byte[] bArr, byte[] bArr2) throws Exception {
        return cipherWithAES128(bArr, bArr2, 2);
    }

    public static String decryptWithAES128ToHex(String str, byte[] bArr) throws Exception {
        return NXByteUtil.bytesToHexString(cipherWithAES128(str, bArr, 2));
    }

    public static String decryptWithAES128ToHex(byte[] bArr, byte[] bArr2) throws Exception {
        return NXByteUtil.bytesToHexString(cipherWithAES128(bArr, bArr2, 2));
    }

    public static byte[] encodeHmacSha256(String str, String str2) throws Exception {
        return encodeHmacSha256(str, str2.getBytes("UTF-8"));
    }

    public static byte[] encodeHmacSha256(String str, byte[] bArr) throws Exception {
        if (str == null || bArr == null) {
            throw new IllegalArgumentException();
        }
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(str.getBytes("UTF-8"), ALGORITHM_HMAC_SHA_256);
            Mac mac = Mac.getInstance(ALGORITHM_HMAC_SHA_256);
            mac.init(secretKeySpec);
            return mac.doFinal(bArr);
        } catch (UnsupportedEncodingException e) {
            throw e;
        } catch (InvalidKeyException e2) {
            throw e2;
        } catch (NoSuchAlgorithmException e3) {
            throw e3;
        } catch (Exception e4) {
            throw e4;
        }
    }

    public static String encodeHmacSha256ToHexString(String str, String str2) throws Exception {
        return NXByteUtil.bytesToHexString(encodeHmacSha256(str, str2));
    }

    public static String encodeHmacSha256ToHexString(String str, byte[] bArr) throws Exception {
        return NXByteUtil.bytesToHexString(encodeHmacSha256(str, bArr));
    }

    public static byte[] encryptWithAES128(String str, byte[] bArr) throws Exception {
        return cipherWithAES128(str, bArr, 1);
    }

    public static byte[] encryptWithAES128(byte[] bArr, byte[] bArr2) throws Exception {
        return cipherWithAES128(bArr, bArr2, 1);
    }

    public static String encryptWithAES128ToHex(String str, byte[] bArr) throws Exception {
        return NXByteUtil.bytesToHexString(cipherWithAES128(str, bArr, 1));
    }

    public static String encryptWithAES128ToHex(byte[] bArr, byte[] bArr2) throws Exception {
        return NXByteUtil.bytesToHexString(cipherWithAES128(bArr, bArr2, 1));
    }

    private static byte[] encryptWithRSA(String str, String str2, String str3) throws Exception {
        if (str == null || str2 == null || str3 == null) {
            throw new IllegalArgumentException();
        }
        try {
            RSAPublicKey rSAPublicKey = (RSAPublicKey) KeyFactory.getInstance(ALGORITHM_RSA).generatePublic(new RSAPublicKeySpec(new BigInteger(str2, 16), new BigInteger(str, 16)));
            Cipher cipher = Cipher.getInstance(CIPHER_TRANSFORMATION_RSA);
            cipher.init(1, rSAPublicKey);
            return cipher.doFinal(str3.getBytes());
        } catch (InvalidKeyException e) {
            throw e;
        } catch (NoSuchAlgorithmException e2) {
            throw e2;
        } catch (InvalidKeySpecException e3) {
            throw e3;
        } catch (BadPaddingException e4) {
            throw e4;
        } catch (IllegalBlockSizeException e5) {
            throw e5;
        } catch (NoSuchPaddingException e6) {
            throw e6;
        } catch (Exception e7) {
            throw e7;
        }
    }

    public static String encryptWithRSAToHexString(String str, String str2, String str3) throws Exception {
        return NXByteUtil.bytesToHexString(encryptWithRSA(str, str2, str3));
    }

    private byte[] makeNPSNAES128Key(long j) {
        try {
            return encryptWithAES128(String.format(Locale.ENGLISH, "%019d", Long.valueOf(j)).substring(3), String.format(Locale.ENGLISH, "%019d", Long.valueOf(j)).substring(4).getBytes());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public byte[] decrypt(byte[] bArr, NXToyCryptoType nXToyCryptoType, long j) {
        NXToyDecrypt nXToyDecrypt = null;
        switch (nXToyCryptoType) {
            case NONE:
                nXToyDecrypt = new NXToyDecrypt() { // from class: com.nexon.core.util.NXCrypto.NXCrypto.4
                    @Override // com.nexon.core.util.NXCrypto.interfaces.NXToyDecrypt
                    public byte[] decrypt(byte[] bArr2) throws Exception {
                        return bArr2;
                    }
                };
                break;
            case NPSN:
                final byte[] bArrMakeNPSNAES128Key = makeNPSNAES128Key(j);
                nXToyDecrypt = new NXToyDecrypt() { // from class: com.nexon.core.util.NXCrypto.NXCrypto.5
                    @Override // com.nexon.core.util.NXCrypto.interfaces.NXToyDecrypt
                    public byte[] decrypt(byte[] bArr2) throws Exception {
                        return NXCrypto.decryptWithAES128(bArrMakeNPSNAES128Key, bArr2);
                    }
                };
                break;
            case COMMON:
                nXToyDecrypt = new NXToyDecrypt() { // from class: com.nexon.core.util.NXCrypto.NXCrypto.6
                    @Override // com.nexon.core.util.NXCrypto.interfaces.NXToyDecrypt
                    public byte[] decrypt(byte[] bArr2) throws Exception {
                        return NXCrypto.decryptWithAES128(NXByteUtil.HexStringToBytes(NXCrypto.COMMON_AES_KEY), bArr2);
                    }
                };
                break;
        }
        try {
            return nXToyDecrypt.decrypt(bArr);
        } catch (Exception e) {
            ToyLog.e("Catch exception. message is : " + e.getMessage());
            return null;
        }
    }

    public byte[] encrypt(byte[] bArr, NXToyCryptoType nXToyCryptoType, long j) {
        NXToyEncrypt nXToyEncrypt = null;
        switch (nXToyCryptoType) {
            case NONE:
                nXToyEncrypt = new NXToyEncrypt() { // from class: com.nexon.core.util.NXCrypto.NXCrypto.1
                    @Override // com.nexon.core.util.NXCrypto.interfaces.NXToyEncrypt
                    public byte[] encrypt(byte[] bArr2) throws Exception {
                        return bArr2;
                    }
                };
                break;
            case NPSN:
                final byte[] bArrMakeNPSNAES128Key = makeNPSNAES128Key(j);
                nXToyEncrypt = new NXToyEncrypt() { // from class: com.nexon.core.util.NXCrypto.NXCrypto.2
                    @Override // com.nexon.core.util.NXCrypto.interfaces.NXToyEncrypt
                    public byte[] encrypt(byte[] bArr2) throws Exception {
                        return NXCrypto.encryptWithAES128(bArrMakeNPSNAES128Key, bArr2);
                    }
                };
                break;
            case COMMON:
                nXToyEncrypt = new NXToyEncrypt() { // from class: com.nexon.core.util.NXCrypto.NXCrypto.3
                    @Override // com.nexon.core.util.NXCrypto.interfaces.NXToyEncrypt
                    public byte[] encrypt(byte[] bArr2) throws Exception {
                        return NXCrypto.encryptWithAES128(NXByteUtil.HexStringToBytes(NXCrypto.COMMON_AES_KEY), bArr2);
                    }
                };
                break;
        }
        try {
            return nXToyEncrypt.encrypt(bArr);
        } catch (Exception e) {
            ToyLog.e("Catch exception. message is : " + e.getMessage());
            return null;
        }
    }
}
