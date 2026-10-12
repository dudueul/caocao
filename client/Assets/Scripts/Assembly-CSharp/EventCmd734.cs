using System;
using System.Xml;
using Caocao;

[Serializable]
public class EventCmd734 : EventCmdBase
{
	public static readonly string ms_code;

	public UnitSide targetSide;

	public EnumData.CompareSign calSignIndex;

	public int queueCnt;

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
