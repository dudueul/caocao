using System;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

public class UserSoldierDeck : ITransmittable
{
	private ObscuredULong m_srl;

	private ObscuredULong m_uid;

	private ObscuredInt m_deckId;

	private ObscuredInt m_soldierId1;

	private ObscuredInt m_soldierId2;

	private ObscuredInt m_soldierId3;

	private ObscuredInt m_soldierId4;

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

	public int deckId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int soldierId1
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int soldierId2
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int soldierId3
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int soldierId4
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

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	public void SetDefaultDeck()
	{
	}
}
