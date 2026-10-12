using System.Collections.Generic;

public static class StopWatch
{
	private class TimerInfo
	{
		public float startupTime;

		public float accumTime;

		public bool paused;
	}

	private static Dictionary<string, TimerInfo> m_timerName2Time;

	private static float m_lastFastTime;

	public static void StartFastTime()
	{
	}

	public static void PrintFastElapsedTime()
	{
	}

	public static void StartTime(string timerName)
	{
	}

	public static void PauseTime(string timerName)
	{
	}

	public static void PrintElapsedTime(string timerName)
	{
	}
}
