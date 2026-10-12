using UnityEngine;

public class PopupBrawlTagDirection : CaocaoUIPanel
{
	public enum Type
	{
		In = 0,
		Out = 1,
		Retreat = 2
	}

	[SerializeField]
	private UITexture face;

	[SerializeField]
	private UILabelLocalized desc;

	[SerializeField]
	private PlayMakerFSM playMakerfsm;

	private void Start()
	{
	}

	public void Show(UserUnit userUnit, Type type)
	{
	}

	private string GetDesc(Type type)
	{
		return null;
	}

	protected override void ShowPanelImpl()
	{
	}

	protected override void ClosePanelImpl()
	{
	}
}
