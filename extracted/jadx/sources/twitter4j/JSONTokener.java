package twitter4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public class JSONTokener {
    private int character;
    private boolean eof;
    private int index;
    private int line;
    private char previous;
    private final Reader reader;
    private boolean usePrevious;

    public JSONTokener(InputStream inputStream) throws JSONException {
        this(new InputStreamReader(inputStream));
    }

    public JSONTokener(Reader reader) {
        this.reader = reader.markSupported() ? reader : new BufferedReader(reader);
        this.eof = false;
        this.usePrevious = false;
        this.previous = (char) 0;
        this.index = 0;
        this.character = 1;
        this.line = 1;
    }

    public JSONTokener(String str) {
        this(new StringReader(str));
    }

    public void back() throws JSONException {
        if (this.usePrevious || this.index <= 0) {
            throw new JSONException("Stepping back two steps is not supported");
        }
        this.index--;
        this.character--;
        this.usePrevious = true;
        this.eof = false;
    }

    public boolean end() {
        return this.eof && !this.usePrevious;
    }

    public boolean more() throws JSONException {
        next();
        if (end()) {
            return false;
        }
        back();
        return true;
    }

    public char next() throws JSONException {
        int i;
        if (this.usePrevious) {
            this.usePrevious = false;
            i = this.previous;
        } else {
            try {
                i = this.reader.read();
                if (i <= 0) {
                    this.eof = true;
                    i = 0;
                }
            } catch (IOException e) {
                throw new JSONException(e);
            }
        }
        this.index++;
        if (this.previous == '\r') {
            this.line++;
            this.character = i != 10 ? 1 : 0;
        } else if (i == 10) {
            this.line++;
            this.character = 0;
        } else {
            this.character++;
        }
        this.previous = (char) i;
        return this.previous;
    }

    public char next(char c) throws JSONException {
        char next = next();
        if (next != c) {
            throw syntaxError("Expected '" + c + "' and instead saw '" + next + "'");
        }
        return next;
    }

    public String next(int i) throws JSONException {
        if (i == 0) {
            return "";
        }
        char[] cArr = new char[i];
        for (int i2 = 0; i2 < i; i2++) {
            cArr[i2] = next();
            if (end()) {
                throw syntaxError("Substring bounds error");
            }
        }
        return new String(cArr);
    }

    public char nextClean() throws JSONException {
        char next;
        do {
            next = next();
            if (next == 0) {
                break;
            }
        } while (next <= ' ');
        return next;
    }

    public String nextString(char c) throws JSONException {
        StringBuilder sb = new StringBuilder();
        while (true) {
            char next = next();
            switch (next) {
                case 0:
                case '\n':
                case '\r':
                    throw syntaxError("Unterminated string");
                case '\\':
                    char next2 = next();
                    switch (next2) {
                        case '\"':
                        case '\'':
                        case '/':
                        case '\\':
                            sb.append(next2);
                            break;
                        case 'b':
                            sb.append('\b');
                            break;
                        case 'f':
                            sb.append('\f');
                            break;
                        case 'n':
                            sb.append('\n');
                            break;
                        case 'r':
                            sb.append('\r');
                            break;
                        case 't':
                            sb.append('\t');
                            break;
                        case 'u':
                            sb.append((char) Integer.parseInt(next(4), 16));
                            break;
                        default:
                            throw syntaxError("Illegal escape.");
                    }
                    break;
                default:
                    if (next == c) {
                        return sb.toString();
                    }
                    sb.append(next);
                    break;
            }
        }
    }

    public Object nextValue() throws JSONException {
        char cNextClean = nextClean();
        switch (cNextClean) {
            case '\"':
            case '\'':
                return nextString(cNextClean);
            case '[':
                back();
                return new JSONArray(this);
            case '{':
                back();
                return new JSONObject(this);
            default:
                StringBuilder sb = new StringBuilder();
                while (cNextClean >= ' ' && ",:]}/\\\"[{;=#".indexOf(cNextClean) < 0) {
                    sb.append(cNextClean);
                    cNextClean = next();
                }
                back();
                String strTrim = sb.toString().trim();
                if (strTrim.equals("")) {
                    throw syntaxError("Missing value");
                }
                return JSONObject.stringToValue(strTrim);
        }
    }

    public JSONException syntaxError(String str) {
        return new JSONException(String.valueOf(str) + toString());
    }

    public String toString() {
        return " at " + this.index + " [character " + this.character + " line " + this.line + "]";
    }
}
