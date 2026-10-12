package com.naver.glink.android.sdk.ui.streaming;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.text.InputFilter;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.naver.glink.android.sdk.R;
import com.naver.glink.android.sdk.a.g;
import com.naver.glink.android.sdk.a.k;
import com.naver.glink.android.sdk.a.t;
import com.naver.glink.android.sdk.api.Responses;
import com.naver.glink.android.sdk.api.streaming.ChattingMessage;
import com.naver.glink.android.sdk.api.streaming.StreamingReportTypesResponse;
import com.naver.glink.android.sdk.api.streaming.c;
import com.naver.glink.android.sdk.d;
import com.naver.plug.android.core.api.PlugError;
import com.naver.plug.android.core.api.request.RequestListener;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes.dex */
public class StreamingReportFragment extends com.naver.glink.android.sdk.ui.parent.a.a {
    private static final String a = "com.naver.glink.ARG_STREAMING_REPORT";
    private Report b;
    private StreamingReportTypesResponse c;
    private StreamingReportTypesResponse.ReportType d;
    private EditText e;
    private View f;
    private TextView g;
    private TextView h;

    public static class Report extends com.naver.plug.android.core.a.a implements Parcelable {
        public static final Parcelable.Creator<Report> CREATOR = new Parcelable.Creator<Report>() { // from class: com.naver.glink.android.sdk.ui.streaming.StreamingReportFragment.Report.1
            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public Report createFromParcel(Parcel parcel) {
                return new Report(parcel);
            }

            @Override // android.os.Parcelable.Creator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public Report[] newArray(int i) {
                return new Report[i];
            }
        };
        public final RequestType a;
        public final String b;
        public final ChattingMessage c;

        public enum RequestType {
            STREAMING,
            CHATTING
        }

        protected Report(Parcel parcel) {
            this.a = (RequestType) parcel.readSerializable();
            this.b = parcel.readString();
            this.c = (ChattingMessage) parcel.readParcelable(ChattingMessage.class.getClassLoader());
        }

        private Report(RequestType requestType, String str, ChattingMessage chattingMessage) {
            this.a = requestType;
            this.b = str;
            this.c = chattingMessage;
        }

        public static Report a(String str) {
            return new Report(RequestType.STREAMING, str, null);
        }

        public static Report a(String str, ChattingMessage chattingMessage) {
            return new Report(RequestType.CHATTING, str, chattingMessage);
        }

        String a() {
            switch (this.a) {
                case STREAMING:
                    return d.a(R.string.live_broadcast_report_message);
                case CHATTING:
                    return d.a(R.string.live_chat_report_message);
                default:
                    return "";
            }
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeSerializable(this.a);
            parcel.writeString(this.b);
            parcel.writeParcelable(this.c, i);
        }
    }

    public static StreamingReportFragment a(Report report) {
        StreamingReportFragment streamingReportFragment = new StreamingReportFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(a, report);
        streamingReportFragment.setArguments(bundle);
        return streamingReportFragment;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final View view) {
        if (this.c == null) {
            c.a(this.b).execute(getActivity(), new RequestListener<StreamingReportTypesResponse>() { // from class: com.naver.glink.android.sdk.ui.streaming.StreamingReportFragment.4
                @Override // com.naver.plug.android.core.api.request.RequestListener
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onSuccess(@NonNull StreamingReportTypesResponse streamingReportTypesResponse) {
                    StreamingReportFragment.this.c = streamingReportTypesResponse;
                    StreamingReportFragment.this.a(view);
                }
            });
            return;
        }
        if (this.b.a == Report.RequestType.CHATTING) {
            this.g.setText(getResources().getString(R.string.live_report_chatting_writer, this.b.c.nickName + "(" + this.c.maskedUserId + ")"));
            this.h.setText(getResources().getString(R.string.live_report_chatting_message, this.b.c.contents));
        }
        b(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(ViewGroup viewGroup, View view) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View childAt = viewGroup.getChildAt(i);
            childAt.setActivated(childAt == view);
        }
    }

    private void b(View view) {
        final ViewGroup viewGroup = (ViewGroup) view.findViewById(R.id.report_types);
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(getActivity());
        for (final StreamingReportTypesResponse.ReportType reportType : this.b.a == Report.RequestType.STREAMING ? this.c.reportTypes : this.c.messageReportTypes) {
            final TextView textView = (TextView) layoutInflaterFrom.inflate(R.layout.item_streaming_report, viewGroup, false);
            textView.setText(reportType.text);
            textView.setOnClickListener(new t() { // from class: com.naver.glink.android.sdk.ui.streaming.StreamingReportFragment.5
                @Override // com.naver.glink.android.sdk.a.t
                public void a(View view2) {
                    StreamingReportFragment.this.d = reportType;
                    StreamingReportFragment.this.a(viewGroup, textView);
                    if (reportType.reason) {
                        StreamingReportFragment.this.e.setEnabled(true);
                        k.a((Context) StreamingReportFragment.this.getActivity(), (View) StreamingReportFragment.this.e);
                    } else {
                        StreamingReportFragment.this.e.setEnabled(false);
                    }
                    StreamingReportFragment.this.c();
                }
            });
            viewGroup.addView(textView);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        this.f.setEnabled(d());
    }

    private boolean d() {
        if (this.d == null) {
            return false;
        }
        return (this.d.reason && TextUtils.isEmpty(this.e.getText())) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        if (d()) {
            c.a(this.b, this.d.code, this.e.getText().toString()).execute(getActivity(), new RequestListener<Responses.x>() { // from class: com.naver.glink.android.sdk.ui.streaming.StreamingReportFragment.6
                @Override // com.naver.plug.android.core.api.request.RequestListener
                /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onSuccess(@NonNull Responses.x xVar) {
                    Toast.makeText(StreamingReportFragment.this.getActivity(), R.string.report_completed, 1).show();
                    StreamingReportFragment.this.dismiss();
                }

                @Override // com.naver.plug.android.core.api.request.RequestListener
                public void onFailure(@NonNull PlugError plugError) {
                    Toast.makeText(StreamingReportFragment.this.getActivity(), plugError.errorMessage, 1).show();
                }
            });
        }
    }

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(R.layout.fragment_streaming_report, viewGroup, false);
    }

    @Override // android.app.DialogFragment, android.app.Fragment
    public void onStart() {
        super.onStart();
        a();
    }

    @Override // android.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.b = (Report) getArguments().getParcelable(a);
        ((TextView) view.findViewById(R.id.title)).setText(this.b.a());
        if (this.b.a == Report.RequestType.CHATTING) {
            view.findViewById(R.id.chatting_message_detail_layout).setVisibility(0);
            this.g = (TextView) view.findViewById(R.id.target_user);
            this.h = (TextView) view.findViewById(R.id.target_message);
        }
        this.e = (EditText) view.findViewById(R.id.report_reason);
        this.e.setFilters(new InputFilter[]{new InputFilter.LengthFilter(20)});
        this.e.addTextChangedListener(new g() { // from class: com.naver.glink.android.sdk.ui.streaming.StreamingReportFragment.1
            @Override // com.naver.glink.android.sdk.a.g, android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                StreamingReportFragment.this.c();
            }
        });
        view.findViewById(R.id.cancel).setOnClickListener(new t() { // from class: com.naver.glink.android.sdk.ui.streaming.StreamingReportFragment.2
            @Override // com.naver.glink.android.sdk.a.t
            public void a(View view2) {
                StreamingReportFragment.this.dismiss();
            }
        });
        this.f = view.findViewById(R.id.report);
        this.f.setOnClickListener(new t() { // from class: com.naver.glink.android.sdk.ui.streaming.StreamingReportFragment.3
            @Override // com.naver.glink.android.sdk.a.t
            public void a(View view2) {
                StreamingReportFragment.this.e();
            }
        });
        a(view);
        c();
    }
}
