using System.Collections;
using System.Diagnostics;
using M;

namespace MAssist
{
	public class MSpreadsheetWriteItem
	{
		public enum ItemType
		{
			LOG = 0,
			ADDITION = 1
		}

		public static int writingItemNum;

		private const int MaxRetryNum = 5;

		private const float TimeoutDelay = 10f;

		private int _currentRetry;

		private float _endTime;

		private string _url;

		private string _logData;

		private ItemType _type;

		public MSpreadsheetWriteItem(Coroutiner coroutiner, string url, string logData, ItemType type)
		{
		}

		[DebuggerHidden]
		private IEnumerator sendLog()
		{
			return null;
		}
	}
}
