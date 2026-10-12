using System;
using System.Collections.Generic;

namespace GuildWar
{
	public abstract class UserData : IDataTableOwner, IDataType<UserData>, IKeyOwner<UserKey>, IClientDataOwner<UserData.ClientData>, IDataRefAble<UserData>, IDataType, IDataUpdateListener<UserData>
	{
		public class ClientData : IClientDataType
		{
			public DataRef<GuildData> guild;

			public DataRef<BuildingData> building;

			public DataRef<UserStatsData> stats;

			public DataRef<GuildMemberGradeData> memberGrade;

			public DataRef<UserTradingPostData> tradingPost;

			public DictionaryRef<UserCorpsKey, UserCorpsData> userCorps;

			public DictionaryRef<UserHistoryKey, UserHistoryData> histories;

			public DictionaryRef<UserSiegeWeaponKey, UserSiegeWeaponData> siegeWeapons;
		}

		private HashSet<IDataRefInternal<UserData>> m_dataRefs;

		public GuildData guild
		{
			get
			{
				return null;
			}
		}

		public BuildingData building
		{
			get
			{
				return null;
			}
		}

		public UserStatsData stats
		{
			get
			{
				return null;
			}
		}

		public GuildMemberGradeData memberGrade
		{
			get
			{
				return null;
			}
		}

		public UserTradingPostData tradingPost
		{
			get
			{
				return null;
			}
		}

		public DictionaryRef<UserCorpsKey, UserCorpsData> userCorps
		{
			get
			{
				return null;
			}
		}

		public DictionaryRef<UserHistoryKey, UserHistoryData> histories
		{
			get
			{
				return null;
			}
		}

		public DictionaryRef<UserSiegeWeaponKey, UserSiegeWeaponData> siegeWeapons
		{
			get
			{
				return null;
			}
		}

		public ulong UserId
		{
			get
			{
				return 0uL;
			}
		}

		public GuildWarUserJobGrade UserGrade
		{
			get
			{
				return GuildWarUserJobGrade.None;
			}
		}

		public string Nation_Name
		{
			get
			{
				return null;
			}
		}

		public abstract Action<UserData> onUpdate { get; set; }

		public abstract ClientData clientData { get; }

		public abstract ulong guild_id { get; }

		public abstract ulong user_id { get; }

		public abstract int face_unit_id { get; }

		public abstract int face_skinitem_id { get; }

		public abstract string nation_name { get; }

		public abstract int p_place_id { get; }

		public abstract int contribution { get; }

		public abstract int create_date { get; }

		protected void OnInited(IDataContainerCreator factory)
		{
		}

		protected void OnModified(IDataContainerLinker<UserKey> factory)
		{
		}

		protected void OnDestroy()
		{
		}

		public abstract DataTables GetDataTables();

		public abstract UserKey GetKey();

		public void AddDataRef(IDataRefInternal<UserData> dataRef)
		{
		}

		public void RemoveDataRef(IDataRefInternal<UserData> dataRef)
		{
		}

		public void ResetDataRefsAll()
		{
		}
	}
}
