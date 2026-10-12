using System;
using System.Xml;

[Serializable]
public class EventCmd0x52 : EventCmdBase
{
	public static readonly string ms_code;

	public int unitID;

	public int jobIdx;

	public override string ToString()
	{
		return null;
	}

	protected override void ImportXmlData(XmlNode xmlNode)
	{
	}
}
