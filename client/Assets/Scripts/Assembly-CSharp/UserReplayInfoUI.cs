using System;
using System.Collections.Generic;
using LitJson;
using Multiplay;
using UnityEngine;

public class UserReplayInfoUI : PopupUserInfo.InnerUI
{
	private class DetailInfo
	{
		public string nationName;

		public int faceUnit;

		public int skinItemId;
	}

	private Transform transform;

	private JsonData replayData;

	private JsonData detailData;

	private Dictionary<ulong, MultiplayUserData> detailDatas;

	private Dictionary<string, UserReplayListItem.CellData> cellDatas;

	private List<UserReplayListItem.CellData> tempCellDatas;

	private ulong firstSrl;

	private bool updatedLast;

	private Comparison<UserReplayListItem.CellData> cellDataSortFunc;

	private UIReuseGrid m_grid;

	public ulong uid { get; set; }

	private UIReuseGrid grid
	{
		get
		{
			return null;
		}
	}

	public void Init(Transform trs, ulong uid)
	{
	}

	public void SetActive(bool active)
	{
	}

	private void RequestReplay()
	{
	}

	private void InitMultiplayUserData(JsonData multiplayUserData)
	{
	}

	public void UpdateUI(JsonData response)
	{
	}

	private void OnNextLoading()
	{
	}

	public void Destroy()
	{
	}
}
