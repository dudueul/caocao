using System.Collections;
using System.Diagnostics;
using UnityEngine;

namespace M
{
	public class Coroutiner : MonoBehaviour
	{
		private static Coroutiner _instance;

		public static Coroutiner instance
		{
			get
			{
				return null;
			}
		}

		[DebuggerHidden]
		public static IEnumerator WaitForRealSeconds(float time)
		{
			return null;
		}
	}
}
