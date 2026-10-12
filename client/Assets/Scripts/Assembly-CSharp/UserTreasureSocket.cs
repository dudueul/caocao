using System;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

[Serializable]
public class UserTreasureSocket : ITransmittable
{
	private ObscuredULong m_srl;

	private ObscuredULong m_userId;

	private ObscuredInt m_socketId;

	private ObscuredULong m_itemSrl;

	private ObscuredInt m_passiveId;

	private ObscuredInt m_prevPassiveId;

	private ObscuredString m_suggestedPassive;

	private int[] m_suggestedPassiveIDs;

	public ulong srl
	{
		get
		{
			return 0uL;
		}
	}

	public ulong uid
	{
		get
		{
			return 0uL;
		}
	}

	public int socketId
	{
		get
		{
			return 0;
		}
	}

	public ulong itemSrl
	{
		get
		{
			return 0uL;
		}
	}

	public int passiveId
	{
		get
		{
			return 0;
		}
	}

	public int prevPassiveId
	{
		get
		{
			return 0;
		}
	}

	public int[] suggestedPassiveIDs
	{
		get
		{
			return null;
		}
	}

	public Action deserializeCallback { get; set; }

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	private void DeserializeSuggestPassiveJson()
	{
	}

	public static UserTreasureSocket MakeBaseTreasureSocket(ulong itemSrl, int passiveId)
	{
		return null;
	}
}
