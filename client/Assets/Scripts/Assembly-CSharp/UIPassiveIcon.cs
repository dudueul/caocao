using System;
using System.Collections.Generic;
using UnityEngine;

public class UIPassiveIcon : MonoBehaviour
{
	public UISprite sprite;

	public UILabelLocalized label;

	public GameObject selectGo;

	private Action<UIPassiveIcon> m_onSelect;

	public List<SpActionBase> passives { get; set; }

	public Condition condition { get; set; }

	public int DescValue { get; set; }

	public bool isPassiveIcon
	{
		get
		{
			return false;
		}
	}

	public void Init(List<SpActionBase> passives, Action<UIPassiveIcon> onSelect)
	{
	}

	public void Init(Condition condition, UnitData triggerUnit, Action<UIPassiveIcon> onSelect, int descValue)
	{
	}

	private void OnClick()
	{
	}

	public void Deselect()
	{
	}

	public void Select()
	{
	}
}
