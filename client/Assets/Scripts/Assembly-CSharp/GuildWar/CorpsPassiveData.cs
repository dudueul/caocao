using System;
using System.Collections.Generic;

namespace GuildWar
{
	public abstract class CorpsPassiveData : IDataTableOwner, IDataType<CorpsPassiveData>, IKeyOwner<CorpsPassiveKey>, IClientDataOwner<CorpsPassiveData.ClientData>, IDataRefAble<CorpsPassiveData>, IDataType, IDataUpdateListener<CorpsPassiveData>
	{
		public class ClientData : IClientDataType
		{
			public DataRef<CorpsData> corps;

			public int passiveListId;

			public GuildWarPassiveList p_passiveList;

			public GuildWarPassiveAttribute p_passiveAttribute;

			public GuildWarPassive p_passive;
		}

		private HashSet<IDataRefInternal<CorpsPassiveData>> m_dataRefs;

		public CorpsData corps
		{
			get
			{
				return null;
			}
		}

		public GuildWarPassiveList p_passiveList
		{
			get
			{
				return null;
			}
		}

		public GuildWarPassiveAttribute p_passiveAttribute
		{
			get
			{
				return null;
			}
		}

		public GuildWarPassive p_passive
		{
			get
			{
				return null;
			}
		}

		public abstract Action<CorpsPassiveData> onUpdate { get; set; }

		public abstract ClientData clientData { get; }

		public abstract int corps_id { get; }

		public abstract int passive_index { get; }

		public abstract int p_passive_list { get; }

		public abstract int create_date { get; }

		public abstract int update_date { get; }

		public abstract int end_date { get; }

		protected void OnInited(IDataContainerCreator factory)
		{
		}

		protected void OnModified(IDataContainerLinker<CorpsPassiveKey> factory)
		{
		}

		protected void OnDestroy()
		{
		}

		public abstract DataTables GetDataTables();

		public abstract CorpsPassiveKey GetKey();

		public void AddDataRef(IDataRefInternal<CorpsPassiveData> dataRef)
		{
		}

		public void RemoveDataRef(IDataRefInternal<CorpsPassiveData> dataRef)
		{
		}

		public void ResetDataRefsAll()
		{
		}
	}
}
