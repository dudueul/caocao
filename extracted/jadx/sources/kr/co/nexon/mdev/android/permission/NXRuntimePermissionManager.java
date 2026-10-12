package kr.co.nexon.mdev.android.permission;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.SparseArray;
import android.view.View;
import com.nexon.core.log.ToyLog;
import com.nexon.core.util.NXStringUtil;
import com.nexon.npaccount.R;
import com.nexon.platform.NXPFinalizer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kr.co.nexon.mdev.android.util.NXPackageUtil;
import kr.co.nexon.npaccount.NXToyLocaleManager;
import kr.co.nexon.toy.android.ui.common.NXPAlertDialog;
import kr.co.nexon.toy.android.ui.permission.NXPPermissionDialog;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class NXRuntimePermissionManager {
    public static final String ACCESS_COARSE_LOCATION = "android.permission.ACCESS_COARSE_LOCATION";
    public static final String ACCESS_FINE_LOCATION = "android.permission.ACCESS_FINE_LOCATION";
    public static final String ADD_VOICEMAIL = "com.android.voicemail.permission.ADD_VOICEMAIL";
    public static final String BODY_SENSORS = "android.permission.BODY_SENSORS";
    public static final String CALL_PHONE = "android.permission.CALL_PHONE";
    public static final String CAMERA = "android.permission.CAMERA";
    public static final String GET_ACCOUNTS = "android.permission.GET_ACCOUNTS";
    public static final int GROUP_CALENDAR = 0;
    public static final int GROUP_CAMERA = 1;
    public static final int GROUP_CONTACTS = 2;
    public static final int GROUP_LOCATION = 3;
    public static final int GROUP_MICROPHONE = 4;
    public static final int GROUP_PHONE = 5;
    public static final int GROUP_SENSORS = 6;
    public static final int GROUP_SMS = 7;
    public static final int GROUP_STORAGE = 8;
    public static final String PROCESS_OUTGOING_CALLS = "android.permission.PROCESS_OUTGOING_CALLS";
    public static final String READ_CALENDAR = "android.permission.READ_CALENDAR";
    public static final String READ_CALL_LOG = "android.permission.READ_CALL_LOG";
    public static final String READ_CONTACTS = "android.permission.READ_CONTACTS";
    public static final String READ_EXTERNAL_STORAGE = "android.permission.READ_EXTERNAL_STORAGE";
    public static final String READ_PHONE_STATE = "android.permission.READ_PHONE_STATE";
    public static final String READ_SMS = "android.permission.READ_SMS";
    public static final String RECEIVE_MMS = "android.permission.RECEIVE_MMS";
    public static final String RECEIVE_SMS = "android.permission.RECEIVE_SMS";
    public static final String RECEIVE_WAP_PUSH = "android.permission.RECEIVE_WAP_PUSH";
    public static final String RECORD_AUDIO = "android.permission.RECORD_AUDIO";
    public static final String SEND_SMS = "android.permission.SEND_SMS";
    public static final String USE_SIP = "android.permission.USE_SIP";
    public static final String WRITE_CALENDAR = "android.permission.WRITE_CALENDAR";
    public static final String WRITE_CALL_LOG = "android.permission.WRITE_CALL_LOG";
    public static final String WRITE_CONTACTS = "android.permission.WRITE_CONTACTS";
    public static final String WRITE_EXTERNAL_STORAGE = "android.permission.WRITE_EXTERNAL_STORAGE";
    private static volatile NXRuntimePermissionManager instance = null;
    private static NXPFinalizer finalizer = new NXPFinalizer() { // from class: kr.co.nexon.mdev.android.permission.NXRuntimePermissionManager.1
        @Override // com.nexon.platform.NXPFinalizer
        public void finalize() {
            NXRuntimePermissionManager unused = NXRuntimePermissionManager.instance = null;
        }
    };
    private NXToyLocaleManager localeManager = null;
    private int permissionRequestCode = 0;
    private SparseArray<NXRuntimePermissionListener> permissionListenerArray = new SparseArray<>();
    private Map<Integer, NXPRequestPermissionInfo> permissionsData = new HashMap();

    private interface RequestPermissionActionPerformed {
        void onActionPerformed();
    }

    private int[] getGrantResultsFromManifest(Activity activity, String[] strArr) {
        if (strArr == null) {
            return new int[0];
        }
        int length = strArr.length;
        int[] iArr = new int[length];
        for (int i = 0; i < length; i++) {
            if (isPermissionFromManifest(activity, strArr[i])) {
                iArr[i] = 0;
            } else {
                iArr[i] = -1;
            }
        }
        return iArr;
    }

    public static NXRuntimePermissionManager getInstance() {
        if (instance == null) {
            synchronized (NXRuntimePermissionManager.class) {
                if (instance == null) {
                    instance = new NXRuntimePermissionManager();
                }
            }
        }
        return instance;
    }

    private int getPermissionGroupCode(String str) {
        if (str.equals(READ_EXTERNAL_STORAGE) || str.equals(WRITE_EXTERNAL_STORAGE)) {
            return 8;
        }
        if (str.equals(READ_CONTACTS) || str.equals(WRITE_CONTACTS) || str.equals(GET_ACCOUNTS)) {
            return 2;
        }
        if (str.equals(READ_PHONE_STATE) || str.equals(CALL_PHONE) || str.equals(READ_CALL_LOG) || str.equals(WRITE_CALL_LOG) || str.equals(ADD_VOICEMAIL) || str.equals(USE_SIP) || str.equals(PROCESS_OUTGOING_CALLS)) {
            return 5;
        }
        if (str.equals(READ_CALENDAR) || str.equals(WRITE_CALENDAR)) {
            return 0;
        }
        if (str.equals(CAMERA)) {
            return 1;
        }
        if (str.equals(ACCESS_FINE_LOCATION) || str.equals(ACCESS_COARSE_LOCATION)) {
            return 3;
        }
        if (str.equals(RECORD_AUDIO)) {
            return 4;
        }
        if (str.equals(BODY_SENSORS)) {
            return 6;
        }
        return (str.equals(SEND_SMS) || str.equals(RECEIVE_SMS) || str.equals(RECEIVE_MMS) || str.equals(READ_SMS) || str.equals(RECEIVE_WAP_PUSH)) ? 7 : -1;
    }

    private ArrayList<String> getPermissionListFromManifest(Activity activity) {
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            PackageInfo packageInfo = activity.getPackageManager().getPackageInfo(activity.getPackageName(), 4104);
            for (int i = 0; i < packageInfo.requestedPermissions.length; i++) {
                ToyLog.d("PERM : " + packageInfo.requestedPermissions[i]);
                arrayList.add(packageInfo.requestedPermissions[i]);
            }
        } catch (PackageManager.NameNotFoundException e) {
            ToyLog.d("" + e.getMessage());
            e.printStackTrace();
        }
        return arrayList;
    }

    private String getRequestPermissionsGroupDescription(String str) {
        switch (getInstance().getPermissionGroupCode(str)) {
            case 0:
                return this.localeManager.getString(R.string.npres_runtime_permission_group_calendar_description);
            case 1:
                return this.localeManager.getString(R.string.npres_runtime_permission_group_camera_description);
            case 2:
                return this.localeManager.getString(R.string.npres_runtime_permission_group_contacts_description);
            case 3:
                return this.localeManager.getString(R.string.npres_runtime_permission_group_location_description);
            case 4:
                return this.localeManager.getString(R.string.npres_runtime_permission_group_microphone_description);
            case 5:
                return this.localeManager.getString(R.string.npres_runtime_permission_group_phone_description);
            case 6:
                return this.localeManager.getString(R.string.npres_runtime_permission_group_sensors_description);
            case 7:
                return this.localeManager.getString(R.string.npres_runtime_permission_group_sms_description);
            case 8:
                return str.equals(READ_EXTERNAL_STORAGE) ? this.localeManager.getString(R.string.npres_runtime_permission_group_read_storage_description) : this.localeManager.getString(R.string.npres_runtime_permission_group_write_storage_description);
            default:
                return "";
        }
    }

    private String getRequestPermissionsGroupName(String str) {
        switch (getInstance().getPermissionGroupCode(str)) {
            case 0:
                return this.localeManager.getString(R.string.npres_runtime_permission_group_calendar);
            case 1:
                return this.localeManager.getString(R.string.npres_runtime_permission_group_camera);
            case 2:
                return this.localeManager.getString(R.string.npres_runtime_permission_group_contacts);
            case 3:
                return this.localeManager.getString(R.string.npres_runtime_permission_group_location);
            case 4:
                return this.localeManager.getString(R.string.npres_runtime_permission_group_microphone);
            case 5:
                return this.localeManager.getString(R.string.npres_runtime_permission_group_phone);
            case 6:
                return this.localeManager.getString(R.string.npres_runtime_permission_group_sensors);
            case 7:
                return this.localeManager.getString(R.string.npres_runtime_permission_group_sms);
            case 8:
                return this.localeManager.getString(R.string.npres_runtime_permission_group_storage);
            default:
                return "";
        }
    }

    private static boolean isGrantsAllPermission(Collection<NXPPermission> collection) {
        if (collection == null || collection.size() <= 0) {
            ToyLog.e(String.format("PermissionList is null or <= 0", new Object[0]));
            return false;
        }
        Iterator<NXPPermission> it = collection.iterator();
        while (it.hasNext()) {
            if (it.next().getGrantResult() == -1) {
                return false;
            }
        }
        return true;
    }

    public static boolean isGrantsAllPermission(int[] iArr) {
        for (int i : iArr) {
            if (i == -1) {
                return false;
            }
        }
        return true;
    }

    private boolean needCheckRuntimePermission(Context context) {
        return Build.VERSION.SDK_INT >= 23 && NXPackageUtil.getTargetSdkVersion(context.getApplicationContext()) >= 23;
    }

    private void preparePermissionsDataAndRequest(Activity activity, @NonNull NXPRequestPermissionInfo nXPRequestPermissionInfo, int i, NXRuntimePermissionListener nXRuntimePermissionListener, @NonNull RequestPermissionActionPerformed requestPermissionActionPerformed) {
        if (activity == null) {
            ToyLog.e(String.format("requestCode = %d , activity is null", Integer.valueOf(i)));
            if (nXRuntimePermissionListener != null) {
                nXRuntimePermissionListener.onResult(i, new String[0], new int[0]);
                return;
            }
            return;
        }
        if (!needCheckRuntimePermission(activity)) {
            if (nXRuntimePermissionListener != null) {
                String[] arrayRequestPermissions = nXPRequestPermissionInfo.toArrayRequestPermissions();
                nXRuntimePermissionListener.onResult(i, arrayRequestPermissions, getGrantResultsFromManifest(activity, arrayRequestPermissions));
                return;
            }
            return;
        }
        this.localeManager = NXToyLocaleManager.getInstance(activity);
        settingPermissionsData(activity, nXPRequestPermissionInfo.getRequestPermissionsHashMap().values());
        if (isGrantsAllPermission(nXPRequestPermissionInfo.getRequestPermissionsHashMap().values())) {
            if (nXRuntimePermissionListener != null) {
                nXRuntimePermissionListener.onResult(i, nXPRequestPermissionInfo.toArrayRequestPermissions(), nXPRequestPermissionInfo.toArrayRequestPermissionResults());
                return;
            }
            return;
        }
        this.permissionRequestCode = i;
        if (nXRuntimePermissionListener != null) {
            this.permissionListenerArray.put(i, nXRuntimePermissionListener);
        }
        this.permissionsData.put(Integer.valueOf(i), nXPRequestPermissionInfo);
        if (requestPermissionActionPerformed != null) {
            requestPermissionActionPerformed.onActionPerformed();
        }
    }

    private void settingPermissionsData(Activity activity, Collection<NXPPermission> collection) {
        for (NXPPermission nXPPermission : collection) {
            String name = nXPPermission.getName();
            nXPPermission.setGrantResult(ActivityCompat.checkSelfPermission(activity, name));
            nXPPermission.setGroupName(getRequestPermissionsGroupName(name));
            if (NXStringUtil.isNull(nXPPermission.getDescription())) {
                nXPPermission.setDescription(getRequestPermissionsGroupDescription(name));
            }
        }
    }

    private boolean shouldShowRequestPermissionRationale(Activity activity, String[] strArr) {
        for (String str : strArr) {
            if (ActivityCompat.checkSelfPermission(activity.getApplicationContext(), str) != 0 && ActivityCompat.shouldShowRequestPermissionRationale(activity, str)) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showPermissionConfirmDialog(Activity activity, NXPRequestPermissionInfo nXPRequestPermissionInfo, View.OnClickListener onClickListener) {
        NXPPermissionDialog nXPPermissionDialogNewInstance = NXPPermissionDialog.newInstance(activity, nXPRequestPermissionInfo);
        nXPPermissionDialogNewInstance.setOnConfirmClickListener(onClickListener);
        nXPPermissionDialogNewInstance.show(activity.getFragmentManager(), NXPPermissionDialog.TAG);
    }

    public boolean checkGrantedPermission(Context context, String str) {
        return ActivityCompat.checkSelfPermission(context, str) == 0;
    }

    public int getPermissionRequestCode() {
        return this.permissionRequestCode;
    }

    public boolean isPermissionFromManifest(Activity activity, String str) {
        Iterator<String> it = getPermissionListFromManifest(activity).iterator();
        while (it.hasNext()) {
            if (str.equals(it.next())) {
                return true;
            }
        }
        return false;
    }

    public void onActivityResult(Activity activity) {
        NXRuntimePermissionListener nXRuntimePermissionListener = this.permissionListenerArray.get(this.permissionRequestCode);
        NXPRequestPermissionInfo nXPRequestPermissionInfo = this.permissionsData.get(Integer.valueOf(this.permissionRequestCode));
        if (nXPRequestPermissionInfo != null) {
            for (NXPPermission nXPPermission : nXPRequestPermissionInfo.getRequestPermissionsHashMap().values()) {
                if (nXPPermission != null) {
                    nXPPermission.setGrantResult(ActivityCompat.checkSelfPermission(activity.getApplicationContext(), nXPPermission.getName()));
                }
            }
            if (nXRuntimePermissionListener != null) {
                nXRuntimePermissionListener.onResult(this.permissionRequestCode, nXPRequestPermissionInfo.toArrayRequestPermissions(), nXPRequestPermissionInfo.toArrayRequestPermissionResults());
            }
        }
    }

    public void onRequestPermissionsResult(final Activity activity, final int i, String[] strArr, int[] iArr) {
        NXRuntimePermissionListener nXRuntimePermissionListener = this.permissionListenerArray.get(i);
        NXPRequestPermissionInfo nXPRequestPermissionInfo = this.permissionsData.get(Integer.valueOf(i));
        if (nXPRequestPermissionInfo == null) {
            ToyLog.e(String.format("%d Permission permissionInfo Empty", Integer.valueOf(i)));
            if (nXRuntimePermissionListener != null) {
                nXRuntimePermissionListener.onResult(i, strArr, iArr);
                return;
            }
            return;
        }
        LinkedHashMap<String, NXPPermission> requestPermissionsHashMap = nXPRequestPermissionInfo.getRequestPermissionsHashMap();
        if (iArr.length > 0) {
            for (int i2 = 0; i2 < iArr.length; i2++) {
                NXPPermission nXPPermission = requestPermissionsHashMap.get(strArr[i2]);
                if (nXPPermission != null) {
                    nXPPermission.setGrantResult(iArr[i2]);
                }
            }
        }
        if (isGrantsAllPermission(iArr)) {
            if (nXRuntimePermissionListener != null) {
                nXRuntimePermissionListener.onResult(i, nXPRequestPermissionInfo.toArrayRequestPermissions(), nXPRequestPermissionInfo.toArrayRequestPermissionResults());
            }
        } else if (i == 100001) {
            if (nXRuntimePermissionListener != null) {
                nXRuntimePermissionListener.onResult(i, nXPRequestPermissionInfo.toArrayRequestPermissions(), nXPRequestPermissionInfo.toArrayRequestPermissionResults());
            }
        } else if (!shouldShowRequestPermissionRationale(activity, nXPRequestPermissionInfo.toArrayRequestPermissions())) {
            showPermissionAlert(activity, NXToyLocaleManager.getInstance(activity).getString(R.string.npres_runtime_permission_message_after), new View.OnClickListener() { // from class: kr.co.nexon.mdev.android.permission.NXRuntimePermissionManager.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    activity.startActivityForResult(new Intent("android.settings.APPLICATION_DETAILS_SETTINGS").setData(Uri.parse("package:" + activity.getPackageName())), i);
                }
            });
        } else if (nXRuntimePermissionListener != null) {
            nXRuntimePermissionListener.onResult(i, nXPRequestPermissionInfo.toArrayRequestPermissions(), nXPRequestPermissionInfo.toArrayRequestPermissionResults());
        }
    }

    public void requestPermissions(final Activity activity, @NonNull final NXPRequestPermissionInfo nXPRequestPermissionInfo, final int i, @Nullable final NXRuntimePermissionListener nXRuntimePermissionListener) {
        preparePermissionsDataAndRequest(activity, nXPRequestPermissionInfo, i, nXRuntimePermissionListener, new RequestPermissionActionPerformed() { // from class: kr.co.nexon.mdev.android.permission.NXRuntimePermissionManager.2
            @Override // kr.co.nexon.mdev.android.permission.NXRuntimePermissionManager.RequestPermissionActionPerformed
            public void onActionPerformed() {
                NXRuntimePermissionManager.this.showPermissionConfirmDialog(activity, nXPRequestPermissionInfo, new View.OnClickListener() { // from class: kr.co.nexon.mdev.android.permission.NXRuntimePermissionManager.2.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        String[] arrayRequestPermissions = nXPRequestPermissionInfo.toArrayRequestPermissions();
                        if (arrayRequestPermissions.length > 0) {
                            ActivityCompat.requestPermissions(activity, arrayRequestPermissions, i);
                        } else if (nXRuntimePermissionListener != null) {
                            nXRuntimePermissionListener.onResult(i, new String[0], new int[0]);
                        }
                    }
                });
            }
        });
    }

    public void requestPermissionsWithoutExplanation(final Activity activity, @NonNull String[] strArr, final int i, @Nullable final NXRuntimePermissionListener nXRuntimePermissionListener) {
        NXPRequestPermissionInfo.Builder builder = new NXPRequestPermissionInfo.Builder();
        for (String str : strArr) {
            builder.addPermission(str, 0);
        }
        final NXPRequestPermissionInfo nXPRequestPermissionInfoBuild = builder.build();
        preparePermissionsDataAndRequest(activity, nXPRequestPermissionInfoBuild, i, nXRuntimePermissionListener, new RequestPermissionActionPerformed() { // from class: kr.co.nexon.mdev.android.permission.NXRuntimePermissionManager.3
            @Override // kr.co.nexon.mdev.android.permission.NXRuntimePermissionManager.RequestPermissionActionPerformed
            public void onActionPerformed() {
                String[] arrayRequestPermissions = nXPRequestPermissionInfoBuild.toArrayRequestPermissions();
                if (arrayRequestPermissions.length > 0) {
                    ActivityCompat.requestPermissions(activity, arrayRequestPermissions, i);
                } else if (nXRuntimePermissionListener != null) {
                    nXRuntimePermissionListener.onResult(i, new String[0], new int[0]);
                }
            }
        });
    }

    public void showPermissionAlert(final Activity activity, final String str, final View.OnClickListener onClickListener) {
        activity.runOnUiThread(new Runnable() { // from class: kr.co.nexon.mdev.android.permission.NXRuntimePermissionManager.5
            @Override // java.lang.Runnable
            public void run() {
                new NXPAlertDialog.Builder(activity).setMessage(str).setPositiveButton(NXToyLocaleManager.getInstance(activity).getString(R.string.confirm), onClickListener).setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: kr.co.nexon.mdev.android.permission.NXRuntimePermissionManager.5.1
                    @Override // android.content.DialogInterface.OnCancelListener
                    public void onCancel(DialogInterface dialogInterface) {
                        onClickListener.onClick(null);
                    }
                }).create().show();
            }
        });
    }
}
