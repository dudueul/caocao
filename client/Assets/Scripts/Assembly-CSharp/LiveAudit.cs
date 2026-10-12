public class LiveAudit : IServerSetting
{
	private string _NPA_SERVICE_ID;

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

	public string MESSENGER_SOCKET_HANDSHAKE_PROTOCOL
	{
		get
		{
			return null;
		}
	}

	public string MESSENGER_SOCKET_DOMAIN
	{
		get
		{
			return null;
		}
	}

	public int MESSENGER_SOCKET_PORT
	{
		get
		{
			return 0;
		}
	}

	public string SQUARE_SOCKET_HANDSHAKE_PROTOCOL
	{
		get
		{
			return null;
		}
	}

	public string SQUARE_SOCKET_DOMAIN
	{
		get
		{
			return null;
		}
	}

	public int SQUARE_SOCKET_PORT
	{
		get
		{
			return 0;
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

	public IServerSetting GetFallback()
	{
		return null;
	}
}
