using System;
using System.Xml;

[Serializable]
public class EventCmd0x40 : EventCmdBase
{
	public static readonly string ms_code;

	public EventPrecedence m_nVariableTemp;

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
