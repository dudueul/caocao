using System;
using System.Collections.Generic;
using UnityEngine;

public class GuildPopupSiegeWarDefenceFacilitySelect : MonoBehaviour
{
	[Serializable]
	public class Item
	{
		public GameObject root;

		public UITexture texImage;

		public UILabelLocalized labelName;

		public GameObject exMark;

		public GameObject objSelect;

		private Action<int> onExMark;

		private Action<int> onClick;

		public int idx { get; protected set; }

		public void Initiate(int idx, Action<int> onExMark, Action<int> onClick)
		{
		}

		public void Selected(int selectedIdx)
		{
		}

		public void UpdateUI(Unit unit)
		{
		}
	}

	public Item[] items;

	private bool initiated;

	private int subTypeIdx;

	private Dictionary<int, GuildSiegeObjectByLV> dicDatas;

	private Action<int, int> callback;

	public void Show(int unitId, EnumData.GuildSiegeObjectType objType, Action<int, int> callback)
	{
	}

	private void UpdateUI()
	{
	}

	public void OnExMark(int idx)
	{
	}

	public void OnSelectItem(int idx)
	{
	}

	public void OnClose()
	{
	}

	public void OnConfirm()
	{
	}

	private void OnDestroy()
	{
	}
}
