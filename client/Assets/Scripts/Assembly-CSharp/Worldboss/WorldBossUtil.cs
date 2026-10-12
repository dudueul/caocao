using System;

namespace Worldboss
{
	public static class WorldBossUtil
	{
		public static WorldBossTier getMyTier(int seasonId, int rankNum, float rankPct)
		{
			return null;
		}

		public static WorldBossTier getMinTier(int seasonId)
		{
			return null;
		}

		public static WorldBossTier getMaxTier(int seasonId)
		{
			return null;
		}

		public static string getTimeText(TimeSpan ts)
		{
			return null;
		}

		public static DateTime calcNextInstanceStart(WorldBossContribution contribution)
		{
			return default(DateTime);
		}

		public static DateTime calcNextContributionStart(WorldBossServerSeason season, DateTime curTime)
		{
			return default(DateTime);
		}

		public static WorldBoss getPhaseBoss(int seasonId, int phase)
		{
			return null;
		}

		public static bool checkSeasonOn(WorldBossServerSeason season, DateTime curTime)
		{
			return false;
		}

		public static bool checkSeasonOff(WorldBossServerSeason season, DateTime curTime)
		{
			return false;
		}

		public static bool checkInstanceEnable(WorldBossInstance instance, DateTime curTime)
		{
			return false;
		}

		public static bool checkContributionEnable(WorldBossContribution contribution, DateTime curTime)
		{
			return false;
		}

		public static bool checkStartNextPhase(WorldBossServerSeason season, DateTime curTime)
		{
			return false;
		}

		public static SeasonStatus getSeasonStatus(WorldBossServerSeason season, WorldBossContribution contribution, WorldBossInstance instance, DateTime curTime)
		{
			return SeasonStatus.None;
		}

		public static bool canReceiveReward(bool isClear, WorldBossPhaseInfo phaseInfo)
		{
			return false;
		}

		public static int CheckBadgeCount()
		{
			return 0;
		}
	}
}
