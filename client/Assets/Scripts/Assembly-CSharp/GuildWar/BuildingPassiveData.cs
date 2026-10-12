using System;
using System.Collections.Generic;

namespace GuildWar
{
	public abstract class BuildingPassiveData : IDataTableOwner, IDataType<BuildingPassiveData>, IKeyOwner<BuildingPassiveKey>, IClientDataOwner<BuildingPassiveData.ClientData>, IDataRefAble<BuildingPassiveData>, IDataType, IDataUpdateListener<BuildingPassiveData>
	{
		public class ClientData : IClientDataType
		{
			public DataRef<BuildingData> building;

			public int passiveListId;

			public GuildWarPassiveList p_passiveList;

			public GuildWarPassiveAttribute p_passiveAttribute;

			public GuildWarPassive p_passive;
		}

		private HashSet<IDataRefInternal<BuildingPassiveData>> m_dataRefs;

		public BuildingData building
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

		public abstract Action<BuildingPassiveData> onUpdate { get; set; }

		public abstract ClientData clientData { get; }

		public abstract int p_place_id { get; }

		public abstract int passive_index { get; }

		public abstract int p_passive_list { get; }

		public abstract int create_date { get; }

		public abstract int update_date { get; }

		public abstract int end_date { get; }

		protected void OnInited(IDataContainerCreator factory)
		{
		}

		protected void OnModified(IDataContainerLinker<BuildingPassiveKey> factory)
		{
		}

		protected void OnDestroy()
		{
		}

		public abstract DataTables GetDataTables();

		public abstract BuildingPassiveKey GetKey();

		public void AddDataRef(IDataRefInternal<BuildingPassiveData> dataRef)
		{
		}

		public void RemoveDataRef(IDataRefInternal<BuildingPassiveData> dataRef)
		{
		}

		public void ResetDataRefsAll()
		{
		}
	}
}
