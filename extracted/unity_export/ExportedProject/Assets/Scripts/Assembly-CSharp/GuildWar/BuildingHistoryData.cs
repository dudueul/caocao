using System;
using System.Collections.Generic;

namespace GuildWar
{
	public abstract class BuildingHistoryData : IDataTableOwner, IDataType<BuildingHistoryData>, IKeyOwner<BuildingHistoryKey>, IClientDataOwner<BuildingHistoryData.ClientData>, IDataRefAble<BuildingHistoryData>, IDataType, IDataUpdateListener<BuildingHistoryData>
	{
		public class ClientData : IClientDataType
		{
		}

		private HashSet<IDataRefInternal<BuildingHistoryData>> m_dataRefs;

		public abstract Action<BuildingHistoryData> onUpdate { get; set; }

		public abstract ClientData clientData { get; }

		public abstract int p_place_id { get; }

		public abstract int history_id { get; }

		public abstract int history_key { get; }

		public abstract int history_value { get; }

		public abstract int latest_date { get; }

		protected void OnInited(IDataContainerCreator factory)
		{
		}

		protected void OnModified(IDataContainerLinker<BuildingHistoryKey> factory)
		{
		}

		protected void OnDestroy()
		{
		}

		public abstract DataTables GetDataTables();

		public abstract BuildingHistoryKey GetKey();

		public void AddDataRef(IDataRefInternal<BuildingHistoryData> dataRef)
		{
		}

		public void RemoveDataRef(IDataRefInternal<BuildingHistoryData> dataRef)
		{
		}

		public void ResetDataRefsAll()
		{
		}
	}
}
