using UnityEngine;

public class BattleDetailListItem : MonoBehaviour
{
	public GameObject m_selected;

	public GameObject m_win;

	public GameObject m_lose;

	public UILabelLocalized m_winName;

	public UILabelLocalized m_loseName;

	public UILabelLocalized m_date;

	private bool m_isSelected;

	public BattleData battleData { get; private set; }

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

	private void OnClick()
	{
	}

	private void Start()
	{
	}

	private void OnDestroy()
	{
	}

	public void UpdateUserInfo()
	{
	}

	public void UpdateUI(BattleData battleData)
	{
	}
}
