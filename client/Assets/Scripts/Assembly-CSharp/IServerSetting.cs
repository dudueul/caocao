public interface IServerSetting
{
	string NAME { get; }

	string GAME_SERVER_HOST_URL { get; }

	string CDN_URL { get; }

	bool USE_PACKET_ENCRYPT { get; }

	string NPA_SERVICE_ID { get; set; }

	string GOOGLE_PUSH_SENDER_ID { get; }

	string S3_QA_URL { get; }

	IServerSetting GetFallback();
}
