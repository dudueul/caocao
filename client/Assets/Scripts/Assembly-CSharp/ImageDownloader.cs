using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using UnityEngine;

public class ImageDownloader : Singleton<ImageDownloader>
{
	protected class ProcessHelper
	{
		public string url;

		public bool onlyDownload;

		public bool needToSave;

		public string cachedPath;

		public Action<Texture> callback;
	}

	private Dictionary<string, Texture2D> m_createdTexMap;

	public void Clear()
	{
	}

	public void DeleteUnusedFiles(string folderName, List<string> exceptFiles)
	{
	}

	public void DownloadImage(string url, string cachedPath, Action<Texture> callback)
	{
	}

	public void LoadTexture(string cachedPath, Action<Texture> callback)
	{
	}

	public void LoadOrDownloadTexture(string url, string cachedPath, Action<Texture> callback)
	{
	}

	[DebuggerHidden]
	private IEnumerator ProcessLoadOrDownload(ProcessHelper helper, Action<WWW, ProcessHelper> onComplete)
	{
		return null;
	}

	private void OnCompleteLoadOrDownload(WWW www, ProcessHelper helper)
	{
	}
}
