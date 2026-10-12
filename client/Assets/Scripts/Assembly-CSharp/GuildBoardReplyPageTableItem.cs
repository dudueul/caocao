using System;
using UnityEngine;

public class GuildBoardReplyPageTableItem : MonoBehaviour
{
	[SerializeField]
	private GuildBoardReplyPageListItem[] m_pageItems;

	private int m_currentPage;

	private int m_totalPages;

	private Action<int> m_pageSelectedCallback;

	public void Init(int currentPage, int totalPages, Action<int> pageSelectedCallback)
	{
	}

	private void UpdateUI()
	{
	}

	public void OnClickPrev()
	{
	}

	public void OnClickNext()
	{
	}
}
