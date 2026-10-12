using UnityEngine;

internal interface ICoordinateSystem
{
	IntVector2 convertWorldPosToGridPos(Vector2 pos);

	Vector2 convertGridPosToWorldPos(IntVector2 gridPos);
}
