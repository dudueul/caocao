using System;
using UnityEngine;

public class GuildBoardReplyPageListItem : MonoBehaviour
{
	private static Color32 deselectedColor;

	private UILabelLocalized m_pageLabel;

	private GameObject m_pageSelectedObj;

	private BoxCollider m_collider;

	private UIButton m_btn;

	private int m_page;

	private bool m_isCurrentPage;

	private Action<int> m_pageSelectedCallback;

	public void Init(int page, bool isCurrentPage, Action<int> pageSelectedCallback)
	{
	}

	private void UpdateUI()
	{
	}
}
