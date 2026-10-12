using System;
using System.Collections.Generic;

public class CentralTimer : Singleton<CentralTimer>
{
	[Serializable]
	public class TimerContext
	{
		private float m_tickSecs;

		private int m_repeatCnt;

		private float m_remainSecs;

		private object m_target;

		private Action m_callback;

		private bool m_isExpired;

		private bool m_hold;

		public bool isExpired
		{
			get
			{
				return false;
			}
		}

		public TimerContext(float term, object target, Action callback, int repeatCnt)
		{
		}

		public void Dispose()
		{
		}

		public bool isTarget(object target, Action callback)
		{
			return false;
		}

		public void Elapse(float elapsed)
		{
		}

		public void RefreshTime(int tickSecs)
		{
		}

		public void Hold(bool hold)
		{
		}
	}

	private const int SEC = 1;

	private const int MIN = 60;

	private const int HOUR = 3600;

	private float prevRealtimeSinceStartup;

	public List<TimerContext> timers;

	public void AddTimer(float term, object target, Action callback, int repeatCount = -1)
	{
	}

	public void RefreshTimer(int duration, object target, Action callback)
	{
	}

	public bool HasTimer(object target, Action callback)
	{
		return false;
	}

	public void RemoveTimer(object target, Action callback)
	{
	}

	public void HoldTimer(object target, Action callback, bool hold)
	{
	}

	private void Start()
	{
	}

	private void Update()
	{
	}
}
