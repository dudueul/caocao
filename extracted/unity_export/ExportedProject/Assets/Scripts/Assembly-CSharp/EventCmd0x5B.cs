using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using System.Xml;

[Serializable]
public class EventCmd0x5B : EventCmdBase
{
	public static readonly string ms_code;

	public bool onceShow;

	public List<ImportantArea> addArea;

	public List<ImportantArea> delArea;

	private List<ImportantArea> m_prevImportantArea;

	private bool m_prevRemind;

	public override string ToString()
	{
		return null;
	}

	public override void Run()
	{
	}

	public override void Skip()
	{
	}

	public override void _Apply()
	{
	}

	public override void _Revert()
	{
	}

	[DebuggerHidden]
	private IEnumerator MoveCamera(float duration)
	{
		return null;
	}

	private void ToggleMarkers(bool show)
	{
	}

	protected override void ImportXmlData(XmlNode xmlNode)
	{
	}
}
