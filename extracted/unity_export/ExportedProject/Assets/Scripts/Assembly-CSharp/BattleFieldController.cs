using System;
using System.Collections;
using System.Diagnostics;
using LitJson;

public class BattleFieldController : Singleton<BattleFieldController>
{
	private UserBattleField m_attackFieldData;

	private BattleFieldDefenceController m_defenceController;

	private int m_opponentBFIndex;

	private int opponentDifficulty;

	private EnumData.BattleFieldSearchType searchType;

	private int m_waitIndex;

	private PanelPopup1BtnConfirm m_popupWaitUser;

	private PanelPopup2Buttons m_popupReconWait;

	private float m_updateReconWaitTime;

	private float m_rematchedTime;

	private float m_footerChangeTime;

	private int m_footerIndex;

	public UserBattleField attackFieldData
	{
		get
		{
			return null;
		}
	}

	public BattleFieldDefenceController defenceController
	{
		get
		{
			return null;
		}
	}

	public int opponentBFIndex
	{
		get
		{
			return 0;
		}
	}

	public bool firstDefenseSetting { get; set; }

	public float updateReconWaitTime
	{
		get
		{
			return 0f;
		}
	}

	public float rematchedTime
	{
		get
		{
			return 0f;
		}
	}

	public void Init()
	{
	}

	public UnitData InstantiateNewUnit(UnitData srcUnitData)
	{
		return null;
	}

	public void SetOpponentDifficulty(int value)
	{
	}

	public void RequestAddWaitUser(EnumData.BattleFieldSearchType searchType)
	{
	}

	public void RequestSearchBattleField()
	{
	}

	public void RequestCancelWaitUser()
	{
	}

	public void RequestCheckWaitUser()
	{
	}

	public void RequestGetOpponentData()
	{
	}

	public void RequestGetBattleFieldUnits()
	{
	}

	public void RequestSetBattleFieldDefenseUnits(HttpRequest.ResponseCallback responseCallback)
	{
	}

	public void RequestStartBattleFieldAttack(string clientUnits)
	{
	}

	public void RequestEndBattleFieldAttack()
	{
	}

	private void ResponseAddWaitUser(JsonData response)
	{
	}

	public void ResponseSearchBattleField(JsonData response)
	{
	}

	private void ResponseCancelWaitUser(JsonData response)
	{
	}

	private void ResponseCheckWaitUser(JsonData response)
	{
	}

	private void ResponseGetOpponentData(JsonData response)
	{
	}

	private void ResponseGetBattleFieldUnits(JsonData response)
	{
	}

	public bool CheckResponseSetBattleFieldDefenseUnits(JsonData response)
	{
		return false;
	}

	private void ResponseSetBattleFieldDefenseUnits(JsonData response)
	{
	}

	private void ResponseStartBattleFieldAttack(JsonData response)
	{
	}

	private bool ShowResponseError(JsonData packetData, Action confirmCallBack = null)
	{
		return false;
	}

	private bool CheckInvalidSearchResultOnRecon(JsonData packetData)
	{
		return false;
	}

	private void ShowPopupWaitIndex()
	{
	}

	private void ClosePopupWaitIndex()
	{
	}

	private void ShowPopupBattleFieldEntrance()
	{
	}

	private void ClosePopupBattleFieldEntrance()
	{
	}

	private void Update()
	{
	}

	private void SetDescPopupWaitIndex()
	{
	}

	private void SetDescPopupReconWait()
	{
	}

	protected override void Awake()
	{
	}

	protected override void OnDestroy()
	{
	}

	[DebuggerHidden]
	private IEnumerator ChangeSceneCoroutine(string sceneName)
	{
		return null;
	}
}
