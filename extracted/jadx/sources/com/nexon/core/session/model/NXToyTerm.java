package com.nexon.core.session.model;

import com.nexon.core.requestpostman.result.NXClassInfo;
import java.util.List;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NXToyTerm extends NXClassInfo {
    public static final String TYPE_NIGHT_PUSH = "nightpush";
    public static final String TYPE_OPTIONAL = "optional";
    public static final String TYPE_PUSH = "push";
    public static final String TYPE_TOAST = "toast";
    public int childTermsID;
    public String contents;
    public int isAgree;
    public int isOptional = -1;
    public int isUpdate;
    public int optional;
    public int parentsTermsID;
    public int termID;
    public String title;
    public List<String> type;
}
