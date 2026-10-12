using LitJson;
using UnityEngine;

public class StorybookSelectPopup : PanelSelfRegister2UIComponentsMonobehaviour
{
	public enum SeriesTabType
	{
		All = 0,
		Complete = 1,
		Serializing = 2
	}

	private Storybook pdStorybook;

	[SerializeField]
	private UIReuseGrid grid;

	[SerializeField]
	private UIReuseScrollView scrollView;

	private bool m_adjustScrollView;

	private int m_focusedStorybookId;

	private int m_forceFocusId;

	private SeriesTabType seriesTabType;

	[SerializeField]
	private GameObject m_storyTabObj;

	[SerializeField]
	private GameObject m_storyTabTypeObj;

	[SerializeField]
	private UISprite m_storyTabTypeBG;

	[SerializeField]
	private UILabelLocalized m_storyTabTypeLabel;

	[SerializeField]
	private UIButton m_storyTabGridItemAll;

	[SerializeField]
	private UIButton m_storyTabGridItemComplete;

	[SerializeField]
	private UIButton m_storyTabGridItemSeries;

	private GameObject m_tierBtn;

	public void UpdateUI()
	{
	}

	public void OnStorybookListSelected(int focusedStorybookId)
	{
	}

	public void OnStorybookListDoubleClicked(int storybookId)
	{
	}

	private void OnStorybookEnter(int storybookId)
	{
	}

	public void Close()
	{
	}

	protected override void Awake()
	{
	}

	private void OnEnable()
	{
	}

	private void OnDisable()
	{
	}

	private void Init()
	{
	}

	private void OnGetStorybookData(JsonData response)
	{
	}

	public void OnUpdate(JsonData response)
	{
	}

	public void ShowStoryTypeList()
	{
	}

	public void AddSeriesTabGridEvent()
	{
	}

	public void SelectStoryType(SeriesTabType tabType)
	{
	}

	private void UpdateStorySeriesSortLabel()
	{
	}

	private string GetStringStorySeriesType(SeriesTabType tabType)
	{
		return null;
	}

	public void SetFocusSpecificStorybook(int storybookId)
	{
	}

	public static void Show()
	{
	}

	public override void ShowPanel()
	{
	}

	public override void ClosePanel()
	{
	}
}
