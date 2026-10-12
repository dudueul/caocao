using UnityEngine;

public interface ITooltipController
{
	TooltipComponent.Type componentType { get; }

	GameObject tooltipPrefab { get; }

	void Update(TooltipComponent comp, TooltipContentsGenerator generator);
}
