public abstract class ClickTooltipComponent : TooltipComponent
{
	protected virtual void OnDisable()
	{
	}

	protected abstract void AddClickHandler();

	protected abstract void ClearClickHandler();
}
