using System.Net.Security;
using System.Net.Sockets;

namespace WebSocketSharp.Net.Security
{
	internal class SslStream : System.Net.Security.SslStream
	{
		public bool DataAvailable
		{
			get
			{
				return false;
			}
		}

		public SslStream(NetworkStream innerStream)
			: base(null)
		{
		}

		public SslStream(NetworkStream innerStream, bool leaveInnerStreamOpen)
			: base(null)
		{
		}

		public SslStream(NetworkStream innerStream, bool leaveInnerStreamOpen, RemoteCertificateValidationCallback userCertificateValidationCallback)
			: base(null)
		{
		}

		public SslStream(NetworkStream innerStream, bool leaveInnerStreamOpen, RemoteCertificateValidationCallback userCertificateValidationCallback, LocalCertificateSelectionCallback userCertificateSelectionCallback)
			: base(null)
		{
		}
	}
}
