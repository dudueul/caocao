using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using LitJson;

public abstract class DownloadProcessor : BasePatchProcessor
{
	public class DecodeInfo
	{
		public List<string> incompletedFileNames;

		public DecodeState decodeStates { get; set; }

		public byte[] bytes { get; set; }

		public DecodeInfo(byte[] bytes)
		{
		}
	}

	public enum DecodeState
	{
		Decoding = 0,
		Incompleted = 1,
		Done = 2
	}

	private List<DecodeInfo> m_decodeInfos;

	private long decompressedBytes;

	private int downloadedFileCount;

	protected string localizationBundleName;

	protected bool reloadLocalization;

	protected readonly AssetBundleListInfo m_bundleListInfo;

	public override AssetBundleListInfo GetBundleListInfo()
	{
		return null;
	}

	protected void Import(JsonData json)
	{
	}

	[DebuggerHidden]
	private IEnumerator MoveLegacyFiles()
	{
		return null;
	}

	[DebuggerHidden]
	public override IEnumerator DownloadAssetBundles(DateTime eventScriptDate)
	{
		return null;
	}

	private void OnFinishDownload(string path)
	{
	}

	[DebuggerHidden]
	private IEnumerator LoadDownloadedFile(string path)
	{
		return null;
	}

	private void OnError(string msg)
	{
	}

	[DebuggerHidden]
	public IEnumerator Decompress()
	{
		return null;
	}

	private DecodeInfo GetNextDecodeInfo()
	{
		return null;
	}

	private int GetRemainingDecodeInfo()
	{
		return 0;
	}

	[DebuggerHidden]
	protected IEnumerator DownloadFile(string url, int estimatedTotalSize, Action<byte[]> onDone, Action<int> onDelta)
	{
		return null;
	}

	private static int UpdateDownloadedDelta(Action<int> onDelta, int prev, int cur)
	{
		return 0;
	}
}
