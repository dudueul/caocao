using System;
using System.Collections.Generic;

namespace GuildWar
{
	public abstract class CorpsHistoryData : IDataTableOwner, IDataType<CorpsHistoryData>, IKeyOwner<CorpsHistoryKey>, IClientDataOwner<CorpsHistoryData.ClientData>, IDataRefAble<CorpsHistoryData>, IDataType, IDataUpdateListener<CorpsHistoryData>
	{
		public class ClientData : IClientDataType
		{
		}

		private HashSet<IDataRefInternal<CorpsHistoryData>> m_dataRefs;

		public abstract Action<CorpsHistoryData> onUpdate { get; set; }

		public abstract ClientData clientData { get; }

		public abstract int corps_id { get; }

		public abstract int history_id { get; }

		public abstract int history_key { get; }

		public abstract int history_value { get; }

		public abstract int latest_date { get; }

		protected void OnInited(IDataContainerCreator factory)
		{
		}

		protected void OnModified(IDataContainerLinker<CorpsHistoryKey> factory)
		{
		}

		protected void OnDestroy()
		{
		}

		public abstract DataTables GetDataTables();

		public abstract CorpsHistoryKey GetKey();

		public void AddDataRef(IDataRefInternal<CorpsHistoryData> dataRef)
		{
		}

		public void RemoveDataRef(IDataRefInternal<CorpsHistoryData> dataRef)
		{
		}

		public void ResetDataRefsAll()
		{
		}
	}
}
