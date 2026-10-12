using System;
using System.Collections.Generic;

namespace GuildWar
{
	public static class Logic
	{
		public class IContext
		{
			public int worldId;

			public long curTime;

			public DataTables dataTables;
		}

		public class CorpsCustomActionResource
		{
			private int m_actionPoint;

			private int m_cooldownTime;

			private readonly string m_name;

			private EFunctionCategory m_category;

			public int actionPoint
			{
				get
				{
					return 0;
				}
			}

			public int cooldownTime
			{
				get
				{
					return 0;
				}
			}

			public string name
			{
				get
				{
					return null;
				}
			}

			public EFunctionCategory category
			{
				get
				{
					return EFunctionCategory.None;
				}
			}

			public CorpsCustomActionResource(int _cooldownTime, int _actionPoint, string _name, EFunctionCategory _category)
			{
			}
		}

		public class BuildingCustomActionResource
		{
			private int m_actionPoint;

			private int m_cooldownTime;

			public int actionPoint
			{
				get
				{
					return 0;
				}
			}

			public int cooldownTime
			{
				get
				{
					return 0;
				}
			}

			public BuildingCustomActionResource(int _cooldownTime, int _actionPoint)
			{
			}
		}

		public class UserCustomActionResource
		{
			private int m_actionPoint;

			private int m_cooldownTime;

			public int actionPoint
			{
				get
				{
					return 0;
				}
			}

			public int cooldownTime
			{
				get
				{
					return 0;
				}
			}

			public UserCustomActionResource(int _cooldownTime, int _actionPoint)
			{
			}
		}

		public class GuildCustomActionResource
		{
			private int m_actionPoint;

			private int m_cooldownTime;

			public int actionPoint
			{
				get
				{
					return 0;
				}
			}

			public int cooldownTime
			{
				get
				{
					return 0;
				}
			}

			public GuildCustomActionResource(int _cooldownTime, int _actionPoint)
			{
			}
		}

		public class IActionResourceCalculated
		{
			public bool available;

			public long cooldown_time;

			public int action_point;

			public long lastest_action_point_date;
		}

		private static long s_previousTimeStamp;

		private static long s_currentTimeStamp;

		private static Dictionary<ECorpsCustomActionType, CorpsCustomActionResource> s_corpsCustomActionResources;

		private static Dictionary<EBuildingCustomActionType, BuildingCustomActionResource> s_buildingCustomActionResources;

		private static Dictionary<EUserCustomActionType, UserCustomActionResource> s_userCustomActionResources;

		private static Dictionary<EGuildCustomActionType, GuildCustomActionResource> s_guildCustomActionResources;

		static Logic()
		{
		}

		public static IContext CreateContext(int worldId)
		{
			return null;
		}

		public static int GetPermanentDataGuildWarConst(int id)
		{
			return 0;
		}

		public static int GetPermanentDataGuildWarConst(EGuildWarConstantsId id)
		{
			return 0;
		}

		public static void InitTimeStamp()
		{
		}

		public static void UpdateTimeStamp()
		{
		}

		public static long GetAbsolutePreviousTimeStamp()
		{
			return 0L;
		}

		public static long GetAbsoluteCurrentTimeStamp()
		{
			return 0L;
		}

		public static int GetPreviousTimeStamp()
		{
			return 0;
		}

		public static int GetCurrentTimeStamp()
		{
			return 0;
		}

		public static int GetMaxTimeStamp()
		{
			return 0;
		}

		public static int GetLeftTime()
		{
			return 0;
		}

		private static void InitStaticVariables()
		{
		}

		public static CorpsCustomActionResource GetCorpsCustomActionResource(ECorpsCustomActionType customActionType)
		{
			return null;
		}

		public static Dictionary<ECorpsCustomActionType, CorpsCustomActionResource> GetCorpsCustomActionResources()
		{
			return null;
		}

		public static BuildingCustomActionResource GetBuildingCustomActionResource(EBuildingCustomActionType customActionType)
		{
			return null;
		}

		public static Dictionary<EBuildingCustomActionType, BuildingCustomActionResource> GetBuildingCustomActionResources()
		{
			return null;
		}

		public static UserCustomActionResource GetUserCustomActionResource(EUserCustomActionType customActionType)
		{
			return null;
		}

		public static Dictionary<EUserCustomActionType, UserCustomActionResource> GetUserCustomActionResources()
		{
			return null;
		}

		public static GuildCustomActionResource GetGuildCustomActionResource(EGuildCustomActionType customActionType)
		{
			return null;
		}

		public static Dictionary<EGuildCustomActionType, GuildCustomActionResource> GetGuildCustomActionResources()
		{
			return null;
		}

		public static int GetCurrentActionPoint(long currentDate, int maxActionPoint, int actionPointRecoveryTime, int actionPointRecoveryAmount, int currentActionPoint, long currentLastestActionPointDate, out long newLastestActionPointDate)
		{
			newLastestActionPointDate = default(long);
			return 0;
		}

		private static IActionResourceCalculated CalculateActionResource(long currentDate, long sourceCooldownTime, int cooldownSecs, int chargeActionPoint, int maxActionPoint, int actionPointRecoveryTime, int actionPointRecoveryAmount, int currentActionPoint, long currentLastestActionPointDate)
		{
			return null;
		}

		public static IActionResourceCalculated CalculateCorpsCombatActionResource(CorpsFunctionData corpsFunctionData)
		{
			return null;
		}

		public static IActionResourceCalculated CalculateCorpsCombatActionResource(int clanDbShard, int worldId, int corpsId, int functionId)
		{
			return null;
		}

		public static IActionResourceCalculated CalculateCorpsCustomActionResource(int clanDbShard, int worldId, int corpsId, ECorpsCustomActionType customActionType, int customActionKey)
		{
			return null;
		}

		public static IActionResourceCalculated CalculateBuildingCustomActionResource(int worldId, int p_placeId, EBuildingCustomActionType customActionType)
		{
			return null;
		}

		public static UserStatsData GetUserStats(IContext context, ulong guildId, ulong userId)
		{
			return null;
		}

		public static BuildingData GetBuilding(IContext context, int p_placeId)
		{
			return null;
		}

		public static GuildWarBuilding GetPermanentBuilding(int building_id)
		{
			return null;
		}

		public static GuildWarScheduleDetailDateType GetGuildWarScheduleDetailDateType(GuildWarScheduleDetail scheduleDetail, DateTime checkDate)
		{
			return GuildWarScheduleDetailDateType.None;
		}
	}
}
