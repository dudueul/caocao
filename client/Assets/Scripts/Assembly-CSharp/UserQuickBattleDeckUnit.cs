public class UserQuickBattleDeckUnit : UserUnitGroup.UnitInfo
{
	public int areaId;

	public int p;

	public UserQuickBattleDeckUnit(int index, int areaId, int p, UserUnitGroup.UnitInfo unitInfo)
	{
	}

	public override UserUnitGroup.UnitInfo DeepCopy()
	{
		return null;
	}
}
