using System.IO;

namespace System.Security.Cryptography
{
	public class DecryptedInfo : IDisposable
	{
		public RijndaelManaged rijndaelCipher;

		private ICryptoTransform m_Decryptor;

		public ulong decryptedLength;

		public byte[] bufferFromFile;

		public MemoryStream memoryStream;

		public ICryptoTransform Decryptor
		{
			get
			{
				return null;
			}
			set
			{
			}
		}

		public DecryptedInfo(string path, string secretKey)
		{
		}

		public DecryptedInfo(byte[] contents, string secretKey)
		{
		}

		private void SetCipherInfoPostProcessing(string secretKey, byte[] Salt)
		{
		}

		private void SetCipherInfo()
		{
		}

		private byte[] GetSalt(BinaryReader fi)
		{
			return null;
		}

		void IDisposable.Dispose()
		{
		}
	}
}
