using System;
using System.Collections;
using System.Diagnostics;
using UnityEngine;
using UnityEngine.EventSystems;
using UnityEngine.UI;

public class DwarfCtrl : MonoBehaviour
{
	private static int dwarfReferenceCount;

	private readonly string dwarfAxePath;

	public string url;

	private int dwarfBrowserID;

	private bool objectFocused;

	private DwarfPluginHelper dwarfHelper;

	private GraphicRaycaster m_Raycaster;

	private EventSystem m_EventSystem;

	private Texture orgTexture;

	private Action<string> OnDwarfFinish__BackingField;

	public event Action<string> OnDwarfFinish
	{
		add
		{
		}
		remove
		{
		}
	}

	private void OnEnable()
	{
	}

	private void OnDisable()
	{
	}

	private void Start()
	{
	}

	private void Update()
	{
	}

	private void OnGUI()
	{
	}

	private int GetLength(byte[] bytes, int maxLength)
	{
		return 0;
	}

	[DebuggerHidden]
	private IEnumerator DwarfDetectRoutine()
	{
		return null;
	}

	private void ProcessMouseInput()
	{
	}

	private bool CheckMouseRaycast(GameObject gameObject, out int x, out int y)
	{
		x = default(int);
		y = default(int);
		return false;
	}

	private void ProcessMouseClick()
	{
	}

	private void ProcessMouseMove()
	{
	}

	private void ProcessMouseWheel()
	{
	}

	private void StartDwarf()
	{
	}

	private void StopDwarf()
	{
	}
}
