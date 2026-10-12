using System;
using UnityEngine;

public class GuildUIRecruitMemberListItem : UIReuseScrollViewCell
{
	public static GuildUIMemberCategory.IGuildUIMemberListBody listObj;

	public static Action<int> onClick;

	protected UILabelLocalized[] arLabels;

	private GameObject objSelect;

	protected GuildUIMemberListObject.GuildMemberCellData m_cellData;

	public override int Index
	{
		get
		{
			return 0;
		}
	}

	public override void UpdateData(IReuseCellData cellData)
	{
	}

	public virtual void Initiate()
	{
	}

	protected virtual void UpdateUI()
	{
	}

	private void OnClick()
	{
	}

	protected virtual void OnDestroy()
	{
	}
}
