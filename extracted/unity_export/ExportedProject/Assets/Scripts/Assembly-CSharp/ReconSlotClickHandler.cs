using System;
using UnityEngine;

public class ReconSlotClickHandler : MonoBehaviour
{
	public Vector2 gridPos;

	private const float touchDistanceThreshold = 0.1f;

	private Vector3 mousedownPos;

	private float mousedownTime;

	public Action<Vector2, ReconSlot> clickCallBack { get; set; }

	public bool checkThreshold { get; set; }

	private void Awake()
	{
	}

	private void OnMouseDown()
	{
	}

	private void OnMouseUpAsButton()
	{
	}
}
