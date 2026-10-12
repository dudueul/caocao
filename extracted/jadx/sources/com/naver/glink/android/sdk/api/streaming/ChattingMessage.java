package com.naver.glink.android.sdk.api.streaming;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.Keep;
import com.naver.plug.android.core.api.Response;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
@Keep
public class ChattingMessage extends Response implements Parcelable {
    public static final Parcelable.Creator<ChattingMessage> CREATOR = new Parcelable.Creator<ChattingMessage>() { // from class: com.naver.glink.android.sdk.api.streaming.ChattingMessage.1
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public ChattingMessage createFromParcel(Parcel parcel) {
            return new ChattingMessage(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public ChattingMessage[] newArray(int i) {
            return new ChattingMessage[i];
        }
    };
    public String contents;
    public boolean deleted;
    public String nickName;
    public boolean own;
    public String profileImageUrl;
    public String streamingMessageId;
    private String streamingMessageType;
    public String userId;

    @Keep
    public enum MessageType {
        UNKNOWN("unknown"),
        TEXT("text"),
        REACTION("reaction");

        public final String code;

        MessageType(String str) {
            this.code = str;
        }

        public static MessageType from(String str) {
            for (MessageType messageType : values()) {
                if (messageType.code.equals(str)) {
                    return messageType;
                }
            }
            return UNKNOWN;
        }
    }

    public ChattingMessage() {
    }

    protected ChattingMessage(Parcel parcel) {
        this.profileImageUrl = parcel.readString();
        this.streamingMessageType = parcel.readString();
        this.nickName = parcel.readString();
        this.streamingMessageId = parcel.readString();
        this.own = parcel.readByte() != 0;
        this.userId = parcel.readString();
        this.contents = parcel.readString();
    }

    public ChattingMessage copy() {
        ChattingMessage chattingMessage = new ChattingMessage();
        chattingMessage.profileImageUrl = this.profileImageUrl;
        chattingMessage.streamingMessageType = this.streamingMessageType;
        chattingMessage.nickName = this.nickName;
        chattingMessage.streamingMessageId = this.streamingMessageId;
        chattingMessage.own = this.own;
        chattingMessage.userId = this.userId;
        chattingMessage.contents = this.contents;
        return chattingMessage;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public MessageType getType() {
        return MessageType.from(this.streamingMessageType);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.profileImageUrl);
        parcel.writeString(this.streamingMessageType);
        parcel.writeString(this.nickName);
        parcel.writeString(this.streamingMessageId);
        parcel.writeByte((byte) (this.own ? 1 : 0));
        parcel.writeString(this.userId);
        parcel.writeString(this.contents);
    }
}
