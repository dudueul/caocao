using System.Threading;
using RockVR.Common;
using UnityEngine;

namespace RockVR.Video
{
	public class VideoCaptureCtrl : Singleton<VideoCaptureCtrl>
	{
		public enum StatusType
		{
			NOT_START = 0,
			STARTED = 1,
			PAUSED = 2,
			STOPPED = 3,
			FINISH = 4
		}

		public enum ErrorCodeType
		{
			CAMERA_AUDIO_CAPTURE_NOT_FOUND = -1,
			FFMPEG_NOT_FOUND = -2,
			VIDEO_AUDIO_MERGE_TIMEOUT = -3
		}

		public bool debug;

		public bool startOnAwake;

		public float captureTime;

		public bool quitAfterCapture;

		public RockVR.Common.EventDelegate eventDelegate;

		[SerializeField]
		private VideoCaptureBase[] _videoCaptures;

		[SerializeField]
		private AudioCapture _audioCapture;

		private int videoCaptureFinishCount;

		private int videoCaptureRequiredCount;

		private Thread videoMergeThread;

		private Thread garbageCollectionThread;

		private bool isCaptureAudio;

		private bool isOfflineRender;

		public StatusType status { get; protected set; }

		public VideoCaptureBase[] videoCaptures
		{
			get
			{
				return null;
			}
			set
			{
			}
		}

		public AudioCapture audioCapture
		{
			get
			{
				return null;
			}
			set
			{
			}
		}

		private void Start()
		{
		}

		private void Update()
		{
		}

		public void StartCapture()
		{
		}

		public void StopCapture()
		{
		}

		public void ToggleCapture()
		{
		}

		private void OnVideoCaptureComplete()
		{
		}

		private void OnAudioCaptureComplete()
		{
		}

		private void VideoMergeThreadFunction()
		{
		}

		private void GarbageCollectionThreadFunction()
		{
		}

		private void Cleanup()
		{
		}

		private bool IsCaptureAudio()
		{
			return false;
		}

		protected override void Awake()
		{
		}

		protected override void OnApplicationQuit()
		{
		}
	}
}
