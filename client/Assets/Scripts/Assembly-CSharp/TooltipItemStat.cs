public class TooltipItemStat : TooltipComponent
{
	public UILabelLocalized atkVal;

	public UILabelLocalized intVal;

	public UILabelLocalized defVal;

	public UILabelLocalized agiVal;

	public UILabelLocalized morVal;

	public UILabelLocalized movVal;

	public UILabelLocalized hpVal;

	public UILabelLocalized mpVal;

	private UserItem m_userItem;

	private int m_id;

	private TroopStat m_baseItemStat;

	private RuneStat m_baseRuneStat;

	private UserUnit m_userUnit;

	private Type m_type;

	private UserItem userItem
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	private void OnEnable()
	{
	}

	private void OnDestroy()
	{
	}

	private void ItemUpdated()
	{
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

	public void UpdateRuneStat(Rune newRunePd, RuneStat runeStat)
	{
	}

	private void UpdateRuneStatImpl(Rune newRunePd, RuneStat runeStat)
	{
	}

	private void UpdateComponentImpl(Type type, int id)
	{
	}

	private void SetStatCompare(UILabelLocalized label, int stat, int statToCompare)
	{
	}

	private void SetStat(UILabelLocalized label, int stat)
	{
	}

	private void SetStat(UILabelLocalized label, int stat, int enhanceStat)
	{
	}

	private bool HasStat(Item item)
	{
		return false;
	}
}
