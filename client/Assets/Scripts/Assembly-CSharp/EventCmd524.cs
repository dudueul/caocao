using System;
using System.Xml;
using Caocao;

[Serializable]
public class EventCmd524 : EventCmdBase
{
	public static readonly string ms_code;

	public Caocao.Weather newWeather;

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
