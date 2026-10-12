using System.Collections;
using UnityEngine;

public static class Yielders
{
	public class WaitForRealSeconds : IEnumerator
	{
		private float durationSecs;

		private float startTime;

		public object Current
		{
			get
			{
				return null;
			}
		}

		public WaitForRealSeconds(float seconds)
		{
		}

		public bool MoveNext()
		{
			return false;
		}

		public void Reset()
		{
		}
	}

	public static WaitForEndOfFrame endOfFrame;

	public static WaitForFixedUpdate fixedUpdate;
}
