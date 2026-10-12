using System.Collections;
using System.Diagnostics;

public class CdnDownloadProcessor : DownloadProcessor
{
	private byte[] assetBundleCache;

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

	private byte[] ReadAssetBundleListFromLocalFile(string localFilePathFromUrl)
	{
		return null;
	}

	public override string GetForeignPath()
	{
		return null;
	}

	[DebuggerHidden]
	public override IEnumerator PostProcess()
	{
		return null;
	}
}
