using UnityEngine;

public class AutoClearIndicator : CaocaoUIModalPopup
{
	public enum ClearType
	{
		None = 0,
		StateClear = 1,
		Expedition = 2,
		GuildWarWait = 3,
		GuildWarAttend = 4
	}

	private static GameObject indicator;

	private UILabelLocalized desc;

	private int stateId;

	private EnumData.EventType eventType;

	private ClearType clearType;

	private int totalCount;

	private int currentCount;

	private string footer;

	private float lastUpdatedTime;

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

	public static void Show(EnumData.EventType _eventType, int _stateId, int _totalCount, ClearType _clearType)
	{
	}

	public static void Hide()
	{
	}

	public static void UpdateState(int _stateId)
	{
	}

	private void Update()
	{
	}

	private void UpdateFooter()
	{
	}

	private void UpdateText()
	{
	}
}
