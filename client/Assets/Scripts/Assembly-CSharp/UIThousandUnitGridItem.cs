using System;
using System.Collections.Generic;
using Caocao;
using UnityEngine;

public class UIThousandUnitGridItem : MonoBehaviour
{
	public UITexture faceTexture;

	public List<GameObject> gradeGos;

	public UILabelLocalized nameLabel;

	public UILabelLocalized lvLabel;

	public Color bossLabelColor;

	public Color eliteLabelColor;

	public Color soldierLabelColor;

	public Action<UIThousandUnitGridItem> onSelect;

	public ThousandBattleInfo.UnitInfoBase unitInfo { get; set; }

	public int baseLevel { get; set; }

	public TroopsGrade enemyUnitType { get; set; }

	public void UpdateUi()
	{
	}

	private void OnClick()
	{
	}
}
