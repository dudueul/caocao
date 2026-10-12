using System;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

public class UserRuneItem : ITransmittable, IUpsertedMarkable
{
	private ObscuredULong m_srl;

	private ObscuredULong m_userId;

	private ObscuredInt m_id;

	private ObscuredInt m_amount;

	private ObscuredInt m_passiveId;

	private Rune m_runeInfo;

	private RunePassive m_runePassiveInfo;

	private bool m_isAdded;

	private bool m_isUpdated;

	public Rune runeInfo
	{
		get
		{
			return null;
		}
	}

	public RunePassive runePassiveInfo
	{
		get
		{
			return null;
		}
	}

	public ulong srl
	{
		get
		{
			return 0uL;
		}
	}

	public int id
	{
		get
		{
			return 0;
		}
		private set
		{
		}
	}

	public int amount
	{
		get
		{
			return 0;
		}
	}

	public int passiveId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public string nameWithPrefix
	{
		get
		{
			return null;
		}
	}

	public Action deserializeCallback { get; set; }

	[DoNotJsonfy]
	public bool isAdded
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	[DoNotJsonfy]
	public bool isUpdated
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public void Init(RuneItemSlot runeItemSlot)
	{
	}

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}
}
