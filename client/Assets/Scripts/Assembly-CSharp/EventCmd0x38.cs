using System;
using System.Xml;

[Serializable]
public class EventCmd0x38 : EventCmdBase
{
	public static readonly string ms_code;

	public UnitInfo unitInfo;

	public EnumData.Attribution nAttributiton;

	public EnumData.CalculatingSign nCalSignIndex;

	public int nParam;

	public override string ToString()
	{
		return null;
	}

	protected override void ImportXmlData(XmlNode xmlNode)
	{
	}
}
