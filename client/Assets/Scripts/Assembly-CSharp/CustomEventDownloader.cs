using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using UnityEngine;

public class CustomEventDownloader : SceneSingleton<CustomEventDownloader>
{
	private Dictionary<string, WWW> m_images;

	private Texture2D m_texture;

	private string m_cachePath;

	private string m_url;

	public void Init(string url, string[] filenames)
	{
	}

	public void GetTexture(string filename, Action<Texture> onResponse)
	{
	}

	[DebuggerHidden]
	private IEnumerator OpenUrl(string url, Action<WWW> onResponse)
	{
		return null;
	}

	public void DestroyTexture()
	{
	}

	protected override void OnDestroy()
	{
	}
}
