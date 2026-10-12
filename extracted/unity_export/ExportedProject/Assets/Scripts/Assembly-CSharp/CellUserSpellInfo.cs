using System;
using UnityEngine;

public class CellUserSpellInfo : MonoBehaviour
{
	public Action panelCloseCallBack;

	[SerializeField]
	private UILabelLocalized labelCost;

	[SerializeField]
	private UILabelLocalized labelName;

	[SerializeField]
	private UILabelLocalized labelDesc;

	[SerializeField]
	private UIButton btnUse;

	private int pdId;

	public void Show(UserSpell pdUserSpell)
	{
	}

	public void OnBtnClickUse()
	{
	}

	private void CallBackFunc()
	{
	}
}
