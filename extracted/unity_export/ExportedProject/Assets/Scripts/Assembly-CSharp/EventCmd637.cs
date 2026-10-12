using System;
using System.Xml;
using Caocao;

[Serializable]
public class EventCmd637 : EventCmdBase
{
	public static readonly string ms_code;

	public int bfIndex;

	public IntVector2 coord;

	public UnitDirection direct;

	public int aiType;

	public TargetInfo aiTargetUnitInfo;

	public IntVector2 aiTargetPos;

	public override string ToString()
	{
		return null;
	}

	public override void Run()
	{
	}

	public override void _Apply()
	{
	}

	public override void _Revert()
	{
	}

	protected override void ImportXmlData(XmlNode xmlNode)
	{
	}
}
