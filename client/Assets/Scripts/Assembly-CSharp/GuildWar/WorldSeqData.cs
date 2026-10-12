using System;
using System.Collections.Generic;

namespace GuildWar
{
	public abstract class WorldSeqData : IDataTableOwner, IDataType<WorldSeqData>, IClientDataOwner<WorldSeqData.ClientData>, IDataRefAble<WorldSeqData>, IDataType, IDataUpdateListener<WorldSeqData>
	{
		public class ClientData : IClientDataType
		{
		}

		private HashSet<IDataRefInternal<WorldSeqData>> m_dataRefs;

		public abstract Action<WorldSeqData> onUpdate { get; set; }

		public abstract ClientData clientData { get; }

		public abstract short clan_db_shard { get; }

		public abstract int world_id { get; }

		public abstract int instance_index { get; }

		public abstract int seq { get; }

		public abstract int tick { get; }

		protected void OnInited(IDataContainerCreator factory)
		{
		}

		protected void OnModified(IDataContainerLinker factory)
		{
		}

		protected void OnDestroy()
		{
		}

		public abstract DataTables GetDataTables();

		public void AddDataRef(IDataRefInternal<WorldSeqData> dataRef)
		{
		}

		public void RemoveDataRef(IDataRefInternal<WorldSeqData> dataRef)
		{
		}

		public void ResetDataRefsAll()
		{
		}
	}
}
