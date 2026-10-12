using System;
using System.Collections;
using System.Diagnostics;
using System.Xml;

[Serializable]
public class EventCmd329 : EventCmdBase
{
	public static readonly string ms_code;

	public int townId;

	public int x;

	public int y;

	public override string ToString()
	{
		return null;
	}

	public override void Run()
	{
	}

	[DebuggerHidden]
	private IEnumerator Wait(float time)
	{
		return null;
	}

	[DebuggerHidden]
	private IEnumerator WaitCameraMoveFinish(float time)
	{
		return null;
	}

	protected override void ImportXmlData(XmlNode xmlNode)
	{
	}
}
