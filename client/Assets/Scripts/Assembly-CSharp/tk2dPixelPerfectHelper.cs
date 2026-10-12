using System;
using UnityEngine;

public class tk2dPixelPerfectHelper : MonoBehaviour
{
	private static tk2dPixelPerfectHelper _inst;

	[NonSerialized]
	public Camera cam;

	public int collectionTargetHeight;

	public float collectionOrthoSize;

	public float targetResolutionHeight;

	[NonSerialized]
	public float scaleD;

	[NonSerialized]
	public float scaleK;

	public static tk2dPixelPerfectHelper inst
	{
		get
		{
			return null;
		}
	}

	public bool CameraIsOrtho
	{
		get
		{
			return false;
		}
	}

	private void Awake()
	{
	}

	public virtual void Setup()
	{
	}

	public static float CalculateScaleForPerspectiveCamera(float fov, float zdist)
	{
		return 0f;
	}
}
