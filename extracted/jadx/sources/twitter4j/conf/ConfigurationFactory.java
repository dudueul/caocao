package twitter4j.conf;

/* JADX INFO: loaded from: /home/jtk/work/caocao/extracted/apk/classes2.dex */
public interface ConfigurationFactory {
    void dispose();

    Configuration getInstance();

    Configuration getInstance(String str);
}
