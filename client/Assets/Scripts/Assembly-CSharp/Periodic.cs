using System.Collections;
using System.Diagnostics;
using LitJson;

public class Periodic : Singleton<Periodic>
{
	private enum VIP_HARVEST_TYPE
	{
		LOGIN = 0,
		CONNECTING = 1,
		MAX = 2
	}

	private bool[] m_isVipHarvest;

	private float m_lastCheckTime;

	public void RequestBattleHistory()
	{
	}

	private void OnGetBattleHistory(JsonData response)
	{
	}

	public void StartVipHarvest()
	{
	}

	private void StartHarvestToConnecting()
	{
	}

	private void VipHarvestToLogin()
	{
	}

	private void VipHarvestToConnecting()
	{
	}

	[DebuggerHidden]
	private IEnumerator _VipHarvest()
	{
		return null;
	}
}
