using LitJson;

public class FallbackServerSetting : IServerSetting
{
	public string NAME { get; private set; }

	public string GAME_SERVER_HOST_URL { get; private set; }

	public string CDN_URL { get; private set; }

	public bool USE_PACKET_ENCRYPT { get; private set; }

	public string NPA_SERVICE_ID { get; set; }

	public string GOOGLE_PUSH_SENDER_ID { get; private set; }

	public string S3_QA_URL { get; private set; }

	public bool Valid { get; private set; }

	public FallbackServerSetting(JsonData response)
	{
	}

	public IServerSetting GetFallback()
	{
		return null;
	}
}
