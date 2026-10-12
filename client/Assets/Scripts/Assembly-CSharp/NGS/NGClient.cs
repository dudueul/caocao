using System;
using System.Runtime.InteropServices;

namespace NGS
{
	public static class NGClient
	{
		public delegate void SendCallbackDelegate(IntPtr bytes, uint size, uint sessionID);

		[StructLayout((LayoutKind)0, Size = 32)]
		private struct Interface_0
		{
			private IntPtr _0;

			private IntPtr _1;

			private IntPtr _2;

			public IntPtr _3;
		}

		private delegate uint NgsDelegate_1(IntPtr _1, ref IntPtr _2, bool _3);

		private delegate void NgsDelegate_2();

		private delegate void NgsDelegate_3(IntPtr _1, uint _2, uint _3);

		private static IntPtr s_h;

		private static IntPtr s_c;

		private static GCHandle s_gch;

		public static void Control(byte[] bytes, uint sessionID)
		{
		}

		public static bool Start(SendCallbackDelegate callback, bool needPatch)
		{
			return false;
		}

		public static void Stop()
		{
		}
	}
}
