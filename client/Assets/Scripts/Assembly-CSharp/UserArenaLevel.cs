using System;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

[Serializable]
public class UserArenaLevel : ITransmittable
{
	private ObscuredULong m_srl;

	private ObscuredULong m_userId;

	private ObscuredInt m_level;

	private ObscuredInt m_stageId;

	private ObscuredString m_npcGroup;

	private ObscuredInt m_clear;

	private ObscuredInt m_mission1;

	private ObscuredInt m_mission2;

	private ObscuredInt m_mission3;

	private ObscuredString m_supplyList;

	private ObscuredString m_decideList;

	private ObscuredInt m_battleCnt;

	private ObscuredInt m_refreshCnt;

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

	public int level
	{
		get
		{
			return 0;
		}
	}

	public int stageId
	{
		get
		{
			return 0;
		}
	}

	public JsonData npcGroup { get; private set; }

	public bool clear
	{
		get
		{
			return false;
		}
	}

	public bool mission1
	{
		get
		{
			return false;
		}
	}

	public bool mission2
	{
		get
		{
			return false;
		}
	}

	public bool mission3
	{
		get
		{
			return false;
		}
	}

	public int stamp
	{
		get
		{
			return 0;
		}
	}

	public JsonData supplyList { get; private set; }

	public JsonData decideList { get; private set; }

	public int battleCnt
	{
		get
		{
			return 0;
		}
	}

	public int refreshCnt
	{
		get
		{
			return 0;
		}
	}

	public Action deserializeCallback { get; set; }

	public override void Serialize(JsonWriter writer)
	{
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	public ArenaLevel GetPd()
	{
		return null;
	}

	public ArenaStage GetStagePd()
	{
		return null;
	}

	public ArenaNPCFormation GetNpcFormationPd()
	{
		return null;
	}
}
