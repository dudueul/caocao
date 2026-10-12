using System;
using System.Runtime.InteropServices;

namespace RockVR.Video
{
	public class VideoMuxing
	{
		public string filePath;

		private VideoCapture videoCapture;

		private AudioCapture audioCapture;

		public VideoMuxing(VideoCapture videoCapture, AudioCapture audioCapture)
		{
		}

		public bool Muxing()
		{
			return false;
		}

		[PreserveSig]
		private static extern IntPtr MuxingLib_Get(int rate, string path, string vpath, string apath, string ffpath);

		[PreserveSig]
		private static extern void MuxingLib_Muxing(IntPtr api);

		[PreserveSig]
		private static extern void MuxingLib_Clean(IntPtr api);
	}
}
