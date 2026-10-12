using System;
using UnityEngine;

namespace M
{
	public class Destroyer : MonoBehaviour
	{
		private static Destroyer _instance;

		private Action _destroyAction;

		public static void add(Action action)
		{
		}

		private void OnDestroy()
		{
		}
	}
}
