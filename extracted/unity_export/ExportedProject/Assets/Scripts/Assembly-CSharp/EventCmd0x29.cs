using System;
using System.Collections;
using System.Diagnostics;
using System.Xml;

[Serializable]
public class EventCmd0x29 : EventCmdBase
{
	public static readonly string ms_code;

	public IntVector2 vPos;

	public int unitID;

	public float delay;

	public int faceType;

	public string editorUnitName;

	public override string ToString()
	{
		return null;
	}

	public override void Run()
	{
	}

	[DebuggerHidden]
	private IEnumerator _AppearPortrait()
	{
		return null;
	}

	protected override void ImportXmlData(XmlNode xmlNode)
	{
	}
}
