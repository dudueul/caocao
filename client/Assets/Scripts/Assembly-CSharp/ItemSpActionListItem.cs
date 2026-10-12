using UnityEngine;

public class ItemSpActionListItem : MonoBehaviour
{
	public UISprite m_spActionIcon;

	public UILabelLocalized m_spActionName;

	public UILabelLocalized m_spActionType;

	public UILabelLocalized m_spActionDesc;

	private int labelHeight;

	private Vector3 m_boxCenter;

	private Vector3 m_boxSize;

	public static ItemSpActionListItem AddListItemTo(Transform grid, bool multiSpAction)
	{
		return null;
	}

	private void Init()
	{
	}

	public Vector3 UpdateUI(Item itemData, Vector3 localPos, int passiveIndex)
	{
		return default(Vector3);
	}
}
