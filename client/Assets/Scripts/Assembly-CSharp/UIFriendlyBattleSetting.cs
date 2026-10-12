using System;
using LitJson;
using UnityEngine;

public class UIFriendlyBattleSetting : MonoBehaviour
{
	private enum BtnType
	{
		ChangeMap = 0,
		EditDeck = 1
	}

	[SerializeField]
	private PvpMapInfoListItem mapInfo;

	[SerializeField]
	private UIBattleFormationFaces annihilationDeck;

	[SerializeField]
	private UIBattleFormationFaces brawlDeck;

	[SerializeField]
	private GameObject objHostBtns;

	[SerializeField]
	private GameObject objOpponentBtns;

	[SerializeField]
	private GameObject btnEditFairRule;

	[SerializeField]
	private UIButton btnHostChangeMap;

	[SerializeField]
	private UILabelLocalized labelHostChangeMap;

	[SerializeField]
	private UIButton btnHostEditDeck;

	[SerializeField]
	private UIButton btnOppoEditDeck;

	[SerializeField]
	private UILabelLocalized labelOppoEditDeck;

	[SerializeField]
	private UILabelLocalized[] labels;

	public FairRule fairRule
	{
		get
		{
			return null;
		}
	}

	private FriendlyMatchingRoomMember me
	{
		get
		{
			return null;
		}
	}

	private bool isHost
	{
		get
		{
			return false;
		}
	}

	private FriendlyMatchingRoom roomInfo
	{
		get
		{
			return null;
		}
	}

	private string roomId
	{
		get
		{
			return null;
		}
	}

	public void UpdateUI()
	{
	}

	public void ShowRandomMap(int mapId, Action callback)
	{
	}

	public void ApplyFairRule()
	{
	}

	private void UpdateFairRuleUI()
	{
	}

	public void OnSocketCallback(JsonData jsonData)
	{
	}

	private void UpdateButtons()
	{
	}

	private void UpdateEditDeckBtn()
	{
	}

	private void OnChangeFairRule(JsonData fairRuleData)
	{
	}

	public void OnBtnChangeMap()
	{
	}

	public void OnBtnEditUnits()
	{
	}

	public void OnBtnSetFairRule()
	{
	}

	private void Start()
	{
	}

	private void OnDestroy()
	{
	}
}
