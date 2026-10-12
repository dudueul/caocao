using System;
using System.Runtime.InteropServices;

internal class DwarfPluginHelper
{
	[PreserveSig]
	private static extern void InitializeDwarf(string dwarfAxePath, bool isRelativePath);

	public void Invoke_InitializeDwarf(string dwarfAxePath, bool isRelativePath = false)
	{
	}

	[PreserveSig]
	private static extern void CleanupDwarf();

	public void Invoke_CleanupDwarf()
	{
	}

	[PreserveSig]
	private static extern int StartBrowser(int width, int height, string url);

	public int Invoke_StartBrowser(int width, int height, string url)
	{
		return 0;
	}

	[PreserveSig]
	private static extern int GetDwarfError(byte[] errorMsgBuffer, int bufferLength);

	public int Invoke_GetDwarfError(byte[] errorMsgBuffer, int bufferLength)
	{
		return 0;
	}

	[PreserveSig]
	private static extern void StopBrowser(int id);

	public void Invoke_StopBrowser(int id)
	{
	}

	[PreserveSig]
	private static extern bool IsClosedBrowser(int id);

	public bool Invoke_IsClosedBrowser(int id)
	{
		return false;
	}

	[PreserveSig]
	private static extern bool GetTextureView(int id, ref IntPtr textureView);

	public bool Invoke_GetTextureView(int id, ref IntPtr textureView)
	{
		return false;
	}

	[PreserveSig]
	private static extern bool SetCurrentUrl(int id, string url);

	public bool Invoke_SetCurrentUrl(int id, string url)
	{
		return false;
	}

	[PreserveSig]
	private static extern bool GetCurrentUrl(int id, byte[] urlBuffer, int bufferLength);

	public bool Invoke_GetCurrentUrl(int id, byte[] urlBuffer, int bufferLength)
	{
		return false;
	}

	[PreserveSig]
	private static extern void SendMouseClick(int id, DWARF_MOUSE_BTN_TYPE buttonType, int x, int y, bool btnDown);

	public void Invoke_SendMouseClick(int id, DWARF_MOUSE_BTN_TYPE buttonType, int x, int y, bool btnDown)
	{
	}

	[PreserveSig]
	private static extern void SendMouseMove(int id, int x, int y);

	public void Invoke_SendMouseMove(int id, int x, int y)
	{
	}

	[PreserveSig]
	private static extern void SendMouseWheel(int id, int x, int y, float delta);

	public void Invoke_SendMouseWheel(int id, int x, int y, float delta)
	{
	}

	[PreserveSig]
	private static extern void SendKeyboardEvent(int id, int keyCode, bool keyDown);

	public void Invoke_SendKeyboardEvent(int id, int keyCode, bool keyDown)
	{
	}

	[PreserveSig]
	private static extern void RegisterBrowserFinishCallback(int id, FuncType_OnBrowserFinished proc);

	public void Invoke_RegisterBrowserFinishCallback(int id, FuncType_OnBrowserFinished proc)
	{
	}

	[PreserveSig]
	private static extern void RegisterPopupCallback(int id, FuncType_OnBrowserPopup proc);

	public void Invoke_RegisterPopupCallback(int id, FuncType_OnBrowserPopup proc)
	{
	}

	[PreserveSig]
	private static extern void RegisterJSObjectCallback(int id, FuncType_OnBrowserJSObjectCallback proc);

	public void Invoke_RegisterJSObjectCallback(int id, FuncType_OnBrowserJSObjectCallback proc)
	{
	}

	[PreserveSig]
	private static extern void RegisterUrlChangedCallback(int id, FuncType_OnBrowserUrlChanged proc);

	public void Invoke_RegisterUrlChangedCallback(int id, FuncType_OnBrowserUrlChanged proc)
	{
	}
}
