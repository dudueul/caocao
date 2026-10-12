using System;
using System.Xml;
using Caocao;

[Serializable]
public class EventCmd829 : EventCmdBase
{
	public static readonly string ms_code;

	public UnitSide turnSide;

	public override string ToString()
	{
		return null;
	}

	public override void Run()
	{
	}

	public void PassTurnTo()
	{
	}

	protected override void ImportXmlData(XmlNode xmlNode)
	{
	}
}
