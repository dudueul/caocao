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
//
// Login UI: Login.unity saves UI Root/Title_KR/{LoginBG, LoginUI x2} INACTIVE.
// The original turned them on from the Title_KR PlayMaker FSM:
//   Check For LoginUI Activation → Activate LoginUI Debug|Release → (CI logos)
//   → CI HIDE (LoginBG on) → ShowLogin (BlackBG/FO off)
// In the rebuild every PlayMaker action (ActivateGameObject, Wait, …) and the
// whole NGUI runtime (UIPanel.LateUpdate/FillAllDrawCalls, UIDrawCall,
// UICamera) are signature-only stubs: nothing activates the login UI, nothing
// could draw it, and the UI Root camera clears to solid black. ShowLoginUI()
// applies the FSM's end state, and OnGUI draws an IMGUI login/status panel so
// the connection to the backend is visible until NGUI is restored.
//
// NOTE: a global-namespace `Debug` stub (Plugins/Assembly-CSharp-firstpass)
// shadows UnityEngine.Debug for unqualified calls — always write
// UnityEngine.Debug here or the log is silently dropped.
using System;
using System.Diagnostics;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;

public class SessionBoot : MonoBehaviour
{
	// [RE] deterministic ulong from the device id — stand-in for the Nexon
	// account id; replace with a real auth provider in the auth milestone.
	public bool autoStart = true;

	public string nextSceneAfterLogin = "Town";   // [RE] Login → Town spine (Lab 01 §1.2)

	// IMGUI login/status panel — stands in for the stubbed NGUI login screen
	public bool showStatusOverlay = true;

	private const int StatusLogLines = 8;

	private static SessionBoot ms_instance;

	private bool started;

	private bool logined;

	private string loginedUserId;

	private GuildWar.Backend.Session session;

	private float startedAt;

	private string bootSceneName;

	private string serverUrl;

	private readonly Queue<string> statusLog = new Queue<string>();

	private string statusLogText = string.Empty;

	private GUIStyle titleStyle;

	private GUIStyle textStyle;

	private GUIStyle logStyle;

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
		ms_instance.bootSceneName = SceneManager.GetActiveScene().name;
		ms_instance.ShowLoginUI();
		ms_instance.WireLoginSceneUI();
	}

	// [RE] end state of the Title_KR FSM (Login.unity): one LoginUI variant on,
	// CI logos off, LoginBG on, BlackBG/FO off. The FSM picked the variant via
	// isDebugBuild (editor/dev → Debug, the one with the ID input_area).
	private void ShowLoginUI()
	{
		var title = FindInActiveScene("UI Root", "Title_KR");
		if (title == null)
			return;
		bool wantDebugVariant = UnityEngine.Debug.isDebugBuild;
		foreach (Transform child in title)
		{
			switch (child.name)
			{
				case "LoginBG":
					child.gameObject.SetActive(true);
					break;
				case "LoginUI":
					bool isDebugVariant = child.Find("input_area") != null;
					child.gameObject.SetActive(isDebugVariant == wantDebugVariant);
					break;
				case "CI_NEXON":
				case "CI_KOEI":
				case "CI_AKOU":
				case "BlackBG":
				case "FO":
					child.gameObject.SetActive(false);
					break;
			}
		}
		UnityEngine.Debug.Log("[SessionBoot] login UI activated (" + (wantDebugVariant ? "Debug" : "Release") + " LoginUI)");
	}

	// Transform.Find also reaches inactive objects (GameObject.Find does not).
	private static Transform FindInActiveScene(string rootName, string childPath)
	{
		foreach (var root in SceneManager.GetActiveScene().GetRootGameObjects())
		{
			if (root.name != rootName)
				continue;
			var found = root.transform.Find(childPath);
			if (found != null)
				return found;
		}
		return null;
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
			UnityEngine.Debug.LogWarning("[SessionBoot] " + goName + " has no UIButton");
			return;
		}
		if (button.onClick == null)
			button.onClick = new List<EventDelegate>();
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
		startedAt = Time.realtimeSinceStartup;
		UnityEngine.Debug.Log("[SessionBoot] StartSession — device userId=" + GetGuestUserId());
		GuildWar.Backend.PImpl.OnAnySessionLogined += OnSocketLogined;
		session = GuildWar.Backend.PImpl.CreateLocalSession(GetGuestUserId());
	}

	private void OnSocketLogined(string userId)
	{
		if (logined)
			return;
		logined = true;
		loginedUserId = userId;
		UnityEngine.Debug.Log("[SessionBoot] socket logined as " + userId + " → " + nextSceneAfterLogin);
		// [RE] the Login scene's full-screen black overlays (BlackBG/FadeTexture/
		// Dim) were faded out by PlayMaker FSM tweens in the original; FSMs are
		// stubbed in the rebuild, so clear them directly (Milestone 3 polish
		// replaces this with a proper NGUI fade).
		foreach (var overlayName in new[] { "BlackBG", "FadeTexture", "Dim", "Black" })
		{
			var overlay = GameObject.Find(overlayName);
			if (overlay != null)
				overlay.SetActive(false);
		}
		StartCoroutine(LoadNextSceneAfterFade());
	}

	[DebuggerHidden]
	private System.Collections.IEnumerator LoadNextSceneAfterFade()
	{
		// [RE] small delay so the login UI is visible/legible before the switch
		yield return new UnityEngine.WaitForSeconds(1.5f);
		SceneManager.LoadScene(nextSceneAfterLogin);
	}

	private void OnEnable()
	{
		Application.logMessageReceived += OnLogMessage;
	}

	private void OnDisable()
	{
		Application.logMessageReceived -= OnLogMessage;
	}

	private void Start()
	{
		try
		{
			serverUrl = ServerSetting.instance.GAME_SERVER_HOST_URL;
		}
		catch (Exception ex)
		{
			serverUrl = "(ServerSetting failed: " + ex.Message + ")";
		}
		UnityEngine.Debug.Log("[SessionBoot] Start — autoStart=" + autoStart + " server=" + serverUrl);
		if (autoStart)
			StartSession();
	}

	// Mirrors the session-flow logs (and any warning/error) onto the panel.
	// Main-thread logs only — transport threads don't reach this callback.
	private void OnLogMessage(string condition, string stackTrace, LogType type)
	{
		if (string.IsNullOrEmpty(condition))
			return;
		bool flowLog = condition.StartsWith("[SessionBoot]") || condition.StartsWith("[SessionImpl]")
			|| condition.StartsWith("[SocketIO]") || condition.StartsWith("[HttpRequest]")
			|| condition.StartsWith("[WsTransport]");
		if (!flowLog && type == LogType.Log)
			return;
		string line = condition;
		int newline = line.IndexOf('\n');
		if (newline >= 0)
			line = line.Substring(0, newline);
		if (line.Length > 160)
			line = line.Substring(0, 160) + "…";
		if (type != LogType.Log)
			line = "[" + type + "] " + line;
		statusLog.Enqueue(line);
		while (statusLog.Count > StatusLogLines)
			statusLog.Dequeue();
		statusLogText = string.Join("\n", statusLog.ToArray());
	}

	private string StatusText()
	{
		if (logined)
			return "Logged in as " + loginedUserId + " — loading " + nextSceneAfterLogin + "…";
		if (!started)
			return "Press Login (or any key) to connect.";
		string elapsed = "  (" + Mathf.FloorToInt(Time.realtimeSinceStartup - startedAt) + "s)";
		if (session == null)
			return "Starting session…" + elapsed;
		if (string.IsNullOrEmpty(session.authKey))
			return "1/3  Requesting auth key (REST /user/login)…" + elapsed;
		if (!session.socketLogined)
			return "2/3  Connecting game socket and logging in…" + elapsed;
		return "3/3  Logged in — waiting for scene switch…" + elapsed;
	}

	private void EnsureStyles()
	{
		if (titleStyle != null)
			return;
		titleStyle = new GUIStyle(GUI.skin.label) { fontSize = 28, fontStyle = FontStyle.Bold, alignment = TextAnchor.MiddleCenter };
		textStyle = new GUIStyle(GUI.skin.label) { fontSize = 16, wordWrap = true };
		logStyle = new GUIStyle(GUI.skin.label) { fontSize = 12, wordWrap = true };
		logStyle.normal.textColor = new Color(0.75f, 0.75f, 0.75f);
	}

	// Plain GUI (not GUILayout): the control set changes when Login is clicked,
	// which GUILayout rejects between its Layout and Repaint passes.
	private void OnGUI()
	{
		if (!showStatusOverlay)
			return;
		EnsureStyles();
		if (SceneManager.GetActiveScene().name != bootSceneName)
		{
			// later scenes are NGUI too (same stubbed renderer) — keep a one-line
			// session indicator so a blank scene isn't mistaken for a dead client
			string state = session == null ? "no session" : (session.socketLogined ? "logged in as " + loginedUserId : "socket offline");
			GUI.Label(new Rect(8f, 8f, Screen.width - 16f, 24f), "[SessionBoot] " + state + " · scene " + SceneManager.GetActiveScene().name, textStyle);
			return;
		}
		// 1280x720 reference layout scaled to the screen
		float scale = Mathf.Max(0.5f, Mathf.Min(Screen.width / 1280f, Screen.height / 720f));
		Matrix4x4 prevMatrix = GUI.matrix;
		GUI.matrix = Matrix4x4.Scale(new Vector3(scale, scale, 1f));
		float w = Screen.width / scale;
		float h = Screen.height / scale;
		var panel = new Rect((w - 640f) * 0.5f, (h - 380f) * 0.5f, 640f, 380f);
		GUI.Box(panel, GUIContent.none);
		float x = panel.x + 24f;
		float y = panel.y + 16f;
		float cw = panel.width - 48f;
		GUI.Label(new Rect(x, y, cw, 40f), "CAOCAO", titleStyle);
		y += 48f;
		GUI.Label(new Rect(x, y, cw, 24f), "Server: " + serverUrl, textStyle);
		y += 28f;
		GUI.Label(new Rect(x, y, cw, 24f), StatusText(), textStyle);
		y += 36f;
		if (!started && GUI.Button(new Rect(x + (cw - 200f) * 0.5f, y, 200f, 44f), "Login"))
			OnClickLogin();
		y += 56f;
		GUI.Label(new Rect(x, y, cw, panel.yMax - y - 12f), statusLogText, logStyle);
		GUI.matrix = prevMatrix;
	}
}
