using UnityEngine.Events;
using UnityEngine.SceneManagement;

namespace CodeStage.AntiCheat.Detectors
{
	public class ObscuredCheatingDetector : ActDetectorBase
	{
		internal const string COMPONENT_NAME = "Obscured Cheating Detector";

		internal const string FINAL_LOG_PREFIX = "[ACTk] Obscured Cheating Detector: ";

		private static int instancesInScene;

		public float floatEpsilon;

		public float vector2Epsilon;

		public float vector3Epsilon;

		public float quaternionEpsilon;

		public static ObscuredCheatingDetector Instance { get; private set; }

		private static ObscuredCheatingDetector GetOrCreateInstance
		{
			get
			{
				return null;
			}
		}

		internal static bool IsRunning
		{
			get
			{
				return false;
			}
		}

		private ObscuredCheatingDetector()
		{
		}

		public static void StartDetection()
		{
		}

		public static void StartDetection(UnityAction callback)
		{
		}

		public static void StopDetection()
		{
		}

		public static void Dispose()
		{
		}

		private void Awake()
		{
		}

		protected override void OnDestroy()
		{
		}

		private void OnEnable()
		{
		}

		private void OnDisable()
		{
		}

		private void OnLevelFinishedLoading(Scene scene, LoadSceneMode mode)
		{
		}

		private void StartDetectionInternal(UnityAction callback)
		{
		}

		protected override void StartDetectionAutomatically()
		{
		}

		protected override void PauseDetector()
		{
		}

		protected override void ResumeDetector()
		{
		}

		protected override void StopDetectionInternal()
		{
		}

		protected override void DisposeInternal()
		{
		}
	}
}
