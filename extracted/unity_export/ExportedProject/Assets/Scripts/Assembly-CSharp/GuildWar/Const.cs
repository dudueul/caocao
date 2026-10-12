namespace GuildWar
{
	public static class Const
	{
		public const int guildWarJoinMinGuildLevel = 15;

		public const float minPeriodWebServerLogin = 5f;

		public const float minPeriodWebServerGetAuthKey = 5f;

		public const float durationWebServerAuthKey = 3600f;

		public const float minPeriodSocketServerConnect = 5f;

		public const float minPeriodSocketServerLogin = 5f;

		public const float minPeriodSocketServerRoomAPI = 5f;

		public const float minPeriodGetWorldMetaData = 1f;

		public const float maxPeriodGetWorldMetaData = 20f;

		public const float minPeriodGetWorldData = 1f;

		public const float minPeriodGetSameWorldData = 0.5f;

		public const float maxPeriodGetSameWorldData = 60f;

		public const string TownColorPlayerPrefsKey = "GuildWar_TownColor";

		public const int g_iGuildWarTileXCount = 20;

		public const int g_iGuildWarTileYCount = 20;

		public const int g_iGuildWarTileIndexInit = 15000001;

		public const float g_fHexTileODDQRatio = 0.8660254f;

		public const float g_fGuildWarTileHeight = 0.96f;

		public const float g_fGuildWarTileWidth = 0.83f;

		public const int g_maxRange = 3;

		public static string GetTownFilterTypeLabelText(GuildWarTownFilterType sortType)
		{
			return null;
		}
	}
}
