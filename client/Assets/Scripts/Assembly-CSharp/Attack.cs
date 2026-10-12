public class Attack : AttackBase
{
	public int attackIdx { get; set; }

	public int attackSuccessCount { get; set; }

	public int criticalOccuredCount { get; set; }

	public bool isCriticalOccuredAtLeastOneTime
	{
		get
		{
			return false;
		}
	}

	public Attack(int attackIdx)
	{
	}
}
