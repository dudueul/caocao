using System;
using System.Collections.Generic;
using System.Collections.Specialized;
using System.Diagnostics;
using System.IO;
using System.Net;
using System.Net.Sockets;
using System.Security.Cryptography.X509Certificates;
using WebSocketSharp.Net;
using WebSocketSharp.Net.WebSockets;

namespace WebSocketSharp
{
	public static class Ext
	{
		private const string _tspecials = "()<>@,;:\\\"/[]?={} \t";

		private static byte[] compress(this byte[] value)
		{
			return null;
		}

		private static MemoryStream compress(this Stream stream)
		{
			return null;
		}

		private static byte[] compressToArray(this Stream stream)
		{
			return null;
		}

		private static byte[] decompress(this byte[] value)
		{
			return null;
		}

		private static MemoryStream decompress(this Stream stream)
		{
			return null;
		}

		private static byte[] decompressToArray(this Stream stream)
		{
			return null;
		}

		private static byte[] readBytes(this Stream stream, byte[] buffer, int offset, int length)
		{
			return null;
		}

		private static bool readBytes(this Stream stream, byte[] buffer, int offset, int length, Stream dest)
		{
			return false;
		}

		private static void times(this ulong n, Action act)
		{
		}

		internal static byte[] Append(this ushort code, string reason)
		{
			return null;
		}

		internal static string CheckIfCanRead(this Stream stream)
		{
			return null;
		}

		internal static string CheckIfClosable(this WebSocketState state)
		{
			return null;
		}

		internal static string CheckIfConnectable(this WebSocketState state)
		{
			return null;
		}

		internal static string CheckIfOpen(this WebSocketState state)
		{
			return null;
		}

		internal static string CheckIfValidCloseStatusCode(this ushort code)
		{
			return null;
		}

		internal static string CheckIfValidControlData(this byte[] data, string paramName)
		{
			return null;
		}

		internal static string CheckIfValidProtocols(this string[] protocols)
		{
			return null;
		}

		internal static string CheckIfValidSendData(this byte[] data)
		{
			return null;
		}

		internal static string CheckIfValidSendData(this FileInfo file)
		{
			return null;
		}

		internal static string CheckIfValidSendData(this string data)
		{
			return null;
		}

		internal static string CheckIfValidServicePath(this string servicePath)
		{
			return null;
		}

		internal static string CheckIfValidSessionID(this string id)
		{
			return null;
		}

		internal static void Close(this WebSocketSharp.Net.HttpListenerResponse response, WebSocketSharp.Net.HttpStatusCode code)
		{
		}

		internal static void CloseWithAuthChallenge(this WebSocketSharp.Net.HttpListenerResponse response, string challenge)
		{
		}

		internal static byte[] Compress(this byte[] value, CompressionMethod method)
		{
			return null;
		}

		internal static Stream Compress(this Stream stream, CompressionMethod method)
		{
			return null;
		}

		internal static byte[] CompressToArray(this Stream stream, CompressionMethod method)
		{
			return null;
		}

		internal static bool Contains<T>(this IEnumerable<T> source, Func<T, bool> condition)
		{
			return false;
		}

		internal static bool ContainsTwice(this string[] values)
		{
			return false;
		}

		internal static T[] Copy<T>(this T[] src, long length)
		{
			return null;
		}

		internal static void CopyTo(this Stream src, Stream dest)
		{
		}

		internal static void CopyTo(this Stream src, Stream dest, bool setDefaultPosition)
		{
		}

		internal static byte[] Decompress(this byte[] value, CompressionMethod method)
		{
			return null;
		}

		internal static Stream Decompress(this Stream stream, CompressionMethod method)
		{
			return null;
		}

		internal static byte[] DecompressToArray(this Stream stream, CompressionMethod method)
		{
			return null;
		}

		internal static bool EqualsWith(this int value, char c, Action<int> action)
		{
			return false;
		}

		internal static string GetAbsolutePath(this Uri uri)
		{
			return null;
		}

		internal static string GetMessage(this CloseStatusCode code)
		{
			return null;
		}

		internal static string GetNameInternal(this string nameAndValue, string separator)
		{
			return null;
		}

		internal static string GetValueInternal(this string nameAndValue, string separator)
		{
			return null;
		}

		internal static TcpListenerWebSocketContext GetWebSocketContext(this TcpClient client, string protocol, bool secure, X509Certificate cert, Logger logger)
		{
			return null;
		}

		internal static bool IsCompressionExtension(this string value)
		{
			return false;
		}

		internal static bool IsPortNumber(this int value)
		{
			return false;
		}

		internal static bool IsReserved(this ushort code)
		{
			return false;
		}

		internal static bool IsReserved(this CloseStatusCode code)
		{
			return false;
		}

		internal static bool IsText(this string value)
		{
			return false;
		}

		internal static bool IsToken(this string value)
		{
			return false;
		}

		internal static string Quote(this string value)
		{
			return null;
		}

		internal static byte[] ReadBytes(this Stream stream, int length)
		{
			return null;
		}

		internal static byte[] ReadBytes(this Stream stream, long length, int bufferLength)
		{
			return null;
		}

		internal static void ReadBytesAsync(this Stream stream, int length, Action<byte[]> completed, Action<Exception> error)
		{
		}

		internal static string RemovePrefix(this string value, params string[] prefixes)
		{
			return null;
		}

		internal static T[] Reverse<T>(this T[] array)
		{
			return null;
		}

		[DebuggerHidden]
		internal static IEnumerable<string> SplitHeaderValue(this string value, params char[] separator)
		{
			return null;
		}

		internal static byte[] ToByteArray(this Stream stream)
		{
			return null;
		}

		internal static byte[] ToByteArrayInternally(this ushort value, ByteOrder order)
		{
			return null;
		}

		internal static byte[] ToByteArrayInternally(this ulong value, ByteOrder order)
		{
			return null;
		}

		internal static CompressionMethod ToCompressionMethod(this string value)
		{
			return CompressionMethod.None;
		}

		internal static string ToExtensionString(this CompressionMethod method)
		{
			return null;
		}

		internal static IPAddress ToIPAddress(this string hostNameOrAddress)
		{
			return null;
		}

		internal static List<TSource> ToList<TSource>(this IEnumerable<TSource> source)
		{
			return null;
		}

		internal static ushort ToUInt16(this byte[] src, ByteOrder srcOrder)
		{
			return 0;
		}

		internal static ulong ToUInt64(this byte[] src, ByteOrder srcOrder)
		{
			return 0uL;
		}

		internal static string TrimEndSlash(this string value)
		{
			return null;
		}

		internal static bool TryCreateWebSocketUri(this string uriString, out Uri result, out string message)
		{
			result = null;
			message = null;
			return false;
		}

		internal static string Unquote(this string value)
		{
			return null;
		}

		internal static void WriteBytes(this Stream stream, byte[] value)
		{
		}

		public static bool Contains(this string value, params char[] chars)
		{
			return false;
		}

		public static bool Contains(this NameValueCollection collection, string name)
		{
			return false;
		}

		public static bool Contains(this NameValueCollection collection, string name, string value)
		{
			return false;
		}

		public static void Emit(this EventHandler eventHandler, object sender, EventArgs e)
		{
		}

		public static void Emit<TEventArgs>(this EventHandler<TEventArgs> eventHandler, object sender, TEventArgs e) where TEventArgs : EventArgs
		{
		}

		public static WebSocketSharp.Net.CookieCollection GetCookies(this NameValueCollection headers, bool response)
		{
			return null;
		}

		public static string GetDescription(this WebSocketSharp.Net.HttpStatusCode code)
		{
			return null;
		}

		public static string GetName(this string nameAndValue, string separator)
		{
			return null;
		}

		public static KeyValuePair<string, string> GetNameAndValue(this string nameAndValue, string separator)
		{
			return default(KeyValuePair<string, string>);
		}

		public static string GetStatusDescription(this int code)
		{
			return null;
		}

		public static string GetValue(this string nameAndValue, string separator)
		{
			return null;
		}

		public static bool IsCloseStatusCode(this ushort value)
		{
			return false;
		}

		public static bool IsEnclosedIn(this string value, char c)
		{
			return false;
		}

		public static bool IsHostOrder(this ByteOrder order)
		{
			return false;
		}

		public static bool IsLocal(this IPAddress address)
		{
			return false;
		}

		public static bool IsNullOrEmpty(this string value)
		{
			return false;
		}

		public static bool IsPredefinedScheme(this string value)
		{
			return false;
		}

		public static bool IsUpgradeTo(this WebSocketSharp.Net.HttpListenerRequest request, string protocol)
		{
			return false;
		}

		public static bool MaybeUri(this string value)
		{
			return false;
		}

		public static T[] SubArray<T>(this T[] array, int startIndex, int length)
		{
			return null;
		}

		public static void Times(this int n, Action act)
		{
		}

		public static void Times(this long n, Action act)
		{
		}

		public static void Times(this uint n, Action act)
		{
		}

		public static void Times(this ulong n, Action act)
		{
		}

		public static void Times(this int n, Action<int> act)
		{
		}

		public static void Times(this long n, Action<long> act)
		{
		}

		public static void Times(this uint n, Action<uint> act)
		{
		}

		public static void Times(this ulong n, Action<ulong> act)
		{
		}

		public static T To<T>(this byte[] src, ByteOrder srcOrder) where T : struct
		{
			return default(T);
		}

		public static byte[] ToByteArray<T>(this T value, ByteOrder order) where T : struct
		{
			return null;
		}

		public static byte[] ToHostOrder(this byte[] src, ByteOrder srcOrder)
		{
			return null;
		}

		public static string ToString<T>(this T[] array, string separator)
		{
			return null;
		}

		public static Uri ToUri(this string uriString)
		{
			return null;
		}

		public static string UrlDecode(this string value)
		{
			return null;
		}

		public static string UrlEncode(this string value)
		{
			return null;
		}

		public static void WriteContent(this WebSocketSharp.Net.HttpListenerResponse response, byte[] content)
		{
		}
	}
}
