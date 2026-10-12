using System;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

public class UserBattleFormation : ITransmittable
{
	private ObscuredULong m_srl;

	private string m_formationName;

	private ObscuredInt m_level;

	private BattleFormation m_battleFormation;

	public ulong srl
	{
		get
		{
			return 0uL;
		}
	}

	public string formationName
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int level
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public Action deserializeCallback { get; set; }

	public BattleFormation battleFormation
	{
		get
		{
			return null;
		}
	}

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	public void CopyTo(UserBattleFormation dst)
	{
	}

	private BattleFormation GetBattleFormation()
	{
		return null;
	}

	public int GetMaxLevel()
	{
		return 0;
	}

	public BattleFormationFunc GetBattleFormationFunc(int level)
	{
		return null;
	}

	public int GetEffectValue(EnumData.BattleFormationEffectType targetEffectType)
	{
		return 0;
	}

	public int GetEffectValue(EnumData.BattleFormationEffectType targetEffectType, int level)
	{
		return 0;
	}

	public void ApplyDeck(BattleFormation battleFormation)
	{
	}
}
