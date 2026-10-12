using LitJson;
using UnityEngine;

public class UserDefaultInfoUI : PopupUserInfo.InnerUI
{
	private enum PvpRecordTab
	{
		Current = 0,
		Best = 1
	}

	private class InfoUserData : UserData
	{
		public override bool willRegister
		{
			get
			{
				return false;
			}
		}
	}

	private Transform transform;

	private JsonData jsonData;

	private JsonData privacyData;

	private PvpRecordTab pvpTab;

	private InfoUserData infoUserData;

	private float deltaTime;

	private const float RANDOM_ANI_SEC = 5f;

	private Transform m_contentList;

	private UILabelLocalized m_labelBattlePoint;

	private UILabelLocalized m_labelStory;

	private UILabelLocalized m_labelThousand;

	private Transform m_trsRaid;

	private UIGrid m_gridPvpScore;

	private UIGrid m_gridUnit;

	private UILabelLocalized m_labelCombatPower;

	private GameObject m_objDim;

	private CaocaoTabButtons m_tabButton;

	public ulong uid { get; set; }

	private Transform contentList
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized labelBattlePoint
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized labelStory
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized labelThousand
	{
		get
		{
			return null;
		}
	}

	private Transform trsRaid
	{
		get
		{
			return null;
		}
	}

	private UIGrid gridPvpScore
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

	private UILabelLocalized labelCombatPower
	{
		get
		{
			return null;
		}
	}

	private GameObject objDim
	{
		get
		{
			return null;
		}
	}

	private CaocaoTabButtons tabButton
	{
		get
		{
			return null;
		}
	}

	public void Init(Transform trs, ulong uid)
	{
	}

	private void OnTabChanged(CaocaoButton button)
	{
	}

	public void SetActive(bool active)
	{
	}

	public void UpdateUI(JsonData response)
	{
	}

	private void UpdatePvpRecordUI()
	{
	}

	private void UpdateUnitGroupUI(bool openHeroInfo)
	{
	}

	private void OpenUnitInfo(int unitId)
	{
	}

	public void Update()
	{
	}

	public void Destroy()
	{
	}
}
