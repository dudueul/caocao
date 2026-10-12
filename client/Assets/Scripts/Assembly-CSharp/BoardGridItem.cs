using LitJson;
using UnityEngine;

public class BoardGridItem : UIReuseScrollViewCell
{
	public class CellData : IReuseCellData
	{
		public int Index { get; set; }

		public int width { get; set; }

		public int height { get; set; }

		public PostData postData { get; set; }

		public EnumData.BoardType type { get; set; }
	}

	private bool m_helpful;

	private CellData m_cellData;

	private bool m_voted;

	[SerializeField]
	private UILabelLocalized m_tagLabel;

	[SerializeField]
	private UILabelLocalized m_nameLabel;

	[SerializeField]
	private UILabelLocalized m_rankLabel;

	[SerializeField]
	private UILabelLocalized m_timeLabel;

	[SerializeField]
	private UILabelLocalized m_contentLabel;

	[SerializeField]
	private UILabelLocalized m_upBtnLabel;

	[SerializeField]
	private UILabelLocalized m_downBtnLabel;

	[SerializeField]
	private UIButton[] m_btnObjs;

	[SerializeField]
	private GameObject m_tagObj;

	[SerializeField]
	private GameObject m_contentHideObj;

	public override void UpdateData(IReuseCellData cellData)
	{
	}

	private void UpdateContentUI()
	{
	}

	public void OnBtnHelpful()
	{
	}

	public void OnBtnShowContent()
	{
	}

	private void RequestCallbackWriteHelpful(JsonData response)
	{
	}

	public void EnableBtn(bool enable)
	{
	}

	private void AddDataValue()
	{
	}

	private void ShowNotice(string msg)
	{
	}

	private bool ContentHide()
	{
		return false;
	}
}
