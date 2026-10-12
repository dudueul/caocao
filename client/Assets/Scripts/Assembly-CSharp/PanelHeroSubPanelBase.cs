public class PanelHeroSubPanelBase : SelfRegister2UIComponentsMonobehaviour
{
	protected UserUnit userUnit { get; private set; }

	protected Unit unitData { get; private set; }

	protected ulong m_unitSrl
	{
		get
		{
			return 0uL;
		}
	}

	public void SetUserUnit(UserUnit unit)
	{
	}

	public virtual void UpdateUI()
	{
	}
}
