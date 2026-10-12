using System.Collections.Generic;
using LitJson;
using UnityEngine;

public class PanelBoard : CaocaoUIModalPopup
{
	public enum BoardInfoType
	{
		Statistics = 0,
		Board = 1
	}

	private enum SortType
	{
		Recommend = 0,
		Newest = 1,
		MyPost = 2
	}

	private EnumData.CountryCode m_serviceZone;

	private EnumData.BoardType m_type;

	private BoardInfoType m_boardInfoType;

	private SortType m_sortType;

	private Unit m_unitData;

	private Item m_treasureData;

	private int m_pageIdx;

	private BoardData m_boardData;

	private Dictionary<ulong, PostData> m_postDatas;

	[SerializeField]
	private UIReuseScrollView m_reuseScrollView;

	[SerializeField]
	private UIReuseGrid m_reuseGrid;

	[SerializeField]
	private UILabelLocalized m_title;

	[SerializeField]
	private UILabelLocalized m_userRating;

	[SerializeField]
	private UILabelLocalized m_btnRatingLabel;

	[SerializeField]
	private UISlider m_ratingSlider;

	[SerializeField]
	private BoardInfo m_boardInfo;

	[SerializeField]
	private UILabelLocalized m_btnLanguage;

	[SerializeField]
	private GameObject m_btnPrevious;

	[SerializeField]
	private GameObject m_btnNext;

	[SerializeField]
	private GameObject m_btnSortRecommend;

	[SerializeField]
	private GameObject m_btnSortLatest;

	[SerializeField]
	private UILabelLocalized m_maxJobLvCount;

	[SerializeField]
	private Transform m_treasure;

	[SerializeField]
	private Transform m_passive;

	[SerializeField]
	private Transform m_runeSet;

	[SerializeField]
	private Transform m_train;

	[SerializeField]
	private Transform m_tile;

	[SerializeField]
	private GameObject m_btnStatistics;

	[SerializeField]
	private GameObject m_btnBoard;

	[SerializeField]
	private GameObject m_board;

	[SerializeField]
	private GameObject m_statistics;

	[SerializeField]
	private UISprite[] m_sortBtnSprite;

	[SerializeField]
	private UISprite[] m_infoTypeBtnSprite;

	private bool responsedBoard;

	private bool responsedStatistics;

	private bool isBtnLanguageActive
	{
		get
		{
			return false;
		}
	}

	private void Start()
	{
	}

	private void Init(EnumData.BoardType type, BoardInfoType boardInfoType, int dataId, int dataId2 = 0)
	{
	}

	private void InitInfoTypeUI()
	{
	}

	private void InitUnitBoard(int unitId)
	{
	}

	private void InitTreasureBoard(int treasureId)
	{
	}

	private void UpdateBoardInfoUI()
	{
	}

	private void UpdateUI(List<PostData> addPosts)
	{
	}

	private void UpdateLanguageUI()
	{
	}

	private void UpdateRatingUI()
	{
	}

	private void NextPageLoading()
	{
	}

	public void OnBtnNext()
	{
	}

	public void OnBtnPrevious()
	{
	}

	public void OnBtnRating()
	{
	}

	public void OnBtnSort()
	{
	}

	private void UpdateSortBtnSprites()
	{
	}

	public void OnBtnInfoType()
	{
	}

	private void UpdateInfoTypeBtnSprites()
	{
	}

	public static void Show(EnumData.BoardType type, BoardInfoType boardInfoType, int dataId, int dataId2 = 0)
	{
	}

	public static void Refresh()
	{
	}

	private void GetBoardList()
	{
	}

	private void RequestCallbackBoardList(JsonData response)
	{
	}

	private void GetStatistics()
	{
	}

	private void RequestCallbackStatistics(JsonData response)
	{
	}

	private int GetDataId()
	{
		return 0;
	}

	private void ResetGridItem()
	{
	}

	private string GetBoardRating()
	{
		return null;
	}

	private void RefreshUI()
	{
	}

	protected override void ShowPanelImpl()
	{
	}

	protected override void ClosePanelImpl()
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
}
