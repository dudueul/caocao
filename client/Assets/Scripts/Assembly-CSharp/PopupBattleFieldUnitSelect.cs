using UnityEngine;

public class PopupBattleFieldUnitSelect : MonoBehaviour
{
	[SerializeField]
	private Transform m_transBtnConfirm;

	[SerializeField]
	private Transform m_transBtnCancel;

	[SerializeField]
	private Transform m_transBtnMove;

	[SerializeField]
	private Transform m_transBtnPullOut;

	[SerializeField]
	private ClickHandler m_bgClickHandler;

	private UnitData m_arrangedUnit;

	private Vector3 m_initArrangePos;

	private int m_initArrangeBFIndex;

	private int m_initArrangeBFGroup;

	private ReconSlot m_controlReconSlot;

	public void Show(UserUnit userUnit, ReconSlot reconSlot, Vector2 pos)
	{
	}

	public void ShowFieldSelect(UserUnit userUnit, Vector2 pos)
	{
	}

	public void ClosePopup()
	{
	}

	public void OnClickBtn()
	{
	}

	private void OnBgClick()
	{
	}

	private void AdjustBtnPosition(bool fieldSelect, UnitData unitData)
	{
	}

	private void OnEnable()
	{
	}

	private void OnDisable()
	{
	}

	private void OnDestroy()
	{
	}
}
