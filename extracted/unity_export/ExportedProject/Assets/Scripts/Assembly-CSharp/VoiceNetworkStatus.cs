public class VoiceNetworkStatus
{
	private enum NetStatus
	{
		Disconnected = 0,
		Connecting = 1,
		Connected = 2
	}

	private NetStatus netStatus;

	public string Status
	{
		get
		{
			return null;
		}
	}

	public void Connected()
	{
	}

	public void Disconnected()
	{
	}

	public void Connecting()
	{
	}
}
