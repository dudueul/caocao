using System.Collections.Generic;
using Worldboss;

public class PopupWorldboss : CaocaoUIModalPopup
{
	public UILabelLocalized titleMonth;

	public CaocaoTabButtons tabButtons;

	public UIWorldBossHistory history;

	public UIWorldBossStatus status;

	public UIWorldBossShop shop;

	public UIWorldBossHelp help;

	private bool m_onSync;

	private SeasonStatus? m_lastSeasonStatus;

	public bool onSync
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public SeasonStatus lastSeasonStatus
	{
		get
		{
			return SeasonStatus.None;
		}
		set
		{
		}
	}

	public void OnEnable()
	{
	}

	public void OnDisable()
	{
	}

	protected override void OnDestroy()
	{
	}

	public static void Show()
	{
	}

	private void Initialize()
	{
	}

	private void UpdateTitle()
	{
	}

	private void SyncToServer()
	{
	}

	public void UpdateTabBtn()
	{
	}

	private void SetActiveNewTag(CaocaoButton tabButton, bool active)
	{
	}

	private bool OnCheckTabChange(CaocaoButton tabButton)
	{
		return false;
	}

	private void OnTabChanged(CaocaoButton tabButton)
	{
	}

	public Dictionary<int, ulong> GetRankRecords()
	{
		return null;
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
