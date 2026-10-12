using System;

public abstract class CaocaoUIPanel : PanelSelfRegister2UIComponentsMonobehaviour
{
	private Action UpdateButtonStates__BackingField;

	public Action OnShowPanel;

	public Action OnClosePanel;

	public event Action UpdateButtonStates
	{
		add
		{
		}
		remove
		{
		}
	}

	private void AttachUpdateButtonFunc()
	{
	}

	protected abstract void ShowPanelImpl();

	public sealed override void ShowPanel()
	{
	}

	protected abstract void ClosePanelImpl();

	public sealed override void ClosePanel()
	{
	}

	protected override void OnDestroy()
	{
	}
}
