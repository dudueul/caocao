using LitJson;

// [RE] Rebuilt HeartBeats (TypeDefIndex 10898).
// Decompiled facts kept: networkInterval/aliveInterval ints, lastTimeUser
// Interaction float, m_serverNoticeMsg, entrustTurn/entrustBattle (AI
// delegation while away), SendHeartBeats → JSON response handler
// OnHeartBeats(JsonData), OnApplicationPause handling, OnUseOffer.
// The original SendHeartBeats also drove the CDN patcher (PatcherUtil.
// GetBundleTarget → ResourceManager.patchProcessor); that path moves to
// the resource milestone — provenance noted below.
using UnityEngine;

public class HeartBeats : Singleton<HeartBeats>
{
	// [RE] fields are readonly in the original (ctor-initialized); rebuild
	// exposes them for inspector tuning.
	[SerializeField]
	private int networkInterval = 30;      // seconds between REST keepalives

	[SerializeField]
	private int aliveInterval = 120;       // seconds before "is user alive?" logic

	[SerializeField]
	private float lastTimeUserInteraction;

	[SerializeField]
	private string m_serverNoticeMsg;

	[SerializeField]
	private bool entrustTurn;

	[SerializeField]
	private bool entrustBattle;

	private float m_networkTimer;

	private float m_aliveTimer;

	public string serverNoticeMsg
	{
		get
		{
			return m_serverNoticeMsg;
		}
	}

	public bool isEntrustTurn
	{
		get
		{
			return entrustTurn;
		}
	}

	public bool isEntrustBattle
	{
		get
		{
			return entrustBattle;
		}
	}

	public void NotifyUserInteraction()
	{
		lastTimeUserInteraction = 0f;
	}

	public void Start()
	{
		lastTimeUserInteraction = 0f;
	}

	private void Update()
	{
		lastTimeUserInteraction += Time.unscaledDeltaTime;
		m_networkTimer += Time.unscaledDeltaTime;
		m_aliveTimer += Time.unscaledDeltaTime;
		if (m_networkTimer >= networkInterval)
		{
			m_networkTimer = 0f;
			SendHeartBeats();
		}
		if (m_aliveTimer >= aliveInterval)
		{
			m_aliveTimer = 0f;
			CheckUserInteractionTimeout();
		}
	}

	private void CheckUserInteractionTimeout()
	{
		// [RE] after aliveInterval with no interaction the server was told the
		// client may be abandoned → entrust (AI plays your turn).
		entrustTurn = lastTimeUserInteraction > aliveInterval;
		entrustBattle = entrustTurn;
	}

	private void SendHeartBeats()
	{
		// [RE] REST keepalive: /user/updateHistory returns {success, seq,
		// notice} — the decompiled OnHeartBeats(JsonData response) consumed it.
		HttpRequest.instance.Send("/user/updateHistory", "POST", false, OnHeartBeats);
	}

	private void OnHeartBeats(JsonData response)
	{
		if (response == null)
			return;
		if (response.Keys.Contains("notice"))
			m_serverNoticeMsg = response["notice"].ToString();
		if (response.Keys.Contains("entrustTurn"))
			entrustTurn = (bool)response["entrustTurn"];
		if (response.Keys.Contains("entrustBattle"))
			entrustBattle = (bool)response["entrustBattle"];
	}

	private void OnApplicationPause(bool pause)
	{
		// [RE] original forces an immediate heartbeat on resume so the server
		// can re-sync time/rooms after the app slept.
		if (!pause)
		{
			m_networkTimer = networkInterval;
		}
	}

	private void OnUseOffer(JsonData response)
	{
		// [RE] offer/ads hook — consumed by the ads milestone.
	}
}
