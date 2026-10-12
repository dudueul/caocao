using System;
using System.Collections.Generic;

namespace GuildWar
{
	public abstract class BuildingCustomFunctionData : IDataTableOwner, IDataType<BuildingCustomFunctionData>, IKeyOwner<BuildingCustomFunctionKey>, IClientDataOwner<BuildingCustomFunctionData.ClientData>, IDataRefAble<BuildingCustomFunctionData>, IDataType, IDataUpdateListener<BuildingCustomFunctionData>
	{
		public class ClientData : IClientDataType
		{
			public DataRef<BuildingData> building;
		}

		private HashSet<IDataRefInternal<BuildingCustomFunctionData>> m_dataRefs;

		public BuildingData building
		{
			get
			{
				return null;
			}
		}

		public abstract Action<BuildingCustomFunctionData> onUpdate { get; set; }

		public abstract ClientData clientData { get; }

		public abstract int p_place_id { get; }

		public abstract short custom_action_type { get; }

		public abstract int cooldown_date { get; }

		protected void OnInited(IDataContainerCreator factory)
		{
		}

		protected void OnModified(IDataContainerLinker<BuildingCustomFunctionKey> factory)
		{
		}

		protected void OnDestroy()
		{
		}

		public abstract DataTables GetDataTables();

		public abstract BuildingCustomFunctionKey GetKey();

		public void AddDataRef(IDataRefInternal<BuildingCustomFunctionData> dataRef)
		{
		}

		public void RemoveDataRef(IDataRefInternal<BuildingCustomFunctionData> dataRef)
		{
		}

		public void ResetDataRefsAll()
		{
		}
	}
}
