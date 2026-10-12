using UnityEngine;

public class BattleFieldDefenseDeckItem : MonoBehaviour
{
	[SerializeField]
	private UnitUI m_unitUI;

	[SerializeField]
	private UILabelLocalized m_unitName;

	[SerializeField]
	private UILabelLocalized m_jobTypeName;

	public void SetUnitInfo(UserUnit userUnit)
	{
	}
}
