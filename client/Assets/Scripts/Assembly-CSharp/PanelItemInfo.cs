using UnityEngine;

public class PanelItemInfo : MonoBehaviour
{
	private Item m_itemData;

	public PrefabInstance m_itemInst;

	public UILabelLocalized m_name;

	public UILabelLocalized m_type;

	public UILabelLocalized m_count;

	public UILabelLocalized m_level;

	private ItemStatListItem m_statListItem;

	private ItemSpActionListItem[] m_spActionListItem;

	private ItemJobTypeListItem m_jobTypeListItem;

	private ItemDescListItem m_descListItem;

	public GameObject m_obtainInfoBtn;

	public UIScrollView m_scrollView;

	public Item itemData
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	private void InitUI()
	{
	}

	protected virtual void UpdateUI()
	{
	}

	public void ShowObtainInfo()
	{
	}
}
