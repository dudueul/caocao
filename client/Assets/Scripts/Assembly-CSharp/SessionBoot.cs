// [RE] Rebuild-only boot glue — the counterpart of the original
// CaoCaoUnityActivity.onCreate (Adjust deep-link → NPAccount.onActivityCreated)
// and the NPA account login that produced (userId, authKey).
// Rebuild: device-id guest auth → PImpl.CreateLocalSession → the session's
// own state machine (UpdateWebAuthKey → socket connect → login → enterToWorld).
// Attach once in the Login scene (rebuild scene edit) or instantiate here.
using System;
using UnityEngine;

public class SessionBoot : MonoBehaviour
{
	// [RE] deterministic ulong from the device id — stand-in for the Nexon
	// account id; replace with a real auth provider in the auth milestone.
	public bool autoStart = true;

	private bool started;

	public ulong GetGuestUserId()
	{
		var raw = SystemInfo.deviceUniqueIdentifier ?? "caocao-dev";
		using (var sha = new System.Security.Cryptography.SHA256Managed())
		{
			var hash = sha.ComputeHash(System.Text.Encoding.UTF8.GetBytes(raw));
			return BitConverter.ToUInt64(hash, 0) & 0x7fffffffffffffffUL;
		}
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
		Debug.Log("[SessionBoot] socket logined — enter world");
		// [RE] Login → Town transition happens in the flow milestone; for now
		// we enter the default world room directly.
	}

	private void Start()
	{
		if (autoStart)
			StartSession();
	}
}
