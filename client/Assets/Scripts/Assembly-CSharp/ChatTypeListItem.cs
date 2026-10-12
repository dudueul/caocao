using UnityEngine;

public class ChatTypeListItem : MonoBehaviour
{
	private enum Type
	{
		Channel = 0,
		All = 1,
		Whisper = 2,
		Guild = 3
	}

	[SerializeField]
	private Type m_type;

	[SerializeField]
	private UILabelLocalized m_label;

	[SerializeField]
	private UIButton m_btn;

	private void OnEnable()
	{
	}
}
