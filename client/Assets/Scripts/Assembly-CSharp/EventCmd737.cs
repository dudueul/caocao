using System;
using System.Xml;

[Serializable]
public class EventCmd737 : EventCmdBase
{
	public static readonly string ms_code;

	public int level;

	public EnumData.CompareSign sign;

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
