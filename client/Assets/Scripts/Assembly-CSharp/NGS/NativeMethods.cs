using System;
using System.Runtime.InteropServices;

namespace NGS
{
	internal static class NativeMethods
	{
		[PreserveSig]
		public static extern IntPtr LoadLibrary(string lpFileName);

		[PreserveSig]
		public static extern IntPtr GetProcAddress(IntPtr hModule, uint ordinal);
	}
}
