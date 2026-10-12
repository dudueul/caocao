using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

public class UserQuickBattleDeck : ITransmittable
{
	private ObscuredULong m_srl;

	private ObscuredULong m_userId;

	private ObscuredString m_nickname;

	private ObscuredInt m_battleType;

	private ObscuredInt m_battlemapId;

	private ObscuredInt m_slotNum;

	private ObscuredULong m_unitGroupSrl;

	private ObscuredULong m_formationInfoSrl;

	private ObscuredString unitInfo;

	private ObscuredString formationInfo;

	private List<UserQuickBattleDeckUnit> m_deckUnits;

	public ulong srl
	{
		get
		{
			return 0uL;
		}
	}

	public ulong userId
	{
		get
		{
			return 0uL;
		}
	}

	public string nickname
	{
		get
		{
			return null;
		}
	}

	public int battleType
	{
		get
		{
			return 0;
		}
	}

	public int battlemapId
	{
		get
		{
			return 0;
		}
	}

	public int slotNum
	{
		get
		{
			return 0;
		}
	}

	public ulong unitGroupSrl
	{
		get
		{
			return 0uL;
		}
	}

	public ulong formationInfoSrl
	{
		get
		{
			return 0uL;
		}
	}

	public List<UserQuickBattleDeckUnit> deckUnits
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

	public void UpdateQuickBattleDeckUnit()
	{
	}
}
