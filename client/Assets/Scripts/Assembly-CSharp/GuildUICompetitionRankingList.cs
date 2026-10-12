using System;
using UnityEngine;

public class GuildUICompetitionRankingList : MonoBehaviour
{
	[Serializable]
	public class ListObject : GuildUIMemberListObject
	{
		protected override Func<GuildUIMemberCategory, GuildMemberCellData, GuildMemberCellData, int> GenerateCustomSortFunc()
		{
			return null;
		}
	}

	public Action<ulong, int> onSelectedItem;

	public Action<ulong, int> onReselectedItem;

	public ListObject listObject;

	protected void OnSelectItem()
	{
	}

	protected void OnReselectItem()
	{
	}

	protected void OnEnable()
	{
	}

	private void OnUpdateUI()
	{
	}

	private void OnDisable()
	{
	}

	private void OnDestroy()
	{
	}
}
