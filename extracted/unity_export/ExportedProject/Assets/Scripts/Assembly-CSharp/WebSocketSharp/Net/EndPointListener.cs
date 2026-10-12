using System;
using System.Collections.Generic;
using System.Net;
using System.Net.Sockets;
using System.Security.Cryptography;
using System.Security.Cryptography.X509Certificates;

namespace WebSocketSharp.Net
{
	internal sealed class EndPointListener
	{
		private static readonly string _defaultCertFolderPath;

		private List<ListenerPrefix> _all;

		private X509Certificate2 _cert;

		private IPEndPoint _endpoint;

		private Dictionary<ListenerPrefix, HttpListener> _prefixes;

		private bool _secure;

		private Socket _socket;

		private List<ListenerPrefix> _unhandled;

		private Dictionary<HttpConnection, HttpConnection> _unregistered;

		public X509Certificate2 Certificate
		{
			get
			{
				return null;
			}
		}

		public bool IsSecure
		{
			get
			{
				return false;
			}
		}

		public EndPointListener(IPAddress address, int port, bool secure, string certFolderPath, X509Certificate2 defaultCert)
		{
		}

		private static void addSpecial(List<ListenerPrefix> prefixes, ListenerPrefix prefix)
		{
		}

		private void checkIfRemove()
		{
		}

		private static RSACryptoServiceProvider createRSAFromFile(string filename)
		{
			return null;
		}

		private static X509Certificate2 getCertificate(int port, string certFolderPath, X509Certificate2 defaultCert)
		{
			return null;
		}

		private static HttpListener matchFromList(string host, string path, List<ListenerPrefix> list, out ListenerPrefix prefix)
		{
			prefix = null;
			return null;
		}

		private static void onAccept(object sender, EventArgs e)
		{
		}

		private static bool removeSpecial(List<ListenerPrefix> prefixes, ListenerPrefix prefix)
		{
			return false;
		}

		private HttpListener searchListener(Uri uri, out ListenerPrefix prefix)
		{
			prefix = null;
			return null;
		}

		internal static bool CertificateExists(int port, string certFolderPath)
		{
			return false;
		}

		internal void RemoveConnection(HttpConnection connection)
		{
		}

		public void AddPrefix(ListenerPrefix prefix, HttpListener listener)
		{
		}

		public bool BindContext(HttpListenerContext context)
		{
			return false;
		}

		public void Close()
		{
		}

		public void RemovePrefix(ListenerPrefix prefix, HttpListener listener)
		{
		}

		public void UnbindContext(HttpListenerContext context)
		{
		}
	}
}
