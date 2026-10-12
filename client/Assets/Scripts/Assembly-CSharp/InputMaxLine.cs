using UnityEngine;

public class InputMaxLine : MonoBehaviour
{
	[SerializeField]
	private int MAX_LINE;

	[SerializeField]
	private UILabelLocalized m_label;

	[SerializeField]
	private UILabel m_uiLabel;

	[SerializeField]
	private UIInput m_input;

	private void Start()
	{
	}

	public void OnTextChanged()
	{
	}
}
