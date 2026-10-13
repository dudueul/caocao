using System;
using System.Collections.Generic;

namespace GuildWar
{
	public abstract class TileCandidatesData : IDataTableOwner, IDataType<TileCandidatesData>, IClientDataOwner<TileCandidatesData.ClientData>, IDataRefAble<TileCandidatesData>, IDataType, IDataUpdateListener<TileCandidatesData>
	{
		public class ClientData : IClientDataType
		{
			public DataRef<WorldData> world;
		}

		public delegate TileState GetTileStateFunction(TileData tileData);

		public enum TileState
		{
			None = 0,
			Selected = 1,
			Enabled = 2,
			Disabled = 3
		}

		private class SourceData
		{
			public TileKey tileKey;

			// [RE] param made optional — derived ctors chain implicitly
			public SourceData(TileKey tileKey = default(TileKey))
			{
			}
		}

		private class SourceBuildingData : SourceData
		{
			public ulong ownerGuildId;

			public bool available;

			public int range;

			public SourceBuildingData(TileKey tileKey, ulong ownerGuildId, bool available, int range)
			{
			}
		}

		private class SourceCorpsData : SourceData
		{
			public int posX;

			public int posY;

			public SourceCorpsData(TileKey tileKey, int posX, int posY)
			{
			}
		}

		private TileCandidatesStates _tileCandidatesStates;

		private BuildingData _selectedBuilding;

		private CorpsData _selectedCorps;

		private GetTileStateFunction getFunctionTileState;

		private Dictionary<TileKey, TileState> m_tileCandidates;

		private Dictionary<BuildingKey, SourceBuildingData> sourceBuildingDatas;

		private Dictionary<CorpsKey, SourceCorpsData> sourceCorpsDatas;

		private bool needToUpdateTileCandidates;

		private long prevTileCandidatesStateUpdateTime;

		private HashSet<IDataRefInternal<TileCandidatesData>> m_dataRefs;

		public WorldData world
		{
			get
			{
				return null;
			}
		}

		public TileCandidatesStates tileCandidatesStates
		{
			get
			{
				return TileCandidatesStates.NoSelect;
			}
		}

		public BuildingData selectedBuilding
		{
			get
			{
				return null;
			}
		}

		public CorpsData selectedCorps
		{
			get
			{
				return null;
			}
		}

		public Dictionary<TileKey, TileState> tileCandidates
		{
			get
			{
				return null;
			}
		}

		public abstract Action<TileCandidatesData> onUpdate { get; set; }

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

		public void UpdateBuildingData(BuildingKey buildingKey, BuildingData buildingData)
		{
		}

		public void UpdateCorpsData(CorpsKey corpsKey, CorpsData corpsData)
		{
		}

		private void UpdateSourceBuildingData(BuildingKey buildingKey, BuildingData buildingData)
		{
		}

		private void UpdateSourceCorpsData(CorpsKey corpsKey, CorpsData corpsData)
		{
		}

		private void UpdateTileCandidatesList()
		{
		}

		private void UpdateTileCandidatesState()
		{
		}

		public void SetTileCandidatesStates_NoSelect()
		{
		}

		public void SetTileCandidatesStates_SelectCorps(CorpsData corpsData)
		{
		}

		public void SetTileCandidatesStates_SelectBuilding(BuildingData buildingData)
		{
		}

		public void SetTileCandidatesStates_UserFunction(List<KeyValuePair<BuildingData, int>> buildingDatas, GetTileStateFunction getTileState)
		{
		}

		public void SetTileCandidatesStates_CorpsFunction(CorpsData corpsData, GetTileStateFunction getTileState)
		{
		}

		public void SetTileCandidatesStates_NoSelect_SelectPingTargetTile(int p_pingId)
		{
		}

		public void SetTileCandidatesStates_SelectCorps_SelectPingTargetTile(CorpsData corpsData, int p_pingId)
		{
		}

		public void SetTileCandidatesStates_SelectBuilding_SelectPingTargetTile(BuildingData buildingData, int p_pingId)
		{
		}

		public TileState GetTileState(TileKey tileKey)
		{
			return TileState.None;
		}

		public abstract DataTables GetDataTables();

		public void AddDataRef(IDataRefInternal<TileCandidatesData> dataRef)
		{
		}

		public void RemoveDataRef(IDataRefInternal<TileCandidatesData> dataRef)
		{
		}

		public void ResetDataRefsAll()
		{
		}
	}
}
