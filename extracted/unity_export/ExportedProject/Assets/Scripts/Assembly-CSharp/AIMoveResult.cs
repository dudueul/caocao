public struct AIMoveResult
{
	public int result;

	public bool isNormalAttack;

	public EnumData.Skills skillId;

	public UnitData targetData;

	public AIMoveResult(bool isNormalAttack, EnumData.Skills skillId, UnitData targetData, int result)
	{
		this.result = 0;
		this.isNormalAttack = false;
		this.skillId = EnumData.Skills.NONE;
		this.targetData = null;
	}
}
