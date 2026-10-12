using System;
using System.Collections;
using System.Diagnostics;
using UnityEngine;

public class DwarfBrowser : CaocaoUIModalPopup
{
	private readonly string dwarfAxePath;

	private string m_url;

	private int m_width;

	private int m_height;

	private int m_dwarfBrowserID;

	private bool m_objectFocused;

	private DwarfPluginHelper m_dwarfHelper;

	private Action<string> m_onDwarfFinish__BackingField;

	private byte[] m_errorBuffer;

	private byte[] m_urlBuffer;

	private Bounds m_textureBounds;

	private int m_offsetX;

	private int m_offsetY;

	private UITexture m_texture;

	private bool m_enableClose;

	private bool m_finished;

	private static int m_dwarfReferenceCount;

	private event Action<string> m_onDwarfFinish
	{
		add
		{
		}
		remove
		{
		}
	}

	public static void Show(string url, Action<string> callback)
	{
	}

	public static void Show(string url, int width, int height, bool enableClose, Action<string> callback)
	{
	}

	private void Start()
	{
	}

	protected override void OnDestroy()
	{
	}

	private void OnEnable()
	{
	}

	private void OnDisable()
	{
	}

	private void StartDwarf()
	{
	}

	private void StopDwarf()
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

	private void Update()
	{
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

	private void OnGUI()
	{
	}

	protected override void ShowPanelImpl()
	{
	}

	protected override void ClosePanelImpl()
	{
	}

	public override void OnFocusOn()
	{
	}

	public override void OnFocusOut()
	{
	}

	public override void OnTappingOutSide()
	{
	}
}
