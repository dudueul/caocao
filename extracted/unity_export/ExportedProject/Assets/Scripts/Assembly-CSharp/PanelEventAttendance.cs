using System.Collections.Generic;
using LitJson;
using UnityEngine;

public class PanelEventAttendance : CaocaoUIModalPopup
{
	private List<GameObject> m_attendRewardObjs;

	protected override void Awake()
	{
	}

	public static bool IsAvailableEventAttendGift()
	{
		return false;
	}

	public static void CheckEventAttendance()
	{
	}

	public static void ShowEventAttendGiftPanel(int eventId, bool requestGift)
	{
	}

	public static EventDailyGift GetAvailableEventInfo()
	{
		return null;
	}

	public static DailyGift GetAvailableDailyGift(int eventId, int sequence)
	{
		return null;
	}

	public void ShowEventAttendPanel(int eventId, bool requestGift)
	{
	}

	private void UpdateUi(int eventId)
	{
	}

	private void SetInfo(EventDailyGift eventGift)
	{
	}

	private void SetReward(int eventId)
	{
	}

	private void OnGetReward(JsonData response)
	{
	}

	public void OnMarkTodayAttendance()
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
