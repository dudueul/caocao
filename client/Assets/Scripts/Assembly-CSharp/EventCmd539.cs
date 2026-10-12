using System;
using System.Collections.Generic;
using System.Xml;

[Serializable]
public class EventCmd539 : EventCmdBase
{
	public static readonly string ms_code;

	public List<int> mapObjectIds;

	public List<IntVector2> positions;

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
