using System;
using System.Collections.Generic;
using System.Xml;

[Serializable]
public class EventCmd830 : EventCmdBase
{
	public static readonly string ms_code;

	public List<LabelChangeInfo> labelChangeInfos;

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
