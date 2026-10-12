using System;
using System.Threading;

namespace WebSocketSharp.Net
{
	internal class ListenerAsyncResult : IAsyncResult
	{
		private AsyncCallback _callback;

		private bool _completed;

		private HttpListenerContext _context;

		private Exception _exception;

		private ManualResetEvent _waitHandle;

		private object _state;

		private object _sync;

		private bool _syncCompleted;

		internal bool EndCalled;

		internal bool InGet;

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

		public ListenerAsyncResult(AsyncCallback callback, object state)
		{
		}

		private static void invokeCallback(object state)
		{
		}

		internal void Complete(Exception exception)
		{
		}

		internal void Complete(HttpListenerContext context)
		{
		}

		internal void Complete(HttpListenerContext context, bool syncCompleted)
		{
		}

		internal HttpListenerContext GetContext()
		{
			return null;
		}
	}
}
