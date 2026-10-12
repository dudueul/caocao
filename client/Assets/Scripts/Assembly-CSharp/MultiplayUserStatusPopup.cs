using UnityEngine;

public class MultiplayUserStatusPopup : Singleton<MultiplayUserStatusPopup>
{
	private GameObject popup;

	private UILabelLocalized descLabel;

	public bool activated
	{
		get
		{
			return false;
		}
	}

	protected MultiplayUserStatusPopup()
	{
	}

	public void On(string desc)
	{
	}

	public void Off()
	{
	}

	protected override void OnDestroy()
	{
	}
}
