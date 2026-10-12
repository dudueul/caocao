using System;
using System.Collections;
using System.Diagnostics;
using System.Xml;
using Caocao;

[Serializable]
public class EventCmd0x33 : EventCmdBase
{
	public static readonly string ms_code;

	public TargetInfo targetUnitInfo;

	public EnumData.EventUnitAnimFsmState nActionID;

	public UnitDirection nDirect;

	public override string ToString()
	{
		return null;
	}

	public override void Run()
	{
	}

	[DebuggerHidden]
	private IEnumerator __Run(UnitData unitData)
	{
		return null;
	}

	protected override void ImportXmlData(XmlNode xmlNode)
	{
	}
}
