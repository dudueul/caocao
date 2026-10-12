using System;
using System.Text;
using UnityEngine;

public class StorybookSelectListItem : UIReuseScrollViewCell
{
	public class StorybookSelectData : IReuseCellData
	{
		private int m_storybookId;

		public Action<int> OnItemClicked;

		public Action<int> OnItemDoubleClicked;

		public int Index { get; set; }

		public int storybookId
		{
			get
			{
				return 0;
			}
			set
			{
			}
		}

		public bool focused { get; set; }
	}

	private StorybookSelectData m_storybookSelectData;

	[SerializeField]
	private UISprite titleImage;

	[SerializeField]
	private Transform lockedObj;

	[SerializeField]
	private UILabelLocalized clearCond;

	[SerializeField]
	private UILabelLocalized needUnitCond;

	[SerializeField]
	private UIButton needUnitBtn;

	[SerializeField]
	private UILabelLocalized recentPlayed;

	[SerializeField]
	private UILabelLocalized storybookTitle;

	[SerializeField]
	private UILabelLocalized achievementRate;

	[SerializeField]
	private Transform selected;

	[SerializeField]
	private Transform purchaseBtn;

	[SerializeField]
	private UIButton btnEnter;

	[SerializeField]
	private UISprite completeModeOne;

	[SerializeField]
	private UISprite completeModeTwo;

	[SerializeField]
	private UISprite completeModeThree;

	[SerializeField]
	private Transform newObj;

	[SerializeField]
	private UIButton btnLanguage;

	[SerializeField]
	private UILabelLocalized dayOfWeek;

	private readonly Color playableColor;

	private static StringBuilder stringBuilder;

	private float lastClickTime;

	public int storybookId
	{
		get
		{
			return 0;
		}
	}

	public override void UpdateData(IReuseCellData cellData)
	{
	}

	public void UpdateUI()
	{
	}

	private void OnClick()
	{
	}

	public void OnSelected()
	{
	}

	public void OnDeselected()
	{
	}
}
