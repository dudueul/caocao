using UnityEngine;

public class EventListItem : MonoBehaviour
{
	public GameObject m_new;

	public GameObject m_selected;

	public GameObject m_questIcon;

	public GameObject m_bossIcon;

	public GameObject m_eventIcon;

	public UILabelLocalized m_title;

	public UILabelLocalized m_enemyLevel;

	public UILabelLocalized m_rewardList;

	public UILabelLocalized m_expire;

	public UISprite[] m_stars;

	private readonly int RewardMaxLength;

	private bool m_isSelected;

	public UserInstance userInst { get; private set; }

	public bool isSelected
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public void UpdateUI(UserInstance userInst)
	{
	}

	private void UpdateRewardList()
	{
	}

	private void OnClick()
	{
	}
}
