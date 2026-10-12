using System;
using UnityEngine;

public class MiniMapCameraController : MonoBehaviour
{
	private int RightsidUIWidth;

	private float ratioOfRightSideUI;

	private float viewRectHRatio;

	public Action<Rect> viewportRectUpdated;

	private void OnEnable()
	{
	}
}
