using System;
using System.Collections.Generic;
using UnityEngine;

namespace GuildWar
{
	public abstract class CorpsData : IDataTableOwner, IDataType<CorpsData>, IKeyOwner<CorpsKey>, IClientDataOwner<CorpsData.ClientData>, IDataRefAble<CorpsData>, IDataType, IDataUpdateListener<CorpsData>
	{
		public class ClientData : IClientDataType
		{
			public DataRef<WorldData> world;

			public DataRef<TileData> tile;

			public DataRef<UserCorpsData> userCorps;

			public DataRef<CorpsInformationData> info;

			public DataRef<CorpsSiegeWeaponData> siegeWeapon;

			public DictionaryRef<CorpsCustomFunctionKey, CorpsCustomFunctionData> customFunctions;

			public DictionaryRef<CorpsFunctionKey, CorpsFunctionData> functions;

			public DictionaryRef<CorpsPassiveKey, CorpsPassiveData> passives;

			public DictionaryRef<CorpsPassiveDamageKey, CorpsPassiveDamageData> passiveDamages;

			public DictionaryRef<VisionCorpsKey, VisionCorpsData> visions;

			public PassiveMarkers passiveMarkers;

			public PassiveValues passiveValues;

			public PassiveDamageValues passiveDamageValues;

			public int globalCoolTime;

			public int moveCoolTime;

			public TileKey clientVisionKey;

			public ulong clientVisionGuildId;
		}

		private int _level;

		public int unit_count;

		public bool isWater;

		public bool isDurty;

		private long m_cachedCorpsCombatActionResourceTime;

		private Dictionary<int, Logic.IActionResourceCalculated> m_cachedCorpsCombatActionResources;

		private long m_cachedCorpsCustomActionResourceTime;

		private Dictionary<ECorpsCustomActionType, Dictionary<int, Logic.IActionResourceCalculated>> m_cachedCorpsCustomActionResources;

		private HashSet<IDataRefInternal<CorpsData>> m_dataRefs;

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

		public UserCorpsData userCorps
		{
			get
			{
				return null;
			}
		}

		public CorpsInformationData info
		{
			get
			{
				return null;
			}
		}

		public CorpsSiegeWeaponData siegeWeapon
		{
			get
			{
				return null;
			}
		}

		public DictionaryRef<CorpsCustomFunctionKey, CorpsCustomFunctionData> customFunctions
		{
			get
			{
				return null;
			}
		}

		public DictionaryRef<CorpsFunctionKey, CorpsFunctionData> functions
		{
			get
			{
				return null;
			}
		}

		public DictionaryRef<CorpsPassiveKey, CorpsPassiveData> passives
		{
			get
			{
				return null;
			}
		}

		public DictionaryRef<CorpsPassiveDamageKey, CorpsPassiveDamageData> passiveDamages
		{
			get
			{
				return null;
			}
		}

		public DictionaryRef<VisionCorpsKey, VisionCorpsData> visions
		{
			get
			{
				return null;
			}
		}

		public int basicAttackRange
		{
			get
			{
				return 0;
			}
		}

		public bool isInGuildSight
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

		public int CurrentMilitaryPower
		{
			get
			{
				return 0;
			}
		}

		public int MaxMilitaryPower
		{
			get
			{
				return 0;
			}
		}

		public string nationName
		{
			get
			{
				return null;
			}
		}

		public int level
		{
			get
			{
				return 0;
			}
		}

		public int CurrentExp
		{
			get
			{
				return 0;
			}
		}

		public int maxLevel
		{
			get
			{
				return 0;
			}
		}

		public CorpsInformationData corpsInformation
		{
			get
			{
				return null;
			}
		}

		public CorpsSiegeWeaponData corpsSiegeWeapon
		{
			get
			{
				return null;
			}
		}

		public Color32[] renderingColors
		{
			get
			{
				return null;
			}
		}

		public UserData owner_user
		{
			get
			{
				return null;
			}
		}

		public GuildData owner_guild
		{
			get
			{
				return null;
			}
		}

		public ulong owner_guild_id
		{
			get
			{
				return 0uL;
			}
		}

		public int basicAttackFunctionId
		{
			get
			{
				return 0;
			}
		}

		public GuildWarCombatActiveFunction p_basicAttackFunction
		{
			get
			{
				return null;
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

		public Vector2Int GridPos
		{
			get
			{
				return default(Vector2Int);
			}
		}

		public bool IsSiegeWeapon
		{
			get
			{
				return false;
			}
		}

		public bool IsWater
		{
			get
			{
				return false;
			}
		}

		public int GetCurrentMaxActionPoint
		{
			get
			{
				return 0;
			}
		}

		public int GetCurrentActionPoint
		{
			get
			{
				return 0;
			}
		}

		public int globalCoolTime
		{
			get
			{
				return 0;
			}
		}

		public int moveCoolTime
		{
			get
			{
				return 0;
			}
		}

		public abstract Action<CorpsData> onUpdate { get; set; }

		public abstract ClientData clientData { get; }

		public abstract int corps_id { get; }

		public abstract ulong user_id { get; }

		public abstract ulong guild_id { get; }

		public abstract int atk { get; }

		public abstract int inte { get; }

		public abstract int def { get; }

		public abstract int agi { get; }

		public abstract int mor { get; }

		public abstract short pos_x { get; }

		public abstract short pos_y { get; }

		public abstract int exp { get; }

		public abstract short forward_direction { get; }

		public abstract int military_power { get; }

		public abstract int total_damage { get; }

		public abstract int destory_corps_count { get; }

		public abstract int lastest_passive_index { get; }

		public abstract int siege_weapon_use_count { get; }

		public abstract short action_point { get; }

		public abstract int lastest_action_date { get; }

		public abstract int update_date { get; }

		public abstract int create_date { get; }

		protected void OnInited(IDataContainerCreator factory)
		{
		}

		protected void OnModified(IDataContainerLinker<CorpsKey> factory)
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

		private void OnPassiveDamageAdded(CorpsPassiveDamageKey key, CorpsPassiveDamageData value)
		{
		}

		private void OnPassiveDamageRemoved(CorpsPassiveDamageKey key, CorpsPassiveDamageData value)
		{
		}

		private void OnPassiveDamageUpdated(CorpsPassiveDamageData value)
		{
		}

		private void CallbackPassive(CorpsPassiveKey corpsPassiveKey, CorpsPassiveData corpsPassiveData)
		{
		}

		private void ResetPassiveMaxValidDate()
		{
		}

		public Color32[] GetGuildColors()
		{
			return null;
		}

		private void UpdatePassiveValues()
		{
		}

		private void UpdatePassiveDamageValues()
		{
		}

		public bool isCorpsPassiveActive(int passiveId)
		{
			return false;
		}

		public List<PassiveMarkerType> GetCorpsPassiveMarkerTypes()
		{
			return null;
		}

		public string GetPrefabPrefix()
		{
			return null;
		}

		public Logic.IActionResourceCalculated CalculateCorpsCombatActionResource(int functionId)
		{
			return null;
		}

		public Logic.IActionResourceCalculated CalculateCustomActionCooltime(ECorpsCustomActionType customActionType, int customActionKey)
		{
			return null;
		}

		public string GetCustomDescription(ECorpsCustomActionType customActionType, int customActionKey)
		{
			return null;
		}

		public abstract DataTables GetDataTables();

		public abstract CorpsKey GetKey();

		public void AddDataRef(IDataRefInternal<CorpsData> dataRef)
		{
		}

		public void RemoveDataRef(IDataRefInternal<CorpsData> dataRef)
		{
		}

		public void ResetDataRefsAll()
		{
		}

	}
}
