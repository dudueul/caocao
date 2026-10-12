package com.google.zxing.datamatrix.decoder;

import com.google.zxing.FormatException;
import com.google.zxing.common.BitSource;
import com.google.zxing.common.DecoderResult;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
final class DecodedBitStreamParser {
    private static final char[] C40_BASIC_SET_CHARS = {'*', '*', '*', ' ', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    private static final char[] C40_SHIFT2_SET_CHARS = {'!', '\"', '#', '$', '%', '&', '\'', '(', ')', '*', '+', ',', '-', '.', '/', ':', ';', '<', '=', '>', '?', '@', '[', '\\', ']', '^', '_'};
    private static final char[] TEXT_BASIC_SET_CHARS = {'*', '*', '*', ' ', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    private static final char[] TEXT_SHIFT2_SET_CHARS = C40_SHIFT2_SET_CHARS;
    private static final char[] TEXT_SHIFT3_SET_CHARS = {'`', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '{', '|', '}', '~', 127};

    private enum Mode {
        PAD_ENCODE,
        ASCII_ENCODE,
        C40_ENCODE,
        TEXT_ENCODE,
        ANSIX12_ENCODE,
        EDIFACT_ENCODE,
        BASE256_ENCODE
    }

    private DecodedBitStreamParser() {
    }

    /* JADX WARN: Code duplicated, block: B:12:0x0033  */
    /* JADX WARN: Code duplicated, block: B:15:0x0042  */
    static DecoderResult decode(byte[] bArr) throws FormatException {
        BitSource bitSource = new BitSource(bArr);
        StringBuilder sb = new StringBuilder(100);
        StringBuilder sb2 = new StringBuilder(0);
        ArrayList arrayList = new ArrayList(1);
        Mode modeDecodeAsciiSegment = Mode.ASCII_ENCODE;
        do {
            if (modeDecodeAsciiSegment == Mode.ASCII_ENCODE) {
                modeDecodeAsciiSegment = decodeAsciiSegment(bitSource, sb, sb2);
            } else {
                switch (modeDecodeAsciiSegment) {
                    case C40_ENCODE:
                        decodeC40Segment(bitSource, sb);
                        break;
                    case TEXT_ENCODE:
                        decodeTextSegment(bitSource, sb);
                        break;
                    case ANSIX12_ENCODE:
                        decodeAnsiX12Segment(bitSource, sb);
                        break;
                    case EDIFACT_ENCODE:
                        decodeEdifactSegment(bitSource, sb);
                        break;
                    case BASE256_ENCODE:
                        decodeBase256Segment(bitSource, sb, arrayList);
                        break;
                    default:
                        throw FormatException.getFormatInstance();
                }
                modeDecodeAsciiSegment = Mode.ASCII_ENCODE;
            }
            if (modeDecodeAsciiSegment != Mode.PAD_ENCODE) {
            }
            if (sb2.length() > 0) {
                sb.append((CharSequence) sb2);
            }
            String string = sb.toString();
            if (arrayList.isEmpty()) {
                arrayList = null;
            }
            return new DecoderResult(bArr, string, arrayList, null);
        } while (bitSource.available() > 0);
        if (sb2.length() > 0) {
            sb.append((CharSequence) sb2);
        }
        String string2 = sb.toString();
        if (arrayList.isEmpty()) {
            arrayList = null;
        }
        return new DecoderResult(bArr, string2, arrayList, null);
    }

    private static void decodeAnsiX12Segment(BitSource bitSource, StringBuilder sb) throws FormatException {
        int bits;
        int[] iArr = new int[3];
        while (bitSource.available() != 8 && (bits = bitSource.readBits(8)) != 254) {
            parseTwoBytes(bits, bitSource.readBits(8), iArr);
            for (int i = 0; i < 3; i++) {
                int i2 = iArr[i];
                if (i2 == 0) {
                    sb.append('\r');
                } else if (i2 == 1) {
                    sb.append('*');
                } else if (i2 == 2) {
                    sb.append('>');
                } else if (i2 == 3) {
                    sb.append(' ');
                } else if (i2 < 14) {
                    sb.append((char) (i2 + 44));
                } else {
                    if (i2 >= 40) {
                        throw FormatException.getFormatInstance();
                    }
                    sb.append((char) (i2 + 51));
                }
            }
            if (bitSource.available() <= 0) {
                return;
            }
        }
    }

    private static Mode decodeAsciiSegment(BitSource bitSource, StringBuilder sb, StringBuilder sb2) throws FormatException {
        boolean z = false;
        do {
            int bits = bitSource.readBits(8);
            if (bits == 0) {
                throw FormatException.getFormatInstance();
            }
            if (bits <= 128) {
                if (z) {
                    bits += 128;
                }
                sb.append((char) (bits - 1));
                return Mode.ASCII_ENCODE;
            }
            if (bits == 129) {
                return Mode.PAD_ENCODE;
            }
            if (bits <= 229) {
                int i = bits - 130;
                if (i < 10) {
                    sb.append('0');
                }
                sb.append(i);
            } else {
                if (bits == 230) {
                    return Mode.C40_ENCODE;
                }
                if (bits == 231) {
                    return Mode.BASE256_ENCODE;
                }
                if (bits == 232) {
                    sb.append((char) 29);
                } else if (bits != 233 && bits != 234) {
                    if (bits == 235) {
                        z = true;
                    } else if (bits == 236) {
                        sb.append("[)>\u001e05\u001d");
                        sb2.insert(0, "\u001e\u0004");
                    } else if (bits == 237) {
                        sb.append("[)>\u001e06\u001d");
                        sb2.insert(0, "\u001e\u0004");
                    } else {
                        if (bits == 238) {
                            return Mode.ANSIX12_ENCODE;
                        }
                        if (bits == 239) {
                            return Mode.TEXT_ENCODE;
                        }
                        if (bits == 240) {
                            return Mode.EDIFACT_ENCODE;
                        }
                        if (bits != 241 && bits >= 242 && (bits != 254 || bitSource.available() != 0)) {
                            throw FormatException.getFormatInstance();
                        }
                    }
                }
            }
        } while (bitSource.available() > 0);
        return Mode.ASCII_ENCODE;
    }

    private static void decodeBase256Segment(BitSource bitSource, StringBuilder sb, Collection<byte[]> collection) throws FormatException {
        int i;
        int iUnrandomize255State;
        int byteOffset = bitSource.getByteOffset() + 1;
        int i2 = byteOffset + 1;
        int iUnrandomize255State2 = unrandomize255State(bitSource.readBits(8), byteOffset);
        if (iUnrandomize255State2 == 0) {
            iUnrandomize255State = bitSource.available() / 8;
            i = i2;
        } else if (iUnrandomize255State2 < 250) {
            iUnrandomize255State = iUnrandomize255State2;
            i = i2;
        } else {
            i = i2 + 1;
            iUnrandomize255State = ((iUnrandomize255State2 - 249) * 250) + unrandomize255State(bitSource.readBits(8), i2);
        }
        if (iUnrandomize255State < 0) {
            throw FormatException.getFormatInstance();
        }
        byte[] bArr = new byte[iUnrandomize255State];
        int i3 = 0;
        int i4 = i;
        while (i3 < iUnrandomize255State) {
            if (bitSource.available() < 8) {
                throw FormatException.getFormatInstance();
            }
            bArr[i3] = (byte) unrandomize255State(bitSource.readBits(8), i4);
            i3++;
            i4++;
        }
        collection.add(bArr);
        try {
            sb.append(new String(bArr, "ISO8859_1"));
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException("Platform does not support required encoding: " + e);
        }
    }

    private static void decodeC40Segment(BitSource bitSource, StringBuilder sb) throws FormatException {
        int bits;
        boolean z = false;
        int[] iArr = new int[3];
        int i = 0;
        while (bitSource.available() != 8 && (bits = bitSource.readBits(8)) != 254) {
            parseTwoBytes(bits, bitSource.readBits(8), iArr);
            for (int i2 = 0; i2 < 3; i2++) {
                int i3 = iArr[i2];
                switch (i) {
                    case 0:
                        if (i3 >= 3) {
                            if (i3 >= C40_BASIC_SET_CHARS.length) {
                                throw FormatException.getFormatInstance();
                            }
                            char c = C40_BASIC_SET_CHARS[i3];
                            if (z) {
                                sb.append((char) (c + 128));
                                z = false;
                            } else {
                                sb.append(c);
                            }
                        } else {
                            i = i3 + 1;
                        }
                        break;
                    case 1:
                        if (z) {
                            sb.append((char) (i3 + 128));
                            z = false;
                        } else {
                            sb.append((char) i3);
                        }
                        i = 0;
                        break;
                    case 2:
                        if (i3 < C40_SHIFT2_SET_CHARS.length) {
                            char c2 = C40_SHIFT2_SET_CHARS[i3];
                            if (z) {
                                sb.append((char) (c2 + 128));
                                z = false;
                            } else {
                                sb.append(c2);
                            }
                        } else if (i3 == 27) {
                            sb.append((char) 29);
                        } else {
                            if (i3 != 30) {
                                throw FormatException.getFormatInstance();
                            }
                            z = true;
                        }
                        i = 0;
                        break;
                    case 3:
                        if (z) {
                            sb.append((char) (i3 + 224));
                            z = false;
                        } else {
                            sb.append((char) (i3 + 96));
                        }
                        i = 0;
                        break;
                    default:
                        throw FormatException.getFormatInstance();
                }
            }
            if (bitSource.available() <= 0) {
                return;
            }
        }
    }

    private static void decodeEdifactSegment(BitSource bitSource, StringBuilder sb) {
        while (bitSource.available() > 16) {
            for (int i = 0; i < 4; i++) {
                int bits = bitSource.readBits(6);
                if (bits == 31) {
                    int bitOffset = 8 - bitSource.getBitOffset();
                    if (bitOffset != 8) {
                        bitSource.readBits(bitOffset);
                        return;
                    }
                    return;
                }
                if ((bits & 32) == 0) {
                    bits |= 64;
                }
                sb.append((char) bits);
            }
            if (bitSource.available() <= 0) {
                return;
            }
        }
    }

    private static void decodeTextSegment(BitSource bitSource, StringBuilder sb) throws FormatException {
        int bits;
        boolean z = false;
        int[] iArr = new int[3];
        int i = 0;
        while (bitSource.available() != 8 && (bits = bitSource.readBits(8)) != 254) {
            parseTwoBytes(bits, bitSource.readBits(8), iArr);
            for (int i2 = 0; i2 < 3; i2++) {
                int i3 = iArr[i2];
                switch (i) {
                    case 0:
                        if (i3 >= 3) {
                            if (i3 >= TEXT_BASIC_SET_CHARS.length) {
                                throw FormatException.getFormatInstance();
                            }
                            char c = TEXT_BASIC_SET_CHARS[i3];
                            if (z) {
                                sb.append((char) (c + 128));
                                z = false;
                            } else {
                                sb.append(c);
                            }
                        } else {
                            i = i3 + 1;
                        }
                        break;
                    case 1:
                        if (z) {
                            sb.append((char) (i3 + 128));
                            z = false;
                        } else {
                            sb.append((char) i3);
                        }
                        i = 0;
                        break;
                    case 2:
                        if (i3 < TEXT_SHIFT2_SET_CHARS.length) {
                            char c2 = TEXT_SHIFT2_SET_CHARS[i3];
                            if (z) {
                                sb.append((char) (c2 + 128));
                                z = false;
                            } else {
                                sb.append(c2);
                            }
                        } else if (i3 == 27) {
                            sb.append((char) 29);
                        } else {
                            if (i3 != 30) {
                                throw FormatException.getFormatInstance();
                            }
                            z = true;
                        }
                        i = 0;
                        break;
                    case 3:
                        if (i3 >= TEXT_SHIFT3_SET_CHARS.length) {
                            throw FormatException.getFormatInstance();
                        }
                        char c3 = TEXT_SHIFT3_SET_CHARS[i3];
                        if (z) {
                            sb.append((char) (c3 + 128));
                            z = false;
                        } else {
                            sb.append(c3);
                        }
                        i = 0;
                        break;
                        break;
                    default:
                        throw FormatException.getFormatInstance();
                }
            }
            if (bitSource.available() <= 0) {
                return;
            }
        }
    }

    private static void parseTwoBytes(int i, int i2, int[] iArr) {
        int i3 = ((i << 8) + i2) - 1;
        int i4 = i3 / 1600;
        iArr[0] = i4;
        int i5 = i3 - (i4 * 1600);
        int i6 = i5 / 40;
        iArr[1] = i6;
        iArr[2] = i5 - (i6 * 40);
    }

    private static int unrandomize255State(int i, int i2) {
        int i3 = i - (((i2 * 149) % 255) + 1);
        return i3 >= 0 ? i3 : i3 + 256;
    }
}
