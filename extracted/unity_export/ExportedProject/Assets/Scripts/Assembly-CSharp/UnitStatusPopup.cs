using UnityEngine;

public class UnitStatusPopup : MonoBehaviour
{
	public UILabelLocalized m_name;

	public UILabelLocalized m_job;

	public UILabelLocalized m_level;

	public PrefabInstance m_unitStatInst;

	public PrefabInstance m_itemExpInst;

	public GameObject m_popupButton;

	private void OnDisable()
	{
	}

	public void UpdateUI()
	{
	}
}
