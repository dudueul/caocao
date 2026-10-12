using System;
using System.Xml;

[Serializable]
public class EventCmd0x25 : EventCmdBase
{
	public static readonly string ms_code;

	public IntVector2 vPos;

	public TargetInfo targetUnitInfo;

	public override string ToString()
	{
		return null;
	}

	public override void Run()
	{
	}

	protected override void ImportXmlData(XmlNode xmlNode)
	{
	}
}
