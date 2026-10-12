using System;
using System.Collections.Generic;
using Caocao;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

public class ThousandBattleInfo
{
	public class UnitInfoBase
	{
		public ObscuredInt unitId;

		public ObscuredInt weaponTreasureId;

		public ObscuredInt armorTreasureId;

		public ObscuredInt kitTreasureId;

		public List<ObscuredInt> passives;

		public bool used;

		public Dictionary<int, int> scriptPassives;

		public ObscuredInt correctedLevel;

		public void Deserialize(JsonData jsonData)
		{
		}
	}

	public class UnitInfo
	{
		public List<UnitInfoBase> bosses;

		public List<UnitInfoBase> elites;

		public ObscuredInt soldierId;

		public List<UnitInfoBase> soldiers;
	}

	private List<ObscuredULong> m_usedUnits;

	private UnitInfo m_unitInfo;

	public ObscuredInt infoId { get; set; }

	public ObscuredInt currentId { get; set; }

	public ObscuredInt seasonStartStageId { get; set; }

	public List<ObscuredULong> usedUnits
	{
		get
		{
			return null;
		}
	}

	public ObscuredInt maxRecoveryCount { get; set; }

	public ObscuredInt recoveryCount { get; set; }

	public DateTime expireDate { get; set; }

	public ObscuredInt deckCostMultiplier { get; set; }

	public bool isBelowStartStage
	{
		get
		{
			return false;
		}
	}

	public UnitInfo unitInfo
	{
		get
		{
			return null;
		}
	}

	public void Deserialize(JsonData jsonData)
	{
	}

	private void PostProcess()
	{
	}

	private List<EventCmd628> GetEventCmd628(List<EventCmdBase> cmds, ForceFlag forceFlags)
	{
		return null;
	}

	private List<EventCmd629> GetEventCmd629(List<EventCmdBase> cmds, ForceFlag forceFlags)
	{
		return null;
	}

	private List<EventCmd628> GetEventCmd628(List<EventCmdBase> cmds, int bfIndexFrom, int bfIndexTo)
	{
		return null;
	}

	private List<EventCmd629> GetEventCmd629(List<EventCmdBase> cmds, int bfIndexFrom, int bfIndexTo)
	{
		return null;
	}

	private void ExtractScriptInfo()
	{
	}
}
