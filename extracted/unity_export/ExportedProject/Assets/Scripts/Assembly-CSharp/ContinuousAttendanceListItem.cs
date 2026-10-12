using UnityEngine;

public class ContinuousAttendanceListItem : MonoBehaviour
{
	public Transform rewardUiParent;

	public GameObject normal;

	public GameObject special;

	public GameObject dim;

	private DailyGift dailyGiftPd;

	private bool completed
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public void UpdateUI(DailyGift dailyGiftPd, bool completed)
	{
	}

	private void UpdateItemUI()
	{
	}

	private void UpdateDayLabel()
	{
	}
}
