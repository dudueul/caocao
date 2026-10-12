using System.Collections;
using System.Diagnostics;
using UnityEngine;

public class DownloadTexture : MonoBehaviour
{
	public string url;

	public bool pixelPerfect;

	private Texture2D mTex;

	[DebuggerHidden]
	private IEnumerator Start()
	{
		return null;
	}

	private void OnDestroy()
	{
	}
}
