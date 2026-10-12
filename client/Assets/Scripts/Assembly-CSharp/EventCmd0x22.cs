using System;
using System.Xml;

[Serializable]
public class EventCmd0x22 : EventCmdBase
{
	public static readonly string ms_code;

	public int m_nVariableTemp;

	public override string ToString()
	{
		return null;
	}

	protected override void ImportXmlData(XmlNode xmlNode)
	{
	}
}
