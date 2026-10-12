using System;

namespace GuildWar.Backend
{
	public abstract class Manager
	{
		public Action<int> onBeforeResponse;

		public Action<int, string> onResponse;

		public abstract void Update();

		public abstract void LogOut();

		public abstract Session RequestSession(string socketServerUuid, string socketServerIP, int socketServerPort, string logicServerUuid, string logicServerIP, int logicServerPort, string distributorServerUuid, string distributorServerIP, int distributorServerPort, ulong userId);

		public abstract int GetWorldID();

		public abstract int GetClanShardIndex();

		public abstract int GetGuildWarShardIndex();

		public abstract string GetMap();

		public abstract DataTables GetDataTable();

		public abstract WorldData GetWorldData();

		public abstract int GetWorldSeq();

		protected abstract int Send(int clanDbShard, int worldID, string uri, string method, bool showNetworkIndicator, params object[] args);

		protected abstract int SendAndWait(int clanDbShard, int worldID, string uri, string method, bool showNetworkIndicator, params object[] args);

		private int GetInstanceIndex(int clanDbShard, int worldId)
		{
			return 0;
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

		public int RequestBattleSceneEnter(int clanDbShard, int worldId, ulong guildId, ulong userId)
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

		public int RequestMountSiegeWeapon(int clanDbShard, int worldId, ulong guildId, ulong userId, int corpsId, int posX, int posY, int p_siegeWeaponId)
		{
			return 0;
		}

		public int RequestDismountSiegeWeapon(int clanDbShard, int worldId, ulong guildId, ulong userId, int corpsId, int posX, int posY, int p_siegeWeaponId)
		{
			return 0;
		}

		public int RequestNoticePing(int clanDbShard, int worldId, ulong guildId, ulong userId, int p_pingId, int posX, int posY)
		{
			return 0;
		}
	}
}
