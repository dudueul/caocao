using System.Collections.Generic;

public class AssetBundlePackage : JsonImporter, IJsonExportable
{
	public const long maxSize = 10485760L;

	public int buildVersion;

	public int resourceVersion;

	public string nation;

	public string language;

	public string voiceLanguage;

	public int serial;

	public long packageFileSize;

	public Dictionary<string, AssetBundleInfo> bundles;

	public string openDate;

	public long fileSize
	{
		get
		{
			return 0L;
		}
	}
}
