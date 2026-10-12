// [RE] Rebuilt MyUserData (player-state singleton) — full original member
// surface restored (TypeDefIndex: MyUserData / UserData base). Core flow
// implemented: RefreshUserData → REST /user/getUserData → ApplyServerState
// (catalog + inventory + wallet). Endpoints verified against
// analysis/rest_endpoints.txt (scenario/AddScenario, relation/setFavorite,
// goods/ExchangeGoods).
using System;
using System.Collections;
using System.Collections.Generic;
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

	// [RE] rebuild-only currency cache (recovered server contract exposes
	// gold/cash directly on /user/getUserData).
	public long gold;

	public long cash;

	private Action ms_retrieveDatasCallback;

	private static MyUserData ms_instance;

	public static MyUserData instance
	{
		get
		{
			return ms_instance;
		}
	}

	public static bool hasInstance
	{
		get
		{
			return ms_instance != null;
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

	public void Awake()
	{
		ms_instance = this;
	}

	public void OnDestroy()
	{
		if (ms_instance == this)
			ms_instance = null;
	}

	private void Start()
	{
		ms_instance = this;
	}

	private void CaculateCurrentAmbition()
	{
		// [RE] original ambition score calc — arrives with the battle milestone.
	}

	private void RetrieveDatasCallback()
	{
		if (ms_retrieveDatasCallback != null)
			ms_retrieveDatasCallback();
		ms_retrieveDatasCallback = null;
	}

	public void SetRetrieveDatasCallback()
	{
		// [RE] original queued the callback until user data retrieval finished;
		// the rebuild fires it after the next RefreshUserData completes.
		ms_retrieveDatasCallback = null;
	}

	public void SetRetrieveDatasCallback(Action onDone)
	{
		ms_retrieveDatasCallback = onDone;
	}

	public void InitBattleResult()
	{
		m_battleResult = new UserBattleResult();
	}

	public void RegisterBattleResultItem(ulong srl)
	{
		if (m_battleResult != null)
			m_battleResult.RegisterItem(srl);
	}

	public void RegisterBattleResultSaveFlag(int id)
	{
		if (m_battleResult != null)
			m_battleResult.RegisterSaveFlag(id);
	}

	public void RegisterBattleResultRelations(int id)
	{
		if (m_battleResult != null)
			m_battleResult.RegisterRelations(id);
	}

	public override void Serialize(JsonWriter writer)
	{
		// [RE] UserData JSON contract — rebuilt fields only (server persists
		// its own rows; the client never uploads the whole state).
		writer.WriteObjectStart();
		writer.WritePropertyName("gold");
		writer.Write((int)gold);
		writer.WritePropertyName("cash");
		writer.Write((int)cash);
		writer.WriteObjectEnd();
	}

	[AppSealingSecurityMethod]
	protected override void DeserializeImpl(JsonData jsonData)
	{
		ApplyServerState(jsonData);
	}

	// [RE] server → client state application (the login/enter payload).
	public void ApplyServerState(JsonData state)
	{
		if (state == null)
			return;
		if (state.Keys.Contains("catalog"))
			ItemCatalog.Load(state["catalog"]);
		if (state.Keys.Contains("gold"))
			gold = (long)state["gold"];
		if (state.Keys.Contains("cash"))
			cash = (long)state["cash"];
		if (state.Keys.Contains("inventory"))
			items.Deserialize(state["inventory"]);
		online = true;
		RetrieveDatasCallback();
	}

	public void UpdateUnitFavorite(ulong srl, int isFavorite)
	{
		// [RE] original used /unit/... family; nearest recovered endpoint is
		// /relation/setFavorite — kept for API parity.
		HttpRequest.instance.Send("/relation/setFavorite", "POST", false, OnSetUnitFavorite,
			new object[] { srl, isFavorite });
	}

	private void OnSetUnitFavorite(JsonData response)
	{
	}

	public void UpdateUnitRelationFavorite(ulong srl, bool isFavorite)
	{
		HttpRequest.instance.Send("/relation/setFavorite", "POST", false, OnSetUnitRelationFavorite,
			new object[] { srl, isFavorite ? 1 : 0 });
	}

	private void OnSetUnitRelationFavorite(JsonData response)
	{
	}

	public void ExchangeByGold(int silver, int food, Action callback)
	{
		// [RE] recovered endpoint: /goods/ExchangeGoods
		HttpRequest.instance.Send("/goods/ExchangeGoods", "POST", true, delegate(JsonData jd)
		{
			OnExchange(jd);
			if (callback != null)
				callback();
		}, new object[] { silver, food });
	}

	[DebuggerHidden]
	private IEnumerator ProcessToExchangeGoods(int silver, int food, Action callback)
	{
		bool done = false;
		ExchangeByGold(silver, food, delegate
		{
			done = true;
		});
		while (!done)
			yield return null;
		yield break;
	}

	private void OnExchange(JsonData response)
	{
		if (response != null && response.Keys.Contains("gold"))
			gold = (long)response["gold"];
	}

	public void RefreshUserData()
	{
		HttpRequest.instance.Send("/user/getUserData", "POST", true, OnRefreshUserData);
	}

	private void OnRefreshUserData(JsonData jsonData)
	{
		ApplyServerState(jsonData);
	}

	public void AddScenario(int scenarioId, bool isSyncDb, int rootScenarioId)
	{
		if (!isSyncDb)
			return;
		HttpRequest.instance.Send("/scenario/AddScenario", "POST", true, OnAddScenario,
			new object[] { scenarioId, rootScenarioId });
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
		return string.Format("gold={0} cash={1} items={2}", gold, cash, items != null ? items.srl2Item.Count : 0);
	}
}
