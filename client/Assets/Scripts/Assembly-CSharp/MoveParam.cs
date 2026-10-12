using System.Runtime.InteropServices;
using Caocao;
using UnityEngine;

[StructLayout((LayoutKind)0, Size = 44)]
public struct MoveParam
{
	public IntVector2 fromGridPos;

	public IntVector2 toGridPos;

	public Vector2 fromWorldPos;

	public Vector2 toWorldPos;

	public UnitDirection direction;

	public int idx;

	public bool isLastStep;
}
