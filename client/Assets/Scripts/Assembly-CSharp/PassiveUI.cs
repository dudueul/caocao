public class PassiveUI : SpecialStateUI
{
	private Passive m_passive;

	private int m_passiveValue;

	private bool m_isTreasurePassive;

	private bool m_showMinusVal;

	public Passive passive
	{
		get
		{
			return null;
		}
	}

	protected override bool hasData
	{
		get
		{
			return false;
		}
	}

	protected override UIAtlas iconAtlas
	{
		get
		{
			return null;
		}
	}

	protected override string iconName
	{
		get
		{
			return null;
		}
	}

	public int passiveValue
	{
		get
		{
			return 0;
		}
	}

	public int needLv { get; set; }

	public int needJobLv { get; set; }

	public bool isTreasurePassive
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public bool showMinusVal
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public void SetPassive(Passive passive, int passiveVal)
	{
	}

	protected override void OnDestroy()
	{
	}

	public override void SetItemDefaultIcon(EnumData.ItemType itemType)
	{
	}

	protected override void UpdateFrame()
	{
	}
}
