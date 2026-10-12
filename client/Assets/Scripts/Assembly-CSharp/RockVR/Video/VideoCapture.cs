using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using System.Runtime.InteropServices;
using System.Threading;
using UnityEngine;

namespace RockVR.Video
{
	public class VideoCapture : VideoCaptureBase
	{
		[StructLayout((LayoutKind)0, Size = 16)]
		private struct FrameData
		{
			public byte[] pixels;

			public int count;

			public FrameData(byte[] p, int c)
			{
				pixels = null;
				count = 0;
			}
		}

		public bool offlineRender;

		private Texture2D frameTexture;

		private Cubemap frameCubemap;

		private bool isCapturingFrame;

		private bool isCreateRenderTexture;

		private float capturingTime;

		private int capturedFrameCount;

		private int encodedFrameCount;

		private IntPtr libAPI;

		private float originalMaximumDeltaTime;

		private Queue<FrameData> frameQueue;

		private Thread encodeThread;

		public VideoCaptureCtrl.StatusType status { get; set; }

		public void Cleanup()
		{
		}

		public override void StartCapture()
		{
		}

		public override void StopCapture()
		{
		}

		public override void ToggleCapture()
		{
		}

		private new void Awake()
		{
		}

		private void OnRenderImage(RenderTexture source, RenderTexture destination)
		{
		}

		private void LateUpdate()
		{
		}

		private void OnDestroy()
		{
		}

		private void OnApplicationQuit()
		{
		}

		[DebuggerHidden]
		private IEnumerator CaptureFrameAsync()
		{
			return null;
		}

		private void CaptureCubemapFrameSync()
		{
		}

		private void CopyFrameTexture()
		{
		}

		private void EnqueueFrameTexture()
		{
		}

		private void FrameEncodeThreadFunction()
		{
		}

		[PreserveSig]
		private static extern IntPtr VideoCaptureLib_Get(int width, int height, int rate, int proj, string path, string ffpath);

		[PreserveSig]
		private static extern void VideoCaptureLib_WriteFrames(IntPtr api, byte[] data, int count);

		[PreserveSig]
		private static extern void VideoCaptureLib_Close(IntPtr api);

		[PreserveSig]
		private static extern void VideoCaptureLib_Clean(IntPtr api);

		[PreserveSig]
		private static extern IntPtr VideoStreamingLib_Get(int width, int height, int rate, int proj, string address, string ffpath);

		[PreserveSig]
		private static extern void VideoStreamingLib_WriteFrames(IntPtr api, byte[] data, int count);

		[PreserveSig]
		private static extern void VideoStreamingLib_Close(IntPtr api);

		[PreserveSig]
		private static extern void VideoStreamingLib_Clean(IntPtr api);
	}
}
