using System.Collections.Generic;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

public class GuildSiegeWarFacilityWar : ITransmittable
{
	public class MemberData : ITransmittable
	{
		private ObscuredULong m_memberId;

		private ObscuredInt m_slot;

		private ObscuredBool m_recon;

		public ulong memberId
		{
			get
			{
				return 0uL;
			}
		}

		public int slot
		{
			get
			{
				return 0;
			}
		}

		public bool recon
		{
			get
			{
				return false;
			}
		}

		public bool isNpc
		{
			get
			{
				return false;
			}
		}

		public MemberData()
		{
		}

		public MemberData(ulong memberId, int slot)
		{
		}

		protected override void DeserializeImpl(JsonData jsonData)
		{
		}
	}

	private ObscuredULong m_srl;

	private ObscuredULong m_guildId;

	private ObscuredInt m_facilityId;

	private ObscuredInt m_slot;

	private JsonData m_loadedMembers;

	private ObscuredInt m_fall;

	private EnumData.GuildSiegeFacilitySlotType m_slotType;

	private GuildSiegeWarFacility m_facility;

	private GuildSiegeWarFacilityWar[] m_subFacilities;

	public ulong srl
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
	}

	public EnumData.GuildSiegeFacilitySlot slot
	{
		get
		{
			return EnumData.GuildSiegeFacilitySlot.LeftFortress;
		}
	}

	public EnumData.GuildSiegeFacilitySlotType slotType
	{
		get
		{
			return EnumData.GuildSiegeFacilitySlotType.Fortress;
		}
	}

	public List<MemberData> loadedMembers { get; protected set; }

	public bool fall
	{
		get
		{
			return false;
		}
	}

	public int loadedMemberNum
	{
		get
		{
			return 0;
		}
	}

	public int loadedUserNum
	{
		get
		{
			return 0;
		}
	}

	public int loadedNpcNum
	{
		get
		{
			return 0;
		}
	}

	public int level
	{
		get
		{
			return 0;
		}
	}

	public int aliveCount { get; private set; }

	public GuildSiegeWarFacility facility
	{
		get
		{
			return null;
		}
	}

	public GuildSiegeWarFacilityWar[] subFacilities
	{
		get
		{
			return null;
		}
	}

	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	public void SetSubFacilities(GuildSiegeWarFacilityWar facility0, GuildSiegeWarFacilityWar facility1, GuildSiegeWarFacilityWar facility2, GuildSiegeWarFacilityWar common)
	{
	}

	public GuildSiegeWarFacilityWar GetDependentFacilityWar()
	{
		return null;
	}

	public bool HasSubFacilityWar(GuildSiegeWarFacilityWar fwar)
	{
		return false;
	}

	public void UpdateLoadedMember()
	{
	}

	public void Clear()
	{
	}

	public void LoadMember(ulong memberId, int slot)
	{
	}

	public void UnloadMember(ulong memberId)
	{
	}

	public void UpdateAliveDeckCount(GuildData guildData)
	{
	}

	public int GetMemberSlot(ulong memberId)
	{
		return 0;
	}
}
