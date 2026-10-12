using System;
using System.Collections.Generic;
using UnityEngine;

namespace GuildWar
{
	public abstract class BuildingData : IDataTableOwner, IDataType<BuildingData>, IKeyOwner<BuildingKey>, IClientDataOwner<BuildingData.ClientData>, IDataRefAble<BuildingData>, IDataType, IDataUpdateListener<BuildingData>
	{
		public class ClientData : IClientDataType
		{
			public DataRef<TileData> tile;

			public DataRef<GuildData> ownerGuild;

			public DataRef<WorldData> world;

			public DictionaryRef<BuildingCustomFunctionKey, BuildingCustomFunctionData> customFunctions;

			public DictionaryRef<BuildingPassiveKey, BuildingPassiveData> passiveLists;

			public DictionaryRef<BuildingPassiveDamageKey, BuildingPassiveDamageData> passiveDamages;

			public DictionaryRef<UserKey, UserData> users;

			public DictionaryRef<VisionBuildingKey, VisionBuildingData> visions;

			public int p_buildingId;

			public GuildWarBuilding p_building;

			public GuildWarBuildingBattleData p_buildingBattleData;

			public GuildWarPlace p_place;

			public PassiveMarkers passiveMarkers;

			public PassiveValues passiveValues;

			public PassiveDamageValues passiveDamageValues;

			public TileKey clientVisionKey;

			public ulong clientVisionGuildId;
		}

		private HashSet<IDataRefInternal<BuildingData>> m_dataRefs;

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

		public GuildData ownerGuild
		{
			get
			{
				return null;
			}
		}

		public DictionaryRef<BuildingCustomFunctionKey, BuildingCustomFunctionData> customFunctions
		{
			get
			{
				return null;
			}
		}

		public DictionaryRef<BuildingPassiveKey, BuildingPassiveData> passiveLists
		{
			get
			{
				return null;
			}
		}

		public DictionaryRef<BuildingPassiveDamageKey, BuildingPassiveDamageData> passiveDamages
		{
			get
			{
				return null;
			}
		}

		public DictionaryRef<UserKey, UserData> users
		{
			get
			{
				return null;
			}
		}

		public DictionaryRef<VisionBuildingKey, VisionBuildingData> visions
		{
			get
			{
				return null;
			}
		}

		public GuildWarBuilding p_building
		{
			get
			{
				return null;
			}
		}

		public GuildWarBuildingBattleData p_buildingBattleData
		{
			get
			{
				return null;
			}
		}

		public GuildWarPlace p_place
		{
			get
			{
				return null;
			}
		}

		public bool isInClientVision
		{
			get
			{
				return false;
			}
		}

		public bool isInCameraSight
		{
			get
			{
				return false;
			}
		}

		public bool isInSight
		{
			get
			{
				return false;
			}
		}

		public Color32[] renderingColors
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

		public ulong owner_guild_id
		{
			get
			{
				return 0uL;
			}
		}

		public List<UserData> OnUsers
		{
			get
			{
				return null;
			}
		}

		public bool isAvailable
		{
			get
			{
				return false;
			}
		}

		public bool isAttackable
		{
			get
			{
				return false;
			}
		}

		public int corpsSpawnDistance
		{
			get
			{
				return 0;
			}
		}

		public bool repairing
		{
			get
			{
				return false;
			}
		}

		public int CurrentHP
		{
			get
			{
				return 0;
			}
		}

		public bool IsWater
		{
			get
			{
				return false;
			}
		}

		public Dictionary<int, PassiveValue> corpsPassiveValues
		{
			get
			{
				return null;
			}
		}

		public PassiveValues passiveValues
		{
			get
			{
				return null;
			}
		}

		public PassiveDamageValues passiveDamageValues
		{
			get
			{
				return null;
			}
		}

		public abstract Action<BuildingData> onUpdate { get; set; }

		public abstract ClientData clientData { get; }

		public abstract int p_place_id { get; }

		public abstract short pos_x { get; }

		public abstract short pos_y { get; }

		public abstract ulong guild_id { get; }

		public abstract ulong vision_guild_id { get; }

		public abstract int building_id { get; }

		public abstract int building_hp { get; }

		public abstract int update_date { get; }

		public abstract int lastest_passive_index { get; }

		public abstract int owner_change_date { get; }

		public abstract int auto_attack_tick { get; }

		public abstract int auto_attack_interval_tick { get; }

		public abstract int repair_start_date { get; }

		public abstract int repair_end_date { get; }

		protected void OnInited(IDataContainerCreator factory)
		{
		}

		protected void OnModified(IDataContainerLinker<BuildingKey> factory)
		{
		}

		protected void OnDestroy()
		{
		}

		private void UpdateClientVision()
		{
		}

		private void DestroyClientVision()
		{
		}

		private void OnPassiveDamageAdded(BuildingPassiveDamageKey key, BuildingPassiveDamageData value)
		{
		}

		private void OnPassiveDamageRemoved(BuildingPassiveDamageKey key, BuildingPassiveDamageData value)
		{
		}

		private void OnPassiveDamageUpdated(BuildingPassiveDamageData value)
		{
		}

		private void ResetPassiveMaxValidDate()
		{
		}

		public Color32[] GetGuildColors()
		{
			return null;
		}

		public bool isBuildingPassiveActive(int passiveId)
		{
			return false;
		}

		public List<PassiveMarkerType> GetCorpsPassiveMarkerTypes()
		{
			return null;
		}

		private void UpdatePassiveValues()
		{
		}

		private void UpdatePassiveDamageValues()
		{
		}

		public abstract DataTables GetDataTables();

		public abstract BuildingKey GetKey();

		public void AddDataRef(IDataRefInternal<BuildingData> dataRef)
		{
		}

		public void RemoveDataRef(IDataRefInternal<BuildingData> dataRef)
		{
		}

		public void ResetDataRefsAll()
		{
		}
	}
}
