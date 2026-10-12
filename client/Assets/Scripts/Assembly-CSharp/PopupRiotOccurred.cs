using System;
using System.Collections.Generic;
using UnityEngine;

public class PopupRiotOccurred : SelfRegister2UIComponentsMonobehaviour
{
	[SerializeField]
	private UILabelLocalized m_occurredDesc;

	[SerializeField]
	private UILabelLocalized m_townsName;

	private Action m_callback;

	protected override void Awake()
	{
	}

	public void Init(List<int> townsList, Action callback)
	{
	}

	private void OnClick()
	{
	}

	private void ClosePopup()
	{
	}

	public static void Show(List<int> townsList, Action callback = null)
	{
	}
}
