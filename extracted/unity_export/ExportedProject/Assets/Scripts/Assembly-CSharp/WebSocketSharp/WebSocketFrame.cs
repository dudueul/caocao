using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using System.IO;

namespace WebSocketSharp
{
	internal class WebSocketFrame : IEnumerable<byte>, IEnumerable
	{
		private byte[] _extPayloadLength;

		private Fin _fin;

		private Mask _mask;

		private byte[] _maskingKey;

		private Opcode _opcode;

		private PayloadData _payloadData;

		private byte _payloadLength;

		private Rsv _rsv1;

		private Rsv _rsv2;

		private Rsv _rsv3;

		internal static readonly byte[] EmptyUnmaskPingData;

		public byte[] ExtendedPayloadLength
		{
			get
			{
				return null;
			}
		}

		public Fin Fin
		{
			get
			{
				return Fin.More;
			}
		}

		public bool IsBinary
		{
			get
			{
				return false;
			}
		}

		public bool IsClose
		{
			get
			{
				return false;
			}
		}

		public bool IsCompressed
		{
			get
			{
				return false;
			}
		}

		public bool IsContinuation
		{
			get
			{
				return false;
			}
		}

		public bool IsControl
		{
			get
			{
				return false;
			}
		}

		public bool IsData
		{
			get
			{
				return false;
			}
		}

		public bool IsFinal
		{
			get
			{
				return false;
			}
		}

		public bool IsFragmented
		{
			get
			{
				return false;
			}
		}

		public bool IsMasked
		{
			get
			{
				return false;
			}
		}

		public bool IsPerMessageCompressed
		{
			get
			{
				return false;
			}
		}

		public bool IsPing
		{
			get
			{
				return false;
			}
		}

		public bool IsPong
		{
			get
			{
				return false;
			}
		}

		public bool IsText
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

		public Mask Mask
		{
			get
			{
				return Mask.Unmask;
			}
		}

		public byte[] MaskingKey
		{
			get
			{
				return null;
			}
		}

		public Opcode Opcode
		{
			get
			{
				return Opcode.Cont;
			}
		}

		public PayloadData PayloadData
		{
			get
			{
				return null;
			}
		}

		public byte PayloadLength
		{
			get
			{
				return 0;
			}
		}

		public Rsv Rsv1
		{
			get
			{
				return Rsv.Off;
			}
		}

		public Rsv Rsv2
		{
			get
			{
				return Rsv.Off;
			}
		}

		public Rsv Rsv3
		{
			get
			{
				return Rsv.Off;
			}
		}

		static WebSocketFrame()
		{
		}

		private WebSocketFrame()
		{
		}

		public WebSocketFrame(Opcode opcode, PayloadData payload)
		{
		}

		public WebSocketFrame(Opcode opcode, Mask mask, PayloadData payload)
		{
		}

		public WebSocketFrame(Fin fin, Opcode opcode, Mask mask, PayloadData payload)
		{
		}

		public WebSocketFrame(Fin fin, Opcode opcode, Mask mask, PayloadData payload, bool compressed)
		{
		}

		private static byte[] createMaskingKey()
		{
			return null;
		}

		private static string dump(WebSocketFrame frame)
		{
			return null;
		}

		private static bool isControl(Opcode opcode)
		{
			return false;
		}

		private static bool isData(Opcode opcode)
		{
			return false;
		}

		private static WebSocketFrame parse(byte[] header, Stream stream, bool unmask)
		{
			return null;
		}

		private static string print(WebSocketFrame frame)
		{
			return null;
		}

		internal static WebSocketFrame CreateCloseFrame(Mask mask, PayloadData payload)
		{
			return null;
		}

		internal static WebSocketFrame CreatePongFrame(Mask mask, PayloadData payload)
		{
			return null;
		}

		public static WebSocketFrame CreateCloseFrame(Mask mask, byte[] data)
		{
			return null;
		}

		public static WebSocketFrame CreateCloseFrame(Mask mask, CloseStatusCode code, string reason)
		{
			return null;
		}

		public static WebSocketFrame CreateFrame(Fin fin, Opcode opcode, Mask mask, byte[] data, bool compressed)
		{
			return null;
		}

		public static WebSocketFrame CreatePingFrame(Mask mask)
		{
			return null;
		}

		public static WebSocketFrame CreatePingFrame(Mask mask, byte[] data)
		{
			return null;
		}

		[DebuggerHidden]
		public IEnumerator<byte> GetEnumerator()
		{
			return null;
		}

		public static WebSocketFrame Parse(byte[] src)
		{
			return null;
		}

		public static WebSocketFrame Parse(Stream stream)
		{
			return null;
		}

		public static WebSocketFrame Parse(byte[] src, bool unmask)
		{
			return null;
		}

		public static WebSocketFrame Parse(Stream stream, bool unmask)
		{
			return null;
		}

		public static void ParseAsync(Stream stream, Action<WebSocketFrame> completed)
		{
		}

		public static void ParseAsync(Stream stream, Action<WebSocketFrame> completed, Action<Exception> error)
		{
		}

		public static void ParseAsync(Stream stream, bool unmask, Action<WebSocketFrame> completed, Action<Exception> error)
		{
		}

		public void Print(bool dumped)
		{
		}

		public string PrintToString(bool dumped)
		{
			return null;
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
