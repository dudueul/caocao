using System;
using System.Collections.Generic;
using System.Xml;

[Serializable]
public class EventCmd0x06 : EventCmdBase
{
	public static readonly string ms_code;

	public bool forceEnableCaocao;

	public int battleUnits;

	public List<UnitInfo> lPickedUp;

	public List<UnitInfo> lLimitedOut;

	public List<UnitSaveFlag> unitSaveFlag;

	public List<SaveFlagInfo> saveFlagInfos;

	public bool meetTheSaveflagConditions { get; private set; }

	public override string ToString()
	{
		return null;
	}

	private void AddItem(string sName, int nUnitID, bool bIsPickedUp)
	{
	}

	private bool FindUnitItem(int nUnitID)
	{
		return false;
	}

	private bool GetSaveFlagState()
	{
		return false;
	}

	protected override void ImportXmlData(XmlNode xmlNode)
	{
	}
}
