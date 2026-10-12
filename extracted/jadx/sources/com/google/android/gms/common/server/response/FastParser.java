package com.google.android.gms.common.server.response;

import android.util.Log;
import com.google.android.gms.common.server.response.FastJsonResponse;
import com.google.android.gms.common.util.Base64Utils;
import com.google.android.gms.common.util.JsonUtils;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import twitter4j.PagableResponseListImpl;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class FastParser<T extends FastJsonResponse> {
    private static final char[] zzwv = {'u', 'l', 'l'};
    private static final char[] zzww = {'r', 'u', 'e'};
    private static final char[] zzwx = {'r', 'u', 'e', '\"'};
    private static final char[] zzwy = {'a', 'l', 's', 'e'};
    private static final char[] zzwz = {'a', 'l', 's', 'e', '\"'};
    private static final char[] zzxa = {'\n'};
    private static final zza<Integer> zzxc = new com.google.android.gms.common.server.response.zza();
    private static final zza<Long> zzxd = new zzb();
    private static final zza<Float> zzxe = new zzc();
    private static final zza<Double> zzxf = new zzd();
    private static final zza<Boolean> zzxg = new zze();
    private static final zza<String> zzxh = new zzf();
    private static final zza<BigInteger> zzxi = new zzg();
    private static final zza<BigDecimal> zzxj = new zzh();
    private final char[] zzwq = new char[1];
    private final char[] zzwr = new char[32];
    private final char[] zzws = new char[1024];
    private final StringBuilder zzwt = new StringBuilder(32);
    private final StringBuilder zzwu = new StringBuilder(1024);
    private final Stack<Integer> zzxb = new Stack<>();

    public static class ParseException extends Exception {
        public ParseException(String str) {
            super(str);
        }

        public ParseException(String str, Throwable th) {
            super(str, th);
        }

        public ParseException(Throwable th) {
            super(th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    interface zza<O> {
        O zzh(FastParser fastParser, BufferedReader bufferedReader) throws ParseException, IOException;
    }

    private final int zza(BufferedReader bufferedReader, char[] cArr) throws ParseException, IOException {
        int i;
        char cZzj = zzj(bufferedReader);
        if (cZzj == 0) {
            throw new ParseException("Unexpected EOF");
        }
        if (cZzj == ',') {
            throw new ParseException("Missing value");
        }
        if (cZzj == 'n') {
            zzb(bufferedReader, zzwv);
            return 0;
        }
        bufferedReader.mark(1024);
        if (cZzj == '\"') {
            boolean z = false;
            int i2 = 0;
            while (i2 < cArr.length && bufferedReader.read(cArr, i2, 1) != -1) {
                char c = cArr[i2];
                if (Character.isISOControl(c)) {
                    throw new ParseException("Unexpected control character while reading string");
                }
                if (c == '\"' && !z) {
                    bufferedReader.reset();
                    bufferedReader.skip(i2 + 1);
                    return i2;
                }
                if (c == '\\') {
                    z = !z;
                } else {
                    z = false;
                }
                i2++;
            }
            i = i2;
        } else {
            cArr[0] = cZzj;
            i = 1;
            while (i < cArr.length && bufferedReader.read(cArr, i, 1) != -1) {
                if (cArr[i] == '}' || cArr[i] == ',' || Character.isWhitespace(cArr[i]) || cArr[i] == ']') {
                    bufferedReader.reset();
                    bufferedReader.skip(i - 1);
                    cArr[i] = 0;
                    return i;
                }
                i++;
            }
        }
        if (i == cArr.length) {
            throw new ParseException("Absurdly long value");
        }
        throw new ParseException("Unexpected EOF");
    }

    private final String zza(BufferedReader bufferedReader) throws ParseException, IOException {
        String strZzb = null;
        this.zzxb.push(2);
        char cZzj = zzj(bufferedReader);
        switch (cZzj) {
            case '\"':
                this.zzxb.push(3);
                strZzb = zzb(bufferedReader, this.zzwr, this.zzwt, null);
                zzk(3);
                if (zzj(bufferedReader) != ':') {
                    throw new ParseException("Expected key/value separator");
                }
                return strZzb;
            case ']':
                zzk(2);
                zzk(1);
                zzk(5);
                return strZzb;
            case '}':
                zzk(2);
                return strZzb;
            default:
                throw new ParseException(new StringBuilder(19).append("Unexpected token: ").append(cZzj).toString());
        }
    }

    private final String zza(BufferedReader bufferedReader, char[] cArr, StringBuilder sb, char[] cArr2) throws ParseException, IOException {
        switch (zzj(bufferedReader)) {
            case '\"':
                return zzb(bufferedReader, cArr, sb, cArr2);
            case 'n':
                zzb(bufferedReader, zzwv);
                return null;
            default:
                throw new ParseException("Expected string");
        }
    }

    /* JADX WARN: Switch 'out' block B:29:0x0036 for B:13:0x0047 already processed. Defaulting to fallback option. */
    private final <T extends FastJsonResponse> ArrayList<T> zza(BufferedReader bufferedReader, FastJsonResponse.Field<?, ?> field) throws ParseException, IOException {
        PagableResponseListImpl pagableResponseListImpl = (ArrayList<T>) new ArrayList();
        char cZzj = zzj(bufferedReader);
        switch (cZzj) {
            case ']':
                zzk(5);
                return pagableResponseListImpl;
            case 'n':
                zzb(bufferedReader, zzwv);
                zzk(5);
                return null;
            case '{':
                this.zzxb.push(1);
                while (true) {
                    try {
                        FastJsonResponse fastJsonResponseNewConcreteTypeInstance = field.newConcreteTypeInstance();
                        if (!zza(bufferedReader, fastJsonResponseNewConcreteTypeInstance)) {
                            return pagableResponseListImpl;
                        }
                        pagableResponseListImpl.add(fastJsonResponseNewConcreteTypeInstance);
                        char cZzj2 = zzj(bufferedReader);
                        switch (cZzj2) {
                            case ',':
                                if (zzj(bufferedReader) != '{') {
                                    throw new ParseException("Expected start of next object in array");
                                }
                                this.zzxb.push(1);
                                break;
                            case ']':
                                zzk(5);
                                return pagableResponseListImpl;
                            default:
                                throw new ParseException(new StringBuilder(19).append("Unexpected token: ").append(cZzj2).toString());
                        }
                    } catch (IllegalAccessException e) {
                        throw new ParseException("Error instantiating inner object", e);
                    } catch (InstantiationException e2) {
                        throw new ParseException("Error instantiating inner object", e2);
                    }
                }
                break;
            default:
                throw new ParseException(new StringBuilder(19).append("Unexpected token: ").append(cZzj).toString());
        }
    }

    private final <O> ArrayList<O> zza(BufferedReader bufferedReader, zza<O> zzaVar) throws ParseException, IOException {
        char cZzj = zzj(bufferedReader);
        if (cZzj != 'n') {
            if (cZzj == '[') {
                this.zzxb.push(5);
                ArrayList<O> arrayList = new ArrayList<>();
                while (true) {
                    bufferedReader.mark(1024);
                    switch (zzj(bufferedReader)) {
                        case 0:
                            throw new ParseException("Unexpected EOF");
                        case ',':
                            break;
                        case ']':
                            zzk(5);
                            return arrayList;
                        default:
                            bufferedReader.reset();
                            arrayList.add(zzaVar.zzh(this, bufferedReader));
                            break;
                    }
                }
            } else {
                throw new ParseException("Expected start of array");
            }
        } else {
            zzb(bufferedReader, zzwv);
            return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final boolean zza(BufferedReader bufferedReader, FastJsonResponse fastJsonResponse) throws ParseException, IOException {
        HashMap map;
        Map<String, FastJsonResponse.Field<?, ?>> fieldMappings = fastJsonResponse.getFieldMappings();
        String strZza = zza(bufferedReader);
        if (strZza == null) {
            zzk(1);
            return false;
        }
        while (strZza != null) {
            FastJsonResponse.Field<?, ?> field = fieldMappings.get(strZza);
            if (field == null) {
                strZza = zzb(bufferedReader);
            } else {
                this.zzxb.push(4);
                switch (field.getTypeIn()) {
                    case 0:
                        if (!field.isTypeInArray()) {
                            fastJsonResponse.setInteger(field, zzd(bufferedReader));
                        } else {
                            fastJsonResponse.setIntegers((FastJsonResponse.Field) field, (ArrayList<Integer>) zza(bufferedReader, zzxc));
                        }
                        break;
                    case 1:
                        if (!field.isTypeInArray()) {
                            fastJsonResponse.setBigInteger(field, zzf(bufferedReader));
                        } else {
                            fastJsonResponse.setBigIntegers((FastJsonResponse.Field) field, (ArrayList<BigInteger>) zza(bufferedReader, zzxi));
                        }
                        break;
                    case 2:
                        if (!field.isTypeInArray()) {
                            fastJsonResponse.setLong(field, zze(bufferedReader));
                        } else {
                            fastJsonResponse.setLongs((FastJsonResponse.Field) field, (ArrayList<Long>) zza(bufferedReader, zzxd));
                        }
                        break;
                    case 3:
                        if (!field.isTypeInArray()) {
                            fastJsonResponse.setFloat(field, zzg(bufferedReader));
                        } else {
                            fastJsonResponse.setFloats((FastJsonResponse.Field) field, (ArrayList<Float>) zza(bufferedReader, zzxe));
                        }
                        break;
                    case 4:
                        if (!field.isTypeInArray()) {
                            fastJsonResponse.setDouble(field, zzh(bufferedReader));
                        } else {
                            fastJsonResponse.setDoubles((FastJsonResponse.Field) field, (ArrayList<Double>) zza(bufferedReader, zzxf));
                        }
                        break;
                    case 5:
                        if (!field.isTypeInArray()) {
                            fastJsonResponse.setBigDecimal(field, zzi(bufferedReader));
                        } else {
                            fastJsonResponse.setBigDecimals((FastJsonResponse.Field) field, (ArrayList<BigDecimal>) zza(bufferedReader, zzxj));
                        }
                        break;
                    case 6:
                        if (!field.isTypeInArray()) {
                            fastJsonResponse.setBoolean(field, zza(bufferedReader, false));
                        } else {
                            fastJsonResponse.setBooleans((FastJsonResponse.Field) field, (ArrayList<Boolean>) zza(bufferedReader, zzxg));
                        }
                        break;
                    case 7:
                        if (!field.isTypeInArray()) {
                            fastJsonResponse.setString(field, zzc(bufferedReader));
                        } else {
                            fastJsonResponse.setStrings((FastJsonResponse.Field) field, (ArrayList<String>) zza(bufferedReader, zzxh));
                        }
                        break;
                    case 8:
                        fastJsonResponse.setDecodedBytes(field, Base64Utils.decode(zza(bufferedReader, this.zzws, this.zzwu, zzxa)));
                        break;
                    case 9:
                        fastJsonResponse.setDecodedBytes(field, Base64Utils.decodeUrlSafe(zza(bufferedReader, this.zzws, this.zzwu, zzxa)));
                        break;
                    case 10:
                        char cZzj = zzj(bufferedReader);
                        if (cZzj == 'n') {
                            zzb(bufferedReader, zzwv);
                            map = null;
                        } else {
                            if (cZzj != '{') {
                                throw new ParseException("Expected start of a map object");
                            }
                            this.zzxb.push(1);
                            map = new HashMap();
                            while (true) {
                                switch (zzj(bufferedReader)) {
                                    case 0:
                                        throw new ParseException("Unexpected EOF");
                                    case '\"':
                                        String strZzb = zzb(bufferedReader, this.zzwr, this.zzwt, null);
                                        if (zzj(bufferedReader) != ':') {
                                            String strValueOf = String.valueOf(strZzb);
                                            throw new ParseException(strValueOf.length() != 0 ? "No map value found for key ".concat(strValueOf) : new String("No map value found for key "));
                                        }
                                        if (zzj(bufferedReader) != '\"') {
                                            String strValueOf2 = String.valueOf(strZzb);
                                            throw new ParseException(strValueOf2.length() != 0 ? "Expected String value for key ".concat(strValueOf2) : new String("Expected String value for key "));
                                        }
                                        map.put(strZzb, zzb(bufferedReader, this.zzwr, this.zzwt, null));
                                        char cZzj2 = zzj(bufferedReader);
                                        if (cZzj2 != ',') {
                                            if (cZzj2 != '}') {
                                                throw new ParseException(new StringBuilder(48).append("Unexpected character while parsing string map: ").append(cZzj2).toString());
                                            }
                                            zzk(1);
                                        }
                                        break;
                                    case '}':
                                        zzk(1);
                                        break;
                                    default:
                                        break;
                                }
                            }
                        }
                        fastJsonResponse.setStringMap(field, map);
                        break;
                    case 11:
                        if (field.isTypeInArray()) {
                            char cZzj3 = zzj(bufferedReader);
                            if (cZzj3 != 'n') {
                                this.zzxb.push(5);
                                if (cZzj3 != '[') {
                                    throw new ParseException("Expected array start");
                                }
                                fastJsonResponse.addConcreteTypeArrayInternal(field, field.getOutputFieldName(), zza(bufferedReader, field));
                            } else {
                                zzb(bufferedReader, zzwv);
                                fastJsonResponse.addConcreteTypeArrayInternal(field, field.getOutputFieldName(), null);
                            }
                        } else {
                            char cZzj4 = zzj(bufferedReader);
                            if (cZzj4 == 'n') {
                                zzb(bufferedReader, zzwv);
                                fastJsonResponse.addConcreteTypeInternal(field, field.getOutputFieldName(), null);
                            } else {
                                this.zzxb.push(1);
                                if (cZzj4 != '{') {
                                    throw new ParseException("Expected start of object");
                                }
                                try {
                                    FastJsonResponse fastJsonResponseNewConcreteTypeInstance = field.newConcreteTypeInstance();
                                    zza(bufferedReader, fastJsonResponseNewConcreteTypeInstance);
                                    fastJsonResponse.addConcreteTypeInternal(field, field.getOutputFieldName(), fastJsonResponseNewConcreteTypeInstance);
                                } catch (IllegalAccessException e) {
                                    throw new ParseException("Error instantiating inner object", e);
                                } catch (InstantiationException e2) {
                                    throw new ParseException("Error instantiating inner object", e2);
                                }
                            }
                        }
                        break;
                    default:
                        throw new ParseException(new StringBuilder(30).append("Invalid field type ").append(field.getTypeIn()).toString());
                }
                zzk(4);
                zzk(2);
                char cZzj5 = zzj(bufferedReader);
                switch (cZzj5) {
                    case ',':
                        strZza = zza(bufferedReader);
                        break;
                    case '}':
                        strZza = null;
                        break;
                    default:
                        throw new ParseException(new StringBuilder(55).append("Expected end of object or field separator, but found: ").append(cZzj5).toString());
                }
            }
        }
        PostProcessor<? extends FastJsonResponse> postProcessor = fastJsonResponse.getPostProcessor();
        if (postProcessor != null) {
            postProcessor.postProcess(fastJsonResponse);
        }
        zzk(1);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Switch 'out' block B:3:0x0002 for B:4:0x0006 already processed. Defaulting to fallback option. */
    public final boolean zza(BufferedReader bufferedReader, boolean z) throws ParseException, IOException {
        while (true) {
            char cZzj = zzj(bufferedReader);
            switch (cZzj) {
                case '\"':
                    if (z) {
                        throw new ParseException("No boolean value found in string");
                    }
                    z = true;
                    break;
                case 'f':
                    zzb(bufferedReader, z ? zzwz : zzwy);
                    return false;
                case 'n':
                    zzb(bufferedReader, zzwv);
                    return false;
                case 't':
                    zzb(bufferedReader, z ? zzwx : zzww);
                    return true;
                default:
                    throw new ParseException(new StringBuilder(19).append("Unexpected token: ").append(cZzj).toString());
            }
        }
    }

    private final String zzb(BufferedReader bufferedReader) throws ParseException, IOException {
        bufferedReader.mark(1024);
        switch (zzj(bufferedReader)) {
            case '\"':
                if (bufferedReader.read(this.zzwq) == -1) {
                    throw new ParseException("Unexpected EOF while parsing string");
                }
                char c = this.zzwq[0];
                boolean z = false;
                while (true) {
                    if (c != '\"' || z) {
                        boolean z2 = c == '\\' && !z;
                        if (bufferedReader.read(this.zzwq) == -1) {
                            throw new ParseException("Unexpected EOF while parsing string");
                        }
                        c = this.zzwq[0];
                        if (Character.isISOControl(c)) {
                            throw new ParseException("Unexpected control character while reading string");
                        }
                        z = z2;
                    }
                }
                break;
            case ',':
                throw new ParseException("Missing value");
            case '[':
                this.zzxb.push(5);
                bufferedReader.mark(32);
                if (zzj(bufferedReader) != ']') {
                    bufferedReader.reset();
                    int i = 1;
                    boolean z3 = false;
                    boolean z4 = false;
                    while (i > 0) {
                        char cZzj = zzj(bufferedReader);
                        if (cZzj == 0) {
                            throw new ParseException("Unexpected EOF while parsing array");
                        }
                        if (Character.isISOControl(cZzj)) {
                            throw new ParseException("Unexpected control character while reading array");
                        }
                        if (cZzj == '\"' && !z4) {
                            z3 = !z3;
                        }
                        int i2 = (cZzj != '[' || z3) ? i : i + 1;
                        i = (cZzj != ']' || z3) ? i2 : i2 - 1;
                        z4 = (cZzj == '\\' && z3) ? !z4 : false;
                    }
                    zzk(5);
                } else {
                    zzk(5);
                }
                break;
            case '{':
                this.zzxb.push(1);
                bufferedReader.mark(32);
                char cZzj2 = zzj(bufferedReader);
                if (cZzj2 == '}') {
                    zzk(1);
                } else {
                    if (cZzj2 != '\"') {
                        throw new ParseException(new StringBuilder(18).append("Unexpected token ").append(cZzj2).toString());
                    }
                    bufferedReader.reset();
                    zza(bufferedReader);
                    while (zzb(bufferedReader) != null) {
                    }
                    zzk(1);
                }
                break;
            default:
                bufferedReader.reset();
                zza(bufferedReader, this.zzws);
                break;
        }
        char cZzj3 = zzj(bufferedReader);
        switch (cZzj3) {
            case ',':
                zzk(2);
                return zza(bufferedReader);
            case '}':
                zzk(2);
                return null;
            default:
                throw new ParseException(new StringBuilder(18).append("Unexpected token ").append(cZzj3).toString());
        }
    }

    private static String zzb(BufferedReader bufferedReader, char[] cArr, StringBuilder sb, char[] cArr2) throws ParseException, IOException {
        boolean z;
        boolean z2;
        sb.setLength(0);
        bufferedReader.mark(cArr.length);
        boolean z3 = false;
        boolean z4 = false;
        while (true) {
            int i = bufferedReader.read(cArr);
            if (i == -1) {
                throw new ParseException("Unexpected EOF while parsing string");
            }
            int i2 = 0;
            while (i2 < i) {
                char c = cArr[i2];
                if (Character.isISOControl(c)) {
                    if (cArr2 == null) {
                        z2 = false;
                        break;
                    }
                    int i3 = 0;
                    while (true) {
                        if (i3 >= cArr2.length) {
                            z2 = false;
                            break;
                        }
                        if (cArr2[i3] == c) {
                            z2 = true;
                            break;
                        }
                        i3++;
                    }
                    if (!z2) {
                        throw new ParseException("Unexpected control character while reading string");
                    }
                }
                if (c == '\"' && !z4) {
                    sb.append(cArr, 0, i2);
                    bufferedReader.reset();
                    bufferedReader.skip(i2 + 1);
                    return z3 ? JsonUtils.unescapeString(sb.toString()) : sb.toString();
                }
                if (c == '\\') {
                    z = true;
                    z4 = !z4;
                } else {
                    z = z3;
                    z4 = false;
                }
                i2++;
                z3 = z;
            }
            sb.append(cArr, 0, i);
            bufferedReader.mark(cArr.length);
        }
    }

    private final void zzb(BufferedReader bufferedReader, char[] cArr) throws ParseException, IOException {
        int i = 0;
        while (i < cArr.length) {
            int i2 = bufferedReader.read(this.zzwr, 0, cArr.length - i);
            if (i2 == -1) {
                throw new ParseException("Unexpected EOF");
            }
            for (int i3 = 0; i3 < i2; i3++) {
                if (cArr[i3 + i] != this.zzwr[i3]) {
                    throw new ParseException("Unexpected character");
                }
            }
            i += i2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String zzc(BufferedReader bufferedReader) throws ParseException, IOException {
        return zza(bufferedReader, this.zzwr, this.zzwt, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int zzd(BufferedReader bufferedReader) throws ParseException, IOException {
        int i;
        int i2;
        boolean z;
        int i3;
        int i4 = 0;
        int iZza = zza(bufferedReader, this.zzws);
        if (iZza == 0) {
            return 0;
        }
        char[] cArr = this.zzws;
        if (iZza <= 0) {
            throw new ParseException("No number to parse");
        }
        if (cArr[0] == '-') {
            i = Integer.MIN_VALUE;
            i2 = 1;
            z = true;
        } else {
            i = -2147483647;
            i2 = 0;
            z = false;
        }
        if (i2 < iZza) {
            i3 = i2 + 1;
            int iDigit = Character.digit(cArr[i2], 10);
            if (iDigit < 0) {
                throw new ParseException("Unexpected non-digit character");
            }
            i4 = -iDigit;
        } else {
            i3 = i2;
        }
        while (i3 < iZza) {
            int i5 = i3 + 1;
            int iDigit2 = Character.digit(cArr[i3], 10);
            if (iDigit2 < 0) {
                throw new ParseException("Unexpected non-digit character");
            }
            if (i4 < -214748364) {
                throw new ParseException("Number too large");
            }
            int i6 = i4 * 10;
            if (i6 < i + iDigit2) {
                throw new ParseException("Number too large");
            }
            i4 = i6 - iDigit2;
            i3 = i5;
        }
        if (!z) {
            return -i4;
        }
        if (i3 <= 1) {
            throw new ParseException("No digits to parse");
        }
        return i4;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final long zze(BufferedReader bufferedReader) throws ParseException, IOException {
        long j;
        int i;
        boolean z;
        int i2;
        long j2 = 0;
        int iZza = zza(bufferedReader, this.zzws);
        if (iZza == 0) {
            return 0L;
        }
        char[] cArr = this.zzws;
        if (iZza <= 0) {
            throw new ParseException("No number to parse");
        }
        if (cArr[0] == '-') {
            j = Long.MIN_VALUE;
            i = 1;
            z = true;
        } else {
            j = -9223372036854775807L;
            i = 0;
            z = false;
        }
        if (i < iZza) {
            i2 = i + 1;
            int iDigit = Character.digit(cArr[i], 10);
            if (iDigit < 0) {
                throw new ParseException("Unexpected non-digit character");
            }
            j2 = -iDigit;
        } else {
            i2 = i;
        }
        while (i2 < iZza) {
            int i3 = i2 + 1;
            int iDigit2 = Character.digit(cArr[i2], 10);
            if (iDigit2 < 0) {
                throw new ParseException("Unexpected non-digit character");
            }
            if (j2 < -922337203685477580L) {
                throw new ParseException("Number too large");
            }
            long j3 = j2 * 10;
            if (j3 < ((long) iDigit2) + j) {
                throw new ParseException("Number too large");
            }
            j2 = j3 - ((long) iDigit2);
            i2 = i3;
        }
        if (!z) {
            return -j2;
        }
        if (i2 <= 1) {
            throw new ParseException("No digits to parse");
        }
        return j2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final BigInteger zzf(BufferedReader bufferedReader) throws ParseException, IOException {
        int iZza = zza(bufferedReader, this.zzws);
        if (iZza == 0) {
            return null;
        }
        return new BigInteger(new String(this.zzws, 0, iZza));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final float zzg(BufferedReader bufferedReader) throws ParseException, IOException {
        int iZza = zza(bufferedReader, this.zzws);
        if (iZza == 0) {
            return 0.0f;
        }
        return Float.parseFloat(new String(this.zzws, 0, iZza));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final double zzh(BufferedReader bufferedReader) throws ParseException, IOException {
        int iZza = zza(bufferedReader, this.zzws);
        if (iZza == 0) {
            return 0.0d;
        }
        return Double.parseDouble(new String(this.zzws, 0, iZza));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final BigDecimal zzi(BufferedReader bufferedReader) throws ParseException, IOException {
        int iZza = zza(bufferedReader, this.zzws);
        if (iZza == 0) {
            return null;
        }
        return new BigDecimal(new String(this.zzws, 0, iZza));
    }

    private final char zzj(BufferedReader bufferedReader) throws ParseException, IOException {
        if (bufferedReader.read(this.zzwq) == -1) {
            return (char) 0;
        }
        while (Character.isWhitespace(this.zzwq[0])) {
            if (bufferedReader.read(this.zzwq) == -1) {
                return (char) 0;
            }
        }
        return this.zzwq[0];
    }

    private final void zzk(int i) throws ParseException {
        if (this.zzxb.isEmpty()) {
            throw new ParseException(new StringBuilder(46).append("Expected state ").append(i).append(" but had empty stack").toString());
        }
        int iIntValue = this.zzxb.pop().intValue();
        if (iIntValue != i) {
            throw new ParseException(new StringBuilder(46).append("Expected state ").append(i).append(" but had ").append(iIntValue).toString());
        }
    }

    public void parse(InputStream inputStream, T t) throws ParseException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream), 1024);
        try {
            try {
                this.zzxb.push(0);
                char cZzj = zzj(bufferedReader);
                switch (cZzj) {
                    case 0:
                        throw new ParseException("No data to parse");
                    case '[':
                        this.zzxb.push(5);
                        Map<String, FastJsonResponse.Field<?, ?>> fieldMappings = t.getFieldMappings();
                        if (fieldMappings.size() != 1) {
                            throw new ParseException("Object array response class must have a single Field");
                        }
                        FastJsonResponse.Field<?, ?> value = fieldMappings.entrySet().iterator().next().getValue();
                        t.addConcreteTypeArrayInternal(value, value.getOutputFieldName(), zza(bufferedReader, value));
                        break;
                    case '{':
                        this.zzxb.push(1);
                        zza(bufferedReader, t);
                        break;
                    default:
                        throw new ParseException(new StringBuilder(19).append("Unexpected token: ").append(cZzj).toString());
                }
                zzk(0);
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    Log.w("FastParser", "Failed to close reader while parsing.");
                }
            } catch (IOException e2) {
                throw new ParseException(e2);
            }
        } catch (Throwable th) {
            try {
                bufferedReader.close();
            } catch (IOException e3) {
                Log.w("FastParser", "Failed to close reader while parsing.");
            }
            throw th;
        }
    }

    public void parse(String str, T t) throws ParseException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(str.getBytes());
        try {
            parse(byteArrayInputStream, t);
        } finally {
            try {
                byteArrayInputStream.close();
            } catch (IOException e) {
                Log.w("FastParser", "Failed to close the input stream while parsing.");
            }
        }
    }
}
