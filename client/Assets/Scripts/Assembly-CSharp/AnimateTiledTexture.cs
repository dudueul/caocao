using System.Collections;
using System.Diagnostics;
using UnityEngine;

public class AnimateTiledTexture : MonoBehaviour
{
	public int Columns;

	public int Rows;

	public float FramesPerSecond;

	public bool RunOnce;

	private Vector2 size;

	private float oneFrameDuration;

	private bool isPlayingAnimationOnEditMode;

	public float RunTimeInSeconds
	{
		get
		{
			return 0f;
		}
	}

	private void OnEnable()
	{
	}

	[DebuggerHidden]
	private IEnumerator UpdateTiling()
	{
		return null;
	}
}
