using UnityEngine;

public class PopupBattleFieldUnitMove : MonoBehaviour
{
	[SerializeField]
	private UILabelLocalized m_btnText;

	[SerializeField]
	private UILabelLocalized m_titleText;

	[SerializeField]
	private UILabelLocalized m_unitName;

	[SerializeField]
	private UnitDataForRenderer m_unitDataRenderer;

	public void SetUnitInfo(UnitData unitData)
	{
	}

	public void OnClickBtnClose()
	{
	}

	public void ClosePopup()
	{
	}
}
