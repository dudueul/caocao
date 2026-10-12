public abstract class MapObjectConditionChecker
{
	protected BattleMapObject m_target;

	protected MapObjectCondition m_conditionProperty;

	public abstract void SetTarget(BattleMapObject target);

	protected abstract void CheckCondition();

	public abstract void DetachCheckFunc();
}
