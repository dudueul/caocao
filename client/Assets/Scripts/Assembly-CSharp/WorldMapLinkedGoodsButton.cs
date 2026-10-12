using System;
using System.Collections.Generic;
using UnityEngine;

public class WorldMapLinkedGoodsButton : MonoBehaviour
{
	public UILabelLocalized timeLabel;

	private List<LinkedGoods> linkedGoodsPdList;

	private int linkedGoodsIndex;

	private DateTime currentStartDate;

	private DateTime currentEndDate;

	private LinkedGoods currentLinkedGoodsPd
	{
		get
		{
			return null;
		}
	}

	public void Start()
	{
	}

	private void OnDestory()
	{
	}

	private void GatherData()
	{
	}

	private void RefineData()
	{
	}

	private void OnTimer()
	{
	}

	private void UpdateLabel()
	{
	}

	public void OnClick()
	{
	}
}
