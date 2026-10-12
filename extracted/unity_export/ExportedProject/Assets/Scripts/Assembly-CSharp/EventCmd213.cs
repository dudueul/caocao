using System;
using System.Xml;

[Serializable]
public class EventCmd213 : EventCmdBase
{
	public static readonly string ms_code;

	public int rootIndex;

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

	public override int GetRouteIndex()
	{
		return 0;
	}

	protected override void ImportXmlData(XmlNode xmlNode)
	{
	}
}
