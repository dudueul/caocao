using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;

namespace WebSocketSharp
{
	internal class PayloadData : IEnumerable<byte>, IEnumerable
	{
		private byte[] _applicationData;

		private byte[] _extensionData;

		private bool _masked;

		public const ulong MaxLength = 9223372036854775807uL;

		internal bool ContainsReservedCloseStatusCode
		{
			get
			{
				return false;
			}
		}

		public byte[] ApplicationData
		{
			get
			{
				return null;
			}
		}

		public byte[] ExtensionData
		{
			get
			{
				return null;
			}
		}

		public bool IsMasked
		{
			get
			{
				return false;
			}
		}

		public ulong Length
		{
			get
			{
				return 0uL;
			}
		}

		public PayloadData()
		{
		}

		public PayloadData(byte[] applicationData)
		{
		}

		public PayloadData(string applicationData)
		{
		}

		public PayloadData(byte[] applicationData, bool masked)
		{
		}

		public PayloadData(byte[] extensionData, byte[] applicationData, bool masked)
		{
		}

		private static void mask(byte[] src, byte[] key)
		{
		}

		[DebuggerHidden]
		public IEnumerator<byte> GetEnumerator()
		{
			return null;
		}

		public void Mask(byte[] maskingKey)
		{
		}

		public byte[] ToByteArray()
		{
			return null;
		}

		public override string ToString()
		{
			return null;
		}

		IEnumerator IEnumerable.GetEnumerator()
		{
			return null;
		}
	}
}
