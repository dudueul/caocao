using System;
using System.Xml;

[Serializable]
public class EventCmd731 : EventCmdBase
{
	public static readonly string ms_code;

	public int triggerIndex;

	public EnumData.EventScenarioTriggerCondition conditionType;

	public EnumData.CompareSign conditionComparison;

	public int conditionIntValue;

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
