using UnityEngine;

namespace antilunchbox
{
	public class Singleton<T> : MonoBehaviour where T : Singleton<T>
	{
		private static T _mInstance;

		private static readonly string ms_tagName;

		private static bool ms_destroyed;

		protected static Singleton<T> mInstance
		{
			get
			{
				return null;
			}
			set
			{
			}
		}

		public static string GetName()
		{
			return null;
		}

		protected virtual void OnApplicationQuit()
		{
		}

		public static void DestroyInstance()
		{
		}
	}
}
