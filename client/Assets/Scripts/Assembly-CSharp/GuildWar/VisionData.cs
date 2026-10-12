using System;
using System.Collections.Generic;

namespace GuildWar
{
	public abstract class VisionData : IDataTableOwner, IDataType<VisionData>, IKeyOwner<VisionKey>, IClientDataOwner<VisionData.ClientData>, IDataRefAble<VisionData>, IDataType, IDataUpdateListener<VisionData>
	{
		public class ClientData : IClientDataType
		{
			public DataRef<TileData> tile;

			public DataRef<GuildData> ownerGuild;

			public DictionaryRef<VisionBuildingKey, VisionBuildingData> buildingVisions;

			public DictionaryRef<VisionCorpsKey, VisionCorpsData> corpsVisions;
		}

		private HashSet<IDataRefInternal<VisionData>> m_dataRefs;

		public TileData tile
		{
			get
			{
				return null;
			}
		}

		public GuildData ownerGuild
		{
			get
			{
				return null;
			}
		}

		public DictionaryRef<VisionBuildingKey, VisionBuildingData> buildingVisions
		{
			get
			{
				return null;
			}
		}

		public DictionaryRef<VisionCorpsKey, VisionCorpsData> corpsVisions
		{
			get
			{
				return null;
			}
		}

		public abstract Action<VisionData> onUpdate { get; set; }

		public abstract ClientData clientData { get; }

		public abstract ulong guild_id { get; }

		public abstract short pos_x { get; }

		public abstract short pos_y { get; }

		public abstract int vision_count { get; }

		protected void OnInited(IDataContainerCreator factory)
		{
		}

		protected void OnModified(IDataContainerLinker<VisionKey> factory)
		{
		}

		protected void OnDestroy()
		{
		}

		public abstract DataTables GetDataTables();

		public abstract VisionKey GetKey();

		public void AddDataRef(IDataRefInternal<VisionData> dataRef)
		{
		}

		public void RemoveDataRef(IDataRefInternal<VisionData> dataRef)
		{
		}

		public void ResetDataRefsAll()
		{
		}
	}
}
