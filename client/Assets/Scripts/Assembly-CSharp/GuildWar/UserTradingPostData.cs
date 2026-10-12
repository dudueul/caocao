using System;
using System.Collections.Generic;

namespace GuildWar
{
	public abstract class UserTradingPostData : IDataTableOwner, IDataType<UserTradingPostData>, IKeyOwner<UserTradingPostKey>, IClientDataOwner<UserTradingPostData.ClientData>, IDataRefAble<UserTradingPostData>, IDataType, IDataUpdateListener<UserTradingPostData>
	{
		public class ClientData : IClientDataType
		{
			public DataRef<UserData> user;
		}

		private HashSet<IDataRefInternal<UserTradingPostData>> m_dataRefs;

		public UserData user
		{
			get
			{
				return null;
			}
		}

		public abstract Action<UserTradingPostData> onUpdate { get; set; }

		public abstract ClientData clientData { get; }

		public abstract ulong guild_id { get; }

		public abstract ulong user_id { get; }

		public abstract int use_count { get; }

		public abstract int get_gold { get; }

		public abstract int use_gold { get; }

		protected void OnInited(IDataContainerCreator factory)
		{
		}

		protected void OnModified(IDataContainerLinker<UserTradingPostKey> factory)
		{
		}

		protected void OnDestroy()
		{
		}

		public abstract DataTables GetDataTables();

		public abstract UserTradingPostKey GetKey();

		public void AddDataRef(IDataRefInternal<UserTradingPostData> dataRef)
		{
		}

		public void RemoveDataRef(IDataRefInternal<UserTradingPostData> dataRef)
		{
		}

		public void ResetDataRefsAll()
		{
		}
	}
}
