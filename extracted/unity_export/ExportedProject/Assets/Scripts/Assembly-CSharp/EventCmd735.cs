using System;
using System.Xml;

[Serializable]
public class EventCmd735 : EventCmdBase
{
	public static readonly string ms_code;

	public TargetInfo targetInfo;

	public bool eventPassive;

	public int passive;

	public int passiveValue;

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
