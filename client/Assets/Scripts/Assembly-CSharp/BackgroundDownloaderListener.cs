using System;
using System.Collections.Generic;
using UnityEngine;

public class BackgroundDownloaderListener : MonoBehaviour
{
	public static BackgroundDownloaderListener instance;

	public Action<long, long> progressHandler;

	public Action<string> finishDownloadHandler;

	public Action<string> errorHandler;

	private long willBeReceivedTotalBytes;

	private List<AssetBundlePackage> m_downloadList;

	private List<long> m_downloaedBytes;

	private string m_bundleVersion;

	private void Awake()
	{
	}

	private void OnDestroy()
	{
	}

	public void Init(List<AssetBundlePackage> downloadList, string bundleVersion)
	{
	}

	private long GetTotalReceivedByte()
	{
		return 0L;
	}

	public void OnProgress(string msg)
	{
	}

	public void OnFinishDownload(string msg)
	{
	}

	public void OnError(string msg)
	{
	}
}
