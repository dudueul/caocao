using System;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

[Serializable]
public class UserArenaScenario : ITransmittable
{
	private ObscuredULong m_srl;

	private ObscuredULong m_userId;

	private ObscuredInt m_scenarioId;

	private ObscuredInt m_purchased;

	private ObscuredInt m_tryCnt;

	private ObscuredInt m_winCnt;

	private ObscuredInt m_clearCnt;

	private ObscuredInt m_perfectCnt;

	private ObscuredInt m_bestLevel;

	private ObscuredInt m_bestStamp;

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

	public int scenarioId
	{
		get
		{
			return 0;
		}
	}

	public bool purchased
	{
		get
		{
			return false;
		}
	}

	public int tryCnt
	{
		get
		{
			return 0;
		}
	}

	public int winCnt
	{
		get
		{
			return 0;
		}
	}

	public int clearCnt
	{
		get
		{
			return 0;
		}
	}

	public int perfectCnt
	{
		get
		{
			return 0;
		}
	}

	public int bestLevel
	{
		get
		{
			return 0;
		}
	}

	public int bestStamp
	{
		get
		{
			return 0;
		}
	}

	public Action deserializeCallback { get; set; }

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}
}
