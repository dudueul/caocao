using UnityEngine;

public class GLinkRecordAndroid : IGLinkRecord
{
	private class OnRecordManagerListener : AndroidJavaProxy
	{
		public OnRecordManagerListener()
			: base((string)null)
		{
		}

		private void onStartRecord()
		{
		}

		private void onErrorRecord()
		{
		}

		private void onFinishRecord(string uri)
		{
		}
	}

	private AndroidJavaClass delegateClass;

	private AndroidJavaObject currentActivity;

	private static void showToast(string message)
	{
	}

	public void startRecord()
	{
	}

	public void stopRecord()
	{
	}
}
