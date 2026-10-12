using System.Collections.Generic;
using UnityEngine;

public class BattleMapFadeManager : SceneSingleton<BattleMapFadeManager>
{
	private GameObject fadeGO;

	private SpriteRenderer fadeRenderer;

	private int lastGoInstID;

	private Dictionary<int, string> directorInstId2TweenName;

	public void Request(BattleMapFade fade)
	{
	}

	private void ChechConflict(BattleMapFade one)
	{
	}

	private void EnsureValidFadeTexture()
	{
	}

	private void Prepare(BattleMapFade one)
	{
	}

	private void StartTween(BattleMapFade one)
	{
	}

	private void OnColorUpdate(Color color)
	{
	}
}
