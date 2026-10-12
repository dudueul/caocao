public abstract class CaocaoUIButton : ButtonSelfRegister2UIComponentsMonobehaviour
{
	protected abstract void UpdateButtonState();

	protected abstract void OnClickImpl();

	public sealed override void OnClick()
	{
	}
}
