using System;
using NPA;

namespace NPAListener
{
	public class PermissionListener : INPRuntimePermissionListener
	{
		private Action callback;

		private Action runTimeFailCallback;

		private Action runTimeSuccessCallback;

		public PermissionListener(Action _callback)
		{
		}

		public PermissionListener(Action failCallBack, Action successCallBack)
		{
		}

		public Action<int, string[], int[]> GetAction()
		{
			return null;
		}

		public Action<int, string[], int[]> GetRunTimeAction()
		{
			return null;
		}

		public void OnRequestPermissionsResult(int requestCode, string[] permissions, int[] grantResults)
		{
		}

		public void OnRequestPermissionsRunTimeResult(int requestCode, string[] permissions, int[] grantResults)
		{
		}

		private void CallJavaClassFunc(string permission)
		{
		}
	}
}
