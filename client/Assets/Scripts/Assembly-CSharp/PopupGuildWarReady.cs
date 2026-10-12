using System.Collections.Generic;
using UnityEngine;

public class PopupGuildWarReady : CaocaoUIModalPopup
{
	[SerializeField]
	private UILabel _statAtkValue;

	[SerializeField]
	private UILabel _statInteValue;

	[SerializeField]
	private UILabel _statDefValue;

	[SerializeField]
	private UILabel _statAgiValue;

	[SerializeField]
	private UILabel _statMorValue;

	[SerializeField]
	private UILabelLocalized _statAtkNum;

	[SerializeField]
	private UILabelLocalized _statInteNum;

	[SerializeField]
	private UILabelLocalized _statDefNum;

	[SerializeField]
	private UILabelLocalized _statAgiNum;

	[SerializeField]
	private UILabelLocalized _statMorNum;

	[SerializeField]
	private UISlider _guildwarFood;

	[SerializeField]
	private UISlider _treasurePoint;

	[SerializeField]
	private UILabel _guildwarFoodValue;

	[SerializeField]
	private UILabel _treasurePointValue;

	private static PopupGuildWarReady _self;

	private int _worldId;

	private int _totalTresurePoint;

	private bool m_invalidUI;

	private bool tradePopupActive;

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

	private void OnEnable()
	{
	}

	private void OnDisable()
	{
	}

	protected override void Awake()
	{
	}

	protected override void OnDestroy()
	{
	}

	public static void Show()
	{
	}

	public static void Close()
	{
	}

	private void Update()
	{
	}

	private void OnUserBaseDataDeserialized(HashSet<GuildWarUserBaseData> datas)
	{
	}

	private void InvalidateUI()
	{
	}

	private void UpdateUI()
	{
	}

	public void OnClickBtnShowBarracks()
	{
	}

	public void OnClickBtnShowTreasureSupplies()
	{
	}

	public void OnClickFoodTrade()
	{
	}

	public void OnClickBtnClose()
	{
	}
}
