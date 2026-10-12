using System.Collections.Generic;

public class MapObjectStatus
{
	public class tileChangeInfo
	{
		public readonly int dx;

		public readonly int dy;

		public readonly int fromTIleId;

		public readonly int newTileId;

		public tileChangeInfo(int x, int y, int fromId, int id)
		{
		}
	}

	public class unitChangeInfo
	{
		public readonly int dx;

		public readonly int dy;

		public readonly bool active;

		public unitChangeInfo(int x, int y, bool _active)
		{
		}
	}

	public readonly int statusId;

	public readonly bool isLoop;

	public readonly string aniType;

	public readonly List<tileChangeInfo> changeTiles;

	public readonly List<unitChangeInfo> changeUnits;

	public readonly int nextStatus;

	public readonly bool setDestroy;

	public bool hasNextStatus
	{
		get
		{
			return false;
		}
	}

	public bool hasSetDestroy
	{
		get
		{
			return false;
		}
	}

	public MapObjectStatus(int id, bool loop, string ani, List<tileChangeInfo> tiles, List<unitChangeInfo> units, int status, bool destory)
	{
	}
}
