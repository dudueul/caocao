using LitJson;
using UnityEngine;

public class UserNationInfoUI : PopupUserInfo.InnerUI
{
	private enum InfoType
	{
		Nation = 0,
		Story = 1,
		Num = 2
	}

	private const int RAID_TYPE_CNT = 4;

	private Transform transform;

	private JsonData nationData;

	private JsonData storyData;

	private InfoType infoType;

	private bool updatedStory;

	private Transform m_nation;

	private Transform m_story;

	private Transform m_contentList;

	private UILabelLocalized m_labelFound;

	private UILabelLocalized m_labelTown;

	private UILabelLocalized m_labelThousandBest;

	private UILabelLocalized[] m_labelBattleField;

	private UILabelLocalized[] m_labelRaid;

	private UILabelLocalized[] m_labelRaidAwakenDifficulty;

	private UILabelLocalized[] m_labelRaidAwakenDamage;

	private UILabelLocalized m_labelArenaLevel;

	private UILabelLocalized m_labelArenaStamp;

	private UILabelLocalized m_labelArenaClear;

	private UIButton m_btnToggle;

	private UILabelLocalized m_labelToggle;

	private GameObject m_objLeftArrow;

	private GameObject m_objRightArrow;

	private UIGrid m_gridStory;

	public ulong uid { get; set; }

	private Transform nation
	{
		get
		{
			return null;
		}
	}

	private Transform story
	{
		get
		{
			return null;
		}
	}

	private Transform contentList
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized labelFound
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized labelTown
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized labelThousandBest
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized[] labelBattleField
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized[] labelRaid
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized[] labelRaidAwakenDifficulty
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized[] labelRaidAwakenDamage
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized labelArenaLevel
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized labelArenaStamp
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized labelArenaClear
	{
		get
		{
			return null;
		}
	}

	private UIButton btnToggle
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized labelToggle
	{
		get
		{
			return null;
		}
	}

	private GameObject objLeftArrow
	{
		get
		{
			return null;
		}
	}

	private GameObject objRightArrow
	{
		get
		{
			return null;
		}
	}

	private UIGrid gridStory
	{
		get
		{
			return null;
		}
	}

	public void Init(Transform trs, ulong uid)
	{
	}

	private void TogglePage()
	{
	}

	private void UpdatePage(bool request)
	{
	}

	public void SetActive(bool active)
	{
	}

	public void UpdateUI(JsonData response)
	{
	}

	private void UpdateNationUI()
	{
	}

	private void UpdateStoryUI()
	{
	}

	public void Destroy()
	{
	}
}
