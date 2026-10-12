using System;
using System.Collections.Generic;

namespace GuildWar
{
	public abstract class UserStatsData : IDataTableOwner, IDataType<UserStatsData>, IKeyOwner<UserStatsKey>, IClientDataOwner<UserStatsData.ClientData>, IDataRefAble<UserStatsData>, IDataType, IDataUpdateListener<UserStatsData>
	{
		public class ClientData : IClientDataType
		{
			public DataRef<UserData> user;
		}

		private HashSet<IDataRefInternal<UserStatsData>> m_dataRefs;

		public UserData user
		{
			get
			{
				return null;
			}
		}

		public abstract Action<UserStatsData> onUpdate { get; set; }

		public abstract ClientData clientData { get; }

		public abstract ulong guild_id { get; }

		public abstract ulong user_id { get; }

		public abstract int base_atk { get; }

		public abstract int base_def { get; }

		public abstract int base_inte { get; }

		public abstract int base_agi { get; }

		public abstract int base_mor { get; }

		public abstract int treasure_point { get; }

		protected void OnInited(IDataContainerCreator factory)
		{
		}

		protected void OnModified(IDataContainerLinker<UserStatsKey> factory)
		{
		}

		protected void OnDestroy()
		{
		}

		public abstract DataTables GetDataTables();

		public abstract UserStatsKey GetKey();

		public void AddDataRef(IDataRefInternal<UserStatsData> dataRef)
		{
		}

		public void RemoveDataRef(IDataRefInternal<UserStatsData> dataRef)
		{
		}

		public void ResetDataRefsAll()
		{
		}
	}
}
