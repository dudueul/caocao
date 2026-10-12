using UnityEngine;

namespace RockVR.Common
{
	public class Singleton<T> : MonoBehaviour where T : MonoBehaviour
	{
		private static T _instance;

		private static object _lock;

		private static bool applicationIsQuitting;

		public static T instance
		{
			get
			{
				return null;
			}
		}

		protected virtual void Awake()
		{
		}

		protected virtual void OnApplicationQuit()
		{
		}
	}
}
