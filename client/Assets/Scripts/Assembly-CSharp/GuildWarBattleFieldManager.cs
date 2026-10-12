using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using GuildWar;
using UnityEngine;

public class GuildWarBattleFieldManager : SceneSingleton<GuildWarBattleFieldManager>
{
	public enum SelectionStates
	{
		NoSelect = 0,
		SelectCorps = 1,
		SelectBuilding = 2,
		SelectUserFunctionTargetTile = 3,
		SelectCorpsFunctionTargetTile = 4,
		SelectCorpsMove = 5,
		NoSelect_SelectPingTargetTile = 6,
		SelectCorps_SelectPingTargetTile = 7,
		SelectBuilding_SelectPingTargetTile = 8
	}

	private int clanDbShard;

	private int worldID;

	private DataTables WorldDataBase;

	private WorldData _worldData;

	private TileCandidatesData _tileCandidates;

	private EGuildWarWorldStatus _worldStatus;

	private bool _tileToggleValue;

	private bool _corpsToggleValue;

	private bool _showAllTileValue;

	private bool _inputSystemValue;

	private HashSet<int> m_requestIndices;

	private bool _popupFinished;

	private Dictionary<int, Dictionary<int, long>> m_combatMessageCooltimes;

	public int SelectedFaceUnitId;

	public int SelectedFaceSkinItemId;

	public int SelectedCorpType;

	public int SelectedJobId;

	public int SelectedMilitaryPower;

	public int SelectedActionPoint;

	public bool isFree;

	private SelectionStates selectionState;

	private BuildingData selectedBuilding;

	private CorpsData selectedCorps;

	private GuildWarPing targetPing;

	private GuildWarCombatActiveFunction targetCorpsFunction;

	private List<GuildWar.TileData> m_movePathCandidates;

	private long lastestMoveUpdateTime;

	private GuildWarRendererTiles.UpdateTileSpriteRendererFunction updateTileSpriteRenderer;

	private int m_eventActionSelectedSiegeWeapon;

	private long m_LastGlobalCoolTime_end;

	private List<int> m_BlockInputRequestIndexs;

	public static Vector3Int[] HexDirectionClockwise;

	public static IntVector2[,] HexDirectionCoord;

	public GuildWarBattleFieldTileMap battleFieldTileMap
	{
		get
		{
			return null;
		}
	}

	public GuildWarBattleFieldController battleFieldController
	{
		get
		{
			return null;
		}
	}

	public int GetSeasonKey
	{
		get
		{
			return 0;
		}
	}

	public int GetSeasonDetailKey
	{
		get
		{
			return 0;
		}
	}

	public int GetBattleWorldId
	{
		get
		{
			return 0;
		}
	}

	public int GetGuildWarTownId
	{
		get
		{
			return 0;
		}
	}

	public GuildWar.UserData MyGuildWarUserData
	{
		get
		{
			return null;
		}
	}

	public GuildWar.GuildData MyGuildWarGuildData
	{
		get
		{
			return null;
		}
	}

	public bool CorpsToggleValue
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public bool ShowAllTileToggleValue
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public bool InputSystemToggleValue
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public SelectionStates GetSelectionState
	{
		get
		{
			return SelectionStates.NoSelect;
		}
	}

	public bool IsSelectionStateNoSelect
	{
		get
		{
			return false;
		}
	}

	public bool IsSelectionStateSelectCorps
	{
		get
		{
			return false;
		}
	}

	public bool IsSelectionStateSelectBuilding
	{
		get
		{
			return false;
		}
	}

	public bool IsSelectionStateSelectUserFunctionTargetTile
	{
		get
		{
			return false;
		}
	}

	public bool IsSelectionStateSelectCorpsFunctionTargetTile
	{
		get
		{
			return false;
		}
	}

	public bool IsSelectionStateSelectCorpsMove
	{
		get
		{
			return false;
		}
	}

	public GuildWarPing GetTargetPing
	{
		get
		{
			return null;
		}
	}

	public GuildWarCombatActiveFunction GetTargetCorpsFunction
	{
		get
		{
			return null;
		}
	}

	private GuildWarRendererTiles.TileSpriteType targetCorpsFunctionRangeSpriteType
	{
		get
		{
			return GuildWarRendererTiles.TileSpriteType.None;
		}
	}

	public long LastGlobalCoolTime_end
	{
		get
		{
			return 0L;
		}
	}

	public bool IsBlockInput
	{
		get
		{
			return false;
		}
	}

	public int GetClanDbShard()
	{
		return 0;
	}

	public int GetWorldId()
	{
		return 0;
	}

	public void RenderingDataInit()
	{
	}

	private void OnResponse(int requestIdx, string errorMsg)
	{
	}

	[DebuggerHidden]
	public IEnumerator RequsetCreateUser(int clan_db_shard, int world_id, Action callback = null)
	{
		return null;
	}

	public bool Init()
	{
		return false;
	}

	protected override void OnDestroy()
	{
	}

	private void OnCorpsRemoved(CorpsKey corpsKey, CorpsData corpsData)
	{
	}

	private void OnWorldDataUpdated(WorldData worldData)
	{
	}

	private void OnMyUserUpdated()
	{
	}

	private void UpdateWorldStatus()
	{
	}

	public void onDirectionCombatMessage(DirectionCombatMessageData direction)
	{
	}

	public void onDirectionPing(DirectionPingData direction)
	{
	}

	private void Update()
	{
	}

	private void LateUpdate()
	{
	}

	private static Sprite UpdateTileSpriteRenderer_GetSprite(GuildWarRendererTiles.TileSpriteType spriteType, Sprite[] tileSprites)
	{
		return null;
	}

	private static Color UpdateTileSpriteRenderer_GetColor(GuildWar.TileData tileData, Color[] moveHeightTileColor)
	{
		return default(Color);
	}

	private void UpdateTileSpriteRenderer_None(GuildWar.TileData tileData, TileCandidatesData.TileState tileState, SpriteRenderer spriteRenderer, Sprite[] tileSprites, Color[] moveHeightTileColor)
	{
	}

	private void UpdateTileSpriteRenderer_Selected(GuildWar.TileData tileData, TileCandidatesData.TileState tileState, SpriteRenderer spriteRenderer, Sprite[] tileSprites, Color[] moveHeightTileColor)
	{
	}

	private void UpdateTileSpriteRenderer_UserFunction_CreateCorps(GuildWar.TileData tileData, TileCandidatesData.TileState tileState, SpriteRenderer spriteRenderer, Sprite[] tileSprites, Color[] moveHeightTileColor)
	{
	}

	private void UpdateTileSpriteRenderer_CorpsCustomFunction_Move(GuildWar.TileData tileData, TileCandidatesData.TileState tileState, SpriteRenderer spriteRenderer, Sprite[] tileSprites, Color[] moveHeightTileColor)
	{
	}

	private void UpdateTileSpriteRenderer_CorpsFunction(GuildWar.TileData tileData, TileCandidatesData.TileState tileState, SpriteRenderer spriteRenderer, Sprite[] tileSprites, Color[] moveHeightTileColor)
	{
	}

	private TileCandidatesData.TileState GetTileState_UserFunction_CreateCorps(GuildWar.TileData tileData)
	{
		return TileCandidatesData.TileState.None;
	}

	private void AddMovePathCandidates(GuildWar.TileData tileData)
	{
	}

	private void SortMovePathCandidates(GuildWar.TileData tileData)
	{
	}

	private void UpdateMovePaths()
	{
	}

	private TileCandidatesData.TileState GetTileState_CorpsCustomFunction_Move(GuildWar.TileData tileData)
	{
		return TileCandidatesData.TileState.None;
	}

	private bool GetTileState_CorpsFunction_Common(GuildWar.TileData tileData, ref TileCandidatesData.TileState tileState)
	{
		return false;
	}

	private TileCandidatesData.TileState GetTileState_CorpsFunction_Tile(GuildWar.TileData tileData)
	{
		return TileCandidatesData.TileState.None;
	}

	private TileCandidatesData.TileState GetTileState_CorpsFunction_AllEnemies(GuildWar.TileData tileData)
	{
		return TileCandidatesData.TileState.None;
	}

	private TileCandidatesData.TileState GetTileState_CorpsFunction_AllAlly(GuildWar.TileData tileData)
	{
		return TileCandidatesData.TileState.None;
	}

	private TileCandidatesData.TileState GetTileState_CorpsFunction_MySelf(GuildWar.TileData tileData)
	{
		return TileCandidatesData.TileState.None;
	}

	private TileCandidatesData.TileState GetTileState_CorpsFunction_OnlyCorpsEnemies(GuildWar.TileData tileData)
	{
		return TileCandidatesData.TileState.None;
	}

	private TileCandidatesData.TileState GetTileState_CorpsFunction_OnlyCorpsAlly(GuildWar.TileData tileData)
	{
		return TileCandidatesData.TileState.None;
	}

	private TileCandidatesData.TileState GetTileState_CorpsFunction_OnlyBuildingEnemies(GuildWar.TileData tileData)
	{
		return TileCandidatesData.TileState.None;
	}

	private TileCandidatesData.TileState GetTileState_CorpsFunction_OnlyBuildingAlly(GuildWar.TileData tileData)
	{
		return TileCandidatesData.TileState.None;
	}

	private TileCandidatesData.TileState GetTileState_CorpsFunction_OnlyCorpsAllyExcludeMySelf(GuildWar.TileData tileData)
	{
		return TileCandidatesData.TileState.None;
	}

	private TileCandidatesData.TileState GetTileState_CorpsFunction_All(GuildWar.TileData tileData)
	{
		return TileCandidatesData.TileState.None;
	}

	private TileCandidatesData.TileState GetTileState_CorpsFunction_EmptyTile(GuildWar.TileData tileData)
	{
		return TileCandidatesData.TileState.None;
	}

	private TileCandidatesData.TileState GetTileState_CorpsFunction_MySelfNotMaxHP(GuildWar.TileData tileData)
	{
		return TileCandidatesData.TileState.None;
	}

	private TileCandidatesData.TileState GetTileState_CorpsFunction_OnlyCorpsAllyNotMaxHP(GuildWar.TileData tileData)
	{
		return TileCandidatesData.TileState.None;
	}

	private TileCandidatesData.TileState GetTileState_CorpsFunction_OnlyCorpsAllyWithDebuff(GuildWar.TileData tileData)
	{
		return TileCandidatesData.TileState.None;
	}

	private TileCandidatesData.TileState GetTileState_CorpsFunction_TileWithEffect(GuildWar.TileData tileData)
	{
		return TileCandidatesData.TileState.None;
	}

	private void UpdateSelectState()
	{
	}

	private void UpdateBattleFieldTileMap()
	{
	}

	private void SetSelectState_NoSelect()
	{
	}

	private void SetSelectState_CorpsMove(CorpsData corpsData)
	{
	}

	private void SetSelectState_CorpsFunction(CorpsData corpsData, GuildWarCombatActiveFunction p_function)
	{
	}

	private void SetSelectState_CreateCorps(List<BuildingData> buildingDatas)
	{
	}

	private void SetSelectState_SelectCorps(CorpsData corpsData)
	{
	}

	private void SetSelectState_SelectBuilding(BuildingData buildingData)
	{
	}

	private void SetSelectState_NoSelect_SelectPingTargetTile(GuildWarPing p_ping)
	{
	}

	private void SetSelectState_SelectCorps_SelectPingTargetTile(CorpsData corpsData, GuildWarPing p_ping)
	{
	}

	private void SetSelectState_SelectBuilding_SelectPingTargetTile(BuildingData buildingData, GuildWarPing p_ping)
	{
	}

	private bool RequestCreateCorps(GuildWar.TileData targetTileData)
	{
		return false;
	}

	private bool RequestDoCorpsMove(CorpsData sourceCorpsData, GuildWar.TileData targetTileData)
	{
		return false;
	}

	private bool CheckFunctionTileType(GuildWarCombatActiveFunction targetCorpsFunction, GuildWar.TileData targetTileData)
	{
		return false;
	}

	private bool RequestDoCorpsFunction(CorpsData sourceCorpsData, GuildWarCombatActiveFunction targetCorpsFunction, GuildWar.TileData targetTileData)
	{
		return false;
	}

	private void TrySelectTile(int posX, int posY)
	{
	}

	public int GetSelectedMyCorpsId()
	{
		return 0;
	}

	public int GetSelectedCorpsId()
	{
		return 0;
	}

	public int GetSelectedPlaceId()
	{
		return 0;
	}

	public CorpsData GetSelectedMyCorps()
	{
		return null;
	}

	public CorpsData GetSelectedCorp()
	{
		return null;
	}

	public BuildingData GetSelectedBuild()
	{
		return null;
	}

	public bool GetSelectedPosition(out int x, out int y)
	{
		x = default(int);
		y = default(int);
		return false;
	}

	public BuildingData GetSelectedMyBuild()
	{
		return null;
	}

	public BuildingData GetMyGuildBuilding()
	{
		return null;
	}

	public void MoveCameraInitPos()
	{
	}

	public List<BuildingData> GetCreateCorpsPosition()
	{
		return null;
	}

	public void ShowCreateCorpsPosition(List<BuildingData> buildingDatasHighPriority)
	{
	}

	private void LogWarning(string errorMsg)
	{
	}

	public void SelectCorps(CorpsData corpsData)
	{
	}

	public void SelectBuilding(BuildingData buildingData)
	{
	}

	public void StartCorpsActiveFunction(int functionId)
	{
	}

	public void StartCorpsCustomFunction(ECorpsCustomActionType customActionType, int customActionKey)
	{
	}

	public void StartPing(int p_pingId)
	{
	}

	public void CancelPing()
	{
	}

	public void CancelTargeting()
	{
	}

	[DebuggerHidden]
	private IEnumerator EventCustomActionSequence(ECorpsCustomActionType customActionType, int customActionKey)
	{
		return null;
	}

	public int CorpsLastGlobalCoolTime(CorpsData corpsData)
	{
		return 0;
	}

	public int CorpsLastMoveCoolTime(CorpsData corpsData)
	{
		return 0;
	}

	public void BlockInputUntilResponse(int requestIndex)
	{
	}

	public Vector2 GetWorldPosWithTileData(int x, int y)
	{
		return default(Vector2);
	}

	public GuildWar.TileData GetTileDataWithWorldPos(float x, float y)
	{
		return null;
	}

	public GuildWar.TileData GetTileData(Vector2Int pos)
	{
		return null;
	}

	public GuildWar.TileData GetTileData(int x, int y)
	{
		return null;
	}

	public Dictionary<HexDirection, GuildWar.TileData> GetNeighborsTile(int x, int y)
	{
		return null;
	}

	private Vector3Int CubeNeighbor(Vector3Int cube, HexDirection direction)
	{
		return default(Vector3Int);
	}

	private void CubeRing(List<GuildWar.TileData> target, int x, int y, int range)
	{
	}

	public IEnumerable<GuildWar.TileData> GetNeighborsTileByEnumerable(int x, int y, int range)
	{
		return null;
	}

	public IEnumerable<GuildWar.TileData> GetNeighborsTileByEnumerable(Vector2Int pos, int range)
	{
		return null;
	}

	public IEnumerable<GuildWar.TileData> GetNeighborsTileByEnumerable(int x, int y)
	{
		return null;
	}

	public IEnumerable<GuildWar.TileData> GetNeighborsTileByEnumerable(Vector2Int pos)
	{
		return null;
	}

	public IEnumerable<GuildWar.TileData> GetNeighborsTileByEnumerable(GuildWar.TileData tile)
	{
		return null;
	}

	private CorpsData GetCorpsByGrid(Vector2Int grid)
	{
		return null;
	}

	public int GetTileCost(List<GuildWar.TileData> Tiles, int corpsjobId)
	{
		return 0;
	}

	public List<KeyValuePair<GuildWar.TileData, List<GuildWar.TileData>>> GetMoveAreaTile(GuildWar.TileData StartTileData, int jobId)
	{
		return null;
	}

	public IEnumerable<GuildWar.TileData> GetAttackableTile(Vector2Int Start, int attackLength)
	{
		return null;
	}

	public List<KeyValuePair<GuildWar.TileData, List<GuildWar.TileData>>> GetMovableTile(Vector2Int Start, int jobId)
	{
		return null;
	}

	public static Vector3Int oddq_to_cube(Vector2Int hexPos)
	{
		return default(Vector3Int);
	}

	public static Vector2Int cube_to_oddr(Vector3Int cubeHex)
	{
		return default(Vector2Int);
	}

	public static Vector3Int cube_round(Vector3 cube)
	{
		return default(Vector3Int);
	}

	public static Vector3Int oddr_to_cube(Vector2Int hexPos)
	{
		return default(Vector3Int);
	}

	public static List<Vector2Int> GetGridArea(Vector2Int StartPos, int radius)
	{
		return null;
	}

	public int TileDistance(GuildWar.TileData A, GuildWar.TileData B)
	{
		return 0;
	}

	public int TileDistance(Vector2Int A, Vector2Int B)
	{
		return 0;
	}

	public Vector2 GetHexTileWorldPosition(int x, int y)
	{
		return default(Vector2);
	}

	public bool IsExistStightObstacle(Vector2Int StartPos, Vector2Int EndPos)
	{
		return false;
	}

	public List<Vector2Int> GetLinePos(Vector2Int StartPos, Vector2Int EndPos)
	{
		return null;
	}

	public void GoToLobby()
	{
	}

	public string RealVisionDataCheck(Vector2Int GridPos)
	{
		return null;
	}

	public static int GetTresureItemTransPoint(UserItem userItem)
	{
		return 0;
	}

	public static int GetTresureItemTransPoint(ulong itemSrl)
	{
		return 0;
	}

	public static int CalculateCreationMilitaryGoods(int militaryPower, int coefficientCorpsGoodsCost)
	{
		return 0;
	}

	public static int CalculateCorpsStat(int baseStat, int statRate, int statRatePlus, int level)
	{
		return 0;
	}

	public static bool isMyData(ulong guildId, ulong userId)
	{
		return false;
	}

	public static int[] GetCorpsRegenerationCoolTimeString(long creatableDate, long currentDate)
	{
		return null;
	}

	private Vector2Int UnitScalePositionToGridPosition(float posX, float posY)
	{
		return default(Vector2Int);
	}

	public void OnMinimapClicked(Vector3 pos)
	{
	}

	public void OnMinimapDragging(Vector3 pos)
	{
	}
}
