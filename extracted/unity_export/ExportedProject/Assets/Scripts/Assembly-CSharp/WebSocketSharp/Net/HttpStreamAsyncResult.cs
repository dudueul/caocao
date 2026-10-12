using System;
using System.Threading;

namespace WebSocketSharp.Net
{
	internal class HttpStreamAsyncResult : IAsyncResult
	{
		private AsyncCallback _callback;

		private bool _completed;

		private object _state;

		private object _sync;

		private ManualResetEvent _waitHandle;

		internal byte[] Buffer;

		internal int Count;

		internal Exception Error;

		internal int Offset;

		internal int SyncRead;

		public object AsyncState
		{
			get
			{
				return null;
			}
		}

		public WaitHandle AsyncWaitHandle
		{
			get
			{
				return null;
			}
		}

		public bool CompletedSynchronously
		{
			get
			{
				return false;
			}
		}

		public bool IsCompleted
		{
			get
			{
				return false;
			}
		}

		public HttpStreamAsyncResult(AsyncCallback callback, object state)
		{
		}

		public void Complete()
		{
		}

		public void Complete(Exception exception)
		{
		}
	}
}
