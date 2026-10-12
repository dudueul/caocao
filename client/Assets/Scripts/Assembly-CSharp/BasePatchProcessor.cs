using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using LitJson;

public abstract class BasePatchProcessor : IPatchProcessor
{
	private List<IPatchView> m_views;

	public AssetBundleList assetBundleList { get; protected set; }

	public void SetAssetBundleList(JsonData json)
	{
	}

	public string GetAssetBundlePassword()
	{
		return null;
	}

	public string GetAssetBundleVersion()
	{
		return null;
	}

	public abstract AssetBundleListInfo GetBundleListInfo();

	public abstract IEnumerator WaitReceivingAssetBundleList();

	public abstract IEnumerator BuildAssetBundleList();

	public abstract IEnumerator DownloadAssetBundles(DateTime eventScriptDate);

	[DebuggerHidden]
	public virtual IEnumerator PostProcess()
	{
		return null;
	}

	public abstract string GetForeignPath();

	public virtual string GetLocalFilePath(string fileName)
	{
		return null;
	}

	public void RegisterView(IPatchView view)
	{
	}

	public void UnregisterView(IPatchView view)
	{
	}

	protected void ProgressTitle(string progress, object param = null)
	{
	}

	protected void ProgressBar(float progress)
	{
	}

	protected void ProgressBytes(long downloaded, long total)
	{
	}

	protected void DecompressCount(int count, int totalCount)
	{
	}

	protected void SetActive(bool active)
	{
	}

	protected void FinishView()
	{
	}

	protected bool IsViewClosed()
	{
		return false;
	}
}
