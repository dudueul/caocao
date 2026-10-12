using System;
using System.Xml;

[Serializable]
public class EventCmd824 : EventCmdBase
{
	public static readonly string ms_code;

	public int seconds;

	public bool wait;

	public override string ToString()
	{
		return null;
	}

	public override void Run()
	{
	}

	private void OnCompleteFadeOut()
	{
	}

	protected override void ImportXmlData(XmlNode xmlNode)
	{
	}
}
