using System.Collections.Generic;
using LitJson;

public class PopupPassiveEquip : CaocaoUIModalPopup
{
	private UserUnit m_userUnit;

	private int m_index;

	private List<int> m_unitPassives;

	private List<int> m_jobPassives;

	private PassiveEquipGridItem m_selected;

	private UIScrollView m_scrollView
	{
		get
		{
			return null;
		}
	}

	private UIGrid m_grid
	{
		get
		{
			return null;
		}
	}

	private TooltipContentsGenerator m_tooltip
	{
		get
		{
			return null;
		}
	}

	public PassiveEquipGridItem selected
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public static void Show(UserUnit userUnit, int index)
	{
	}

	protected override void Awake()
	{
	}

	private void InitUI()
	{
	}

	private void UpdateUI()
	{
	}

	public void OnSelectPassive(PassiveEquipGridItem item)
	{
	}

	public void EquipPassive()
	{
	}

	public void ResetPassive()
	{
	}

	private void ShowNeedGoldPopup()
	{
	}

	private void SendRequestResetPassive(bool isFree, bool useTicket)
	{
	}

	private void OnResetPassive(JsonData response)
	{
	}

	private void OnRefillPassiveResetCount(JsonData response)
	{
	}

	public void BuyPassive()
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
