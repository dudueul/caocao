using GuildWar;
using UnityEngine;

public class UIGuildWarBottomButton : MonoBehaviour
{
	private enum buttonStatus
	{
		None = 0,
		ShowLock = 1,
		ShowActive = 2,
		ShowDisturbance = 3,
		ShowCoolTime = 4,
		ShowActionPointLimit = 5
	}

	private UISprite IconImage;

	private GameObject CoolDown_Black;

	private GameObject CoolDown_Red;

	private GameObject CoolDown_Blue;

	private UILabel CoolDown_TimeText;

	private GameObject Activate;

	private GameObject Selected;

	private int m_FunctionId;

	private EFunctionCategory m_functionCategory;

	private long m_functionCoolDownDate;

	private ECorpsCustomActionType m_CustomActionType;

	private int m_CustomActionKey;

	private long m_CustomfunctionCoolDownDate;

	private bool m_IsCustomFunction;

	private long m_passiveEndTime;

	private long m_lockEndTime;

	private int m_needActionPoint;

	private int m_maxActionPoint;

	private int m_actionPointRecoveryTime;

	private int m_actionPointRecoveryAmount;

	private int m_currentActionPoint;

	private long m_lastestActionDate;

	private Vector2Int m_pos;

	private bool isAvailableSelect;

	private CorpsFunctionData m_functionData;

	private buttonStatus ButtonStatus;

	public int FunctionId
	{
		get
		{
			return 0;
		}
	}

	public ECorpsCustomActionType CustomActionType
	{
		get
		{
			return ECorpsCustomActionType.None;
		}
	}

	public int CustomActionKey
	{
		get
		{
			return 0;
		}
	}

	public bool IsCustomFunction
	{
		get
		{
			return false;
		}
	}

	private bool CheckNeedActionPoint
	{
		get
		{
			return false;
		}
	}

	private void Start()
	{
	}

	public void ClearData()
	{
	}

	public void InitData(CorpsFunctionData functionData, long passiveEndTime, long lockEndTime, int maxActionPoint, int actionPointRecoveryTime, int actionPointRecoveryAmount, int currentActionPoint, long lastestActionDate, Vector2Int pos)
	{
	}

	public void InitData(CorpsCustomFunctionData functionData, long passiveEndTime, long lockEndTime, Vector2Int pos)
	{
	}

	private void SetIcon(string atlasName, string icon)
	{
	}

	private void SetSelectActive()
	{
	}

	private void ShowActive()
	{
	}

	private void ShowLock()
	{
	}

	private void ShowDisturbance(int remainTime)
	{
	}

	private void ShowCoolTime(int remainTime)
	{
	}

	private void ShowActionPointLimit()
	{
	}

	private void ChangeFunction()
	{
	}

	private void ChangeCustomFunction()
	{
	}

	private void Update()
	{
	}

	public void OnBtnClick()
	{
	}
}
