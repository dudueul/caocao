using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using CodeStage.AntiCheat.ObscuredTypes;
using LitJson;

public class WorldMapControlFlow : Singleton<WorldMapControlFlow>
{
	public delegate void RefreshTownEventHandler(object sender, EventArgs args);

	public enum sortType
	{
		regionName = 0,
		townName = 1,
		status = 2,
		mayor = 3,
		popularity = 4
	}

	private ObscuredInt m_controlTownId;

	private bool m_isOpenTownList;

	private float m_scrollLocalPosY;

	private ObscuredInt m_controlInstanceId;

	private ObscuredInt m_controlBattleFieldId;

	private RefreshTownEventHandler OnRefreshTown__BackingField;

	private int m_sumFood;

	private int m_sumSilver;

	public int controlTownId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public bool isOpenTownList
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public float scrollLocalPosY
	{
		get
		{
			return 0f;
		}
		set
		{
		}
	}

	public int controlInstanceId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int controlBattleFieldId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public int sumFood
	{
		get
		{
			return 0;
		}
	}

	public int sumSilver
	{
		get
		{
			return 0;
		}
	}

	public event RefreshTownEventHandler OnRefreshTown
	{
		add
		{
		}
		remove
		{
		}
	}

	protected WorldMapControlFlow()
	{
	}

	private void Start()
	{
	}

	private void InitSumFoodSilver()
	{
	}

	[DebuggerHidden]
	public IEnumerator HarvestAll(List<int> townIds)
	{
		return null;
	}

	[DebuggerHidden]
	public IEnumerator HarvestAll(sortType sortingOrder, bool isDescend)
	{
		return null;
	}

	public int GetHarvestWaitTime()
	{
		return 0;
	}

	public List<int> GetHarvestAvailableTownIds(sortType sortingOrder, bool isDescend, bool harvestFood, bool harvestSilver)
	{
		return null;
	}

	public List<UserTown> GetSortedTownList(sortType orderType, bool isDescend)
	{
		return null;
	}

	private void OnHarvestAllTowns(JsonData response)
	{
	}

	public void RequestFindOpponent(Action callBack, int renew = 0)
	{
	}

	public void RequestFindShamBattleOpponent(Action callBack)
	{
	}

	public void ChangeScene(string sceneName)
	{
	}

	public void ChangeScene(string sceneName, bool autoDestroyLoadingUI)
	{
	}

	public void ChangeScene(string sceneName, bool autoDestroyLoadingUI, bool showLoadingScene)
	{
	}

	[DebuggerHidden]
	private IEnumerator ChangeSceneCoroutine(string sceneName, bool autoDestroyLoadingUI, bool showLoadingScene)
	{
		return null;
	}

	public void ReconTown()
	{
	}

	public void AttackTown()
	{
	}

	public void ReconTownOnShamBattle()
	{
	}

	public void RenewOppenentUserReconTown()
	{
	}

	private void AttackTownCallback()
	{
	}

	private void ReconTownCallback()
	{
	}

	private void ReconTownOnShamBattleCallback()
	{
	}

	public void Reclaim(BattleData battleData)
	{
	}

	public void RequestGetOpponent(BattleData battleData)
	{
	}

	public void RequestGetOpponent(ulong targetUserId)
	{
	}

	public void ManageTown(int? townId = null)
	{
	}

	public void ReconBattleField(EnumData.BattleFieldSearchType searchType)
	{
	}

	private void ReconBattleFieldCallback(EnumData.BattleFieldSearchType searchType)
	{
	}

	public void DefenseDeckBattleField(bool isFirstSetting = false)
	{
	}
}
