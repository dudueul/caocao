using UnityEngine;

public abstract class PanelPopupBase : CaocaoUIModalPopup
{
	public enum PopupState
	{
		KeepOpen = 0,
		Close = 1
	}

	protected void AttachClickDelegate(GameObject buttonObject)
	{
	}

	protected void DetachClickDelegate(GameObject buttonObject)
	{
	}

	protected void AttachInputDelegate(GameObject inputObject)
	{
	}

	protected void DetachInputDelegate(GameObject inputObject)
	{
	}
}
