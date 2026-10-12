using System;
using System.Xml;

[Serializable]
public class EventCmd0x5E : EventCmdBase
{
	public static readonly string ms_code;

	public UnitInfo unitInfo1;

	public UnitInfo unitInfo2;

	public override string ToString()
	{
		return null;
	}

	protected override void ImportXmlData(XmlNode xmlNode)
	{
	}
}
