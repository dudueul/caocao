using System;
using UnityEngine;

public abstract class GuildPageButtonBase : MonoBehaviour
{
	public Action OnClickCallback;

	public abstract void OnClickImpl();

	public void OnClick()
	{
	}

	protected void DeactivateGOForFullScreenPanelUI()
	{
	}

	protected void ActivateGOForFullScreenPanelUI()
	{
	}
}
