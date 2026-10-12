using System;
using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

public class GuildSiegeWarDefenceDeck : UserUnitGroup
{
	public class UserInfo
	{
		public ObscuredULong userId;

		public ObscuredInt slot;

		public void Deserialize(JsonData jsonData)
		{
		}
	}

	public interface IDefenceInfo
	{
		bool validArrange { get; }

		int slot { get; }

		int coordIdx { get; }

		int coordPos { get; }

		int pos { get; }

		void Reset();

		void UpdateArrange();

		void SetArrange(int slot, int coordIdx, int coordPos, int bfIdx);
	}

	public class DefenceUnitInfo : BattleFieldDefenseUnit, IDefenceInfo
	{
		public const int SLOT_DIGIT = 1000;

		public const int COORDIDX_DIGIT = 100;

		public ObscuredInt unitDataId;

		public ObscuredInt equipDataId;

		public ObscuredInt arrange;

		public ObscuredInt hp;

		public bool validArrange
		{
			get
			{
				return false;
			}
		}

		public int slot { get; protected set; }

		public int coordIdx { get; protected set; }

		public int coordPos { get; protected set; }

		public bool isFallen
		{
			get
			{
				return false;
			}
		}

		public DefenceUnitInfo()
		{
		}

		public DefenceUnitInfo(int pos, int unitId, ulong weaponSrl, ulong armorSrl, ulong kitSrl, int skinItemId)
		{
		}

		public void Reset()
		{
		}

		public override void Deserialize(JsonData jsonData)
		{
		}

		public void UpdateArrange()
		{
		}

		public override UnitInfo DeepCopy()
		{
			return null;
		}

		public void SetArrange(int slot, int coordIdx, int coordPos, int bfIdx = -1)
		{
		}
	}

	public class DefenceObstacleInfo : IDefenceInfo
	{
		public ObscuredInt unitType;

		public ObscuredInt subType;

		public ObscuredInt arrange;

		public ObscuredInt Pos;

		public bool validArrange
		{
			get
			{
				return false;
			}
		}

		public int slot { get; protected set; }

		public int coordIdx { get; protected set; }

		public int coordPos { get; protected set; }

		public int pos { get; protected set; }

		public int arrangeSrl { get; set; }

		public EnumData.GuildSiegeObjectType objType
		{
			get
			{
				return EnumData.GuildSiegeObjectType.Tower;
			}
		}

		public DefenceObstacleInfo()
		{
		}

		public DefenceObstacleInfo(int pos, EnumData.GuildSiegeObjectType type)
		{
		}

		public void Reset()
		{
		}

		public void Deserialize(JsonData jsonData)
		{
		}

		public void UpdateArrange()
		{
		}

		public DefenceObstacleInfo DeepCopy()
		{
			return null;
		}

		public void SetArrange(int slot, int coordIdx, int coordPos, int bfIdx)
		{
		}

		public void SetSubType(int subTypeIdx)
		{
		}
	}

	private UserInfo m_userInfo;

	private ObscuredULong m_guildId;

	private JsonData m_facilityIdData;

	private JsonData m_unitAi;

	private JsonData m_obstacleData;

	private ObscuredInt m_averageLv;

	private DateTime m_reconDate;

	private ObscuredULong m_lockUser;

	private DateTime m_lockDate;

	private JsonData m_unitListData;

	private List<DefenceObstacleInfo> m_obstacleList;

	private ObscuredInt m_facilityId;

	private bool m_isFallen;

	public UserInfo userInfo
	{
		get
		{
			return null;
		}
	}

	public ulong userId
	{
		get
		{
			return 0uL;
		}
	}

	public ulong guildId
	{
		get
		{
			return 0uL;
		}
	}

	public int facilityId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int averageLv
	{
		get
		{
			return 0;
		}
	}

	public List<DefenceObstacleInfo> obstacleList
	{
		get
		{
			return null;
		}
	}

	public JsonData unitListData
	{
		get
		{
			return null;
		}
	}

	public ulong lockUser
	{
		get
		{
			return 0uL;
		}
	}

	public DateTime lockDate
	{
		get
		{
			return default(DateTime);
		}
	}

	public bool isLocked
	{
		get
		{
			return false;
		}
	}

	public bool recon
	{
		get
		{
			return false;
		}
	}

	public bool isFallen
	{
		get
		{
			return false;
		}
	}

	protected override UnitInfo GenerateUnitInfo()
	{
		return null;
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	private void DeserializeObstacles(JsonData obstaclesData)
	{
	}

	public void Revert()
	{
	}

	public void RemoveUnit(int unitId)
	{
	}

	public void RemoveObstacle(int arrangeSrl)
	{
	}
}
