using System;
using System.Reflection;
using UnityEngine.Events;
using UnityEngine.SceneManagement;

namespace CodeStage.AntiCheat.Detectors
{
	public class InjectionDetector : ActDetectorBase
	{
		private class AllowedAssembly
		{
			public readonly string name;

			public readonly int[] hashes;

			public AllowedAssembly(string name, int[] hashes)
			{
			}
		}

		internal const string COMPONENT_NAME = "Injection Detector";

		internal const string FINAL_LOG_PREFIX = "[ACTk] Injection Detector: ";

		private static int instancesInScene;

		private bool signaturesAreNotGenuine;

		private AllowedAssembly[] allowedAssemblies;

		private string[] hexTable;

		public static InjectionDetector Instance { get; private set; }

		private static InjectionDetector GetOrCreateInstance
		{
			get
			{
				return null;
			}
		}

		private InjectionDetector()
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

		private void OnNewAssemblyLoaded(object sender, AssemblyLoadEventArgs args)
		{
		}

		private bool FindInjectionInCurrentAssemblies()
		{
			return false;
		}

		private bool AssemblyAllowed(Assembly ass)
		{
			return false;
		}

		private void LoadAndParseAllowedAssemblies()
		{
		}

		private int GetAssemblyHash(Assembly ass)
		{
			return 0;
		}

		private string PublicKeyTokenToString(byte[] bytes)
		{
			return null;
		}
	}
}
