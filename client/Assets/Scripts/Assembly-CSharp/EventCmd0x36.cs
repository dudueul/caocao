using System;
using System.Collections.Generic;
using System.Xml;

[Serializable]
public class EventCmd0x36 : EventCmdBase
{
	public static readonly string ms_code;

	public TargetInfo targetUnitInfo;

	public List<NumericAttribution> numericAttributions;

	public List<EnumData.Conditions> trueConditions;

	public List<EnumData.Conditions> falseConditions;

	public List<EnumData.spAction> excludePassives;

	public bool useCheckEmpty;

	public bool useMultiTargetOption;

	public bool checkAnyTarget;

	public override string ToString()
	{
		return null;
	}

	public override void Run()
	{
	}

	public bool CheckUnitNumericAttribution(UnitData unitData, NumericAttribution numericAttribution)
	{
		return false;
	}

	protected override void ImportXmlData(XmlNode xmlNode)
	{
	}
}
