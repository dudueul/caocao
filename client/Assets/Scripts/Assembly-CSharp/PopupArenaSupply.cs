using System;
using LitJson;
using UnityEngine;

public class PopupArenaSupply : CaocaoUIModalPopup
{
	private UserArenaLevel userArenaLevel;

	private Action completeCallback;

	private UILabelLocalized m_labelTitle;

	private UIGrid m_gridUnit;

	private UIGrid m_gridItem;

	private Transform m_objBottom;

	private Transform m_objDeckSetting;

	private UILabelLocalized m_labelRefresh;

	private int arenaLevel
	{
		get
		{
			return 0;
		}
	}

	private UILabelLocalized labelTitle
	{
		get
		{
			return null;
		}
	}

	private UIGrid gridUnit
	{
		get
		{
			return null;
		}
	}

	private UIGrid gridItem
	{
		get
		{
			return null;
		}
	}

	private Transform objBottom
	{
		get
		{
			return null;
		}
	}

	private Transform objDeckSetting
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized labelRefresh
	{
		get
		{
			return null;
		}
	}

	protected override void Awake()
	{
	}

	public static void Show(int arenaLevel, Action completeCallback)
	{
	}

	private void UpdateUI()
	{
	}

	private void UpdateUnitSupplyGridUI(JsonData supplyDatas)
	{
	}

	private void UpdateUnitSupplyUI(JsonData supplyData, Transform gridItem)
	{
	}

	private void UpdateDeckSettingUI()
	{
	}

	private void UpdateBottomUI()
	{
	}

	private void UpdateBonusSupplyGridUI(JsonData supplyDatas, int supplyIdx)
	{
	}

	private void UpdateBonusSupplyUI(JsonData supplyData, Transform gridItem, int supplyIdx)
	{
	}

	private void ShowSelectedSupply()
	{
	}

	private void UpdatePassiveBonusUI(int id, bool isLock, Transform gridItem)
	{
	}

	private void UpdateTreasureBonusUI(int id, bool isLock, Transform gridItem)
	{
	}

	private void UpdateCoinBonusUI(int coinAmount, Transform gridItem)
	{
	}

	public void OnUnitListFaceButton()
	{
	}

	public void OnRefreshButton()
	{
	}

	public void OnCommandButton()
	{
	}

	public void OnStageInfoButton()
	{
	}

	public static void ResetSupplyListByCheat()
	{
	}

	protected override void OnDestroy()
	{
	}

	public override void OnFocusOn()
	{
	}

	public override void OnFocusOut()
	{
	}

	public override void OnTappingOutSide()
	{
	}

	protected override void ShowPanelImpl()
	{
	}

	protected override void ClosePanelImpl()
	{
	}
}
