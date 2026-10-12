using System;
using System.Collections.Generic;
using System.Xml;

[Serializable]
public class EventCmd834 : EventCmdBase
{
	public static readonly string ms_code;

	public int triggerIndex;

	public List<int> trueVars;

	public List<int> falseVars;

	private EventScenario.VariableTrigger prev_trigger;

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
