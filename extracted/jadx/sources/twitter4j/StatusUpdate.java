package twitter4j;

import com.adjust.sdk.Constants;
import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public final class StatusUpdate implements Serializable {
    private static final long serialVersionUID = 7422094739799350035L;
    private transient InputStream mediaBody;
    private File mediaFile;
    private long[] mediaIds;
    private String mediaName;
    private boolean possiblySensitive;
    private final String status;
    private long inReplyToStatusId = -1;
    private GeoLocation location = null;
    private String placeId = null;
    private boolean displayCoordinates = true;

    public StatusUpdate(String str) {
        this.status = str;
    }

    private void appendParameter(String str, double d, List<HttpParameter> list) {
        list.add(new HttpParameter(str, String.valueOf(d)));
    }

    private void appendParameter(String str, long j, List<HttpParameter> list) {
        list.add(new HttpParameter(str, String.valueOf(j)));
    }

    private void appendParameter(String str, String str2, List<HttpParameter> list) {
        if (str2 != null) {
            list.add(new HttpParameter(str, str2));
        }
    }

    HttpParameter[] asHttpParameterArray() {
        ArrayList arrayList = new ArrayList();
        appendParameter("status", this.status, arrayList);
        if (-1 != this.inReplyToStatusId) {
            appendParameter("in_reply_to_status_id", this.inReplyToStatusId, (List<HttpParameter>) arrayList);
        }
        if (this.location != null) {
            appendParameter("lat", this.location.getLatitude(), arrayList);
            appendParameter(Constants.LONG, this.location.getLongitude(), arrayList);
        }
        appendParameter("place_id", this.placeId, arrayList);
        if (!this.displayCoordinates) {
            appendParameter("display_coordinates", "false", arrayList);
        }
        if (this.mediaFile != null) {
            arrayList.add(new HttpParameter("media[]", this.mediaFile));
            arrayList.add(new HttpParameter("possibly_sensitive", this.possiblySensitive));
        } else if (this.mediaName != null && this.mediaBody != null) {
            arrayList.add(new HttpParameter("media[]", this.mediaName, this.mediaBody));
            arrayList.add(new HttpParameter("possibly_sensitive", this.possiblySensitive));
        } else if (this.mediaIds != null && this.mediaIds.length >= 1) {
            arrayList.add(new HttpParameter("media_ids", StringUtil.join(this.mediaIds)));
        }
        return (HttpParameter[]) arrayList.toArray(new HttpParameter[arrayList.size()]);
    }

    public StatusUpdate displayCoordinates(boolean z) {
        setDisplayCoordinates(z);
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        StatusUpdate statusUpdate = (StatusUpdate) obj;
        if (this.displayCoordinates == statusUpdate.displayCoordinates && this.inReplyToStatusId == statusUpdate.inReplyToStatusId && this.possiblySensitive == statusUpdate.possiblySensitive) {
            if (this.location == null ? statusUpdate.location != null : !this.location.equals(statusUpdate.location)) {
                return false;
            }
            if (this.mediaBody == null ? statusUpdate.mediaBody != null : !this.mediaBody.equals(statusUpdate.mediaBody)) {
                return false;
            }
            if (this.mediaFile == null ? statusUpdate.mediaFile != null : !this.mediaFile.equals(statusUpdate.mediaFile)) {
                return false;
            }
            if (this.mediaName == null ? statusUpdate.mediaName != null : !this.mediaName.equals(statusUpdate.mediaName)) {
                return false;
            }
            if (this.mediaIds == null ? statusUpdate.mediaIds != null : !Arrays.equals(this.mediaIds, statusUpdate.mediaIds)) {
                return false;
            }
            if (this.placeId == null ? statusUpdate.placeId != null : !this.placeId.equals(statusUpdate.placeId)) {
                return false;
            }
            if (this.status != null) {
                if (this.status.equals(statusUpdate.status)) {
                    return true;
                }
            } else if (statusUpdate.status == null) {
                return true;
            }
            return false;
        }
        return false;
    }

    public long getInReplyToStatusId() {
        return this.inReplyToStatusId;
    }

    public GeoLocation getLocation() {
        return this.location;
    }

    public String getPlaceId() {
        return this.placeId;
    }

    public String getStatus() {
        return this.status;
    }

    public int hashCode() {
        return ((((((((((((((((((this.status != null ? this.status.hashCode() : 0) * 31) + ((int) (this.inReplyToStatusId ^ (this.inReplyToStatusId >>> 32)))) * 31) + (this.location != null ? this.location.hashCode() : 0)) * 31) + (this.placeId != null ? this.placeId.hashCode() : 0)) * 31) + (this.displayCoordinates ? 1 : 0)) * 31) + (this.possiblySensitive ? 1 : 0)) * 31) + (this.mediaName != null ? this.mediaName.hashCode() : 0)) * 31) + (this.mediaBody != null ? this.mediaBody.hashCode() : 0)) * 31) + (this.mediaFile != null ? this.mediaFile.hashCode() : 0)) * 31) + (this.mediaIds != null ? StringUtil.join(this.mediaIds).hashCode() : 0);
    }

    public StatusUpdate inReplyToStatusId(long j) {
        setInReplyToStatusId(j);
        return this;
    }

    public boolean isDisplayCoordinates() {
        return this.displayCoordinates;
    }

    boolean isForUpdateWithMedia() {
        return (this.mediaFile == null && this.mediaName == null) ? false : true;
    }

    public boolean isPossiblySensitive() {
        return this.possiblySensitive;
    }

    public StatusUpdate location(GeoLocation geoLocation) {
        setLocation(geoLocation);
        return this;
    }

    public StatusUpdate media(File file) {
        setMedia(file);
        return this;
    }

    public StatusUpdate media(String str, InputStream inputStream) {
        setMedia(str, inputStream);
        return this;
    }

    public StatusUpdate placeId(String str) {
        setPlaceId(str);
        return this;
    }

    public StatusUpdate possiblySensitive(boolean z) {
        setPossiblySensitive(z);
        return this;
    }

    public void setDisplayCoordinates(boolean z) {
        this.displayCoordinates = z;
    }

    public void setInReplyToStatusId(long j) {
        this.inReplyToStatusId = j;
    }

    public void setLocation(GeoLocation geoLocation) {
        this.location = geoLocation;
    }

    public void setMedia(File file) {
        this.mediaFile = file;
    }

    public void setMedia(String str, InputStream inputStream) {
        this.mediaName = str;
        this.mediaBody = inputStream;
    }

    public void setMediaIds(long[] jArr) {
        this.mediaIds = jArr;
    }

    public void setPlaceId(String str) {
        this.placeId = str;
    }

    public void setPossiblySensitive(boolean z) {
        this.possiblySensitive = z;
    }

    public String toString() {
        return "StatusUpdate{status='" + this.status + "', inReplyToStatusId=" + this.inReplyToStatusId + ", location=" + this.location + ", placeId='" + this.placeId + "', displayCoordinates=" + this.displayCoordinates + ", possiblySensitive=" + this.possiblySensitive + ", mediaName='" + this.mediaName + "', mediaBody=" + this.mediaBody + ", mediaFile=" + this.mediaFile + ", mediaIds=" + this.mediaIds + '}';
    }
}
