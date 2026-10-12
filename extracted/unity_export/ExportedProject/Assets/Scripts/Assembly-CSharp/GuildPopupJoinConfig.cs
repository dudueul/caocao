using UnityEngine;

public class GuildPopupJoinConfig : CaocaoUIModalPopup
{
	public Transform tabButtonRoot;

	public UILabelLocalized labelThumbRankLimit;

	public UILabelLocalized labelMinRank;

	public UISlider sliderRankLimit;

	public PressableButton subPressableBtn;

	public PressableButton addPressableBtn;

	public float editTweenPosition;

	public UIInput inputRecruitMsg;

	public float recruitMsgTweenDuration;

	public GameObject editButton;

	public GameObject completeButton;

	public GameObject objLockOther;

	protected EnumData.GuildAcceptType acceptType;

	protected int rankLimitValue;

	protected string recruitMsg;

	protected bool editingRecruitMsg;

	protected bool willSubmit;

	protected float origPos;

	protected bool tweening;

	private const float SLIDER_SOUND_GAP = 0.05f;

	private int prevRankLimit;

	private float lastSliderSoundTime;

	private bool byButtonSlide;

	protected override void Awake()
	{
	}

	protected virtual void Start()
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

	public void OnAcceptTypeChanged()
	{
	}

	public void OnRankLimitValueChanged()
	{
	}

	public void OnRankLimitSubBtnClicked()
	{
	}

	public void OnRankLimitSubBtnClickedImpl(int count)
	{
	}

	public void OnRankLimitAddBtnClicked()
	{
	}

	public void OnRankLimitAddBtnClickedImpl(int count)
	{
	}

	private void ChangedRankLimit(int changedValue)
	{
	}

	public virtual void OnExecuteClicked()
	{
	}

	public void OnEditMsgClicked()
	{
	}

	private void Tween(float pos)
	{
	}

	public void OnRecruitMsgSubmit()
	{
	}

	private void OnSelectInput(bool selected)
	{
	}

	private void OnSelectScroll(bool selected)
	{
	}

	private void Update()
	{
	}

	public static void Show(EnumData.GuildAcceptType acceptType, int rankLimit, string recruitMsg)
	{
	}

	protected virtual void OnUpdateMyGuild()
	{
	}

	protected virtual void OnGuildError(string error, ref bool handled)
	{
	}

	protected override void OnDestroy()
	{
	}
}
