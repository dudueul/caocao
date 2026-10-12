using UnityEngine;

public class UnitStatListItem : MonoBehaviour
{
	private UILabelLocalized m_str;

	private UILabelLocalized m_dex;

	private UILabelLocalized m_wis;

	private UILabelLocalized m_luk;

	private UILabelLocalized m_cha;

	private Vector3 m_boxCenter;

	private Vector3 m_boxSize;

	public static UnitStatListItem AddListItemTo(Transform grid)
	{
		return null;
	}

	private void Init()
	{
	}

	public Vector3 UpdateUI(Unit unit, Vector3 localPos)
	{
		return default(Vector3);
	}

	private void SetStat(UILabelLocalized label, int stat)
	{
	}
}
