using System;
using System.Xml;

[Serializable]
public class EventCmd337 : EventCmdBase
{
	public enum TimeScaleStepType
	{
		x1 = 0,
		x2 = 1,
		x3 = 2,
		x4 = 3
	}

	public static readonly string ms_code;

	public bool reset;

	public TimeScaleStepType timeScaleStep;

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
