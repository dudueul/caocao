using System;
using System.Xml;
using Caocao;

[Serializable]
public class EventCmd525 : EventCmdBase
{
	public static readonly string ms_code;

	public Caocao.Weather nextWeather;

	public Caocao.Weather beforeNextWeather;

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

	public override void _Revert()
	{
	}

	protected override void ImportXmlData(XmlNode xmlNode)
	{
	}
}
