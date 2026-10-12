using System;
using System.Collections;
using System.Diagnostics;
using System.Xml;

[Serializable]
public class EventCmd0x0E : EventCmdBase
{
	public static readonly string ms_code;

	public override string ToString()
	{
		return null;
	}

	public override void Run()
	{
	}

	[DebuggerHidden]
	private IEnumerator RunCoroutine()
	{
		return null;
	}

	[DebuggerHidden]
	private IEnumerator ShowResultPopup()
	{
		return null;
	}

	[DebuggerHidden]
	private IEnumerator WaitAndShowResultPanel()
	{
		return null;
	}

	protected override void ImportXmlData(XmlNode xmlNode)
	{
	}
}
