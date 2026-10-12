using System;
using UnityEngine;

public class UIAnnihilationWarMatchInfo : MonoBehaviour
{
	public int index;

	public Transform profileUserInfo;

	public Transform profileUserInfoWithPVPRankIcon;

	public UILabelLocalized tierPointLabel;

	public UISprite tierIconSprite;

	public UILabelLocalized powerLabel;

	public UILabelLocalized combatPowerLabel;

	public UILabelLocalized battleFormationNameLabel;

	public UITexture[] faceUiTextures;

	public GameObject selectGo;

	public GameObject dummyUser;

	private UILabelLocalized rankLabel;

	private UILabelLocalized nameLabel;

	private AnnihilationWarEnemy m_enemy;

	public AnnihilationWarEnemy enemy
	{
		get
		{
			return null;
		}
	}

	public Action<UIAnnihilationWarMatchInfo> onSelect { get; set; }

	public void RefreshEnemy()
	{
	}

	public void UpdateUI()
	{
	}

	private string GetDifficultyString()
	{
		return null;
	}

	private string GetDifficultyDescString()
	{
		return null;
	}

	public void Select()
	{
	}

	public void Deselect()
	{
	}

	public void OnClick()
	{
	}

	public void OnClickBattleFormation()
	{
	}
}
