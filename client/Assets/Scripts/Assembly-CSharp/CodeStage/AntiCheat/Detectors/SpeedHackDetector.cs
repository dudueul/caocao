using UnityEngine.Events;
using UnityEngine.SceneManagement;

namespace CodeStage.AntiCheat.Detectors
{
	public class SpeedHackDetector : ActDetectorBase
	{
		internal const string COMPONENT_NAME = "Speed Hack Detector";

		internal const string FINAL_LOG_PREFIX = "[ACTk] Speed Hack Detector: ";

		private const long TICKS_PER_SECOND = 10000000L;

		private const int THRESHOLD = 5000000;

		private static int instancesInScene;

		public float interval;

		public byte maxFalsePositives;

		public int coolDown;

		private byte currentFalsePositives;

		private int currentCooldownShots;

		private long ticksOnStart;

		private long vulnerableTicksOnStart;

		private long prevTicks;

		private long prevIntervalTicks;

		public static SpeedHackDetector Instance { get; private set; }

		private static SpeedHackDetector GetOrCreateInstance
		{
			get
			{
				return null;
			}
		}

		private SpeedHackDetector()
		{
		}

		public static void StartDetection()
		{
		}

		public static void StartDetection(UnityAction callback)
		{
		}

		public static void StartDetection(UnityAction callback, float interval)
		{
		}

		public static void StartDetection(UnityAction callback, float interval, byte maxFalsePositives)
		{
		}

		public static void StartDetection(UnityAction callback, float interval, byte maxFalsePositives, int coolDown)
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

		private void OnApplicationPause(bool pause)
		{
		}

		private void Update()
		{
		}

		private void StartDetectionInternal(UnityAction callback, float checkInterval, byte falsePositives, int shotsTillCooldown)
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

		private void ResetStartTicks()
		{
		}
	}
}
