using System.Collections.Generic;

public abstract class PanelSelfRegister2UIComponentsMonobehaviour : SelfRegister2UIComponentsMonobehaviour
{
	private static List<PanelSelfRegister2UIComponentsMonobehaviour> curVisiblePanels;

	public virtual void ShowPanel()
	{
	}

	public virtual void ClosePanel()
	{
	}

	protected override void OnDestroy()
	{
	}

	protected virtual void ClosePanelByShortcut()
	{
	}

	public static void CloseAllPanelByShortcut()
	{
	}
}
