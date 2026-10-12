using System;
using System.Collections.Generic;
using System.Xml;

[Serializable]
public class EventCmd0x4A : EventCmdBase
{
	public static readonly string ms_code;

	public int battleUnits;

	public List<int> lPickedUp;

	public List<int> lLimitedOut;

	public List<UnitSaveFlag> unitSaveFlag;

	public List<SaveFlagInfo> saveFlagInfos;

	public override string ToString()
	{
		return null;
	}

	public override void Run()
	{
	}

	private bool GetSaveFlagState()
	{
		return false;
	}

	private void AddItem(int nUnitID, bool bIsPickedUp)
	{
	}

	private bool FindUnitItem(int nUnitID)
	{
		return false;
	}

	protected override void ImportXmlData(XmlNode xmlNode)
	{
	}
}
