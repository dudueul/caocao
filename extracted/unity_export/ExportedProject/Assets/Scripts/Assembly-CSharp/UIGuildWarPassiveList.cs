using GuildWar;
using UnityEngine;

public class UIGuildWarPassiveList : MonoBehaviour
{
	[SerializeField]
	private UISprite m_iconSprite;

	[SerializeField]
	private UILabelLocalized m_nameLabel;

	[SerializeField]
	private UILabelLocalized m_leftTimeLabel;

	private GuildWarPassiveList m_guildWarPassiveList;

	private CorpsPassiveData m_corpsPassive;

	private BuildingPassiveData m_buildingPassive;

	private bool m_enableState;

	public bool enableState
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public GuildWarPassiveList guildWarPassiveList
	{
		get
		{
			return null;
		}
	}

	public CorpsPassiveData corpsPassive
	{
		get
		{
			return null;
		}
	}

	public BuildingPassiveData buildingPassive
	{
		get
		{
			return null;
		}
	}

	public void SetPassiveListWithBuildingPassive(GuildWarPassiveList guildWarPassiveList, BuildingPassiveData buildingPassive)
	{
	}

	public void SetPassiveListWithCorpsPassive(GuildWarPassiveList guildWarPassiveList, CorpsPassiveData corpsPassive)
	{
	}

	private void Start()
	{
	}

	private void Update()
	{
	}

	private void UpdateUIEnableState()
	{
	}

	private void UpdateIconUI()
	{
	}

	private void UpdateNameLabelUI()
	{
	}

	private void UpdateLeftTimeUI()
	{
	}

	public void UpdateUI()
	{
	}
}
