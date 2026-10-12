using System;
using System.Collections.Generic;
using GuildWar;
using UnityEngine;

public class GuildWarBattleFieldTileMap : MonoBehaviour
{
	public static GuildWarBattleFieldTileMap instance;

	[SerializeField]
	public Color[] MoveHeightTileColor;

	[SerializeField]
	private Transform _guildWarMapTexture;

	[NonSerialized]
	public IntVector2 TileMapSize;

	[NonSerialized]
	public GuildWarMapInfo mapInfo;

	[SerializeField]
	private MapBorder mapBorder;

	[SerializeField]
	private Camera MinimapBGCam;

	[SerializeField]
	private Camera MinimapObjectCam;

	[SerializeField]
	private Camera MinimapFogCam;

	[SerializeField]
	private GuildWarMinimapTiles MinimapObjects;

	[SerializeField]
	private GuildWarMinimapTiles MinimapFogs;

	[SerializeField]
	private float CameraSize;

	[SerializeField]
	private SpriteRenderer MinimapBGBorder_East;

	[SerializeField]
	private SpriteRenderer MinimapBGBorder_West;

	[SerializeField]
	private SpriteRenderer MinimapBGBorder_South;

	[SerializeField]
	private SpriteRenderer MinimapBGBorder_North;

	private DataTables dataTables;

	private WorldData worldData;

	private ClientVisionData clientVisionData;

	private bool m_minimapBgTextureUpdated;

	private int prevObjectsPositionSeq;

	private int prevMyGuildVisionSeq;

	private CameraData cameraData;

	private GameObject buildingParent;

	private Dictionary<BuildingKey, GuildWarRendererBuild> rendererBuildings;

	private GameObject corpsParent;

	private Dictionary<CorpsKey, GuildWarRendererCorps> rendererCorps;

	private GameObject tilesGameObject;

	private GuildWarRendererTiles rendererTiles;

	public Action<int, int> OnClicked;

	private GameObject tileEffectsParent;

	private Dictionary<TileEffectKey, GuildWarRendererTileEffect> rendererTileEffects;

	private Queue<GuildWarRendererTileEffect> reuseTileEffects;

	private List<KeyValuePair<TileEffectKey, GuildWarRendererTileEffect>> toDeletesTileEffect;

	private GameObject pingsParent;

	public float cameraSize
	{
		get
		{
			return 0f;
		}
	}

	private int myGuildVisionSeq
	{
		get
		{
			return 0;
		}
	}

	private void Start()
	{
	}

	private void OnDestroy()
	{
	}

	public void Init()
	{
	}

	private void InitMinimapBorder()
	{
	}

	public void RenderingDataInit(WorldData worldData)
	{
	}

	public void RenderingDataExit()
	{
	}

	private string EffectTypeExtention(int effectType, Vector2Int from, Vector2Int to)
	{
		return null;
	}

	public void onDirectionCombatSource(DirectionCombatSourceData direction)
	{
	}

	public void onDirectionCombatTarget(DirectionCombatTargetData direction)
	{
	}

	public void onDirectionChangeHp(DirectionChangeHpData direction)
	{
	}

	private void ChangeGuildWarMapTexture(string GuildWarMapId)
	{
	}

	private void ChangeGuildWarMapTextreByIndex(string GuildWarMapId, int Index)
	{
	}

	private void LoadGuildWarMap(string GuildWarMapId)
	{
	}

	private void Awake()
	{
	}

	private void Update()
	{
	}

	private void LateUpdate()
	{
	}

	public void UpdateDatas()
	{
	}

	private void InitClientVision(WorldData worldData)
	{
	}

	private void UpdateClientVisionBG()
	{
	}

	private void UpdateClientVisionObjects()
	{
	}

	private void UpdateClientVisionFogs()
	{
	}

	private void UpdateClientVision()
	{
	}

	private void InitCamera(WorldData worldData)
	{
	}

	private Vector2Int GetClippingHalfSize()
	{
		return default(Vector2Int);
	}

	private void RefreshCamera()
	{
	}

	private void InitBuildings(WorldData worldData)
	{
	}

	private void OnBuildingAdded(BuildingKey buildingKey, BuildingData buildingData)
	{
	}

	private void RenderingBuilding(BuildingKey buildingKey, BuildingData buildingData)
	{
	}

	private void UpdateBuildings()
	{
	}

	private void InitCorps(WorldData worldData)
	{
	}

	private void OnCorpsAdded(CorpsKey corpsKey, CorpsData corpsData)
	{
	}

	private void RenderingCorps(CorpsKey corpsKey, CorpsData corpsData)
	{
	}

	private void UpdateCorps()
	{
	}

	private void InitTiles(WorldData worldData)
	{
	}

	private void UpdateTiles()
	{
	}

	public void UpdateTileCandidates(Dictionary<TileKey, TileCandidatesData.TileState> tileCandidates, GuildWarRendererTiles.UpdateTileSpriteRendererFunction updateTileSpriteRenderer)
	{
	}

	private void OnClickedRenderTiles(int posX, int posY)
	{
	}

	private void InitTileEffects(WorldData worldData)
	{
	}

	private void OnTileEffectAdded(TileEffectKey tileEffectKey, TileEffectData tileEffectData)
	{
	}

	private void OnTileEffectUpdated(TileEffectData tileEffectData)
	{
	}

	private void RenderingTileEffect(TileEffectKey tileEffectKey, TileEffectData tileEffectData)
	{
	}

	private void UpdateTileEffects()
	{
	}

	private void InitPings(WorldData worldData)
	{
	}

	public void AddPing(int p_pingId, int posX, int posY)
	{
	}

	private void SetLayerChildrenAll(GameObject gameObject)
	{
	}

	private void UpdatePings()
	{
	}
}
