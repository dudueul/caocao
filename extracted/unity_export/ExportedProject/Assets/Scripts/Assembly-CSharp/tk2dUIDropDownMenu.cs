using System;
using System.Collections.Generic;
using UnityEngine;

public class tk2dUIDropDownMenu : MonoBehaviour
{
	public tk2dUIItem dropDownButton;

	public tk2dTextMesh selectedTextMesh;

	[HideInInspector]
	public float height;

	public tk2dUIDropDownItem dropDownItemTemplate;

	[SerializeField]
	private string[] startingItemList;

	[SerializeField]
	private int startingIndex;

	private List<string> itemList;

	private Action OnSelectedItemChange__BackingField;

	public string SendMessageOnSelectedItemChangeMethodName;

	private int index;

	private List<tk2dUIDropDownItem> dropDownItems;

	private bool isExpanded;

	[SerializeField]
	[HideInInspector]
	private tk2dUILayout menuLayoutItem;

	[SerializeField]
	[HideInInspector]
	private tk2dUILayout templateLayoutItem;

	public List<string> ItemList
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public int Index
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public string SelectedItem
	{
		get
		{
			return null;
		}
	}

	public GameObject SendMessageTarget
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public tk2dUILayout MenuLayoutItem
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public tk2dUILayout TemplateLayoutItem
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public event Action OnSelectedItemChange
	{
		add
		{
		}
		remove
		{
		}
	}

	private void Awake()
	{
	}

	private void OnEnable()
	{
	}

	private void OnDisable()
	{
	}

	public void UpdateList()
	{
	}

	public void SetSelectedItem()
	{
	}

	private tk2dUIDropDownItem CreateAnotherDropDownItem()
	{
		return null;
	}

	private void ItemSelected(tk2dUIDropDownItem item)
	{
	}

	private void ExpandButtonPressed()
	{
	}

	private void ExpandList()
	{
	}

	private void CollapseList()
	{
	}

	private void DropDownItemHoverBtnToggle(tk2dUIUpDownHoverButton upDownHoverButton)
	{
	}

	private void OnDestroy()
	{
	}
}
