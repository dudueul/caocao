using System;
using System.Collections.Generic;
using UnityEngine;

namespace Thingsoft
{
	public class GameInput
	{
		private class TouchEventInfo
		{
			public int fingerId;

			public TouchPhase touchPhase;

			public Vector2 position;
		}

		private static bool m_enabled;

		private static List<GameInput> ms_gameInputs;

		private Action<Vector2> OnBegan__BackingField;

		private Action<Vector2> OnMove__BackingField;

		private Action<Vector2> OnEnded__BackingField;

		private Action<float> OnPinchBegan__BackingField;

		private Action<float> OnPinchMove__BackingField;

		private Action<float> OnPinchEnded__BackingField;

		private const string m_mouseScrollWheel = "Mouse ScrollWheel";

		private float m_widthRatio;

		private List<TouchEventInfo> m_touchEventInfos;

		private Vector2 m_lastMousePosition;

		public static bool enabled
		{
			get
			{
				return false;
			}
			set
			{
			}
		}

		public int touchCount
		{
			get
			{
				return 0;
			}
		}

		public float widthRatio
		{
			get
			{
				return 0f;
			}
		}

		public event Action<Vector2> OnBegan
		{
			add
			{
			}
			remove
			{
			}
		}

		public event Action<Vector2> OnMove
		{
			add
			{
			}
			remove
			{
			}
		}

		public event Action<Vector2> OnEnded
		{
			add
			{
			}
			remove
			{
			}
		}

		public event Action<float> OnPinchBegan
		{
			add
			{
			}
			remove
			{
			}
		}

		public event Action<float> OnPinchMove
		{
			add
			{
			}
			remove
			{
			}
		}

		public event Action<float> OnPinchEnded
		{
			add
			{
			}
			remove
			{
			}
		}

		public static void ClearAll()
		{
		}

		private TouchEventInfo GetTouchEventInfos(int fingerId)
		{
			return null;
		}

		public void Dispose()
		{
		}

		private void UpdateTouchEvents()
		{
		}

		private void UpdateMouseEvents()
		{
		}

		public Vector2 GetLogicalCoord(Vector2 physicsCoord)
		{
			return default(Vector2);
		}

		public void Init()
		{
		}

		public void Clear()
		{
		}

		public void Update()
		{
		}
	}
}
