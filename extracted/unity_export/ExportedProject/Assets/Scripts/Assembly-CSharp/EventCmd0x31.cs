using System;
using System.Collections;
using System.Diagnostics;
using System.Xml;
using Thingsoft.Pool;

[Serializable]
public class EventCmd0x31 : EventCmdBase
{
	public static readonly string ms_code;

	public int nExtent;

	public UnitInfo unitInfo;

	public IntVector2 vStartPos;

	public IntVector2 vEndPos;

	public EnumData.ForceType nGroupIndex;

	public string editorUnitName;

	public override string ToString()
	{
		return null;
	}

	public override void Run()
	{
	}

	private void FindUnits(Thingsoft.Pool.UnitData units)
	{
	}

	[DebuggerHidden]
	private IEnumerator __Run()
	{
		return null;
	}

	[DebuggerHidden]
	private IEnumerator Disappear(UnitData unit)
	{
		return null;
	}

	protected override void ImportXmlData(XmlNode xmlNode)
	{
	}
}
