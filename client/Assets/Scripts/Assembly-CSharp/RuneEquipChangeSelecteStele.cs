using System;
using UnityEngine;

public class RuneEquipChangeSelecteStele : MonoBehaviour
{
	[SerializeField]
	private UIToggle m_blueDragonSelectBtn;

	[SerializeField]
	private UIToggle m_vermillionBirdSelectBtn;

	[SerializeField]
	private UIToggle m_whiteTigerSelectBtn;

	[SerializeField]
	private UIToggle m_blackTortoiseSelectBtn;

	[SerializeField]
	private UIToggle m_ticketSelectBtn;

	[SerializeField]
	private UILabelLocalized m_blueDragonAmount;

	[SerializeField]
	private UILabelLocalized m_vermillionBirdAmount;

	[SerializeField]
	private UILabelLocalized m_whiteTigerAmount;

	[SerializeField]
	private UILabelLocalized m_blackTortoiseAmount;

	[SerializeField]
	private UILabelLocalized m_ticketAmount;

	[SerializeField]
	private UIGrid m_buttonGrid;

	[SerializeField]
	private UIGrid m_goodsGrid;

	public Action<EnumData.RaidStuffType, EnumData.RaidStuffType> OnSelectedStuffTyepChanged;

	private int m_needSteleAmount;

	private int m_needTicketAmount;

	private bool showTicketUI;

	private EnumData.RaidStuffType m_selectedSteleType;

	public EnumData.RaidStuffType selectedSteleType
	{
		get
		{
			return EnumData.RaidStuffType.NONE;
		}
	}

	public bool canReproduct
	{
		get
		{
			return false;
		}
	}

	public int GetNeedAmount(EnumData.RaidStuffType stuffType)
	{
		return 0;
	}

	private void Init()
	{
	}

	private void OnToggleStele(EnumData.RaidStuffType steleType)
	{
	}

	public void UpdateUI(int needSteleAmount, int needTicketAmount, EnumData.RuneEquipType runeEquipType)
	{
	}

	private void InitializeSelectBtnOnClickFunc(UIToggle togglebtn, EnumData.RaidStuffType stuffType)
	{
	}

	private void UpdateSelectBtnNeedAmountLabel(UIToggle selectBtn, EnumData.RaidStuffType stuffType)
	{
	}

	private void UpdateOwnAmountLabelColor(UILabelLocalized label, EnumData.RaidStuffType stuffType)
	{
	}

	public void CancelSelection()
	{
	}
}
