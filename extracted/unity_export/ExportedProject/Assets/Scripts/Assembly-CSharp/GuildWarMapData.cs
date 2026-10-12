using System;
using System.Collections.Generic;
using UnityEngine;

public class GuildWarMapData : ScriptableObject
{
	[Serializable]
	public class TileDataForScriptableObj
	{
		[SerializeField]
		public Vector2Int GridPos;

		[SerializeField]
		public int Id;

		public TileDataForScriptableObj(Vector2Int gridPos, int id)
		{
		}
	}

	[Serializable]
	public class BuildPlaceDataForScriptableObj
	{
		[SerializeField]
		public Vector2Int GridPos;

		[SerializeField]
		public int Id;

		public BuildPlaceDataForScriptableObj(Vector2Int gridPos, int id)
		{
		}
	}

	public int XCount;

	public int YCount;

	public List<TileDataForScriptableObj> Tiles;

	public List<BuildPlaceDataForScriptableObj> BuildPlaceDatas;

	public static GuildWarMapData Load(string mapName)
	{
		return null;
	}
}
