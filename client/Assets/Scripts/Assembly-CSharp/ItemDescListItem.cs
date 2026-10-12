using UnityEngine;

public class ItemDescListItem : MonoBehaviour
{
	[SerializeField]
	private UILabelLocalized m_desc;

	private int labelHeight;

	private Vector3 m_boxCenter;

	private Vector3 m_boxSize;

	public static ItemDescListItem AddListItemTo(Transform grid)
	{
		return null;
	}

	private void Init()
	{
	}

	public Vector3 UpdateUI(Item ItemData, Vector3 localPos)
	{
		return default(Vector3);
	}

	public Vector3 UpdateUI(string desc, Vector3 localPos)
	{
		return default(Vector3);
	}
}
