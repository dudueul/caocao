using System.Collections;

public abstract class AIPattern
{
	public abstract EnumData.aiType GetAIType();

	public abstract IEnumerator ProcessMovementAI(UnitData unitData);
}
