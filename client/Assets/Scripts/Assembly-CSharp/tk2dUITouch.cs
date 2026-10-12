using System.Runtime.InteropServices;
using UnityEngine;

[StructLayout((LayoutKind)0, Size = 28)]
public struct tk2dUITouch
{
	public const int MOUSE_POINTER_FINGER_ID = 9999;

	public TouchPhase phase { get; private set; }

	public int fingerId { get; private set; }

	public Vector2 position { get; private set; }

	public Vector2 deltaPosition { get; private set; }

	public float deltaTime { get; private set; }

	public tk2dUITouch(TouchPhase _phase, int _fingerId, Vector2 _position, Vector2 _deltaPosition, float _deltaTime)
	{
		phase = TouchPhase.Began;
		fingerId = 0;
		position = default(Vector2);
		deltaPosition = default(Vector2);
		deltaTime = 0f;
	}

	public tk2dUITouch(Touch touch)
	{
		phase = TouchPhase.Began;
		fingerId = 0;
		position = default(Vector2);
		deltaPosition = default(Vector2);
		deltaTime = 0f;
	}

	public override string ToString()
	{
		return null;
	}
}
