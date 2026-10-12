using System;
using System.Collections.Generic;
using UnityEngine;

public class PopupEmoticon : CaocaoUIModalPopup
{
	private enum TabType
	{
		Used = 0,
		All = 1,
		Buy = 2
	}

	private string EMOTICON_USED_IDS_KEY;

	private string EMOTICON_SELECTED_TAB_INDEX_KEY;

	private string EMOTICON_ALL_SELECTED_STICKERSET_ID_KEY;

	private string EMOTICON_BUY_SELECTED_STICKERSET_ID_KEY;

	private const int PER_PAGE_MAX_SET_COUNT = 4;

	private const int EMOTICON_SAVE_MAX_COUNT = 8;

	[SerializeField]
	private UIGrid stickerGrid;

	[SerializeField]
	private UILabelLocalized pageLabel;

	[SerializeField]
	private UIGrid stickerSetGrid;

	[SerializeField]
	private CaocaoTabButtons tabBtns;

	[SerializeField]
	private List<StickerListItem> stickerItems;

	private List<StickerSetListItem> stickerSetItems;

	private List<int> usedStickerIds;

	private int preViewSelectedStickerSetId;

	private int allMaxPage;

	private int buyMaxPage;

	private int currentPage;

	public Action<int> onEmoticonClickFunc;

	public Action<int> onEmoticonClickToClosePopupFunc;

	public Action onPopupCloseFunc;

	private bool isInit;

	private bool preView;

	private TabType tabType;

	private int selectedStickerSetId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	private int maxPage
	{
		get
		{
			return 0;
		}
	}

	public static PopupEmoticon Show(int stickerSetId = -1, bool preView = false, bool hasBg = false)
	{
		return null;
	}

	public static void Show(Vector3 localPosition, int stickerSetId = -1, bool preView = false, bool hasBg = false)
	{
	}

	public void Show(IDepthManagerblePopup _parent)
	{
	}

	protected override void Awake()
	{
	}

	private void InitUi()
	{
	}

	private void InitStickerSetUI()
	{
	}

	private void UpdateUI()
	{
	}

	private void UpdatePageLabelUI()
	{
	}

	private void UpdateStickerSetUi()
	{
	}

	private void UpdateStickerUi()
	{
	}

	private void OnStickerSetClick(int stickerSetId)
	{
	}

	private void OnStickerClick(int stickerId)
	{
	}

	public void OnNextPage()
	{
	}

	public void OnPrevPage()
	{
	}

	private void OnTabChanged(CaocaoButton tabBtn)
	{
	}

	private int GetBuyStickerSetCount()
	{
		return 0;
	}

	private int GetStickerSetCount()
	{
		return 0;
	}

	private int GetStickerSetIndex(int stickerSetId)
	{
		return 0;
	}

	private void InitUsedStickerId()
	{
	}

	private void SaveUsedStickerId()
	{
	}

	private void PushUsedStickerId(int stickerId)
	{
	}

	private string CombinePlayerprefsKeyAndUID(string key)
	{
		return null;
	}

	protected override void OnDestroy()
	{
	}

	public override void OnFocusOn()
	{
	}

	public override void OnFocusOut()
	{
	}

	public override void OnTappingOutSide()
	{
	}

	protected override void ShowPanelImpl()
	{
	}

	protected override void ClosePanelImpl()
	{
	}
}
