using System;
using System.Collections.Generic;
using GuildWar;
using UnityEngine;

public class GuildWarRendererTiles : GuildWarRenderer
{
	public enum TileSpriteType
	{
		None = 0,
		AttackRange = 1,
		SelectableTarget = 2,
		Selected = 3,
		Movable = 4,
		SkillRange = 5,
		MoveRange = 6,
		COUNT = 7
	}

	public delegate void UpdateTileSpriteRendererFunction(GuildWar.TileData tileData, TileCandidatesData.TileState tileState, SpriteRenderer spriteRenderer, Sprite[] tileSprites, Color[] moveHeightTileColor);

	private WorldData _worldData;

	private Sprite[] tileSprites;

	private Color[] moveHeightTileColor;

	public Action<int, int> OnClicked;

	private PolygonCollider2D polygonCollider;

	private float mousedownTime;

	private Vector3 mousedownPos;

	private Queue<GuildWarRendererTile> rendererTileReusePool;

	private Dictionary<TileKey, GuildWarRendererTile> rendererTiles;

	private int tileCandidatesVersion;

	private UpdateTileSpriteRendererFunction updateTileSpriteRenderer;

	private void Start()
	{
	}

	private void Update()
	{
	}

	private void LateUpdate()
	{
	}

	private void OnMouseUpAsButton()
	{
	}

	private void OnMouseDown()
	{
	}

	public void RenderringInit(WorldData worldData, Color[] moveHeightTileColor)
	{
	}

	public void RenderingUpdate()
	{
	}

	public void RenderringExit()
	{
	}

	private GuildWarRendererTile GetRendererTile(TileKey tileKey)
	{
		return null;
	}

	private void DisposeTileObject(GuildWarRendererTile rendererTile)
	{
	}

	private void InitTileObjects()
	{
	}

	public void UpdateTileCandidates(Dictionary<TileKey, TileCandidatesData.TileState> tileCandidates, UpdateTileSpriteRendererFunction updateTileSpriteRenderer)
	{
	}

	private void GetMousePositionToHexPosition(Vector3 mousePosition, out int posX, out int posY)
	{
		posX = default(int);
		posY = default(int);
	}

	private void OnMouseUpAsButtonTileObject()
	{
	}

	private void OnMouseDownTileObject()
	{
	}
}
