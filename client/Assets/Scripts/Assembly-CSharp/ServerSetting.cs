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

	public static IServerSetting instance
	{
		get
		{
			return null;
		}
	}

	public string NAME
	{
		get
		{
			return null;
		}
	}

	public string GAME_SERVER_HOST_URL
	{
		get
		{
			return null;
		}
	}

	public string CDN_URL
	{
		get
		{
			return null;
		}
	}

	public bool USE_PACKET_ENCRYPT
	{
		get
		{
			return false;
		}
	}

	public string NPA_SERVICE_ID
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public string GOOGLE_PUSH_SENDER_ID
	{
		get
		{
			return null;
		}
	}

	public string S3_QA_URL
	{
		get
		{
			return null;
		}
	}

	public static void ChangeServer(IServerSetting serverSetting)
	{
	}

	public static IServerSetting GetAssetbundleBuildBassServerSetting(EnumData.CountryCode countryCode, string strBaseServer)
	{
		return null;
	}

	private static IServerSetting GetServerSetting(EnumData.CountryCode countryCode, Environment env)
	{
		return null;
	}

	public IServerSetting GetFallback()
	{
		return null;
	}
}
