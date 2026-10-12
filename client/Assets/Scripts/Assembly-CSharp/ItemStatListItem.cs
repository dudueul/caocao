using UnityEngine;

public class ItemStatListItem : MonoBehaviour
{
	public UILabelLocalized m_atk;

	public UILabelLocalized m_def;

	public UILabelLocalized m_int;

	public UILabelLocalized m_agi;

	public UILabelLocalized m_mor;

	public UILabelLocalized m_mov;

	private Vector3 m_boxCenter;

	private Vector3 m_boxSize;

	public static ItemStatListItem AddListItemTo(Transform grid)
	{
		return null;
	}

	private void Init()
	{
	}

	public Vector3 UpdateUI(UserItem selectedItem, UserItem equippedItem, Vector3 localPos)
	{
		return default(Vector3);
	}

	public Vector3 UpdateUI(Item item, Vector3 localPos)
	{
		return default(Vector3);
	}

	private void SetStatCompare(UILabelLocalized label, int stat, int statToCompare)
	{
	}

	private void SetStat(UILabelLocalized label, int stat)
	{
	}
}
