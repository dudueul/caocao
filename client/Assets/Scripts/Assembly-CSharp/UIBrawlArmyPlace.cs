using System.Collections.Generic;
using UnityEngine;

public class UIBrawlArmyPlace : MonoBehaviour
{
	private const int mapWidth = 8;

	private const int mapHeight = 5;

	[SerializeField]
	private List<PrefabInstance> tileParents;

	private List<UIBattleFormationTile> tiles;

	private List<int> ownIndexList;

	private List<int> opponentIndexList;

	private void Start()
	{
	}

	private void OnEnable()
	{
	}

	public void UpdateUI()
	{
	}

	private void InitUI()
	{
	}

	private void InitMapInfo()
	{
	}

	private void InitTiles()
	{
	}

	private Texture GetFaceTexture(int bfIndex)
	{
		return null;
	}
}
