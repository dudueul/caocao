using System;
using System.Xml;

[Serializable]
public class EventCmd998 : EventCmdBase
{
	public static readonly string ms_code;

	public int funnelGroup;

	public int funnelIdx;

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
