// [RE] Rebuilt ServerSetting (TypeDefIndex 10919).
// Original: per-country Live/Stage/Dev classes (LiveGLB$$get_GAME_SERVER_HOST_URL
// → "http://nxm-caocao-ww.nexon.com:9080", CDN → akamaized.net, USE_PACKET_ENCRYPT=1)
// selected by countryCode (100=JPN, 10000=GLB) — see ServerSetting$$GetServerSetting.
// Rebuild: env table points LOCAL at the Cloudflare backend (wrangler dev),
// with the original live values preserved as reference rows.
using UnityEngine;

public class ServerSetting : IServerSetting
{
	public enum Environment
	{
		LOCAL = 0,
		DEV = 1,
		DEV_AS_LIVE = 2,
		STAGE = 3,
		LIVE = 4
	}

	private static IServerSetting ms_cur;

	// [RE] rebuilt config — editable in Inspector on the singleton object
	[Header("Rebuild target (LOCAL = wrangler dev)")]
	public string localName = "LOCAL";
	public string localGameServerHostUrl = "http://localhost:8787";
	public string localCdnUrl = "http://localhost:8787/cdn";
	public bool localUsePacketEncrypt = false;   // [RE] Live had 1; we choose at deploy
	public string localNpaServiceId = "caocao-rebuild";
	public string localGooglePushSenderId = "";
	public string localS3QaUrl = "";

	[Header("Reference: original LiveGLB (evidence)")]
	public string referenceLiveUrl = "http://nxm-caocao-ww.nexon.com:9080";
	public string referenceLiveCdn = "https://nxm-caocao-ww.akamaized.net";

	private ServerSetting.Environment env = Environment.LOCAL;

	public static IServerSetting instance
	{
		get
		{
			if (ms_cur == null)
				ms_cur = GetServerSetting(EnumData.CountryCode.KOR, Environment.LOCAL);
			return ms_cur;
		}
	}

	public string NAME
	{
		get
		{
			return localName;
		}
	}

	public string GAME_SERVER_HOST_URL
	{
		get
		{
			return localGameServerHostUrl;
		}
	}

	public string CDN_URL
	{
		get
		{
			return localCdnUrl;
		}
	}

	public bool USE_PACKET_ENCRYPT
	{
		get
		{
			return localUsePacketEncrypt;
		}
	}

	public string NPA_SERVICE_ID
	{
		get
		{
			return localNpaServiceId;
		}
		set
		{
			localNpaServiceId = value;
		}
	}

	public string GOOGLE_PUSH_SENDER_ID
	{
		get
		{
			return localGooglePushSenderId;
		}
	}

	public string S3_QA_URL
	{
		get
		{
			return localS3QaUrl;
		}
	}

	public static void ChangeServer(IServerSetting serverSetting)
	{
		ms_cur = serverSetting;
	}

	public static IServerSetting GetAssetbundleBuildBassServerSetting(EnumData.CountryCode countryCode, string strBaseServer)
	{
		// [RE] original resolved CDN base per country for assetbundle builds;
		// rebuild keeps it single-CDN.
		return instance;
	}

	private static IServerSetting GetServerSetting(EnumData.CountryCode countryCode, Environment env)
	{
		// [RE] original: switch(countryCode){100: LiveJPN/StageJPN/DevJPN,
		// 10000: LiveGLB/StageGLB/DevGLB} — rebuilt as a single configurable row.
		var go = instance as ServerSetting;
		return go != null ? go : new ServerSetting();
	}

	public IServerSetting GetFallback()
	{
		// [RE] original fallback chain: LIVE → STAGE → DEV → LOCAL
		return this;
	}
}
