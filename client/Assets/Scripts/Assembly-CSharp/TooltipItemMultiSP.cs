using UnityEngine;

public class TooltipItemMultiSP : TooltipComponent
{
	private bool colorInit;

	private Color iconColor;

	private Color spNameColor;

	private Color spTypeColor;

	private Color spDescColor;

	private Color dimColor;

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

	private void UpdateSocketPassiveUI(int id, int equipUnitId, UserTreasureSocket userSocket)
	{
	}

	private void UpdateComponentImpl(Type type, int id, int passiveIndex, int equipUnitId, UserTreasureSocket userSocket = null)
	{
	}
}
