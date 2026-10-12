using System;
using System.Runtime.InteropServices;
using RockVR.Common;
using UnityEngine;

namespace RockVR.Video
{
	public class AudioCapture : MonoBehaviour
	{
		public RockVR.Common.EventDelegate eventDelegate;

		private IntPtr libAPI;

		private IntPtr audioPointer;

		private byte[] audioByteBuffer;

		public VideoCaptureCtrl.StatusType status { get; set; }

		public string filePath { get; protected set; }

		public void Cleanup()
		{
		}

		public void StartCapture()
		{
		}

		public void StopCapture()
		{
		}

		public void PauseCapture()
		{
		}

		private void Awake()
		{
		}

		private void OnAudioFilterRead(float[] data, int channels)
		{
		}

		[PreserveSig]
		private static extern IntPtr AudioCaptureLib_Get(int rate, string path, string ffpath);

		[PreserveSig]
		private static extern void AudioCaptureLib_WriteFrame(IntPtr api, byte[] data);

		[PreserveSig]
		private static extern void AudioCaptureLib_Close(IntPtr api);

		[PreserveSig]
		private static extern void AudioCaptureLib_Clean(IntPtr api);
	}
}
