using RockVR.Common;
using UnityEngine;

namespace RockVR.Video
{
	public class VideoCaptureBase : MonoBehaviour
	{
		public enum ModeType
		{
			LOCAL = 0,
			LIVE_STREAMING = 1
		}

		public enum FormatType
		{
			NORMAL = 0,
			PANORAMA = 1
		}

		public enum PanoramaProjectionType
		{
			CUBEMAP = 0,
			EQUIRECTANGULAR = 1
		}

		public enum StereoType
		{
			NONE = 0,
			TOP_BOTTOM = 1,
			LEFT_RIGHT = 2
		}

		public enum StereoFormatType
		{
			FULL = 0,
			HALF = 1
		}

		public enum FrameSizeType
		{
			_640x480 = 0,
			_720x480 = 1,
			_960x540 = 2,
			_1280x720 = 3,
			_1920x1080 = 4,
			_2048x1080 = 5,
			_3840x2160 = 6,
			_4096x2160 = 7,
			_7680x4320 = 8
		}

		public enum CubemapSizeType
		{
			_512 = 0,
			_1024 = 1,
			_2048 = 2
		}

		public enum EncodeQualityType
		{
			Low = 0,
			Medium = 1,
			High = 2
		}

		public enum AntiAliasingType
		{
			_1 = 0,
			_2 = 1,
			_4 = 2,
			_8 = 3
		}

		public enum TargetFramerateType
		{
			_18 = 0,
			_24 = 1,
			_30 = 2,
			_45 = 3,
			_60 = 4
		}

		public FormatType format;

		public StereoType stereo;

		public StereoFormatType stereoFormat;

		public FrameSizeType frameSize;

		public CubemapSizeType _cubemapSize;

		public PanoramaProjectionType panoramaProjection;

		public EncodeQualityType encodeQuality;

		public AntiAliasingType _antiAliasing;

		public TargetFramerateType _targetFramerate;

		protected Camera captureCamera;

		public bool isDedicated;

		public bool captureGUI;

		protected float deltaFrameTime;

		public ModeType mode;

		public bool customPath;

		public string customPathFolder;

		public string streamingAddress;

		public RockVR.Common.EventDelegate eventDelegate;

		public float interPupillaryDistance;

		protected Material stereoPackMaterial;

		protected Material copyReverseMaterial;

		protected Material cubemap2Equirectangular;

		protected Material blitMaterial;

		protected RenderTexture frameRenderTexture;

		protected RenderTexture stereoTargetTexture;

		protected RenderTexture finalTargetTexture;

		protected RenderTexture panoramaTempRenderTexture;

		protected RenderTexture faceTarget;

		public int frameWidth
		{
			get
			{
				return 0;
			}
		}

		public int frameHeight
		{
			get
			{
				return 0;
			}
		}

		public int cubemapSize
		{
			get
			{
				return 0;
			}
		}

		public int antiAliasing
		{
			get
			{
				return 0;
			}
		}

		public int bitrate
		{
			get
			{
				return 0;
			}
		}

		public int targetFramerate
		{
			get
			{
				return 0;
			}
		}

		public string filePath { get; protected set; }

		public bool isPanorama
		{
			get
			{
				return false;
			}
		}

		public bool isLiveStreaming
		{
			get
			{
				return false;
			}
		}

		protected void Awake()
		{
		}

		public virtual void StartCapture()
		{
		}

		public virtual void StopCapture()
		{
		}

		public virtual void ToggleCapture()
		{
		}

		protected void RenderTextureToPNG(RenderTexture rtex, string fileName)
		{
		}

		protected void TextureToPNG(Texture2D tex, string fileName)
		{
		}

		protected void SetStereoVideoFormat(RenderTexture frameRenderTexture)
		{
		}

		protected void RenderToCubemapWithGUI(Camera cameraTarget, RenderTexture cubemapTarget)
		{
		}

		private void RenderCameraToRenderTexture(Camera camera, RenderTexture frameRenderTexture, RenderTexture stereoTarget)
		{
		}
	}
}
