using UnityEngine;

public abstract class TooltipComponent : MonoBehaviour
{
	public enum Type
	{
		Item = 0,
		Goods = 1,
		Unit = 2,
		Passive = 3,
		LordEffect = 4,
		MayorEffect = 5,
		Rune = 6,
		BaseStat = 7,
		RelationPassive = 8,
		ArenaPassive = 9
	}

	public abstract void UpdateComponent(Type type, int id);

	public abstract void UpdateComponent(Type type, int id, ITransmittable data);

	public abstract void UpdateComponent(Type descType, int descId, Type triggerType, int triggerId);

	public abstract void UpdateComponent(Type type, int id, params object[] parameters);

	public void UpdateComponent(Type type, ITooltipController controller, TooltipContentsGenerator generator)
	{
	}
}
