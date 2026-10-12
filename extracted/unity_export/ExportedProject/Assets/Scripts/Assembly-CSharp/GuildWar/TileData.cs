using System;
using System.Collections.Generic;
using UnityEngine;

namespace GuildWar
{
	public abstract class TileData : IDataTableOwner, IDataType<TileData>, IKeyOwner<TileKey>, IClientDataOwner<TileData.ClientData>, IDataRefAble<TileData>, IDataType, IDataUpdateListener<TileData>
	{
		public class ClientData : IClientDataType
		{
			public DataRef<WorldData> world;

			public DictionaryRef<BuildingKey, BuildingData> buildings;

			public DictionaryRef<CorpsKey, CorpsData> corps;

			public DictionaryRef<TileEffectKey, TileEffectData> tileEffects;

			public DictionaryRef<VisionKey, VisionData> guildVisions;

			public DictionaryRef<VisionBuildingKey, VisionBuildingData> buildingVisions;

			public DictionaryRef<VisionCorpsKey, VisionCorpsData> corpsVisions;

			public GuildWarTile p_tile;

			public GuildWarTileType p_tileType;

			public bool __isInClientVision;

			public int clientVisionTrianglesIndex;
		}

		public bool isInCameraSight;

		public int moveCost;

		public TileKey moveBeforePath;

		private HashSet<IDataRefInternal<TileData>> m_dataRefs;

		public WorldData world
		{
			get
			{
				return null;
			}
		}

		public DictionaryRef<BuildingKey, BuildingData> buildings
		{
			get
			{
				return null;
			}
		}

		public DictionaryRef<CorpsKey, CorpsData> corps
		{
			get
			{
				return null;
			}
		}

		public DictionaryRef<TileEffectKey, TileEffectData> tileEffects
		{
			get
			{
				return null;
			}
		}

		public DictionaryRef<VisionKey, VisionData> guildVisions
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

		public int p_tile_type_id
		{
			get
			{
				return 0;
			}
		}

		public GuildWarTile p_tile
		{
			get
			{
				return null;
			}
		}

		public GuildWarTileType p_tileType
		{
			get
			{
				return null;
			}
		}

		public Vector2Int GridPos
		{
			get
			{
				return default(Vector2Int);
			}
		}

		public bool isWater
		{
			get
			{
				return false;
			}
		}

		public bool isOnCorps
		{
			get
			{
				return false;
			}
		}

		public CorpsData OnCorps
		{
			get
			{
				return null;
			}
		}

		public bool isOnBuild
		{
			get
			{
				return false;
			}
		}

		public BuildingData OnBuild
		{
			get
			{
				return null;
			}
		}

		public bool isInSight
		{
			get
			{
				return false;
			}
		}

		public bool isInClientVision
		{
			get
			{
				return false;
			}
		}

		public int TileId
		{
			get
			{
				return 0;
			}
		}

		public int TileTypeId
		{
			get
			{
				return 0;
			}
		}

		public Vector3 Pos
		{
			get
			{
				return default(Vector3);
			}
		}

		public abstract Action<TileData> onUpdate { get; set; }

		public abstract ClientData clientData { get; }

		public abstract short pos_x { get; }

		public abstract short pos_y { get; }

		public abstract int p_tile_id { get; }

		public abstract Dictionary<int, List<TileKey>> neighborsTiles { get; }

		protected void OnInited(IDataContainerCreator factory)
		{
		}

		protected void OnModified(IDataContainerLinker<TileKey> factory)
		{
		}

		protected void OnDestroy()
		{
		}

		public void __SetClientVision(ulong guildId, bool visible)
		{
		}

		public bool __UpdateClientVisionObject()
		{
			return false;
		}

		public abstract DataTables GetDataTables();

		public abstract TileKey GetKey();

		public void AddDataRef(IDataRefInternal<TileData> dataRef)
		{
		}

		public void RemoveDataRef(IDataRefInternal<TileData> dataRef)
		{
		}

		public void ResetDataRefsAll()
		{
		}
	}
}
