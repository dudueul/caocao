public class EffectAutoUseItem : EffectBase
{
	private int beforeHp;

	private int beforeMp;

	private int resultHp;

	private int resultMp;

	public EffectAutoUseItem(UnitData triggerUnit, UnitData targetUnit)
		: base(null, null)
	{
	}

	public override void _Apply()
	{
	}

	private bool CheckWasteOfUsage(Item itemData, int itemId)
	{
		return false;
	}

	private bool ValidateHpMpDecreased(Item itemData, int itemId)
	{
		return false;
	}

	private bool CheckGameModeAndSide()
	{
		return false;
	}

	public void CacheHpMp()
	{
	}
}
