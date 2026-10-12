using LitJson;
using UnityEngine;

public class PopupBoardRating : CaocaoUIModalPopup
{
	private const string StarSpriteName = "Board_Star";

	private const string StarEmptySpriteName = "Board_StarEmpty";

	private EnumData.BoardType m_type;

	private int m_mySrl;

	private int m_dataId;

	private int m_rating;

	private int m_initRating;

	private int m_upValue;

	private int m_downValue;

	private bool m_isHide;

	[SerializeField]
	private UILabelLocalized m_rateLabel;

	[SerializeField]
	private UILabelLocalized m_upLabel;

	[SerializeField]
	private UILabelLocalized m_downLabel;

	[SerializeField]
	private UIButton[] m_starBtns;

	[SerializeField]
	private UIButton m_btnDelete;

	[SerializeField]
	private UIInput m_input;

	[SerializeField]
	private GameObject m_hideObj;

	private bool m_deleteMode;

	public void Init(EnumData.BoardType type, BoardData boardData)
	{
	}

	private void UpdateUI()
	{
	}

	private void InitDeleteBtn()
	{
	}

	public void OnBtnStar()
	{
	}

	public void OnBtnEdit()
	{
	}

	public void OnBtnDelete()
	{
	}

	public void OnBtnConfirm()
	{
	}

	private void WriteBoard(string comment, int rating)
	{
	}

	private void RequestCallbackWriteBoard(JsonData response)
	{
	}

	private bool IsValidComment()
	{
		return false;
	}

	private void ShowFirstWriteBoard()
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
