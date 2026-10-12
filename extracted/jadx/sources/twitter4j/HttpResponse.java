package twitter4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;
import twitter4j.conf.ConfigurationContext;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public abstract class HttpResponse {
    private static final Logger logger = Logger.getLogger(HttpResponseImpl.class);
    protected final HttpClientConfiguration CONF;
    protected InputStream is;
    private JSONObject json;
    private JSONArray jsonArray;
    protected String responseAsString;
    protected int statusCode;
    private boolean streamConsumed;

    HttpResponse() {
        this.responseAsString = null;
        this.streamConsumed = false;
        this.json = null;
        this.jsonArray = null;
        this.CONF = ConfigurationContext.getInstance().getHttpClientConfiguration();
    }

    public HttpResponse(HttpClientConfiguration httpClientConfiguration) {
        this.responseAsString = null;
        this.streamConsumed = false;
        this.json = null;
        this.jsonArray = null;
        this.CONF = httpClientConfiguration;
    }

    private void disconnectForcibly() {
        try {
            disconnect();
        } catch (Exception e) {
        }
    }

    public JSONArray asJSONArray() throws TwitterException {
        if (this.jsonArray == null) {
            Reader readerAsReader = null;
            try {
                try {
                    if (this.responseAsString == null) {
                        readerAsReader = asReader();
                        this.jsonArray = new JSONArray(new JSONTokener(readerAsReader));
                    } else {
                        this.jsonArray = new JSONArray(this.responseAsString);
                    }
                    if (this.CONF.isPrettyDebugEnabled()) {
                        logger.debug(this.jsonArray.toString(1));
                    } else {
                        logger.debug(this.responseAsString != null ? this.responseAsString : this.jsonArray.toString());
                    }
                    if (readerAsReader != null) {
                        try {
                            readerAsReader.close();
                        } catch (IOException e) {
                        }
                    }
                    disconnectForcibly();
                } catch (JSONException e2) {
                    if (logger.isDebugEnabled()) {
                        throw new TwitterException(String.valueOf(e2.getMessage()) + ":" + this.responseAsString, e2);
                    }
                    throw new TwitterException(e2.getMessage(), e2);
                }
            } catch (Throwable th) {
                if (0 != 0) {
                    try {
                        readerAsReader.close();
                    } catch (IOException e3) {
                    }
                }
                disconnectForcibly();
                throw th;
            }
        }
        return this.jsonArray;
    }

    public JSONObject asJSONObject() throws TwitterException {
        if (this.json == null) {
            Reader readerAsReader = null;
            try {
                try {
                    if (this.responseAsString == null) {
                        readerAsReader = asReader();
                        this.json = new JSONObject(new JSONTokener(readerAsReader));
                    } else {
                        this.json = new JSONObject(this.responseAsString);
                    }
                    if (this.CONF.isPrettyDebugEnabled()) {
                        logger.debug(this.json.toString(1));
                    } else {
                        logger.debug(this.responseAsString != null ? this.responseAsString : this.json.toString());
                    }
                    if (readerAsReader != null) {
                        try {
                            readerAsReader.close();
                        } catch (IOException e) {
                        }
                    }
                    disconnectForcibly();
                } catch (Throwable th) {
                    if (0 != 0) {
                        try {
                            readerAsReader.close();
                        } catch (IOException e2) {
                        }
                    }
                    disconnectForcibly();
                    throw th;
                }
            } catch (JSONException e3) {
                if (this.responseAsString == null) {
                    throw new TwitterException(e3.getMessage(), e3);
                }
                throw new TwitterException(String.valueOf(e3.getMessage()) + ":" + this.responseAsString, e3);
            }
        }
        return this.json;
    }

    public Reader asReader() {
        try {
            return new BufferedReader(new InputStreamReader(this.is, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            return new InputStreamReader(this.is);
        }
    }

    public InputStream asStream() {
        if (this.streamConsumed) {
            throw new IllegalStateException("Stream has already been consumed.");
        }
        return this.is;
    }

    public String asString() throws Throwable {
        if (this.responseAsString == null) {
            BufferedReader bufferedReader = null;
            InputStream inputStreamAsStream = null;
            try {
                try {
                    inputStreamAsStream = asStream();
                    if (inputStreamAsStream == null) {
                        if (inputStreamAsStream != null) {
                            try {
                                inputStreamAsStream.close();
                            } catch (IOException e) {
                            }
                        }
                        if (0 != 0) {
                            try {
                                bufferedReader.close();
                            } catch (IOException e2) {
                            }
                        }
                        disconnectForcibly();
                        return null;
                    }
                    BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(inputStreamAsStream, "UTF-8"));
                    try {
                        StringBuilder sb = new StringBuilder();
                        while (true) {
                            String line = bufferedReader2.readLine();
                            if (line == null) {
                                break;
                            }
                            sb.append(line).append("\n");
                        }
                        this.responseAsString = sb.toString();
                        logger.debug(this.responseAsString);
                        inputStreamAsStream.close();
                        this.streamConsumed = true;
                        if (inputStreamAsStream != null) {
                            try {
                                inputStreamAsStream.close();
                            } catch (IOException e3) {
                            }
                        }
                        if (bufferedReader2 != null) {
                            try {
                                bufferedReader2.close();
                            } catch (IOException e4) {
                            }
                        }
                        disconnectForcibly();
                    } catch (IOException e5) {
                        e = e5;
                        bufferedReader = bufferedReader2;
                    } catch (Throwable th) {
                        th = th;
                        bufferedReader = bufferedReader2;
                        if (inputStreamAsStream != null) {
                            try {
                                inputStreamAsStream.close();
                            } catch (IOException e6) {
                            }
                        }
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (IOException e7) {
                            }
                        }
                        disconnectForcibly();
                        throw th;
                    }
                } catch (IOException e8) {
                    e = e8;
                }
            } catch (Throwable th2) {
                th = th2;
            }
            throw new TwitterException(e.getMessage(), e);
        }
        return this.responseAsString;
    }

    public abstract void disconnect() throws IOException;

    public abstract String getResponseHeader(String str);

    public abstract Map<String, List<String>> getResponseHeaderFields();

    public int getStatusCode() {
        return this.statusCode;
    }

    public String toString() {
        return "HttpResponse{statusCode=" + this.statusCode + ", responseAsString='" + this.responseAsString + "', is=" + this.is + ", streamConsumed=" + this.streamConsumed + '}';
    }
}
