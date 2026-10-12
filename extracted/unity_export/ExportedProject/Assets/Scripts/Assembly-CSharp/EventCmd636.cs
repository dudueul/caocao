using System;
using System.Collections.Generic;
using System.Xml;
using Caocao;

[Serializable]
public class EventCmd636 : EventCmdBase
{
	public static readonly string ms_code;

	public int slotNumber;

	public List<IntRect> coords;

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
