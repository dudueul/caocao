using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using UnityEngine;

public class PopupProvoke : CaocaoUIModalPopup
{
	private static PopupProvoke instance;

	private ulong opponentID;

	private int townID;

	private List<int> m_autoTextIds;

	private ProvokeListItem m_selectedItem;

	private int selectedId;

	private UIInput m_input
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized m_label
	{
		get
		{
			return null;
		}
	}

	private GameObject m_presetList
	{
		get
		{
			return null;
		}
	}

	private UIGrid m_presetGrid
	{
		get
		{
			return null;
		}
	}

	private UIScrollView m_presetScrollView
	{
		get
		{
			return null;
		}
	}

	public ProvokeListItem selectedItem
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	protected override void Awake()
	{
	}

	public static void SetSelectedItem(ProvokeListItem selected)
	{
	}

	private void UpdateAutoTextIndex()
	{
	}

	public void Init(ulong opponentID, int townID)
	{
	}

	protected override void ShowPanelImpl()
	{
	}

	protected override void ClosePanelImpl()
	{
	}

	public void ShowList()
	{
	}

	public void SendProvkeMsg()
	{
	}

	private bool IsValidMsg(string str)
	{
		return false;
	}

	[DebuggerHidden]
	private IEnumerator ChangeScene(string sceneName)
	{
		return null;
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
