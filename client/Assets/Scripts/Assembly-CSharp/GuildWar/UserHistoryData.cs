using System;
using System.Collections.Generic;

namespace GuildWar
{
	public abstract class UserHistoryData : IDataTableOwner, IDataType<UserHistoryData>, IKeyOwner<UserHistoryKey>, IClientDataOwner<UserHistoryData.ClientData>, IDataRefAble<UserHistoryData>, IDataType, IDataUpdateListener<UserHistoryData>
	{
		public class ClientData : IClientDataType
		{
			public DataRef<UserData> user;
		}

		private HashSet<IDataRefInternal<UserHistoryData>> m_dataRefs;

		public UserData user
		{
			get
			{
				return null;
			}
		}

		public abstract Action<UserHistoryData> onUpdate { get; set; }

		public abstract ClientData clientData { get; }

		public abstract ulong guild_id { get; }

		public abstract ulong user_id { get; }

		public abstract int history_id { get; }

		public abstract int history_key { get; }

		public abstract int history_value { get; }

		public abstract int latest_date { get; }

		protected void OnInited(IDataContainerCreator factory)
		{
		}

		protected void OnModified(IDataContainerLinker<UserHistoryKey> factory)
		{
		}

		protected void OnDestroy()
		{
		}

		public abstract DataTables GetDataTables();

		public abstract UserHistoryKey GetKey();

		public void AddDataRef(IDataRefInternal<UserHistoryData> dataRef)
		{
		}

		public void RemoveDataRef(IDataRefInternal<UserHistoryData> dataRef)
		{
		}

		public void ResetDataRefsAll()
		{
		}
	}
}
