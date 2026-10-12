public abstract class DuelAttack1Base : DuelAttackBase
{
	protected EnumData.DuelAttack1 m_attack1Type;

	protected bool m_isCritical;

	public bool isCritical
	{
		get
		{
			return false;
		}
	}
}
