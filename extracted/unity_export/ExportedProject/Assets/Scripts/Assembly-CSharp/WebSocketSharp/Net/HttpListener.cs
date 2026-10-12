using System;
using System.Collections.Generic;
using System.Security.Cryptography.X509Certificates;
using System.Security.Principal;

namespace WebSocketSharp.Net
{
	public sealed class HttpListener : IDisposable
	{
		private AuthenticationSchemes _authSchemes;

		private Func<HttpListenerRequest, AuthenticationSchemes> _authSchemeSelector;

		private string _certFolderPath;

		private Dictionary<HttpConnection, HttpConnection> _connections;

		private List<HttpListenerContext> _contextQueue;

		private Func<IIdentity, NetworkCredential> _credentialsFinder;

		private X509Certificate2 _defaultCert;

		private bool _disposed;

		private bool _ignoreWriteExceptions;

		private bool _listening;

		private HttpListenerPrefixCollection _prefixes;

		private string _realm;

		private Dictionary<HttpListenerContext, HttpListenerContext> _registry;

		private List<ListenerAsyncResult> _waitQueue;

		internal bool IsDisposed
		{
			get
			{
				return false;
			}
		}

		public AuthenticationSchemes AuthenticationSchemes
		{
			get
			{
				return AuthenticationSchemes.None;
			}
			set
			{
			}
		}

		public Func<HttpListenerRequest, AuthenticationSchemes> AuthenticationSchemeSelector
		{
			get
			{
				return null;
			}
			set
			{
			}
		}

		public string CertificateFolderPath
		{
			get
			{
				return null;
			}
			set
			{
			}
		}

		public X509Certificate2 DefaultCertificate
		{
			get
			{
				return null;
			}
			set
			{
			}
		}

		public bool IgnoreWriteExceptions
		{
			get
			{
				return false;
			}
			set
			{
			}
		}

		public bool IsListening
		{
			get
			{
				return false;
			}
		}

		public static bool IsSupported
		{
			get
			{
				return false;
			}
		}

		public HttpListenerPrefixCollection Prefixes
		{
			get
			{
				return null;
			}
		}

		public string Realm
		{
			get
			{
				return null;
			}
			set
			{
			}
		}

		public bool UnsafeConnectionNtlmAuthentication
		{
			get
			{
				return false;
			}
			set
			{
			}
		}

		public Func<IIdentity, NetworkCredential> UserCredentialsFinder
		{
			get
			{
				return null;
			}
			set
			{
			}
		}

		private void cleanup(bool force)
		{
		}

		private void cleanupConnections()
		{
		}

		private void cleanupContextRegistry()
		{
		}

		private void cleanupWaitQueue()
		{
		}

		private void close(bool force)
		{
		}

		private HttpListenerContext getContextFromQueue()
		{
			return null;
		}

		private void sendServiceUnavailable()
		{
		}

		internal void AddConnection(HttpConnection connection)
		{
		}

		internal ListenerAsyncResult BeginGetContext(ListenerAsyncResult asyncResult)
		{
			return null;
		}

		internal void CheckDisposed()
		{
		}

		internal void RegisterContext(HttpListenerContext context)
		{
		}

		internal void RemoveConnection(HttpConnection connection)
		{
		}

		internal AuthenticationSchemes SelectAuthenticationScheme(HttpListenerContext context)
		{
			return AuthenticationSchemes.None;
		}

		internal void UnregisterContext(HttpListenerContext context)
		{
		}

		public void Abort()
		{
		}

		public IAsyncResult BeginGetContext(AsyncCallback callback, object state)
		{
			return null;
		}

		public void Close()
		{
		}

		public HttpListenerContext EndGetContext(IAsyncResult asyncResult)
		{
			return null;
		}

		public HttpListenerContext GetContext()
		{
			return null;
		}

		public void Start()
		{
		}

		public void Stop()
		{
		}

		void IDisposable.Dispose()
		{
		}
	}
}
