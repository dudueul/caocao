// [RE] Rebuild-only boot glue — the counterpart of the original
// CaoCaoUnityActivity.onCreate (Adjust deep-link → NPAccount.onActivityCreated)
// and the NPA account login that produced (userId, authKey).
// Rebuild: device-id guest auth → PImpl.CreateLocalSession → the session's
// own state machine (UpdateWebAuthKey → socket connect → login → enterToWorld).
//
// Scene-independence: a [RuntimeInitializeOnLoadMethod] self-instantiates in
// any scene, then wires the original NGUI buttons by name (Login scene has
// GameObjects "BtnLogin" (Nexon login) / "BtnTOYLogin" (TOY = internal
// codename)). No scene YAML surgery required — provenance: Login.unity object
// list, docs/lab/08 §8.4 step 1.
using System;
using System.Diagnostics;
using System.Collections;
using UnityEngine;
using UnityEngine.SceneManagement;

public class SessionBoot : MonoBehaviour
{
	// [RE] deterministic ulong from the device id — stand-in for the Nexon
	// account id; replace with a real auth provider in the auth milestone.
	public bool autoStart = true;

	public string nextSceneAfterLogin = "Town";   // [RE] Login → Town spine (Lab 01 §1.2)

	private static SessionBoot ms_instance;

	private bool started;

	private bool logined;

	public ulong GetGuestUserId()
	{
		var raw = SystemInfo.deviceUniqueIdentifier ?? "caocao-dev";
		using (var sha = new System.Security.Cryptography.SHA256Managed())
		{
			var hash = sha.ComputeHash(System.Text.Encoding.UTF8.GetBytes(raw));
			return BitConverter.ToUInt64(hash, 0) & 0x7fffffffffffffffUL;
		}
	}

	// [RE] original: Awake ran inside UnityPlayer activity; rebuild uses the
	// managed boot path so it works in editor + standalone + Android alike.
	[RuntimeInitializeOnLoadMethod(RuntimeInitializeLoadType.AfterSceneLoad)]
	private static void Bootstrap()
	{
		if (ms_instance != null)
			return;
		var go = new GameObject("SessionBoot");
		DontDestroyOnLoad(go);
		ms_instance = go.AddComponent<SessionBoot>();
		ms_instance.WireLoginSceneUI();
	}

	private void WireLoginSceneUI()
	{
		// [RE] NGUI wiring: UIButton.onClick + EventDelegate(target, "Method")
		// — the original panels used the same mechanism (EventDelegate stub).
		WireButton("BtnLogin", OnClickLogin);
		WireButton("BtnTOYLogin", OnClickLogin);
		// fallback: any key also logs in (useful while UI pieces are stubbed)
		StartCoroutine(WatchAnyKey());
	}

	private void WireButton(string goName, Action callback)
	{
		var go = GameObject.Find(goName);
		if (go == null)
			return;
		var button = go.GetComponent<UIButton>();
		if (button == null)
		{
			Debug.LogWarning("[SessionBoot] " + goName + " has no UIButton");
			return;
		}
		var d = new EventDelegate(this, "OnClickLogin");
		button.onClick.Add(d);
	}

	[DebuggerHidden]
	private IEnumerator WatchAnyKey()
	{
		while (!logined)
		{
			if (Input.anyKeyDown)
				OnClickLogin();
			yield return null;
		}
	}

	public void OnClickLogin()
	{
		StartSession();
	}

	public void StartSession()
	{
		if (started)
			return;
		started = true;
		GuildWar.Backend.PImpl.CreateLocalSession(GetGuestUserId());
		GuildWar.Backend.PImpl.OnAnySessionLogined += OnSocketLogined;
	}

	private void OnSocketLogined(string userId)
	{
		if (logined)
			return;
		logined = true;
		Debug.Log("[SessionBoot] socket logined as " + userId + " → " + nextSceneAfterLogin);
		SceneManager.LoadScene(nextSceneAfterLogin);
	}

	private void Start()
	{
		if (autoStart)
			StartSession();
	}
}
