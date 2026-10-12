using System;
using System.Collections.Generic;
using LitJson;

public class RaidManager : Singleton<RaidManager>
{
	private enum eRequests
	{
		eGetRaidInfos = 0,
		eSummon = 1,
		eCancelSummon = 2,
		eRefreshSummon = 3,
		eRefreshEnter = 4,
		eStartBattle = 5,
		eEndBattle = 6,
		eGetRaidRanking = 7,
		eRefreshUnits = 8,
		eAutoClear = 9,
		eRefreshSummonInBattle = 10,
		eSummonInBattle = 11,
		eAddReserve = 12,
		eDeleteReserve = 13,
		eCalibrateReserve = 14
	}

	private class RequestComparer : IEqualityComparer<eRequests>
	{
		bool IEqualityComparer<eRequests>.Equals(eRequests x, eRequests y)
		{
			return false;
		}

		int IEqualityComparer<eRequests>.GetHashCode(eRequests obj)
		{
			return 0;
		}
	}

	private Dictionary<ulong, Dictionary<ulong, DateTime>> m_rankingRequestTime;

	private Dictionary<eRequests, HttpRequest.ResponseCallback> m_callbackMap;

	public int CheckBadgeCount()
	{
		return 0;
	}

	public bool CheckRankingRequestTime(ulong guildId, ulong raidId)
	{
		return false;
	}

	public int GetReentCost(int raidUseType, int reentCount)
	{
		return 0;
	}

	public IRaidInfo FindMyRaidInfo(int raidType, int useType, int difficulty)
	{
		return null;
	}

	public bool IsUsedUnit(int raidType, int useType, int difficulty, UserUnit unit)
	{
		return false;
	}

	public int GetUsedUnitsCount(int raidType, int useType, int difficulty)
	{
		return 0;
	}

	private bool SetCallback(eRequests type, HttpRequest.ResponseCallback callback)
	{
		return false;
	}

	public void RequestGetRaidInfos(HttpRequest.ResponseCallback callback)
	{
	}

	public void RequestGetRaidAwakenInfos()
	{
	}

	public void RequestSummon(Raid raidData, int multiplier, HttpRequest.ResponseCallback callback)
	{
	}

	public void RequestCancelSummon(Raid raidData, HttpRequest.ResponseCallback callback)
	{
	}

	public void RequestRefreshSummon(Raid raidData, HttpRequest.ResponseCallback callback)
	{
	}

	public void RequestRefreshEnter(int raidType, int useType, int difficulty, ulong raidId, int cost, HttpRequest.ResponseCallback callback)
	{
	}

	public void RequestStartRaidBattle(int raidType, int useType, int difficulty, ulong raidId, string unitIds, int avrLv, int combatPower, HttpRequest.ResponseCallback callback)
	{
	}

	public void RequestEndRaidBattle(List<object> paramObjects, HttpRequest.ResponseCallback callback)
	{
	}

	public void RequestGetRaidRanking(ulong raidId, HttpRequest.ResponseCallback callback)
	{
	}

	public void RequestRefreshUnits(int raidType, int useType, int difficulty, ulong raidId, int cost, HttpRequest.ResponseCallback callback)
	{
	}

	public void RequestAutoClear(int raidType, int useType, int difficulty, ulong raidId, HttpRequest.ResponseCallback callback)
	{
	}

	public void RequestRefreshSummonInBattle(Raid raidData, HttpRequest.ResponseCallback callback)
	{
	}

	public void RequestSummonInBattle(Raid raidData, HttpRequest.ResponseCallback callback)
	{
	}

	public void RequestAddReserve(int idx, int dayofweek, int hour, int raidType, int difficulty, int multiplier, bool calibrate, HttpRequest.ResponseCallback callback)
	{
	}

	public void RequestDeleteReserve(int idx, HttpRequest.ResponseCallback callback)
	{
	}

	public void RequestCalibrateReserve(bool calibrate, HttpRequest.ResponseCallback callback)
	{
	}

	private void OnGetRaidInfos(JsonData response)
	{
	}

	private void OnGetRaidAwakenInfos(JsonData response)
	{
	}

	private void OnSummon(JsonData response)
	{
	}

	private void OnCancelSummon(JsonData response)
	{
	}

	private void OnRefreshSummon(JsonData response)
	{
	}

	private void OnRefreshEnter(JsonData response)
	{
	}

	private void OnStartRaidBattle(JsonData response)
	{
	}

	private void OnEndRaidBattle(JsonData response)
	{
	}

	private void OnGetRaidRanking(JsonData response)
	{
	}

	private void OnRefreshUnits(JsonData response)
	{
	}

	private void OnAutoClearRaid(JsonData response)
	{
	}

	private void OnRefreshSummonInBattle(JsonData response)
	{
	}

	private void OnSummonInBattle(JsonData response)
	{
	}

	private void OnAddReserve(JsonData response)
	{
	}

	private void OnDeleteReserve(JsonData response)
	{
	}

	private void OnCalibrateReserve(JsonData response)
	{
	}

	private void ProcessResponse(eRequests type, JsonData response)
	{
	}

	private bool ShowResponseError(eRequests type, JsonData response, HttpRequest.ResponseCallback responseCallback)
	{
		return false;
	}

	public void SendDamageToServer()
	{
	}

	public void ProcessReserve()
	{
	}
}
