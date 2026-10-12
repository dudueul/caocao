using UnityEngine;

public class ItemJobTypeListItem : MonoBehaviour
{
	public UILabelLocalized m_jobs;

	private int labelHeight;

	private Vector3 m_boxCenter;

	private Vector3 m_boxSize;

	public static ItemJobTypeListItem AddListItemTo(Transform grid)
	{
		return null;
	}

	private void Init()
	{
	}

	public Vector3 UpdateUI(Item itemData, int currJobType, Vector3 localPos)
	{
		return default(Vector3);
	}
}
