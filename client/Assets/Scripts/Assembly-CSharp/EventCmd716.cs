using System;
using System.Xml;

[Serializable]
public class EventCmd716 : EventCmdBase
{
	public static readonly string ms_code;

	public EnumData.CheckUserInfoType targetType;

	public int amount;

	public EnumData.CompareSign calSignIndex;

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

	protected override void ImportXmlData(XmlNode xmlNode)
	{
	}
}
