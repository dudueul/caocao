using System;
using System.Collections.Generic;

namespace GuildWar
{
	public abstract class GuildHistoryData : IDataTableOwner, IDataType<GuildHistoryData>, IKeyOwner<GuildHistoryKey>, IClientDataOwner<GuildHistoryData.ClientData>, IDataRefAble<GuildHistoryData>, IDataType, IDataUpdateListener<GuildHistoryData>
	{
		public class ClientData : IClientDataType
		{
			public DataRef<GuildData> ownerGuild;
		}

		private HashSet<IDataRefInternal<GuildHistoryData>> m_dataRefs;

		public GuildData ownerGuild
		{
			get
			{
				return null;
			}
		}

		public abstract Action<GuildHistoryData> onUpdate { get; set; }

		public abstract ClientData clientData { get; }

		public abstract ulong guild_id { get; }

		public abstract int history_id { get; }

		public abstract int history_value { get; }

		public abstract int latest_date { get; }

		protected void OnInited(IDataContainerCreator factory)
		{
		}

		protected void OnModified(IDataContainerLinker<GuildHistoryKey> factory)
		{
		}

		protected void OnDestroy()
		{
		}

		public abstract DataTables GetDataTables();

		public abstract GuildHistoryKey GetKey();

		public void AddDataRef(IDataRefInternal<GuildHistoryData> dataRef)
		{
		}

		public void RemoveDataRef(IDataRefInternal<GuildHistoryData> dataRef)
		{
		}

		public void ResetDataRefsAll()
		{
		}
	}
}
