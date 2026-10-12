using LitJson;

public class HeartBeats : Singleton<HeartBeats>
{
	private readonly int networkInterval;

	private readonly int aliveInterval;

	private float lastTimeUserInteraction;

	private string m_serverNoticeMsg;

	private bool entrustTurn;

	private bool entrustBattle;

	public void Start()
	{
	}

	private void Update()
	{
	}

	private void CheckUserInteractionTimeout()
	{
	}

	private void SendHeartBeats()
	{
	}

	private void OnHeartBeats(JsonData response)
	{
	}

	private void OnApplicationPause(bool pause)
	{
	}

	private void OnUseOffer(JsonData response)
	{
	}
}
