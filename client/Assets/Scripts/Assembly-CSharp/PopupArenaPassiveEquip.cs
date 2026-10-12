using System.Collections.Generic;

public class PopupArenaPassiveEquip : CaocaoUIModalPopup
{
	private UserArenaUnit m_userArenaUnit;

	private int m_index;

	private List<ulong> m_arenaPassives;

	private ulong m_arenaSrl;

	private PassiveEquipGridItem m_selected;

	private UIGrid m_grid;

	private TooltipContentsGenerator m_tooltip;

	private PassiveEquipGridItem selected
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	private UIGrid grid
	{
		get
		{
			return null;
		}
	}

	private TooltipContentsGenerator tooltip
	{
		get
		{
			return null;
		}
	}

	public static void Show(UserArenaUnit userArenaUnit, int index)
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

	public void ClearPassive()
	{
	}

	public void EquipPassive()
	{
	}

	private void EquipPassive(ulong passiveSrl, ulong prevUnitSrl, int prevUnitPassiveIndex)
	{
	}

	protected override void OnDestroy()
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

	protected override void ShowPanelImpl()
	{
	}

	protected override void ClosePanelImpl()
	{
	}
}
