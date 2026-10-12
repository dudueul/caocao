using System;
using System.Xml;

[Serializable]
public class EventCmd0x3F : EventCmdBase
{
	public static readonly string ms_code;

	public int nParam;

	public EnumData.CompareSign nCalSignIndex;

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
