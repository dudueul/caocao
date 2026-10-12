using System;
using System.Collections.Generic;

namespace GuildWar
{
	public abstract class WorldData : IDataTableOwner, IDataType<WorldData>, IClientDataOwner<WorldData.ClientData>, IDataRefAble<WorldData>, IDataType, IDataUpdateListener<WorldData>
	{
		public class ClientData : IClientDataType
		{
			public DictionaryRef<BuildingKey, BuildingData> buildings;

			public DictionaryRef<CorpsKey, CorpsData> corps;

			public DictionaryRef<GuildKey, GuildData> guilds;

			public DictionaryRef<TileEffectKey, TileEffectData> tileEffects;

			public DictionaryRef<TileKey, TileData> tiles;

			public DataRef<CameraData> camera;

			public DataRef<ClientVisionData> clientVision;

			public DataRef<TileCandidatesData> tileCandidates;

			public int[] objectColorsInPositionArray;

			public int[] objectTrianglesInPositionArray;

			public GuildData myGuild;

			public UserData myUser;

			public Action myUserUpdated;

			public int myGuildVisionSeq;

			public void MyData_OnInited()
			{
			}

			public void MyData_OnDestroy()
			{
			}

			private void OnGuildAdded(GuildKey guildKey, GuildData guildData)
			{
			}

			private void OnGuildRemoved(GuildKey guildKey, GuildData guildData)
			{
			}

			private void OnMyGuildUserAdded(UserKey userKey, UserData userData)
			{
			}

			private void OnMyGuildUserRemoved(UserKey userKey, UserData userData)
			{
			}
		}

		private HashSet<IDataRefInternal<WorldData>> m_dataRefs;

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

		public DictionaryRef<GuildKey, GuildData> guilds
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

		public DictionaryRef<TileKey, TileData> tiles
		{
			get
			{
				return null;
			}
		}

		public CameraData camera
		{
			get
			{
				return null;
			}
		}

		public ClientVisionData clientVision
		{
			get
			{
				return null;
			}
		}

		public TileCandidatesData tileCandidates
		{
			get
			{
				return null;
			}
		}

		public GuildData myGuild
		{
			get
			{
				return null;
			}
		}

		public UserData myUser
		{
			get
			{
				return null;
			}
		}

		public Action myUserUpdated
		{
			get
			{
				return null;
			}
			set
			{
			}
		}

		public int myGuildVisionSeq
		{
			get
			{
				return 0;
			}
		}

		public abstract Action<WorldData> onUpdate { get; set; }

		public abstract ClientData clientData { get; }

		public abstract short clan_db_shard { get; }

		public abstract int world_id { get; }

		public abstract int instance_index { get; }

		public abstract int latest_corps_id { get; }

		public abstract int status { get; }

		public abstract int season_key { get; }

		public abstract int season_detail_key { get; }

		public abstract int next_season_detail_key { get; }

		public abstract int p_battle_world_id { get; }

		public abstract int p_guildwar_town_id { get; }

		public abstract int town_shard_index { get; }

		public abstract int town_grade { get; }

		public abstract int reward_grade { get; }

		public abstract string map { get; }

		public abstract long play_start { get; }

		public abstract long play_end { get; }

		public abstract long expire_date { get; }

		public abstract int p_victory_place_id { get; }

		public abstract ulong victory_guild_id { get; }

		public abstract Action<DirectionCombatSourceData> onDirectionCombatSource { get; set; }

		public abstract Action<DirectionCombatTargetData> onDirectionCombatTarget { get; set; }

		public abstract Action<DirectionChangeHpData> onDirectionChangeHp { get; set; }

		public abstract Action<DirectionCorpsMovedData> onDirectionCorpsMoved { get; set; }

		public abstract Action<DirectionCombatMessageData> onDirectionCombatMessage { get; set; }

		public abstract Action<DirectionPingData> onDirectionPing { get; set; }

		protected void OnInited(IDataContainerCreator factory)
		{
		}

		protected void OnModified(IDataContainerLinker factory)
		{
		}

		protected void OnDestroy()
		{
		}

		public abstract DataTables GetDataTables();

		public void AddDataRef(IDataRefInternal<WorldData> dataRef)
		{
		}

		public void RemoveDataRef(IDataRefInternal<WorldData> dataRef)
		{
		}

		public void ResetDataRefsAll()
		{
		}
	}
}
