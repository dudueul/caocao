public class TooltipRuneHead : TooltipComponent
{
	private Rune m_runePd;

	private UserRuneSlot m_userRuneSlot;

	private RuneItemSlot m_runeItemSlot;

	private UserRuneItem m_userRuneItem;

	private UserUnit m_userUnit;

	private int m_runeSlotNum;

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

	public void UpdateRuneHead(UserRuneSlot userRuneSlot, RuneItemSlot runeItemSlot, bool showDesc)
	{
	}

	public void UpdateRuneHead(UserRuneItem userRuneItem, bool showDesc)
	{
	}

	public void UpdateRuneHead(Rune rune, bool showDesc)
	{
	}

	public void UpdateRuneHead(Rune rune, RunePassive runePassive, bool showDesc)
	{
	}

	private void UpdateComponentImpl(Type type, int id)
	{
	}

	private void UpdateName(Type type)
	{
	}

	private void UpdateType(Type type)
	{
	}

	private void UpdateNum(Type type)
	{
	}

	private void UpdateIconFrame(Type type, int id)
	{
	}

	private void UpdateBoard(Type type, int id)
	{
	}
}
