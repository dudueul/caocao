using System;
using UnityEngine;

public class ItemTierGridItem : MonoBehaviour
{
	public Action<int> OnClickHandler;

	private int m_tier;

	private Tuple<int, int, string> m_minMaxDesc;

	private string m_privateItemDesc;

	public int tier
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public Tuple<int, int, string> minMaxDesc
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public string privateItemDesc
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public void UpdateUI()
	{
	}

	public void OnClick()
	{
	}
}
