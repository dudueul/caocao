using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using UnityEngine;

public class PanelThousandBattleLobby : PanelSelfRegister2UIComponentsMonobehaviour
{
	[Serializable]
	public class ProgressColor
	{
		public float from;

		public float to;

		public Color color;
	}

	public PlayMakerFSM playMakerFsm;

	public List<UITexture> bgTextures;

	public UILabelLocalized mapNameLabel;

	public UITexture miniMapTexture;

	public UIGrid enemyGrid;

	public UIScrollView enemyScrollView;

	public UILabelLocalized remainingSeasonTimeLabel;

	public UILabelLocalized stageProgressLabel;

	public UILabelLocalized stageProgressInfoLabel;

	public UISprite stageProgressSprite;

	public List<GameObject> stageGradeSprites;

	public List<ProgressColor> stageProgressColorInfos;

	public UILabelLocalized stageNameLabel;

	public List<UITexture> stageImages;

	public List<UITexture> stageDestroyedImages;

	public List<GameObject> stageLines;

	public GameObject stageInfoGo;

	public UIGrid itemGrid;

	public UIScrollView itemScrollView;

	public UIThousandBattleRecoveryButton recoveryButton;

	public UIButton battleButton;

	public UIButton autoBattleButton;

	public UILabelLocalized autoBattleButtonLabel;

	public GameObject btnStoreNewTag;

	public GameObject btnStoreLimit;

	public GameObject btnManagementNewtag;

	public const string popupName = "PanelThousandBattleLobby";

	private const int remainingTimeRefreshDuration = 1;

	private bool passPopupActive;

	private bool m_showingExitPopup;

	protected override void Awake()
	{
	}

	protected override void OnDestroy()
	{
	}

	public static void Show()
	{
	}

	public override void ShowPanel()
	{
	}

	public override void ClosePanel()
	{
	}

	public void OnSelectEnemy(UIThousandUnitGridItem unitGridItem)
	{
	}

	private void ClearEnemyGrid()
	{
	}

	private void ClearItemGrid()
	{
	}

	private void UpdateUi()
	{
	}

	private void UpdateRewardInfo()
	{
	}

	private void UpdateStage()
	{
	}

	private void UpdateStageInfo()
	{
	}

	private void UpdateEnemyInfo()
	{
	}

	[DebuggerHidden]
	private IEnumerator UpdateCurrentStage()
	{
		return null;
	}

	public void AddTimer()
	{
	}

	public void RemoveTimer()
	{
	}

	private void UpdateRemainingSeasonTime()
	{
	}

	private void SetButtonEnable(UIButton button, UILabelLocalized btnLabel, bool enable)
	{
	}

	public void GotoWorldMap()
	{
	}

	public void GotoBattle()
	{
	}

	public void ShowBattleInfo()
	{
	}

	public void ClearBattle()
	{
	}

	private HttpRequest.HttpRequestContext RequestPassSection(int infoId, int startId, int lastId, int missionCount, Action callback = null)
	{
		return null;
	}

	public void ShowShop()
	{
	}

	public void ShowManagement()
	{
	}

	private void CheckShopAlarms()
	{
	}

	private void CheckManagementAlarm()
	{
	}

	private void AddEventHandler()
	{
	}

	private void RemoveEventHandler()
	{
	}

	[DebuggerHidden]
	private IEnumerator GetThousandBattleInfos()
	{
		return null;
	}
}
