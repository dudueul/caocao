using System;
using System.Collections.Generic;
using UnityEngine;

namespace GuildWar
{
	public abstract class GuildData : IDataTableOwner, IDataType<GuildData>, IKeyOwner<GuildKey>, IClientDataOwner<GuildData.ClientData>, IDataRefAble<GuildData>, IDataType, IDataUpdateListener<GuildData>
	{
		public class ClientData : IClientDataType
		{
			public DataRef<WorldData> world;

			public DictionaryRef<BuildingKey, BuildingData> buildings;

			public DictionaryRef<GuildCustomFunctionKey, GuildCustomFunctionData> customFunctions;

			public DictionaryRef<GuildFunctionKey, GuildFunctionData> functions;

			public DictionaryRef<UserKey, UserData> users;

			public DictionaryRef<GuildMemberGradeKey, GuildMemberGradeData> memberGrades;

			public DictionaryRef<VisionKey, VisionData> visions;

			public DictionaryRef<GuildCorpsPassiveKey, GuildCorpsPassiveData> guildCorpsPassives;

			public DictionaryRef<GuildHistoryKey, GuildHistoryData> histories;
		}

		private int m_visionDataSeq;

		private HashSet<IDataRefInternal<GuildData>> m_dataRefs;

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

		public DictionaryRef<GuildCustomFunctionKey, GuildCustomFunctionData> customFunctions
		{
			get
			{
				return null;
			}
		}

		public DictionaryRef<GuildFunctionKey, GuildFunctionData> functions
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

		public DictionaryRef<GuildMemberGradeKey, GuildMemberGradeData> memberGrades
		{
			get
			{
				return null;
			}
		}

		public DictionaryRef<VisionKey, VisionData> visions
		{
			get
			{
				return null;
			}
		}

		public DictionaryRef<GuildCorpsPassiveKey, GuildCorpsPassiveData> guildCorpsPassives
		{
			get
			{
				return null;
			}
		}

		public DictionaryRef<GuildHistoryKey, GuildHistoryData> histories
		{
			get
			{
				return null;
			}
		}

		public int visionDataSeq
		{
			get
			{
				return 0;
			}
		}

		public abstract Action<GuildData> onUpdate { get; set; }

		public abstract ClientData clientData { get; }

		public abstract ulong guild_id { get; }

		public abstract string guild_name { get; }

		public abstract int emblem_sprite_index { get; }

		public abstract int guildwar_rank { get; }

		public abstract short position_type { get; }

		public abstract int max_user_join_count { get; }

		public abstract int color_index { get; }

		public abstract int latest_corps_passive_index { get; }

		public abstract int ranking_point { get; }

		protected void OnInited(IDataContainerCreator factory)
		{
		}

		protected void OnModified(IDataContainerLinker<GuildKey> factory)
		{
		}

		protected void OnDestroy()
		{
		}

		private void OnGuildVisionAdded(VisionKey visionKey, VisionData visionData)
		{
		}

		private void OnGuildVisionRemoved(VisionKey visionKey, VisionData visionData)
		{
		}

		public Color32[] GetGuildColors()
		{
			return null;
		}

		public static Color32[] GetNoneTeamGuildColors()
		{
			return null;
		}

		public abstract DataTables GetDataTables();

		public abstract GuildKey GetKey();

		public void AddDataRef(IDataRefInternal<GuildData> dataRef)
		{
		}

		public void RemoveDataRef(IDataRefInternal<GuildData> dataRef)
		{
		}

		public void ResetDataRefsAll()
		{
		}
	}
}
