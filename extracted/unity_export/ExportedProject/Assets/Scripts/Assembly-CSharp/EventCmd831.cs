using System;
using System.Collections.Generic;
using System.Xml;

[Serializable]
public class EventCmd831 : EventCmdBase
{
	public static readonly string ms_code;

	public List<LabelColorChangeInfo> labelColorChangeInfos;

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
