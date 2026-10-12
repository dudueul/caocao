using System;
using System.Collections;
using System.Diagnostics;
using UnityEngine;

public class PvpMapInfoListItem : MonoBehaviour
{
	private const string RandomBattleMapImg = "PVPMapThumbnail_Random";

	public UITexture mapImage;

	public Transform selectedTransf;

	public UILabelLocalized modeNameLabel;

	public UILabelLocalized mapName;

	public GameObject objRandom;

	public Action<int> onSelectedFunc;

	private int warId;

	private bool isRandomCoroutine;

	private Action randomEffectCallback;

	public void UpdateUI(EnumData.BattleMode battleMode, int warId, bool selected = false)
	{
	}

	public void ShowRandomMap(EnumData.BattleMode battleMode, int warId, Action callback)
	{
	}

	[DebuggerHidden]
	private IEnumerator ShowRandomMapCoroutine(EnumData.BattleMode battleMode, int warId)
	{
		return null;
	}

	public void OnSelected()
	{
	}

	public void OnDeSelected()
	{
	}
}
