using System.Collections.Generic;
using LitJson;
using UnityEngine;

public class PopupGuildRankerList : MonoBehaviour
{
	[SerializeField]
	private UIGrid grid;

	[SerializeField]
	private UIScrollView scrollView;

	private List<UIGuildRankerListItem> gridItems;

	public void UpdateUI(List<RankListItem.TopRankerData> rankerList)
	{
	}

	public void UpdateUI(JsonData topRankerData)
	{
	}

	public void OnClose()
	{
	}
}
