package com.google.gson.stream;

import com.google.gson.internal.JsonReaderInternalAccess;
import com.google.gson.internal.bind.JsonTreeReader;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class JsonReader implements Closeable {
    private static final long MIN_INCOMPLETE_INTEGER = -922337203685477580L;
    private static final char[] NON_EXECUTE_PREFIX = ")]}'\n".toCharArray();
    private static final int NUMBER_CHAR_DECIMAL = 3;
    private static final int NUMBER_CHAR_DIGIT = 2;
    private static final int NUMBER_CHAR_EXP_DIGIT = 7;
    private static final int NUMBER_CHAR_EXP_E = 5;
    private static final int NUMBER_CHAR_EXP_SIGN = 6;
    private static final int NUMBER_CHAR_FRACTION_DIGIT = 4;
    private static final int NUMBER_CHAR_NONE = 0;
    private static final int NUMBER_CHAR_SIGN = 1;
    private static final int PEEKED_BEGIN_ARRAY = 3;
    private static final int PEEKED_BEGIN_OBJECT = 1;
    private static final int PEEKED_BUFFERED = 11;
    private static final int PEEKED_DOUBLE_QUOTED = 9;
    private static final int PEEKED_DOUBLE_QUOTED_NAME = 13;
    private static final int PEEKED_END_ARRAY = 4;
    private static final int PEEKED_END_OBJECT = 2;
    private static final int PEEKED_EOF = 17;
    private static final int PEEKED_FALSE = 6;
    private static final int PEEKED_LONG = 15;
    private static final int PEEKED_NONE = 0;
    private static final int PEEKED_NULL = 7;
    private static final int PEEKED_NUMBER = 16;
    private static final int PEEKED_SINGLE_QUOTED = 8;
    private static final int PEEKED_SINGLE_QUOTED_NAME = 12;
    private static final int PEEKED_TRUE = 5;
    private static final int PEEKED_UNQUOTED = 10;
    private static final int PEEKED_UNQUOTED_NAME = 14;
    private final Reader in;
    private int[] pathIndices;
    private String[] pathNames;
    private long peekedLong;
    private int peekedNumberLength;
    private String peekedString;
    private int stackSize;
    private boolean lenient = false;
    private final char[] buffer = new char[1024];
    private int pos = 0;
    private int limit = 0;
    private int lineNumber = 0;
    private int lineStart = 0;
    int peeked = 0;
    private int[] stack = new int[32];

    static {
        JsonReaderInternalAccess.INSTANCE = new JsonReaderInternalAccess() { // from class: com.google.gson.stream.JsonReader.1
            @Override // com.google.gson.internal.JsonReaderInternalAccess
            public void promoteNameToValue(JsonReader jsonReader) throws IOException {
                if (jsonReader instanceof JsonTreeReader) {
                    ((JsonTreeReader) jsonReader).promoteNameToValue();
                    return;
                }
                int iDoPeek = jsonReader.peeked;
                if (iDoPeek == 0) {
                    iDoPeek = jsonReader.doPeek();
                }
                if (iDoPeek == 13) {
                    jsonReader.peeked = 9;
                } else if (iDoPeek == 12) {
                    jsonReader.peeked = 8;
                } else {
                    if (iDoPeek != 14) {
                        throw new IllegalStateException("Expected a name but was " + jsonReader.peek() + jsonReader.locationString());
                    }
                    jsonReader.peeked = 10;
                }
            }
        };
    }

    public JsonReader(Reader reader) {
        this.stackSize = 0;
        int[] iArr = this.stack;
        int i = this.stackSize;
        this.stackSize = i + 1;
        iArr[i] = 6;
        this.pathNames = new String[32];
        this.pathIndices = new int[32];
        if (reader == null) {
            throw new NullPointerException("in == null");
        }
        this.in = reader;
    }

    private void checkLenient() throws IOException {
        if (!this.lenient) {
            throw syntaxError("Use JsonReader.setLenient(true) to accept malformed JSON");
        }
    }

    private void consumeNonExecutePrefix() throws IOException {
        nextNonWhitespace(true);
        this.pos--;
        if (this.pos + NON_EXECUTE_PREFIX.length <= this.limit || fillBuffer(NON_EXECUTE_PREFIX.length)) {
            for (int i = 0; i < NON_EXECUTE_PREFIX.length; i++) {
                if (this.buffer[this.pos + i] != NON_EXECUTE_PREFIX[i]) {
                    return;
                }
            }
            this.pos += NON_EXECUTE_PREFIX.length;
        }
    }

    private boolean fillBuffer(int i) throws IOException {
        char[] cArr = this.buffer;
        this.lineStart -= this.pos;
        if (this.limit != this.pos) {
            this.limit -= this.pos;
            System.arraycopy(cArr, this.pos, cArr, 0, this.limit);
        } else {
            this.limit = 0;
        }
        this.pos = 0;
        do {
            int i2 = this.in.read(cArr, this.limit, cArr.length - this.limit);
            if (i2 == -1) {
                return false;
            }
            this.limit += i2;
            if (this.lineNumber == 0 && this.lineStart == 0 && this.limit > 0 && cArr[0] == 65279) {
                this.pos++;
                this.lineStart++;
                i++;
            }
        } while (this.limit < i);
        return true;
    }

    private boolean isLiteral(char c) throws IOException {
        switch (c) {
            case '#':
            case '/':
            case ';':
            case '=':
            case '\\':
                checkLenient();
            case '\t':
            case '\n':
            case '\f':
            case '\r':
            case ' ':
            case ',':
            case ':':
            case '[':
            case ']':
            case '{':
            case '}':
                return false;
            default:
                return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String locationString() {
        return " at line " + (this.lineNumber + 1) + " column " + ((this.pos - this.lineStart) + 1) + " path " + getPath();
    }

    private int nextNonWhitespace(boolean z) throws IOException {
        char[] cArr = this.buffer;
        int i = this.pos;
        int i2 = this.limit;
        while (true) {
            if (i == i2) {
                this.pos = i;
                if (!fillBuffer(1)) {
                    if (z) {
                        throw new EOFException("End of input" + locationString());
                    }
                    return -1;
                }
                i = this.pos;
                i2 = this.limit;
            }
            int i3 = i + 1;
            char c = cArr[i];
            if (c == '\n') {
                this.lineNumber++;
                this.lineStart = i3;
                i = i3;
            } else if (c == ' ' || c == '\r') {
                i = i3;
            } else if (c == '\t') {
                i = i3;
            } else if (c == '/') {
                this.pos = i3;
                if (i3 == i2) {
                    this.pos--;
                    boolean zFillBuffer = fillBuffer(2);
                    this.pos++;
                    if (!zFillBuffer) {
                        return c;
                    }
                }
                checkLenient();
                switch (cArr[this.pos]) {
                    case '*':
                        this.pos++;
                        if (!skipTo("*/")) {
                            throw syntaxError("Unterminated comment");
                        }
                        i = this.pos + 2;
                        i2 = this.limit;
                        break;
                        break;
                    case '/':
                        this.pos++;
                        skipToEndOfLine();
                        i = this.pos;
                        i2 = this.limit;
                        break;
                    default:
                        return c;
                }
            } else {
                if (c != '#') {
                    this.pos = i3;
                    return c;
                }
                this.pos = i3;
                checkLenient();
                skipToEndOfLine();
                i = this.pos;
                i2 = this.limit;
            }
        }
    }

    private String nextQuotedValue(char c) throws IOException {
        char[] cArr = this.buffer;
        StringBuilder sb = new StringBuilder();
        do {
            int i = this.pos;
            int i2 = this.limit;
            int i3 = i;
            int i4 = i;
            while (i4 < i2) {
                int i5 = i4 + 1;
                char c2 = cArr[i4];
                if (c2 == c) {
                    this.pos = i5;
                    sb.append(cArr, i3, (i5 - i3) - 1);
                    return sb.toString();
                }
                if (c2 == '\\') {
                    this.pos = i5;
                    sb.append(cArr, i3, (i5 - i3) - 1);
                    sb.append(readEscapeCharacter());
                    i5 = this.pos;
                    i2 = this.limit;
                    i3 = i5;
                } else if (c2 == '\n') {
                    this.lineNumber++;
                    this.lineStart = i5;
                }
                i4 = i5;
            }
            sb.append(cArr, i3, i4 - i3);
            this.pos = i4;
        } while (fillBuffer(1));
        throw syntaxError("Unterminated string");
    }

    private String nextUnquotedValue() throws IOException {
        String string;
        StringBuilder sb = null;
        int i = 0;
        while (true) {
            if (this.pos + i < this.limit) {
                switch (this.buffer[this.pos + i]) {
                    case '\t':
                    case '\n':
                    case '\f':
                    case '\r':
                    case ' ':
                    case ',':
                    case ':':
                    case '[':
                    case ']':
                    case '{':
                    case '}':
                        break;
                    case '#':
                    case '/':
                    case ';':
                    case '=':
                    case '\\':
                        checkLenient();
                        break;
                    default:
                        i++;
                        continue;
                }
            } else if (i >= this.buffer.length) {
                if (sb == null) {
                    sb = new StringBuilder();
                }
                sb.append(this.buffer, this.pos, i);
                this.pos += i;
                i = 0;
                if (!fillBuffer(1)) {
                }
            } else if (fillBuffer(i + 1)) {
            }
        }
        if (sb == null) {
            string = new String(this.buffer, this.pos, i);
        } else {
            sb.append(this.buffer, this.pos, i);
            string = sb.toString();
        }
        this.pos += i;
        return string;
    }

    private int peekKeyword() throws IOException {
        String str;
        String str2;
        int i;
        char c = this.buffer[this.pos];
        if (c == 't' || c == 'T') {
            str = "true";
            str2 = "TRUE";
            i = 5;
        } else if (c == 'f' || c == 'F') {
            str = "false";
            str2 = "FALSE";
            i = 6;
        } else {
            if (c != 'n' && c != 'N') {
                return 0;
            }
            str = "null";
            str2 = "NULL";
            i = 7;
        }
        int length = str.length();
        for (int i2 = 1; i2 < length; i2++) {
            if (this.pos + i2 >= this.limit && !fillBuffer(i2 + 1)) {
                return 0;
            }
            char c2 = this.buffer[this.pos + i2];
            if (c2 != str.charAt(i2) && c2 != str2.charAt(i2)) {
                return 0;
            }
        }
        if ((this.pos + length < this.limit || fillBuffer(length + 1)) && isLiteral(this.buffer[this.pos + length])) {
            return 0;
        }
        this.pos += length;
        this.peeked = i;
        return i;
    }

    /* JADX WARN: Code duplicated, block: B:22:0x0057  */
    /* JADX WARN: Code duplicated, block: B:28:0x0067 A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:29:0x0069 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:30:0x006b  */
    /* JADX WARN: Code duplicated, block: B:32:0x0070  */
    /* JADX WARN: Code duplicated, block: B:34:0x0073  */
    /* JADX WARN: Code duplicated, block: B:36:0x0077  */
    /* JADX WARN: Code duplicated, block: B:38:0x007a  */
    /* JADX WARN: Code duplicated, block: B:40:0x007e  */
    /* JADX WARN: Code duplicated, block: B:46:0x0088  */
    /* JADX WARN: Code duplicated, block: B:48:0x008b  */
    /* JADX WARN: Code duplicated, block: B:89:0x0075 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:90:0x007c A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:92:0x008d A[SYNTHETIC] */
    private int peekNumber() throws IOException {
        char c;
        char[] cArr = this.buffer;
        int i = this.pos;
        int i2 = this.limit;
        long j = 0;
        boolean z = false;
        boolean z2 = true;
        char c2 = 0;
        int i3 = 0;
        while (true) {
            if (i + i3 != i2) {
                c = cArr[i + i3];
                switch (c) {
                    case '+':
                        if (c2 == 5) {
                            return 0;
                        }
                        c2 = 6;
                        continue;
                        continue;
                        i3++;
                        break;
                        break;
                    case '-':
                        if (c2 == 0) {
                            z = true;
                            c2 = 1;
                            continue;
                            continue;
                        } else {
                            if (c2 == 5) {
                                return 0;
                            }
                            c2 = 6;
                        }
                        i3++;
                        break;
                    case '.':
                        if (c2 == 2) {
                            return 0;
                        }
                        c2 = 3;
                        continue;
                        continue;
                        i3++;
                        break;
                        break;
                    case 'E':
                    case 'e':
                        if (c2 == 2 && c2 != 4) {
                            return 0;
                        }
                        c2 = 5;
                        continue;
                        continue;
                        i3++;
                        break;
                        break;
                    default:
                        if (c < '0' && c <= '9') {
                            if (c2 == 1 || c2 == 0) {
                                j = -(c - '0');
                                c2 = 2;
                            } else if (c2 == 2) {
                                if (j == 0) {
                                    return 0;
                                }
                                long j2 = (10 * j) - ((long) (c - '0'));
                                z2 &= j > MIN_INCOMPLETE_INTEGER || (j == MIN_INCOMPLETE_INTEGER && j2 < j);
                                j = j2;
                            } else if (c2 == 3) {
                                c2 = 4;
                            } else if (c2 == 5 || c2 == 6) {
                                c2 = 7;
                            }
                            i3++;
                        } else if (isLiteral(c)) {
                            return 0;
                        }
                        break;
                }
            } else {
                if (i3 == cArr.length) {
                    return 0;
                }
                if (fillBuffer(i3 + 1)) {
                    i = this.pos;
                    i2 = this.limit;
                    c = cArr[i + i3];
                    switch (c) {
                        case '+':
                            if (c2 == 5) {
                                return 0;
                            }
                            c2 = 6;
                            continue;
                            continue;
                            i3++;
                            break;
                            break;
                        case '-':
                            if (c2 == 0) {
                                z = true;
                                c2 = 1;
                                continue;
                                continue;
                            } else {
                                if (c2 == 5) {
                                    return 0;
                                }
                                c2 = 6;
                            }
                            i3++;
                            break;
                        case '.':
                            if (c2 == 2) {
                                return 0;
                            }
                            c2 = 3;
                            continue;
                            continue;
                            i3++;
                            break;
                            break;
                        case 'E':
                        case 'e':
                            if (c2 == 2) {
                            }
                            c2 = 5;
                            continue;
                            continue;
                            i3++;
                            break;
                        default:
                            if (c < '0') {
                            }
                            if (isLiteral(c)) {
                                return 0;
                            }
                            break;
                    }
                }
            }
        }
        if (c2 == 2 && z2 && (j != Long.MIN_VALUE || z)) {
            if (!z) {
                j = -j;
            }
            this.peekedLong = j;
            this.pos += i3;
            this.peeked = 15;
            return 15;
        }
        if (c2 != 2 && c2 != 4 && c2 != 7) {
            return 0;
        }
        this.peekedNumberLength = i3;
        this.peeked = 16;
        return 16;
    }

    private void push(int i) {
        if (this.stackSize == this.stack.length) {
            int[] iArr = new int[this.stackSize * 2];
            int[] iArr2 = new int[this.stackSize * 2];
            String[] strArr = new String[this.stackSize * 2];
            System.arraycopy(this.stack, 0, iArr, 0, this.stackSize);
            System.arraycopy(this.pathIndices, 0, iArr2, 0, this.stackSize);
            System.arraycopy(this.pathNames, 0, strArr, 0, this.stackSize);
            this.stack = iArr;
            this.pathIndices = iArr2;
            this.pathNames = strArr;
        }
        int[] iArr3 = this.stack;
        int i2 = this.stackSize;
        this.stackSize = i2 + 1;
        iArr3[i2] = i;
    }

    private char readEscapeCharacter() throws IOException {
        int i;
        if (this.pos == this.limit && !fillBuffer(1)) {
            throw syntaxError("Unterminated escape sequence");
        }
        char[] cArr = this.buffer;
        int i2 = this.pos;
        this.pos = i2 + 1;
        char c = cArr[i2];
        switch (c) {
            case '\n':
                this.lineNumber++;
                this.lineStart = this.pos;
                break;
            case '\"':
            case '\'':
            case '/':
            case '\\':
                break;
            case 'b':
                return '\b';
            case 'f':
                return '\f';
            case 'n':
                return '\n';
            case 'r':
                return '\r';
            case 't':
                return '\t';
            case 'u':
                if (this.pos + 4 > this.limit && !fillBuffer(4)) {
                    throw syntaxError("Unterminated escape sequence");
                }
                char c2 = 0;
                int i3 = this.pos;
                int i4 = i3 + 4;
                while (i3 < i4) {
                    char c3 = this.buffer[i3];
                    char c4 = (char) (c2 << 4);
                    if (c3 >= '0' && c3 <= '9') {
                        i = c3 - '0';
                    } else if (c3 >= 'a' && c3 <= 'f') {
                        i = (c3 - 'a') + 10;
                    } else {
                        if (c3 < 'A' || c3 > 'F') {
                            throw new NumberFormatException("\\u" + new String(this.buffer, this.pos, 4));
                        }
                        i = (c3 - 'A') + 10;
                    }
                    c2 = (char) (i + c4);
                    i3++;
                }
                this.pos += 4;
                return c2;
            default:
                throw syntaxError("Invalid escape sequence");
        }
        return c;
    }

    private void skipQuotedValue(char c) throws IOException {
        char[] cArr = this.buffer;
        do {
            int i = this.pos;
            int i2 = this.limit;
            int i3 = i;
            while (i3 < i2) {
                int i4 = i3 + 1;
                char c2 = cArr[i3];
                if (c2 == c) {
                    this.pos = i4;
                    return;
                }
                if (c2 == '\\') {
                    this.pos = i4;
                    readEscapeCharacter();
                    i4 = this.pos;
                    i2 = this.limit;
                } else if (c2 == '\n') {
                    this.lineNumber++;
                    this.lineStart = i4;
                }
                i3 = i4;
            }
            this.pos = i3;
        } while (fillBuffer(1));
        throw syntaxError("Unterminated string");
    }

    private boolean skipTo(String str) throws IOException {
        while (true) {
            if (this.pos + str.length() > this.limit && !fillBuffer(str.length())) {
                return false;
            }
            if (this.buffer[this.pos] != '\n') {
                for (int i = 0; i < str.length(); i++) {
                    if (this.buffer[this.pos + i] == str.charAt(i)) {
                    }
                }
                return true;
            }
            this.lineNumber++;
            this.lineStart = this.pos + 1;
            this.pos++;
        }
    }

    private void skipToEndOfLine() throws IOException {
        char c;
        do {
            if (this.pos >= this.limit && !fillBuffer(1)) {
                return;
            }
            char[] cArr = this.buffer;
            int i = this.pos;
            this.pos = i + 1;
            c = cArr[i];
            if (c == '\n') {
                this.lineNumber++;
                this.lineStart = this.pos;
                return;
            }
        } while (c != '\r');
    }

    private void skipUnquotedValue() throws IOException {
        do {
            int i = 0;
            while (this.pos + i < this.limit) {
                switch (this.buffer[this.pos + i]) {
                    case '\t':
                    case '\n':
                    case '\f':
                    case '\r':
                    case ' ':
                    case ',':
                    case ':':
                    case '[':
                    case ']':
                    case '{':
                    case '}':
                        break;
                    case '#':
                    case '/':
                    case ';':
                    case '=':
                    case '\\':
                        checkLenient();
                        break;
                    default:
                        i++;
                        break;
                }
                this.pos += i;
                return;
            }
            this.pos += i;
        } while (fillBuffer(1));
    }

    private IOException syntaxError(String str) throws IOException {
        throw new MalformedJsonException(str + locationString());
    }

    public void beginArray() throws IOException {
        int iDoPeek = this.peeked;
        if (iDoPeek == 0) {
            iDoPeek = doPeek();
        }
        if (iDoPeek != 3) {
            throw new IllegalStateException("Expected BEGIN_ARRAY but was " + peek() + locationString());
        }
        push(1);
        this.pathIndices[this.stackSize - 1] = 0;
        this.peeked = 0;
    }

    public void beginObject() throws IOException {
        int iDoPeek = this.peeked;
        if (iDoPeek == 0) {
            iDoPeek = doPeek();
        }
        if (iDoPeek != 1) {
            throw new IllegalStateException("Expected BEGIN_OBJECT but was " + peek() + locationString());
        }
        push(3);
        this.peeked = 0;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.peeked = 0;
        this.stack[0] = 8;
        this.stackSize = 1;
        this.in.close();
    }

    int doPeek() throws IOException {
        int i = this.stack[this.stackSize - 1];
        if (i == 1) {
            this.stack[this.stackSize - 1] = 2;
        } else if (i == 2) {
            switch (nextNonWhitespace(true)) {
                case 44:
                    break;
                case 59:
                    checkLenient();
                    break;
                case 93:
                    this.peeked = 4;
                    return 4;
                default:
                    throw syntaxError("Unterminated array");
            }
        } else {
            if (i == 3 || i == 5) {
                this.stack[this.stackSize - 1] = 4;
                if (i == 5) {
                    switch (nextNonWhitespace(true)) {
                        case 44:
                            break;
                        case 59:
                            checkLenient();
                            break;
                        case 125:
                            this.peeked = 2;
                            return 2;
                        default:
                            throw syntaxError("Unterminated object");
                    }
                }
                int iNextNonWhitespace = nextNonWhitespace(true);
                switch (iNextNonWhitespace) {
                    case 34:
                        this.peeked = 13;
                        return 13;
                    case 39:
                        checkLenient();
                        this.peeked = 12;
                        return 12;
                    case 125:
                        if (i == 5) {
                            throw syntaxError("Expected name");
                        }
                        this.peeked = 2;
                        return 2;
                    default:
                        checkLenient();
                        this.pos--;
                        if (!isLiteral((char) iNextNonWhitespace)) {
                            throw syntaxError("Expected name");
                        }
                        this.peeked = 14;
                        return 14;
                }
            }
            if (i == 4) {
                this.stack[this.stackSize - 1] = 5;
                switch (nextNonWhitespace(true)) {
                    case 58:
                        break;
                    case 59:
                    case 60:
                    default:
                        throw syntaxError("Expected ':'");
                    case 61:
                        checkLenient();
                        if ((this.pos < this.limit || fillBuffer(1)) && this.buffer[this.pos] == '>') {
                            this.pos++;
                        }
                        break;
                }
            } else if (i == 6) {
                if (this.lenient) {
                    consumeNonExecutePrefix();
                }
                this.stack[this.stackSize - 1] = 7;
            } else if (i == 7) {
                if (nextNonWhitespace(false) == -1) {
                    this.peeked = 17;
                    return 17;
                }
                checkLenient();
                this.pos--;
            } else if (i == 8) {
                throw new IllegalStateException("JsonReader is closed");
            }
        }
        switch (nextNonWhitespace(true)) {
            case 34:
                this.peeked = 9;
                return 9;
            case 39:
                checkLenient();
                this.peeked = 8;
                return 8;
            case 44:
            case 59:
                break;
            case 91:
                this.peeked = 3;
                return 3;
            case 93:
                if (i == 1) {
                    this.peeked = 4;
                    return 4;
                }
                break;
            case 123:
                this.peeked = 1;
                return 1;
            default:
                this.pos--;
                int iPeekKeyword = peekKeyword();
                if (iPeekKeyword != 0) {
                    return iPeekKeyword;
                }
                int iPeekNumber = peekNumber();
                if (iPeekNumber != 0) {
                    return iPeekNumber;
                }
                if (!isLiteral(this.buffer[this.pos])) {
                    throw syntaxError("Expected value");
                }
                checkLenient();
                this.peeked = 10;
                return 10;
        }
        if (i != 1 && i != 2) {
            throw syntaxError("Unexpected value");
        }
        checkLenient();
        this.pos--;
        this.peeked = 7;
        return 7;
    }

    public void endArray() throws IOException {
        int iDoPeek = this.peeked;
        if (iDoPeek == 0) {
            iDoPeek = doPeek();
        }
        if (iDoPeek != 4) {
            throw new IllegalStateException("Expected END_ARRAY but was " + peek() + locationString());
        }
        this.stackSize--;
        int[] iArr = this.pathIndices;
        int i = this.stackSize - 1;
        iArr[i] = iArr[i] + 1;
        this.peeked = 0;
    }

    public void endObject() throws IOException {
        int iDoPeek = this.peeked;
        if (iDoPeek == 0) {
            iDoPeek = doPeek();
        }
        if (iDoPeek != 2) {
            throw new IllegalStateException("Expected END_OBJECT but was " + peek() + locationString());
        }
        this.stackSize--;
        this.pathNames[this.stackSize] = null;
        int[] iArr = this.pathIndices;
        int i = this.stackSize - 1;
        iArr[i] = iArr[i] + 1;
        this.peeked = 0;
    }

    public String getPath() {
        StringBuilder sbAppend = new StringBuilder().append('$');
        int i = this.stackSize;
        for (int i2 = 0; i2 < i; i2++) {
            switch (this.stack[i2]) {
                case 1:
                case 2:
                    sbAppend.append('[').append(this.pathIndices[i2]).append(']');
                    break;
                case 3:
                case 4:
                case 5:
                    sbAppend.append('.');
                    if (this.pathNames[i2] != null) {
                        sbAppend.append(this.pathNames[i2]);
                    }
                    break;
            }
        }
        return sbAppend.toString();
    }

    public boolean hasNext() throws IOException {
        int iDoPeek = this.peeked;
        if (iDoPeek == 0) {
            iDoPeek = doPeek();
        }
        return (iDoPeek == 2 || iDoPeek == 4) ? false : true;
    }

    public final boolean isLenient() {
        return this.lenient;
    }

    public boolean nextBoolean() throws IOException {
        int iDoPeek = this.peeked;
        if (iDoPeek == 0) {
            iDoPeek = doPeek();
        }
        if (iDoPeek == 5) {
            this.peeked = 0;
            int[] iArr = this.pathIndices;
            int i = this.stackSize - 1;
            iArr[i] = iArr[i] + 1;
            return true;
        }
        if (iDoPeek != 6) {
            throw new IllegalStateException("Expected a boolean but was " + peek() + locationString());
        }
        this.peeked = 0;
        int[] iArr2 = this.pathIndices;
        int i2 = this.stackSize - 1;
        iArr2[i2] = iArr2[i2] + 1;
        return false;
    }

    public double nextDouble() throws IOException {
        int iDoPeek = this.peeked;
        if (iDoPeek == 0) {
            iDoPeek = doPeek();
        }
        if (iDoPeek == 15) {
            this.peeked = 0;
            int[] iArr = this.pathIndices;
            int i = this.stackSize - 1;
            iArr[i] = iArr[i] + 1;
            return this.peekedLong;
        }
        if (iDoPeek == 16) {
            this.peekedString = new String(this.buffer, this.pos, this.peekedNumberLength);
            this.pos += this.peekedNumberLength;
        } else if (iDoPeek == 8 || iDoPeek == 9) {
            this.peekedString = nextQuotedValue(iDoPeek == 8 ? '\'' : '\"');
        } else if (iDoPeek == 10) {
            this.peekedString = nextUnquotedValue();
        } else if (iDoPeek != 11) {
            throw new IllegalStateException("Expected a double but was " + peek() + locationString());
        }
        this.peeked = 11;
        double d = Double.parseDouble(this.peekedString);
        if (!this.lenient && (Double.isNaN(d) || Double.isInfinite(d))) {
            throw new MalformedJsonException("JSON forbids NaN and infinities: " + d + locationString());
        }
        this.peekedString = null;
        this.peeked = 0;
        int[] iArr2 = this.pathIndices;
        int i2 = this.stackSize - 1;
        iArr2[i2] = iArr2[i2] + 1;
        return d;
    }

    public int nextInt() throws IOException {
        int iDoPeek = this.peeked;
        if (iDoPeek == 0) {
            iDoPeek = doPeek();
        }
        if (iDoPeek == 15) {
            int i = (int) this.peekedLong;
            if (this.peekedLong != i) {
                throw new NumberFormatException("Expected an int but was " + this.peekedLong + locationString());
            }
            this.peeked = 0;
            int[] iArr = this.pathIndices;
            int i2 = this.stackSize - 1;
            iArr[i2] = iArr[i2] + 1;
            return i;
        }
        if (iDoPeek == 16) {
            this.peekedString = new String(this.buffer, this.pos, this.peekedNumberLength);
            this.pos += this.peekedNumberLength;
        } else {
            if (iDoPeek != 8 && iDoPeek != 9 && iDoPeek != 10) {
                throw new IllegalStateException("Expected an int but was " + peek() + locationString());
            }
            if (iDoPeek == 10) {
                this.peekedString = nextUnquotedValue();
            } else {
                this.peekedString = nextQuotedValue(iDoPeek == 8 ? '\'' : '\"');
            }
            try {
                int i3 = Integer.parseInt(this.peekedString);
                this.peeked = 0;
                int[] iArr2 = this.pathIndices;
                int i4 = this.stackSize - 1;
                iArr2[i4] = iArr2[i4] + 1;
                return i3;
            } catch (NumberFormatException e) {
            }
        }
        this.peeked = 11;
        double d = Double.parseDouble(this.peekedString);
        int i5 = (int) d;
        if (i5 != d) {
            throw new NumberFormatException("Expected an int but was " + this.peekedString + locationString());
        }
        this.peekedString = null;
        this.peeked = 0;
        int[] iArr3 = this.pathIndices;
        int i6 = this.stackSize - 1;
        iArr3[i6] = iArr3[i6] + 1;
        return i5;
    }

    public long nextLong() throws IOException {
        int iDoPeek = this.peeked;
        if (iDoPeek == 0) {
            iDoPeek = doPeek();
        }
        if (iDoPeek == 15) {
            this.peeked = 0;
            int[] iArr = this.pathIndices;
            int i = this.stackSize - 1;
            iArr[i] = iArr[i] + 1;
            return this.peekedLong;
        }
        if (iDoPeek == 16) {
            this.peekedString = new String(this.buffer, this.pos, this.peekedNumberLength);
            this.pos += this.peekedNumberLength;
        } else {
            if (iDoPeek != 8 && iDoPeek != 9 && iDoPeek != 10) {
                throw new IllegalStateException("Expected a long but was " + peek() + locationString());
            }
            if (iDoPeek == 10) {
                this.peekedString = nextUnquotedValue();
            } else {
                this.peekedString = nextQuotedValue(iDoPeek == 8 ? '\'' : '\"');
            }
            try {
                long j = Long.parseLong(this.peekedString);
                this.peeked = 0;
                int[] iArr2 = this.pathIndices;
                int i2 = this.stackSize - 1;
                iArr2[i2] = iArr2[i2] + 1;
                return j;
            } catch (NumberFormatException e) {
            }
        }
        this.peeked = 11;
        double d = Double.parseDouble(this.peekedString);
        long j2 = (long) d;
        if (j2 != d) {
            throw new NumberFormatException("Expected a long but was " + this.peekedString + locationString());
        }
        this.peekedString = null;
        this.peeked = 0;
        int[] iArr3 = this.pathIndices;
        int i3 = this.stackSize - 1;
        iArr3[i3] = iArr3[i3] + 1;
        return j2;
    }

    public String nextName() throws IOException {
        String strNextQuotedValue;
        int iDoPeek = this.peeked;
        if (iDoPeek == 0) {
            iDoPeek = doPeek();
        }
        if (iDoPeek == 14) {
            strNextQuotedValue = nextUnquotedValue();
        } else if (iDoPeek == 12) {
            strNextQuotedValue = nextQuotedValue('\'');
        } else {
            if (iDoPeek != 13) {
                throw new IllegalStateException("Expected a name but was " + peek() + locationString());
            }
            strNextQuotedValue = nextQuotedValue('\"');
        }
        this.peeked = 0;
        this.pathNames[this.stackSize - 1] = strNextQuotedValue;
        return strNextQuotedValue;
    }

    public void nextNull() throws IOException {
        int iDoPeek = this.peeked;
        if (iDoPeek == 0) {
            iDoPeek = doPeek();
        }
        if (iDoPeek != 7) {
            throw new IllegalStateException("Expected null but was " + peek() + locationString());
        }
        this.peeked = 0;
        int[] iArr = this.pathIndices;
        int i = this.stackSize - 1;
        iArr[i] = iArr[i] + 1;
    }

    public String nextString() throws IOException {
        String str;
        int iDoPeek = this.peeked;
        if (iDoPeek == 0) {
            iDoPeek = doPeek();
        }
        if (iDoPeek == 10) {
            str = nextUnquotedValue();
        } else if (iDoPeek == 8) {
            str = nextQuotedValue('\'');
        } else if (iDoPeek == 9) {
            str = nextQuotedValue('\"');
        } else if (iDoPeek == 11) {
            str = this.peekedString;
            this.peekedString = null;
        } else if (iDoPeek == 15) {
            str = Long.toString(this.peekedLong);
        } else {
            if (iDoPeek != 16) {
                throw new IllegalStateException("Expected a string but was " + peek() + locationString());
            }
            str = new String(this.buffer, this.pos, this.peekedNumberLength);
            this.pos += this.peekedNumberLength;
        }
        this.peeked = 0;
        int[] iArr = this.pathIndices;
        int i = this.stackSize - 1;
        iArr[i] = iArr[i] + 1;
        return str;
    }

    public JsonToken peek() throws IOException {
        int iDoPeek = this.peeked;
        if (iDoPeek == 0) {
            iDoPeek = doPeek();
        }
        switch (iDoPeek) {
            case 1:
                return JsonToken.BEGIN_OBJECT;
            case 2:
                return JsonToken.END_OBJECT;
            case 3:
                return JsonToken.BEGIN_ARRAY;
            case 4:
                return JsonToken.END_ARRAY;
            case 5:
            case 6:
                return JsonToken.BOOLEAN;
            case 7:
                return JsonToken.NULL;
            case 8:
            case 9:
            case 10:
            case 11:
                return JsonToken.STRING;
            case 12:
            case 13:
            case 14:
                return JsonToken.NAME;
            case 15:
            case 16:
                return JsonToken.NUMBER;
            case 17:
                return JsonToken.END_DOCUMENT;
            default:
                throw new AssertionError();
        }
    }

    public final void setLenient(boolean z) {
        this.lenient = z;
    }

    public void skipValue() throws IOException {
        int i = 0;
        do {
            int iDoPeek = this.peeked;
            if (iDoPeek == 0) {
                iDoPeek = doPeek();
            }
            if (iDoPeek == 3) {
                push(1);
                i++;
            } else if (iDoPeek == 1) {
                push(3);
                i++;
            } else if (iDoPeek == 4 || iDoPeek == 2) {
                this.stackSize--;
                i--;
            } else if (iDoPeek == 14 || iDoPeek == 10) {
                skipUnquotedValue();
            } else if (iDoPeek == 8 || iDoPeek == 12) {
                skipQuotedValue('\'');
            } else if (iDoPeek == 9 || iDoPeek == 13) {
                skipQuotedValue('\"');
            } else if (iDoPeek == 16) {
                this.pos += this.peekedNumberLength;
            }
            this.peeked = 0;
        } while (i != 0);
        int[] iArr = this.pathIndices;
        int i2 = this.stackSize - 1;
        iArr[i2] = iArr[i2] + 1;
        this.pathNames[this.stackSize - 1] = "null";
    }

    public String toString() {
        return getClass().getSimpleName() + locationString();
    }
}
