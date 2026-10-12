using System;
using System.Collections.Generic;

namespace GuildWar
{
	public abstract class VisionBuildingData : IDataTableOwner, IDataType<VisionBuildingData>, IKeyOwner<VisionBuildingKey>, IClientDataOwner<VisionBuildingData.ClientData>, IDataRefAble<VisionBuildingData>, IDataType, IDataUpdateListener<VisionBuildingData>
	{
		public class ClientData : IClientDataType
		{
			public DataRef<TileData> tile;

			public DataRef<VisionData> guildVision;

			public DataRef<BuildingData> building;
		}

		private HashSet<IDataRefInternal<VisionBuildingData>> m_dataRefs;

		public TileData tile
		{
			get
			{
				return null;
			}
		}

		public VisionData guildVision
		{
			get
			{
				return null;
			}
		}

		public BuildingData building
		{
			get
			{
				return null;
			}
		}

		public abstract Action<VisionBuildingData> onUpdate { get; set; }

		public abstract ClientData clientData { get; }

		public abstract int p_place_id { get; }

		public abstract short pos_x { get; }

		public abstract short pos_y { get; }

		public abstract ulong guild_id { get; }

		protected void OnInited(IDataContainerCreator factory)
		{
		}

		protected void OnModified(IDataContainerLinker<VisionBuildingKey> factory)
		{
		}

		protected void OnDestroy()
		{
		}

		public abstract DataTables GetDataTables();

		public abstract VisionBuildingKey GetKey();

		public void AddDataRef(IDataRefInternal<VisionBuildingData> dataRef)
		{
		}

		public void RemoveDataRef(IDataRefInternal<VisionBuildingData> dataRef)
		{
		}

		public void ResetDataRefsAll()
		{
		}
	}
}
