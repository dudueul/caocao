using UnityEngine;

public class PopupHotTimeReward : MonoBehaviour
{
	[SerializeField]
	private UILabelLocalized title;

	[SerializeField]
	private UILabelLocalized desc;

	private CustomEventHotTime hotTimeEvent;

	public void Show(CustomEventHotTime hotTimeEvent)
	{
	}

	private void UpdateUI()
	{
	}

	public void Close()
	{
	}
}
