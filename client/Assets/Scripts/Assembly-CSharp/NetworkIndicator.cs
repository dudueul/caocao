using UnityEngine;

public class NetworkIndicator : Singleton<NetworkIndicator>
{
	private GameObject indicator;

	public bool activated
	{
		get
		{
			return false;
		}
	}

	protected NetworkIndicator()
	{
	}

	public void On()
	{
	}

	public void Off()
	{
	}
}
