using System;
using UnityEngine;

public class PopupVisit : CaocaoUIModalPopup
{
	private bool deleteMode;

	private Action visitCallback;

	private Action editCallback;

	private UIReuseGrid m_grid;

	private UILabelLocalized m_labelFavorite;

	private GameObject m_btnCancel;

	private UILabelLocalized m_labelEdit;

	private UIReuseGrid grid
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized labelFavorite
	{
		get
		{
			return null;
		}
	}

	private GameObject btnCancel
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized labelEdit
	{
		get
		{
			return null;
		}
	}

	public static void Show(IDepthManagerblePopup parentPopup = null, Action visitCallback = null, Action editCallback = null)
	{
	}

	private void Init()
	{
	}

	private void RequestFollowUsers()
	{
	}

	private void UpdateUI()
	{
	}

	private void UpdateFavoriteLabel()
	{
	}

	public void OnBtnDeleteMode()
	{
	}

	public void OnBtnCancelDelete()
	{
	}

	private void ToggleDeleteMode()
	{
	}

	public void OnBtnMyInfo()
	{
	}

	public void OnBtnInput()
	{
	}

	private void OnBtnVisit(VisitUserListItem.CellData data)
	{
	}

	protected override void OnDestroy()
	{
	}

	protected override void ShowPanelImpl()
	{
	}

	protected override void ClosePanelImpl()
	{
	}

	public override void OnFocusOn()
	{
	}

	public override void OnFocusOut()
	{
	}

	public override void OnTappingOutSide()
	{
	}
}
