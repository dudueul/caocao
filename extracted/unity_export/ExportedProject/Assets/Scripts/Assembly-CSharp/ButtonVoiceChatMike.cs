using UnityEngine;

public class ButtonVoiceChatMike : MonoBehaviour
{
	[SerializeField]
	private GameObject mikeOffObj;

	[SerializeField]
	private GameObject muteObj;

	[SerializeField]
	private GameObject disconnectObj;

	protected bool useMike;

	protected bool useChannel;

	protected bool mikeConfigEnabled;

	public virtual void OnClick()
	{
	}

	private void UpdateUI()
	{
	}

	private void ShowNoticeVoiceChatSetting()
	{
	}

	private void ShowNoticeVoiceChatChannel()
	{
	}

	private void SetMikeConfigEnable()
	{
	}

	protected virtual void Start()
	{
	}

	protected virtual void OnDestroy()
	{
	}
}
