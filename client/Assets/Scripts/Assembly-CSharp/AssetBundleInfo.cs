using System.Collections.Generic;

public class AssetBundleInfo : JsonImporter, IJsonExportable
{
	public int fileSize;

	public List<string> assets;

	public bool encrypted;

	public bool updatable;

	public string openDate;

	public string GetNation()
	{
		return null;
	}

	public string GetLanguage()
	{
		return null;
	}

	public string GetVoiceLanguage()
	{
		return null;
	}
}
