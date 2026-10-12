using System;
using System.Collections;
using System.Diagnostics;
using LitJson;
using UnityEngine;

public class MyUserData : UserData
{
	public enum ProtectionStatus
	{
		NoProtected = 0,
		Protected = 1,
		AbsoluteProtected = 2
	}

	public bool online;

	private UserBattleResult m_battleResult;

	public static MyUserData instance
	{
		get
		{
			return null;
		}
	}

	public static bool hasInstance
	{
		get
		{
			return false;
		}
	}

	public ProtectionStatus protectionStatus
	{
		get
		{
			return ProtectionStatus.NoProtected;
		}
	}

	[DoNotJsonfy]
	public TimeSpan protectionDuration
	{
		get
		{
			return default(TimeSpan);
		}
	}

	private Coroutine StartCoroutine(IEnumerator routine)
	{
		return null;
	}

	private void Start()
	{
	}

	private void CaculateCurrentAmbition()
	{
	}

	private void RetrieveDatasCallback()
	{
	}

	public void SetRetrieveDatasCallback()
	{
	}

	public void InitBattleResult()
	{
	}

	public void RegisterBattleResultItem(ulong srl)
	{
	}

	public void RegisterBattleResultSaveFlag(int id)
	{
	}

	public void RegisterBattleResultRelations(int id)
	{
	}

	public override void Serialize(JsonWriter writer)
	{
	}

	[AppSealingSecurityMethod]
	protected override void DeserializeImpl(JsonData jsonData)
	{
	}

	public void UpdateUnitFavorite(ulong srl, int isFavorite)
	{
	}

	private void OnSetUnitFavorite(JsonData response)
	{
	}

	public void UpdateUnitRelationFavorite(ulong srl, bool isFavorite)
	{
	}

	private void OnSetUnitRelationFavorite(JsonData response)
	{
	}

	public void ExchangeByGold(int silver, int food, Action callback)
	{
	}

	[DebuggerHidden]
	private IEnumerator ProcessToExchangeGoods(int silver, int food, Action callback)
	{
		return null;
	}

	private void OnExchange(JsonData response)
	{
	}

	public void RefreshUserData()
	{
	}

	private void OnRefreshUserData(JsonData jsonData)
	{
	}

	public void AddScenario(int scenarioId, bool isSyncDb, int rootScenarioId)
	{
	}

	private void OnAddScenario(JsonData response)
	{
	}

	private void OnSuccessAddScenario(int scenarioId)
	{
	}

	public void CancelToProtection()
	{
	}

	public string GetInfoUserDataForNxLog()
	{
		return null;
	}
}
