using System;
using System.Collections.Generic;

namespace GuildWar
{
	public abstract class TileEffectData : IDataTableOwner, IDataType<TileEffectData>, IKeyOwner<TileEffectKey>, IClientDataOwner<TileEffectData.ClientData>, IDataRefAble<TileEffectData>, IDataType, IDataUpdateListener<TileEffectData>
	{
		public class ClientData : IClientDataType
		{
			public DataRef<WorldData> world;

			public DataRef<TileData> tile;

			public GuildWarTileEffect p_tileEffect;
		}

		private HashSet<IDataRefInternal<TileEffectData>> m_dataRefs;

		public WorldData world
		{
			get
			{
				return null;
			}
		}

		public TileData tile
		{
			get
			{
				return null;
			}
		}

		public GuildWarTileEffect p_tileEffect
		{
			get
			{
				return null;
			}
		}

		public abstract Action<TileEffectData> onUpdate { get; set; }

		public abstract ClientData clientData { get; }

		public abstract short pos_x { get; }

		public abstract short pos_y { get; }

		public abstract int p_tile_effect { get; }

		public abstract int create_date { get; }

		public abstract int update_date { get; }

		public abstract int end_date { get; }

		protected void OnInited(IDataContainerCreator factory)
		{
		}

		protected void OnModified(IDataContainerLinker<TileEffectKey> factory)
		{
		}

		protected void OnDestroy()
		{
		}

		public abstract DataTables GetDataTables();

		public abstract TileEffectKey GetKey();

		public void AddDataRef(IDataRefInternal<TileEffectData> dataRef)
		{
		}

		public void RemoveDataRef(IDataRefInternal<TileEffectData> dataRef)
		{
		}

		public void ResetDataRefsAll()
		{
		}
	}
}
