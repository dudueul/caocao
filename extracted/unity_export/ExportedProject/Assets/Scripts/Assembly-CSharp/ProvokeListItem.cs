using UnityEngine;

public class ProvokeListItem : MonoBehaviour
{
	public AutoText autoText;

	private bool m_isSelected;

	public string text
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized m_text
	{
		get
		{
			return null;
		}
	}

	private GameObject m_selected
	{
		get
		{
			return null;
		}
	}

	public bool isSelected
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public static void InstantiateOn(Transform uiGrid, AutoText autoText)
	{
	}

	public void OnClick()
	{
	}
}
