using System;
using System.Xml;

[Serializable]
public class EventCmd0x26 : EventCmdBase
{
	public static readonly string ms_code;

	public IntVector2 startPos;

	public IntVector2 endPos;

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
