package com.tapjoy.internal;

import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public final class bo extends bn {
    public static final bn.a a = new bn.a() { // from class: com.tapjoy.internal.bo.1
        @Override // com.tapjoy.internal.bn.a
        public final bn a(Reader reader) {
            return new bo(reader);
        }

        @Override // com.tapjoy.internal.bn.a
        public final bn a(String str) {
            return new bo(new StringReader(str));
        }
    };
    private final Reader c;
    private bs k;
    private String l;
    private String m;
    private int n;
    private int o;
    private boolean p;
    private final cj b = new cj();
    private boolean d = false;
    private final char[] e = new char[1024];
    private int f = 0;
    private int g = 0;
    private int h = 1;
    private int i = 1;
    private final List j = new ArrayList();

    public bo(Reader reader) {
        a(bq.EMPTY_DOCUMENT);
        this.p = false;
        if (reader == null) {
            throw new NullPointerException("in == null");
        }
        this.c = reader;
    }

    private void A() {
        char c;
        do {
            if (this.f >= this.g && !a(1)) {
                return;
            }
            char[] cArr = this.e;
            int i = this.f;
            this.f = i + 1;
            c = cArr[i];
            if (c == '\r') {
                return;
            }
        } while (c != '\n');
    }

    private bs a(boolean z) throws IOException {
        if (z) {
            b(bq.NONEMPTY_ARRAY);
        } else {
            switch (y()) {
                case 44:
                    break;
                case 59:
                    z();
                    break;
                case 93:
                    u();
                    bs bsVar = bs.END_ARRAY;
                    this.k = bsVar;
                    return bsVar;
                default:
                    throw d("Unterminated array");
            }
        }
        switch (y()) {
            case 44:
            case 59:
                break;
            case 93:
                if (z) {
                    u();
                    bs bsVar2 = bs.END_ARRAY;
                    this.k = bsVar2;
                    return bsVar2;
                }
                break;
            default:
                this.f--;
                return v();
        }
        z();
        this.f--;
        this.m = "null";
        bs bsVar3 = bs.NULL;
        this.k = bsVar3;
        return bsVar3;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private String a(char c) throws IOException {
        StringBuilder sb = null;
        do {
            int i = this.f;
            while (this.f < this.g) {
                char[] cArr = this.e;
                int i2 = this.f;
                this.f = i2 + 1;
                char c2 = cArr[i2];
                if (c2 == c) {
                    if (this.p) {
                        return "skipped!";
                    }
                    if (sb == null) {
                        return this.b.a(this.e, i, (this.f - i) - 1);
                    }
                    sb.append(this.e, i, (this.f - i) - 1);
                    return sb.toString();
                }
                if (c2 == '\\') {
                    if (sb == null) {
                        sb = new StringBuilder();
                    }
                    sb.append(this.e, i, (this.f - i) - 1);
                    if (this.f == this.g && !a(1)) {
                        throw d("Unterminated escape sequence");
                    }
                    char[] cArr2 = this.e;
                    int i3 = this.f;
                    this.f = i3 + 1;
                    char c3 = cArr2[i3];
                    switch (c3) {
                        case 'b':
                            c3 = '\b';
                            sb.append(c3);
                            i = this.f;
                            break;
                        case 'f':
                            c3 = '\f';
                            sb.append(c3);
                            i = this.f;
                            break;
                        case 'n':
                            c3 = '\n';
                            sb.append(c3);
                            i = this.f;
                            break;
                        case 'r':
                            c3 = '\r';
                            sb.append(c3);
                            i = this.f;
                            break;
                        case 't':
                            c3 = '\t';
                            sb.append(c3);
                            i = this.f;
                            break;
                        case 'u':
                            if (this.f + 4 > this.g && !a(4)) {
                                throw d("Unterminated escape sequence");
                            }
                            String strA = this.b.a(this.e, this.f, 4);
                            this.f += 4;
                            c3 = (char) Integer.parseInt(strA, 16);
                            sb.append(c3);
                            i = this.f;
                            break;
                            break;
                        default:
                            sb.append(c3);
                            i = this.f;
                            break;
                    }
                }
            }
            if (sb == null) {
                sb = new StringBuilder();
            }
            sb.append(this.e, i, this.f - i);
        } while (a(1));
        throw d("Unterminated string");
    }

    private void a(bq bqVar) {
        this.j.add(bqVar);
    }

    private void a(bs bsVar) throws IOException {
        k();
        if (this.k != bsVar) {
            throw new IllegalStateException("Expected " + bsVar + " but was " + k());
        }
        t();
    }

    private boolean a(int i) throws IOException {
        for (int i2 = 0; i2 < this.f; i2++) {
            if (this.e[i2] == '\n') {
                this.h++;
                this.i = 1;
            } else {
                this.i++;
            }
        }
        if (this.g != this.f) {
            this.g -= this.f;
            System.arraycopy(this.e, this.f, this.e, 0, this.g);
        } else {
            this.g = 0;
        }
        this.f = 0;
        do {
            int i3 = this.c.read(this.e, this.g, this.e.length - this.g);
            if (i3 == -1) {
                return false;
            }
            this.g = i3 + this.g;
            if (this.h == 1 && this.i == 1 && this.g > 0 && this.e[0] == 65279) {
                this.f++;
                this.i--;
            }
        } while (this.g < i);
        return true;
    }

    private bs b(boolean z) throws IOException {
        if (z) {
            switch (y()) {
                case 125:
                    u();
                    bs bsVar = bs.END_OBJECT;
                    this.k = bsVar;
                    return bsVar;
                default:
                    this.f--;
                    break;
            }
        } else {
            switch (y()) {
                case 44:
                case 59:
                    break;
                case 125:
                    u();
                    bs bsVar2 = bs.END_OBJECT;
                    this.k = bsVar2;
                    return bsVar2;
                default:
                    throw d("Unterminated object");
            }
        }
        int iY = y();
        switch (iY) {
            case 39:
                z();
            case 34:
                this.l = a((char) iY);
                b(bq.DANGLING_NAME);
                bs bsVar3 = bs.NAME;
                this.k = bsVar3;
                return bsVar3;
            default:
                z();
                this.f--;
                this.l = c(false);
                if (this.l.length() == 0) {
                    throw d("Expected name");
                }
                b(bq.DANGLING_NAME);
                bs bsVar4 = bs.NAME;
                this.k = bsVar4;
                return bsVar4;
        }
    }

    private void b(bq bqVar) {
        this.j.set(this.j.size() - 1, bqVar);
    }

    private String c(boolean z) throws IOException {
        String string = null;
        this.n = -1;
        this.o = 0;
        int i = 0;
        StringBuilder sb = null;
        while (true) {
            if (this.f + i < this.g) {
                switch (this.e[this.f + i]) {
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
                        z();
                        break;
                    default:
                        i++;
                        continue;
                }
            } else if (i >= this.e.length) {
                if (sb == null) {
                    sb = new StringBuilder();
                }
                sb.append(this.e, this.f, i);
                this.o += i;
                this.f = i + this.f;
                if (a(1)) {
                    i = 0;
                } else {
                    i = 0;
                }
            } else if (!a(i + 1)) {
                this.e[this.g] = 0;
            }
        }
        if (z && sb == null) {
            this.n = this.f;
        } else if (this.p) {
            string = "skipped!";
        } else if (sb == null) {
            string = this.b.a(this.e, this.f, i);
        } else {
            sb.append(this.e, this.f, i);
            string = sb.toString();
        }
        this.o += i;
        this.f += i;
        return string;
    }

    private IOException d(String str) throws bu {
        throw new bu(str + " at line " + w() + " column " + x());
    }

    private bs t() throws IOException {
        k();
        bs bsVar = this.k;
        this.k = null;
        this.m = null;
        this.l = null;
        return bsVar;
    }

    private bq u() {
        return (bq) this.j.remove(this.j.size() - 1);
    }

    /* JADX WARN: Code duplicated, block: B:115:0x021c  */
    /* JADX WARN: Code duplicated, block: B:116:0x0220  */
    private bs v() throws IOException {
        bs bsVar;
        int i;
        int i2;
        char c;
        int iY = y();
        switch (iY) {
            case 34:
                break;
            case 39:
                z();
                break;
            case 91:
                a(bq.EMPTY_ARRAY);
                bs bsVar2 = bs.BEGIN_ARRAY;
                this.k = bsVar2;
                return bsVar2;
            case 123:
                a(bq.EMPTY_OBJECT);
                bs bsVar3 = bs.BEGIN_OBJECT;
                this.k = bsVar3;
                return bsVar3;
            default:
                this.f--;
                this.m = c(true);
                if (this.o == 0) {
                    throw d("Expected literal value");
                }
                if (this.n == -1) {
                    bsVar = bs.STRING;
                } else if (this.o == 4 && (('n' == this.e[this.n] || 'N' == this.e[this.n]) && (('u' == this.e[this.n + 1] || 'U' == this.e[this.n + 1]) && (('l' == this.e[this.n + 2] || 'L' == this.e[this.n + 2]) && ('l' == this.e[this.n + 3] || 'L' == this.e[this.n + 3]))))) {
                    this.m = "null";
                    bsVar = bs.NULL;
                } else if (this.o == 4 && (('t' == this.e[this.n] || 'T' == this.e[this.n]) && (('r' == this.e[this.n + 1] || 'R' == this.e[this.n + 1]) && (('u' == this.e[this.n + 2] || 'U' == this.e[this.n + 2]) && ('e' == this.e[this.n + 3] || 'E' == this.e[this.n + 3]))))) {
                    this.m = "true";
                    bsVar = bs.BOOLEAN;
                } else if (this.o == 5 && (('f' == this.e[this.n] || 'F' == this.e[this.n]) && (('a' == this.e[this.n + 1] || 'A' == this.e[this.n + 1]) && (('l' == this.e[this.n + 2] || 'L' == this.e[this.n + 2]) && (('s' == this.e[this.n + 3] || 'S' == this.e[this.n + 3]) && ('e' == this.e[this.n + 4] || 'E' == this.e[this.n + 4])))))) {
                    this.m = "false";
                    bsVar = bs.BOOLEAN;
                } else {
                    this.m = this.b.a(this.e, this.n, this.o);
                    char[] cArr = this.e;
                    int i3 = this.n;
                    int i4 = this.o;
                    char c2 = cArr[i3];
                    if (c2 == '-') {
                        i = i3 + 1;
                        c2 = cArr[i];
                    } else {
                        i = i3;
                    }
                    if (c2 == '0') {
                        i2 = i + 1;
                        c = cArr[i2];
                    } else if (c2 < '1' || c2 > '9') {
                        bsVar = bs.STRING;
                    } else {
                        i2 = i + 1;
                        c = cArr[i2];
                        while (c >= '0' && c <= '9') {
                            i2++;
                            c = cArr[i2];
                        }
                    }
                    if (c == '.') {
                        i2++;
                        c = cArr[i2];
                        while (c >= '0' && c <= '9') {
                            i2++;
                            c = cArr[i2];
                        }
                    }
                    if (c == 'e' || c == 'E') {
                        int i5 = i2 + 1;
                        char c3 = cArr[i5];
                        if (c3 == '+' || c3 == '-') {
                            i5++;
                            c3 = cArr[i5];
                        }
                        if (c3 < '0' || c3 > '9') {
                            bsVar = bs.STRING;
                        } else {
                            i2 = i5 + 1;
                            char c4 = cArr[i2];
                            while (c4 >= '0' && c4 <= '9') {
                                i2++;
                                c4 = cArr[i2];
                            }
                            if (i2 == i3 + i4) {
                                bsVar = bs.NUMBER;
                            } else {
                                bsVar = bs.STRING;
                            }
                        }
                    } else if (i2 == i3 + i4) {
                        bsVar = bs.NUMBER;
                    } else {
                        bsVar = bs.STRING;
                    }
                }
                this.k = bsVar;
                if (this.k == bs.STRING) {
                    z();
                }
                return this.k;
        }
        this.m = a((char) iY);
        bs bsVar4 = bs.STRING;
        this.k = bsVar4;
        return bsVar4;
    }

    private int w() {
        int i = this.h;
        for (int i2 = 0; i2 < this.f; i2++) {
            if (this.e[i2] == '\n') {
                i++;
            }
        }
        return i;
    }

    private int x() {
        int i = this.i;
        for (int i2 = 0; i2 < this.f; i2++) {
            i = this.e[i2] == '\n' ? 1 : i + 1;
        }
        return i;
    }

    private int y() throws IOException {
        boolean z;
        while (true) {
            if (this.f >= this.g && !a(1)) {
                throw new EOFException("End of input");
            }
            char[] cArr = this.e;
            int i = this.f;
            this.f = i + 1;
            char c = cArr[i];
            switch (c) {
                case '\t':
                case '\n':
                case '\r':
                case ' ':
                    break;
                case '#':
                    z();
                    A();
                    break;
                case '/':
                    if (this.f != this.g || a(1)) {
                        z();
                        switch (this.e[this.f]) {
                            case '*':
                                this.f++;
                                while (true) {
                                    if (this.f + "*/".length() <= this.g || a("*/".length())) {
                                        int i2 = 0;
                                        while (true) {
                                            if (i2 >= "*/".length()) {
                                                z = true;
                                            } else if (this.e[this.f + i2] == "*/".charAt(i2)) {
                                                i2++;
                                            } else {
                                                this.f++;
                                            }
                                        }
                                    } else {
                                        z = false;
                                    }
                                }
                                if (!z) {
                                    throw d("Unterminated comment");
                                }
                                this.f += 2;
                                break;
                                break;
                            case '/':
                                this.f++;
                                A();
                                break;
                            default:
                                break;
                        }
                    }
                    return c;
                default:
                    return c;
            }
        }
    }

    private void z() throws IOException {
        if (!this.d) {
            throw d("Use JsonReader.setLenient(true) to accept malformed JSON");
        }
    }

    @Override // com.tapjoy.internal.bp, java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        this.m = null;
        this.k = null;
        this.j.clear();
        this.j.add(bq.CLOSED);
        this.c.close();
    }

    @Override // com.tapjoy.internal.bp
    public final void f() throws IOException {
        a(bs.BEGIN_ARRAY);
    }

    @Override // com.tapjoy.internal.bp
    public final void g() throws IOException {
        a(bs.END_ARRAY);
    }

    @Override // com.tapjoy.internal.bp
    public final void h() throws IOException {
        a(bs.BEGIN_OBJECT);
    }

    @Override // com.tapjoy.internal.bp
    public final void i() throws IOException {
        a(bs.END_OBJECT);
    }

    @Override // com.tapjoy.internal.bp
    public final boolean j() throws IOException {
        k();
        return (this.k == bs.END_OBJECT || this.k == bs.END_ARRAY) ? false : true;
    }

    @Override // com.tapjoy.internal.bp
    public final bs k() throws IOException {
        if (this.k != null) {
            return this.k;
        }
        switch ((bq) this.j.get(this.j.size() - 1)) {
            case EMPTY_DOCUMENT:
                b(bq.NONEMPTY_DOCUMENT);
                bs bsVarV = v();
                if (this.d || this.k == bs.BEGIN_ARRAY || this.k == bs.BEGIN_OBJECT) {
                    return bsVarV;
                }
                throw new IOException("Expected JSON document to start with '[' or '{' but was " + this.k);
            case EMPTY_ARRAY:
                return a(true);
            case NONEMPTY_ARRAY:
                return a(false);
            case EMPTY_OBJECT:
                return b(true);
            case DANGLING_NAME:
                switch (y()) {
                    case 58:
                        break;
                    case 59:
                    case 60:
                    default:
                        throw d("Expected ':'");
                    case 61:
                        z();
                        if ((this.f < this.g || a(1)) && this.e[this.f] == '>') {
                            this.f++;
                        }
                        break;
                }
                b(bq.NONEMPTY_OBJECT);
                return v();
            case NONEMPTY_OBJECT:
                return b(false);
            case NONEMPTY_DOCUMENT:
                try {
                    bs bsVarV2 = v();
                    if (this.d) {
                        return bsVarV2;
                    }
                    throw d("Expected EOF");
                } catch (EOFException e) {
                    bs bsVar = bs.END_DOCUMENT;
                    this.k = bsVar;
                    return bsVar;
                }
            case CLOSED:
                throw new IllegalStateException("JsonReader is closed");
            default:
                throw new AssertionError();
        }
    }

    @Override // com.tapjoy.internal.bp
    public final String l() throws IOException {
        k();
        if (this.k != bs.NAME) {
            throw new IllegalStateException("Expected a name but was " + k());
        }
        String str = this.l;
        t();
        return str;
    }

    @Override // com.tapjoy.internal.bp
    public final String m() throws IOException {
        k();
        if (this.k != bs.STRING && this.k != bs.NUMBER) {
            throw new IllegalStateException("Expected a string but was " + k());
        }
        String str = this.m;
        t();
        return str;
    }

    @Override // com.tapjoy.internal.bp
    public final boolean n() throws IOException {
        k();
        if (this.k != bs.BOOLEAN) {
            throw new IllegalStateException("Expected a boolean but was " + this.k);
        }
        boolean z = this.m == "true";
        t();
        return z;
    }

    @Override // com.tapjoy.internal.bp
    public final void o() throws IOException {
        k();
        if (this.k != bs.NULL) {
            throw new IllegalStateException("Expected null but was " + this.k);
        }
        t();
    }

    @Override // com.tapjoy.internal.bp
    public final double p() throws IOException {
        k();
        if (this.k != bs.STRING && this.k != bs.NUMBER) {
            throw new IllegalStateException("Expected a double but was " + this.k);
        }
        double d = Double.parseDouble(this.m);
        t();
        return d;
    }

    @Override // com.tapjoy.internal.bp
    public final long q() throws IOException {
        long j;
        k();
        if (this.k != bs.STRING && this.k != bs.NUMBER) {
            throw new IllegalStateException("Expected a long but was " + this.k);
        }
        try {
            j = Long.parseLong(this.m);
        } catch (NumberFormatException e) {
            double d = Double.parseDouble(this.m);
            j = (long) d;
            if (j != d) {
                throw new NumberFormatException(this.m);
            }
        }
        t();
        return j;
    }

    @Override // com.tapjoy.internal.bp
    public final int r() throws IOException {
        int i;
        k();
        if (this.k != bs.STRING && this.k != bs.NUMBER) {
            throw new IllegalStateException("Expected an int but was " + this.k);
        }
        try {
            i = Integer.parseInt(this.m);
        } catch (NumberFormatException e) {
            double d = Double.parseDouble(this.m);
            i = (int) d;
            if (i != d) {
                throw new NumberFormatException(this.m);
            }
        }
        t();
        return i;
    }

    @Override // com.tapjoy.internal.bp
    public final void s() throws IOException {
        k();
        if (this.k == bs.END_ARRAY || this.k == bs.END_OBJECT) {
            throw new IllegalStateException("Expected a value but was " + this.k);
        }
        this.p = true;
        int i = 0;
        do {
            try {
                bs bsVarT = t();
                if (bsVarT == bs.BEGIN_ARRAY || bsVarT == bs.BEGIN_OBJECT) {
                    i++;
                } else if (bsVarT == bs.END_ARRAY || bsVarT == bs.END_OBJECT) {
                    i--;
                }
            } finally {
                this.p = false;
            }
        } while (i != 0);
    }

    public final String toString() {
        StringBuilder sbAppend = new StringBuilder().append(getClass().getSimpleName()).append(" near ");
        StringBuilder sb = new StringBuilder();
        int iMin = Math.min(this.f, 20);
        sb.append(this.e, this.f - iMin, iMin);
        sb.append(this.e, this.f, Math.min(this.g - this.f, 20));
        return sbAppend.append((Object) sb).toString();
    }
}
