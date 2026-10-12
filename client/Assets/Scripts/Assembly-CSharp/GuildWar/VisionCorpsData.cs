using System;
using System.Collections.Generic;

namespace GuildWar
{
	public abstract class VisionCorpsData : IDataTableOwner, IDataType<VisionCorpsData>, IKeyOwner<VisionCorpsKey>, IClientDataOwner<VisionCorpsData.ClientData>, IDataRefAble<VisionCorpsData>, IDataType, IDataUpdateListener<VisionCorpsData>
	{
		public class ClientData : IClientDataType
		{
			public DataRef<TileData> tile;

			public DataRef<VisionData> guildVision;

			public DataRef<CorpsData> corps;
		}

		private HashSet<IDataRefInternal<VisionCorpsData>> m_dataRefs;

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

		public CorpsData corps
		{
			get
			{
				return null;
			}
		}

		public abstract Action<VisionCorpsData> onUpdate { get; set; }

		public abstract ClientData clientData { get; }

		public abstract int corps_id { get; }

		public abstract short pos_x { get; }

		public abstract short pos_y { get; }

		public abstract ulong guild_id { get; }

		protected void OnInited(IDataContainerCreator factory)
		{
		}

		protected void OnModified(IDataContainerLinker<VisionCorpsKey> factory)
		{
		}

		protected void OnDestroy()
		{
		}

		public abstract DataTables GetDataTables();

		public abstract VisionCorpsKey GetKey();

		public void AddDataRef(IDataRefInternal<VisionCorpsData> dataRef)
		{
		}

		public void RemoveDataRef(IDataRefInternal<VisionCorpsData> dataRef)
		{
		}

		public void ResetDataRefsAll()
		{
		}
	}
}
