using System;
using System.Collections.Generic;
using UnityEngine;

namespace GuildWar
{
	public abstract class ClientVisionData : IDataTableOwner, IDataType<ClientVisionData>, IClientDataOwner<ClientVisionData.ClientData>, IDataRefAble<ClientVisionData>, IDataType, IDataUpdateListener<ClientVisionData>
	{
		public class ClientData : IClientDataType
		{
			public DataRef<WorldData> world;

			public ulong guildId;

			public Vector3[] tileVertices;

			public int[] visibleTileTriangles;

			public int[] objectTileTriangles;

			public Color32[] objectTileColors;

			public int width;

			public int height;

			public void InitMap(GuildWarMapInfo mapInfo)
			{
			}

			public void SetTileVisible(int pos_x, int pos_y, bool visible)
			{
			}

			public void SetObjectVisible(int pos_x, int pos_y, bool visible)
			{
			}

			public void SetObjectColor(int pos_x, int pos_y, Color32 color)
			{
			}
		}

		private int m_objectSeqKey;

		private HashSet<TileKey> m_invalidObjectTiles;

		private HashSet<IDataRefInternal<ClientVisionData>> m_dataRefs;

		public int objectSeqKey
		{
			get
			{
				return 0;
			}
		}

		public WorldData world
		{
			get
			{
				return null;
			}
		}

		public abstract Action<ClientVisionData> onUpdate { get; set; }

		public abstract ClientData clientData { get; }

		protected void OnInited(IDataContainerCreator factory)
		{
		}

		protected void OnModified(IDataContainerLinker factory)
		{
		}

		protected void OnDestroy()
		{
		}

		public void InvalidateObjectTile(TileKey tileKey)
		{
		}

		public int UpdateObjectTiles()
		{
			return 0;
		}

		public void SetClientVisionGuildId(ulong guild_id)
		{
		}

		public abstract DataTables GetDataTables();

		public void AddDataRef(IDataRefInternal<ClientVisionData> dataRef)
		{
		}

		public void RemoveDataRef(IDataRefInternal<ClientVisionData> dataRef)
		{
		}

		public void ResetDataRefsAll()
		{
		}
	}
}
