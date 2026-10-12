package com.tapjoy.internal;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public abstract class ej {
    public static final ej c = new ej(eg.VARINT, Boolean.class) { // from class: com.tapjoy.internal.ej.1
        @Override // com.tapjoy.internal.ej
        public final /* bridge */ /* synthetic */ int a(Object obj) {
            return 1;
        }

        @Override // com.tapjoy.internal.ej
        public final /* synthetic */ Object a(ek ekVar) throws IOException {
            int iD = ekVar.d();
            if (iD == 0) {
                return Boolean.FALSE;
            }
            if (iD == 1) {
                return Boolean.TRUE;
            }
            throw new IOException(String.format("Invalid boolean value 0x%02x", Integer.valueOf(iD)));
        }

        @Override // com.tapjoy.internal.ej
        public final /* synthetic */ void a(el elVar, Object obj) {
            elVar.c(((Boolean) obj).booleanValue() ? 1 : 0);
        }
    };
    public static final ej d = new ej(eg.VARINT, Integer.class) { // from class: com.tapjoy.internal.ej.7
        @Override // com.tapjoy.internal.ej
        public final /* synthetic */ int a(Object obj) {
            int iIntValue = ((Integer) obj).intValue();
            if (iIntValue >= 0) {
                return el.a(iIntValue);
            }
            return 10;
        }

        @Override // com.tapjoy.internal.ej
        public final /* synthetic */ Object a(ek ekVar) {
            return Integer.valueOf(ekVar.d());
        }

        @Override // com.tapjoy.internal.ej
        public final /* synthetic */ void a(el elVar, Object obj) {
            int iIntValue = ((Integer) obj).intValue();
            if (iIntValue >= 0) {
                elVar.c(iIntValue);
            } else {
                elVar.c(iIntValue);
            }
        }
    };
    public static final ej e = new ej(eg.VARINT, Integer.class) { // from class: com.tapjoy.internal.ej.8
        @Override // com.tapjoy.internal.ej
        public final /* synthetic */ int a(Object obj) {
            return el.a(((Integer) obj).intValue());
        }

        @Override // com.tapjoy.internal.ej
        public final /* synthetic */ Object a(ek ekVar) {
            return Integer.valueOf(ekVar.d());
        }

        @Override // com.tapjoy.internal.ej
        public final /* synthetic */ void a(el elVar, Object obj) {
            elVar.c(((Integer) obj).intValue());
        }
    };
    public static final ej f = new ej(eg.VARINT, Integer.class) { // from class: com.tapjoy.internal.ej.9
        @Override // com.tapjoy.internal.ej
        public final /* synthetic */ int a(Object obj) {
            return el.a(el.b(((Integer) obj).intValue()));
        }

        @Override // com.tapjoy.internal.ej
        public final /* synthetic */ Object a(ek ekVar) {
            int iD = ekVar.d();
            return Integer.valueOf((-(iD & 1)) ^ (iD >>> 1));
        }

        @Override // com.tapjoy.internal.ej
        public final /* synthetic */ void a(el elVar, Object obj) {
            elVar.c(el.b(((Integer) obj).intValue()));
        }
    };
    public static final ej g;
    public static final ej h;
    public static final ej i;
    public static final ej j;
    public static final ej k;
    public static final ej l;
    public static final ej m;
    public static final ej n;
    public static final ej o;
    public static final ej p;
    public static final ej q;
    final Class a;
    ej b;
    private final eg r;

    public static final class a extends IllegalArgumentException {
        public final int a;

        a(int i, Class cls) {
            super("Unknown enum tag " + i + " for " + cls.getCanonicalName());
            this.a = i;
        }
    }

    static {
        ej ejVar = new ej(eg.FIXED32, Integer.class) { // from class: com.tapjoy.internal.ej.10
            @Override // com.tapjoy.internal.ej
            public final /* bridge */ /* synthetic */ int a(Object obj) {
                return 4;
            }

            @Override // com.tapjoy.internal.ej
            public final /* synthetic */ Object a(ek ekVar) {
                return Integer.valueOf(ekVar.f());
            }

            @Override // com.tapjoy.internal.ej
            public final /* synthetic */ void a(el elVar, Object obj) {
                elVar.d(((Integer) obj).intValue());
            }
        };
        g = ejVar;
        h = ejVar;
        i = new ej(eg.VARINT, Long.class) { // from class: com.tapjoy.internal.ej.11
            @Override // com.tapjoy.internal.ej
            public final /* synthetic */ int a(Object obj) {
                return el.a(((Long) obj).longValue());
            }

            @Override // com.tapjoy.internal.ej
            public final /* synthetic */ Object a(ek ekVar) {
                return Long.valueOf(ekVar.e());
            }

            @Override // com.tapjoy.internal.ej
            public final /* synthetic */ void a(el elVar, Object obj) {
                elVar.c(((Long) obj).longValue());
            }
        };
        j = new ej(eg.VARINT, Long.class) { // from class: com.tapjoy.internal.ej.12
            @Override // com.tapjoy.internal.ej
            public final /* synthetic */ int a(Object obj) {
                return el.a(((Long) obj).longValue());
            }

            @Override // com.tapjoy.internal.ej
            public final /* synthetic */ Object a(ek ekVar) {
                return Long.valueOf(ekVar.e());
            }

            @Override // com.tapjoy.internal.ej
            public final /* synthetic */ void a(el elVar, Object obj) {
                elVar.c(((Long) obj).longValue());
            }
        };
        k = new ej(eg.VARINT, Long.class) { // from class: com.tapjoy.internal.ej.13
            @Override // com.tapjoy.internal.ej
            public final /* synthetic */ int a(Object obj) {
                return el.a(el.b(((Long) obj).longValue()));
            }

            @Override // com.tapjoy.internal.ej
            public final /* synthetic */ Object a(ek ekVar) {
                long jE = ekVar.e();
                return Long.valueOf((-(jE & 1)) ^ (jE >>> 1));
            }

            @Override // com.tapjoy.internal.ej
            public final /* synthetic */ void a(el elVar, Object obj) {
                elVar.c(el.b(((Long) obj).longValue()));
            }
        };
        ej ejVar2 = new ej(eg.FIXED64, Long.class) { // from class: com.tapjoy.internal.ej.14
            @Override // com.tapjoy.internal.ej
            public final /* bridge */ /* synthetic */ int a(Object obj) {
                return 8;
            }

            @Override // com.tapjoy.internal.ej
            public final /* synthetic */ Object a(ek ekVar) {
                return Long.valueOf(ekVar.g());
            }

            @Override // com.tapjoy.internal.ej
            public final /* synthetic */ void a(el elVar, Object obj) {
                elVar.d(((Long) obj).longValue());
            }
        };
        l = ejVar2;
        m = ejVar2;
        n = new ej(eg.FIXED32, Float.class) { // from class: com.tapjoy.internal.ej.2
            @Override // com.tapjoy.internal.ej
            public final /* bridge */ /* synthetic */ int a(Object obj) {
                return 4;
            }

            @Override // com.tapjoy.internal.ej
            public final /* synthetic */ Object a(ek ekVar) {
                return Float.valueOf(Float.intBitsToFloat(ekVar.f()));
            }

            @Override // com.tapjoy.internal.ej
            public final /* synthetic */ void a(el elVar, Object obj) {
                elVar.d(Float.floatToIntBits(((Float) obj).floatValue()));
            }
        };
        o = new ej(eg.FIXED64, Double.class) { // from class: com.tapjoy.internal.ej.3
            @Override // com.tapjoy.internal.ej
            public final /* bridge */ /* synthetic */ int a(Object obj) {
                return 8;
            }

            @Override // com.tapjoy.internal.ej
            public final /* synthetic */ Object a(ek ekVar) {
                return Double.valueOf(Double.longBitsToDouble(ekVar.g()));
            }

            @Override // com.tapjoy.internal.ej
            public final /* synthetic */ void a(el elVar, Object obj) {
                elVar.d(Double.doubleToLongBits(((Double) obj).doubleValue()));
            }
        };
        p = new ej(eg.LENGTH_DELIMITED, String.class) { // from class: com.tapjoy.internal.ej.4
            /* JADX WARN: Code duplicated, block: B:23:0x004b  */
            @Override // com.tapjoy.internal.ej
            public final /* synthetic */ int a(Object obj) {
                int i2;
                String str = (String) obj;
                int length = str.length();
                int i3 = 0;
                int i4 = 0;
                while (i3 < length) {
                    char cCharAt = str.charAt(i3);
                    if (cCharAt < 128) {
                        i2 = i4 + 1;
                    } else if (cCharAt < 2048) {
                        i2 = i4 + 2;
                    } else if (cCharAt < 55296 || cCharAt > 57343) {
                        i2 = i4 + 3;
                    } else if (cCharAt > 56319 || i3 + 1 >= length || str.charAt(i3 + 1) < 56320 || str.charAt(i3 + 1) > 57343) {
                        i2 = i4 + 1;
                    } else {
                        i2 = i4 + 4;
                        i3++;
                    }
                    i3++;
                    i4 = i2;
                }
                return i4;
            }

            @Override // com.tapjoy.internal.ej
            public final /* synthetic */ Object a(ek ekVar) {
                return ekVar.a.c(ekVar.h());
            }

            @Override // com.tapjoy.internal.ej
            public final /* synthetic */ void a(el elVar, Object obj) {
                elVar.a.b((String) obj);
            }
        };
        q = new ej(eg.LENGTH_DELIMITED, iu.class) { // from class: com.tapjoy.internal.ej.5
            @Override // com.tapjoy.internal.ej
            public final /* synthetic */ int a(Object obj) {
                return ((iu) obj).c();
            }

            @Override // com.tapjoy.internal.ej
            public final /* synthetic */ Object a(ek ekVar) {
                return ekVar.a.b(ekVar.h());
            }

            @Override // com.tapjoy.internal.ej
            public final /* bridge */ /* synthetic */ void a(el elVar, Object obj) {
                elVar.a((iu) obj);
            }
        };
    }

    public ej(eg egVar, Class cls) {
        this.r = egVar;
        this.a = cls;
    }

    private Object a(it itVar) {
        ei.a(itVar, "source == null");
        return a(new ek(itVar));
    }

    private void a(is isVar, Object obj) {
        ei.a(obj, "value == null");
        ei.a(isVar, "sink == null");
        a(new el(isVar), obj);
    }

    public static String c(Object obj) {
        return obj.toString();
    }

    public int a(int i2, Object obj) {
        int iA = a(obj);
        if (this.r == eg.LENGTH_DELIMITED) {
            iA += el.a(iA);
        }
        return iA + el.a(el.a(i2, eg.VARINT));
    }

    public abstract int a(Object obj);

    public final ej a() {
        ej ejVar = this.b;
        if (ejVar != null) {
            return ejVar;
        }
        ej ejVar2 = new ej(this.r, List.class) { // from class: com.tapjoy.internal.ej.6
            @Override // com.tapjoy.internal.ej
            public final /* synthetic */ int a(int i2, Object obj) {
                List list = (List) obj;
                int size = list.size();
                int i3 = 0;
                int i4 = 0;
                while (i3 < size) {
                    int iA = ej.this.a(i2, list.get(i3)) + i4;
                    i3++;
                    i4 = iA;
                }
                return i4;
            }

            @Override // com.tapjoy.internal.ej
            public final /* synthetic */ int a(Object obj) {
                throw new UnsupportedOperationException("Repeated values can only be sized with a tag.");
            }

            @Override // com.tapjoy.internal.ej
            public final /* synthetic */ Object a(ek ekVar) {
                return Collections.singletonList(ej.this.a(ekVar));
            }

            @Override // com.tapjoy.internal.ej
            public final /* synthetic */ void a(el elVar, int i2, Object obj) {
                List list = (List) obj;
                int size = list.size();
                for (int i3 = 0; i3 < size; i3++) {
                    ej.this.a(elVar, i2, list.get(i3));
                }
            }

            @Override // com.tapjoy.internal.ej
            public final /* synthetic */ void a(el elVar, Object obj) {
                throw new UnsupportedOperationException("Repeated values can only be encoded with a tag.");
            }
        };
        this.b = ejVar2;
        return ejVar2;
    }

    public abstract Object a(ek ekVar);

    public final Object a(InputStream inputStream) {
        ei.a(inputStream, "stream == null");
        return a(iv.a(iv.a(inputStream)));
    }

    public final Object a(byte[] bArr) {
        ei.a(bArr, "bytes == null");
        ir irVar = new ir();
        if (bArr == null) {
            throw new IllegalArgumentException("source == null");
        }
        return a((it) irVar.a(bArr, 0, bArr.length));
    }

    public void a(el elVar, int i2, Object obj) {
        elVar.c(el.a(i2, this.r));
        if (this.r == eg.LENGTH_DELIMITED) {
            elVar.c(a(obj));
        }
        a(elVar, obj);
    }

    public abstract void a(el elVar, Object obj);

    public final void a(OutputStream outputStream, Object obj) {
        ei.a(obj, "value == null");
        ei.a(outputStream, "stream == null");
        is isVarA = iv.a(iv.a(outputStream));
        a(isVarA, obj);
        isVarA.a();
    }

    public final byte[] b(Object obj) {
        ei.a(obj, "value == null");
        ir irVar = new ir();
        try {
            a(irVar, obj);
            return irVar.g();
        } catch (IOException e2) {
            throw new AssertionError(e2);
        }
    }
}
