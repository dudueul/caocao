using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using GuildWar.Backend;
using UnityEngine;

namespace GuildWar
{
	public class Manager : Singleton<Manager>
	{
		private GuildWar.Backend.Manager m_backendManager;

		public Action<bool> OnLoginResult;

		public bool login;

		public bool logout;

		public int _season_key;

		public int _season_detail_key;

		public int _p_battleworld_id;

		public int _p_guildwar_town_id;

		public GuildWar.Backend.Manager backendManager
		{
			get
			{
				return null;
			}
		}

		static Manager()
		{
		}

		public static T ToObjectForGenericReflection<T>(string json)
		{
			return default(T);
		}

		public static void OnLogin(int season_key, int season_detail_key, int p_battleworld_id, int p_guildwar_town_id)
		{
		}

		public static void OnLogout()
		{
		}

		public static DateTime UnixTimeStampToDateTime(long ms)
		{
			return default(DateTime);
		}

		public static long DateTimeToUnixTimeStamp(DateTime dateTime)
		{
			return 0L;
		}

		public static int GetPermanentDataGuildWarConst(int id)
		{
			return 0;
		}

		public static int GetPermanentDataGuildWarConst(EGuildWarConstantsId id)
		{
			return 0;
		}

		public static int GetCorpsSizeGrade(int militaryPower, bool isSiegeWeapon = false)
		{
			return 0;
		}

		public static string GetGoodsTypeName(GuildWarGoods goodsType)
		{
			return null;
		}

		public static void SetGoodsTypeSprite(GuildWarGoods goodsType, UISprite itemIconSprite)
		{
		}

		public static Texture GetGoodsTypeTexture(GuildWarGoods goodsType, Texture texture)
		{
			return null;
		}

		public static DateTime GetDateTimeClient(long date)
		{
			return default(DateTime);
		}

		public static DateTime GetDateTimeServer(long date)
		{
			return default(DateTime);
		}

		public static List<UIGuildWarPassiveData> GetPassiveAttributeDataSortList(DictionaryRef<CorpsPassiveKey, CorpsPassiveData> corpsPassives)
		{
			return null;
		}

		public static List<UIGuildWarPassiveData> GetPassiveAttributeDataSortList(DictionaryRef<BuildingPassiveKey, BuildingPassiveData> buildingPassives)
		{
			return null;
		}

		public static Dictionary<int, UIGuildWarPassiveData> GetPassiveAttributeData(DictionaryRef<CorpsPassiveKey, CorpsPassiveData> corpsPassives)
		{
			return null;
		}

		public static Dictionary<int, UIGuildWarPassiveData> GetPassiveAttributeData(DictionaryRef<BuildingPassiveKey, BuildingPassiveData> buildingPassives)
		{
			return null;
		}

		public static string GetCorpsJobName(int jobType)
		{
			return null;
		}

		public static string GetCorpsJobName(GuildWarCorpsJobType jobType)
		{
			return null;
		}

		public static int GetCorpsLevel(int exp)
		{
			return 0;
		}

		public static Color GetTownStatusColor(EGuildWarTownUIStatus status)
		{
			return default(Color);
		}

		public static string GetTownStatusName(EGuildWarTownUIStatus status)
		{
			return null;
		}

		public static Color GetAttendStatusColor(EGuildWarAttendStatus status)
		{
			return default(Color);
		}

		public static string GetAttendStatusName(EGuildWarAttendStatus status)
		{
			return null;
		}

		public static string GetTownSpriteName(int sizeGrade)
		{
			return null;
		}

		public static string GetTownSizeString(int sizeGrade)
		{
			return null;
		}

		private void Update()
		{
		}

		[DebuggerHidden]
		private IEnumerator UpdateGuildWarLogicTimeStamp()
		{
			return null;
		}

		public int RequestCreateCorps(int clanDbShard, int worldId, int guildId, int posX, int posY, int jobId, int jobTypeId, int faceUnitId, int faceSkinItemId, int militaryPower, bool isFree)
		{
			return 0;
		}

		public int RequestMoveCorps(int clanDbShard, int worldId, int guildId, int[] posX, int[] posY, int sourceCorpsId)
		{
			return 0;
		}

		public int RequestDoCorpsFunction(int clanDbShard, int worldId, int guildId, int sourceCorpsId, int functionId, int functionTargetRange, int functionTargetType, int[] functionActionIds, int targetType, int targetCorpsId, int targetPlacementId, int targetTilePosX, int targetTilePosY, int sourcePosX, int sourcePosY, int targetPosX, int targetPosY)
		{
			return 0;
		}

		public int RequestCorpsDismiss(int clanDbShard, int worldId, ulong guildId, int corpsId, ulong userId)
		{
			return 0;
		}

		public int RequestRefreshTrandingPost(int clanDbShard, int worldId, ulong guildId, ulong userId)
		{
			return 0;
		}

		public int RequestCreateUser(int clanDbShard, int worldId, ulong guildId, ulong userId)
		{
			return 0;
		}

		public int RequestChangeUserFace(int clanDbShard, int worldId, ulong guildId, ulong userId, int faceUnitId, int faceSkinItemId)
		{
			return 0;
		}

		public int RequestMountSiegeWeapon(int clanDbShard, int worldID, ulong guildId, ulong userId, int corpsId, int posX, int posY, int p_siegeWeaponId)
		{
			return 0;
		}

		public int RequestDismountSiegeWeapon(int clanDbShard, int worldID, ulong guildId, ulong userId, int corpsId, int posX, int posY, int p_siegeWeaponId)
		{
			return 0;
		}

		public int RequestNoticePing(int clanDbShard, int worldID, ulong guildId, ulong userId, int p_pingId, int posX, int posY)
		{
			return 0;
		}
	}
}
