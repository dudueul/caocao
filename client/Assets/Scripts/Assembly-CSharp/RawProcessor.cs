using System;
using System.Collections;
using System.Diagnostics;
using LitJson;

public class RawProcessor : BasePatchProcessor
{
	[DebuggerHidden]
	public override IEnumerator WaitReceivingAssetBundleList()
	{
		return null;
	}

	[DebuggerHidden]
	public override IEnumerator BuildAssetBundleList()
	{
		return null;
	}

	[DebuggerHidden]
	public override IEnumerator DownloadAssetBundles(DateTime eventScriptDate)
	{
		return null;
	}

	public void Import(JsonData json)
	{
	}

	public override string GetForeignPath()
	{
		return null;
	}

	public override string GetLocalFilePath(string fileName)
	{
		return null;
	}

	public override AssetBundleListInfo GetBundleListInfo()
	{
		return null;
	}
}
