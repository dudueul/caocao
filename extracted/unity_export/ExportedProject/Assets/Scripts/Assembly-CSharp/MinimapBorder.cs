using System;
using UnityEngine;

public class MinimapBorder : SelfRegister2UIComponentsMonobehaviour
{
	private Camera m_miniMapCamera;

	private BattleCameraController m_mainCameraCtl;

	private bool m_rightButtonDown;

	public Action<Rect> minimapCameraRectUpdated;

	protected override void Awake()
	{
	}

	private void Start()
	{
	}

	public void EnableCamera()
	{
	}

	public void DisableCamera()
	{
	}

	private void OnDisable()
	{
	}

	private void OnEnable()
	{
	}

	public void CloseWindow()
	{
	}

	private void Update()
	{
	}

	public void MoveBorder(Vector3 pos)
	{
	}

	public void ClosePanel()
	{
	}
}
