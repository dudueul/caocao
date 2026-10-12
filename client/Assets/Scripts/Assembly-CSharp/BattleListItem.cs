using UnityEngine;

public class BattleListItem : MonoBehaviour
{
	public GameObject m_new;

	public GameObject m_dim;

	public GameObject m_selected;

	public GameObject m_win;

	public GameObject m_lose;

	public UILabelLocalized m_winLabel;

	public UILabelLocalized m_loseLabel;

	public UILabelLocalized m_battleCountLabel;

	public UILabelLocalized m_honorLabel;

	public GameObject m_honorIcon;

	private bool m_isSelected;

	public BattleList battleList { get; private set; }

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

	public void GotRewards()
	{
	}

	public void UpdateUI(BattleList battleList)
	{
	}

	private void SetHonor(int honor)
	{
	}

	private void OnClick()
	{
	}
}
