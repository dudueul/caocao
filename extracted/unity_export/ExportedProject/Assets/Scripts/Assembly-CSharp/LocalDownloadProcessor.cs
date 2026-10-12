using System.Collections;
using System.Diagnostics;

public class LocalDownloadProcessor : DownloadProcessor
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

	public override string GetForeignPath()
	{
		return null;
	}
}
