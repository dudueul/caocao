using System;
using System.Xml;

[Serializable]
public class EventCmd0x3A : EventCmdBase
{
	public static readonly string ms_code;

	public int nGoalIndex;

	public int nParam;

	public EnumData.CalculatingSign nCalSignIndex;

	public override string ToString()
	{
		return null;
	}

	protected override void ImportXmlData(XmlNode xmlNode)
	{
	}
}
