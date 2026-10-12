public class TooltipTailLordEffectBtn : ClickTooltipComponent
{
	private UserUnit m_userUnit;

	private UserInfo m_userInfo;

	private LordEffect m_lordEffect;

	private int m_index;

	private UIButton m_button
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized m_cost
	{
		get
		{
			return null;
		}
	}

	public override void UpdateComponent(Type type, int id)
	{
	}

	public override void UpdateComponent(Type type, int id, ITransmittable data)
	{
	}

	public override void UpdateComponent(Type type, int id, Type triggerType, int triggerId)
	{
	}

	public override void UpdateComponent(Type type, int id, object[] parameters)
	{
	}

	private void UpdateComponentImpl(Type type, int id, UserUnit userUnit, UserInfo userInfo)
	{
	}

	protected override void AddClickHandler()
	{
	}

	private void Update()
	{
	}

	private bool IsOnEffectTime()
	{
		return false;
	}

	private bool IsOnCoolTime()
	{
		return false;
	}

	private void UpdateBtnState()
	{
	}

	protected override void ClearClickHandler()
	{
	}

	protected void UseLordEffect()
	{
	}
}
