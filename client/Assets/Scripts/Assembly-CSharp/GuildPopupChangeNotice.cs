using UnityEngine;

public class GuildPopupChangeNotice : CaocaoUIModalPopup
{
	public UIInput inputNotice;

	public float editTweenPosition;

	public float tweenDuration;

	public GameObject editButton;

	public GameObject completeButton;

	protected float origPos;

	protected bool editingNotice;

	protected bool willSubmit;

	protected bool tweening;

	protected override void Awake()
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

	public void OnCompleteClicked()
	{
	}

	public void OnEditClicked()
	{
	}

	private void Tween(float pos)
	{
	}

	public void OnSubmitNotice()
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

	public static void Show()
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
