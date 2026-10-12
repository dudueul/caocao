using System;
using UnityEngine;

public class UIBattleFormationPreset : MonoBehaviour
{
	public UISprite bg;

	public UISprite[] numbers;

	public GameObject selectGo;

	public GameObject dimGo;

	public UILabelLocalized nameLabel;

	private bool m_owned;

	public Action<UIBattleFormationPreset> onSelect { get; set; }

	public BattleFormation battleFormation { get; set; }

	public void SetBattleFormation(BattleFormation battleFormation)
	{
	}

	public void Refresh()
	{
	}

	public void OnClick()
	{
	}

	public void Select()
	{
	}

	public void Deselect()
	{
	}
}
