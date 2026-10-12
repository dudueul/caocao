using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using CustomEvent;
using LitJson;
using UnityEngine;

public class BingoEvent : MonoBehaviour
{
	private enum OpenType
	{
		random = 0,
		complete = 1
	}

	[SerializeField]
	private WorldEventRule ruleUI;

	[SerializeField]
	private Transform dateLabel;

	[SerializeField]
	private UIGrid boardGrid;

	[SerializeField]
	private UIGrid specialGrid;

	[SerializeField]
	private UILabelLocalized lines;

	[SerializeField]
	private UILabelLocalized points;

	[SerializeField]
	private ItemUI materialItem;

	[SerializeField]
	private UIButton btnRandomOpen;

	[SerializeField]
	private UILabelLocalized randomLabel;

	[SerializeField]
	private UILabelLocalized randomCenterLabel;

	[SerializeField]
	private UILabelLocalized randomCost;

	[SerializeField]
	private UIButton btnCompleteOpen;

	[SerializeField]
	private UILabelLocalized completeLabel;

	[SerializeField]
	private UILabelLocalized completeCenterLabel;

	[SerializeField]
	private UILabelLocalized completeCost;

	[SerializeField]
	private Transform basicReward;

	[SerializeField]
	private Transform openReward;

	[SerializeField]
	private UIGrid completeRewardGrid;

	[SerializeField]
	private UIScrollView completeRewardScrollView;

	[SerializeField]
	private PlaySoundBridge getSE;

	[SerializeField]
	private PlaySoundBridge bingoSE;

	[SerializeField]
	private PlaySoundBridge specialBingoSE;

	private UIScrollView m_scrollView;

	private Dictionary<int, int> m_rowMaskDic;

	private Dictionary<int, int> m_colMaskDic;

	private int m_dia1Mask;

	private int m_dia2Mask;

	private int m_specialMask;

	public PanelCustomEvents parent { get; private set; }

	public ulong eventIngameId { get; private set; }

	public ulong openEventUnitId { get; private set; }

	public ulong completeEventUnitId { get; private set; }

	public BingoEventUser bingoUser
	{
		get
		{
			return null;
		}
	}

	public int bingoPoints
	{
		get
		{
			return 0;
		}
	}

	public int bingoLines
	{
		get
		{
			return 0;
		}
	}

	public int bingoBoard
	{
		get
		{
			return 0;
		}
	}

	public UIScrollView scrollView
	{
		get
		{
			return null;
		}
	}

	private void OnDisable()
	{
	}

	private void OnDestroy()
	{
	}

	public void Show(PanelCustomEvents parent, CustomEventIngame eventIngame)
	{
	}

	private void MakeBitMask()
	{
	}

	private BingoGridState GetGridItemState(int bingoBoard, int index)
	{
		return BingoGridState.None;
	}

	private bool isOpen(int bingoBoard, int index)
	{
		return false;
	}

	private bool isBingo(int bingoBoard, int index)
	{
		return false;
	}

	private void UpdateControl()
	{
	}

	private void UpdateButtonState()
	{
	}

	private void UpdateDate(DateTime startDate, DateTime endDate)
	{
	}

	private void RequestOpenBingo(OpenType openType)
	{
	}

	private void OnOpenBingo(JsonData response)
	{
	}

	[DebuggerHidden]
	private IEnumerator UpdateBoard(int befBoard, int openIdx, List<RewardInfo> rewardList)
	{
		return null;
	}

	[DebuggerHidden]
	private IEnumerator UpdateSpecialBoard(int openIdx, List<RewardInfo> rewardList, List<RewardInfo> specialRewardList)
	{
		return null;
	}

	[DebuggerHidden]
	private IEnumerator ShowOpenEffect(int openIdx)
	{
		return null;
	}

	[DebuggerHidden]
	private IEnumerator ShowOpenSpecialEffect(int openIdx)
	{
		return null;
	}

	[DebuggerHidden]
	private IEnumerator ShowBingoEffect(int befBoard, int openIdx)
	{
		return null;
	}

	[DebuggerHidden]
	private IEnumerator ShowBingoSpecialEffect(int openIdx, List<RewardInfo> rewardList)
	{
		return null;
	}

	[DebuggerHidden]
	private IEnumerator _showBingoEffect(int befBoard, int bitMask)
	{
		return null;
	}

	private void CreateBingoBoard(int[] orderList, int[] specialList)
	{
	}

	private void CreateBasicReward()
	{
	}

	private void CreateCompleteRewards()
	{
	}

	public void UpdateRecordValue()
	{
	}
}
