using LitJson;
using UnityEngine;

namespace Thingsoft
{
	internal class OptimizeOption : MonoBehaviour
	{
		private delegate string makeDescFromIntFunc(int value);

		public static bool clearPatchTemp;

		public static bool usePool;

		public static bool nguiReleaseInactive;

		public static bool assetbundleUnloadTrue;

		public static bool useEnumPool;

		public static bool memoryLog;

		public static bool use__itween;

		public static bool obscuredStringAdv;

		public static bool websocketPacketAdv;

		public static bool useNguiStaticBuffer;

		public static bool doNotUseSelectSingNode;

		public static bool uicomponentsUpdateOnce;

		public static bool useJosaReplaceAdv;

		public static int fastStepIdx;

		public static bool useItweenColorToAdv;

		public static bool useStaticQPFCtx;

		public static bool unitSelectTune;

		public static bool permDataDestroyAfterLoad;

		public static bool loadFromFileNonDecrypt;

		public static bool adaptiveAssetbundleCache;

		public static bool unloadUsedAssetsWhenGCCollect;

		public static bool unloadAudioData;

		public static bool useMemoryManager;

		public static bool useAdaptiveGCThreshold;

		static OptimizeOption()
		{
		}

		public static void RequestDefaultSetting()
		{
		}

		public static void SetServerDefaults(JsonData data)
		{
		}

		private void OnEnable()
		{
		}

		public static void Read()
		{
		}

		public static void Write()
		{
		}

		public static void Print()
		{
		}

		public void Close()
		{
		}

		public static void Show()
		{
		}

		private void UpdateUI(string target, bool value)
		{
		}

		private void UpdateUI(string target, int value, makeDescFromIntFunc makeDescFunc)
		{
		}

		public void OnToggleClearPatchTemp()
		{
		}

		public void OnToggleUserPool()
		{
		}

		public void OnToggleNGUIReleaseInactive()
		{
		}

		public void OnToggleAssetbundleUnloadTrue()
		{
		}

		public void OnToggleUseEnumPool()
		{
		}

		public void OnToggleMemoryLog()
		{
		}

		public void OnToggleObscuredStringAdv()
		{
		}

		public void OnToggleWebsocketPacketAdv()
		{
		}

		public void OnToggleUseNguiStaticBuffer()
		{
		}

		public void OnToggleDoNotUseSelectSingNode()
		{
		}

		public void OnToggleUicomponentsUpdateOnce()
		{
		}

		public void OnToggleUseJosaReplaceAdv()
		{
		}

		private static string FastStepIdx2Desc(int value)
		{
			return null;
		}

		public void OnToggleFastStepIdx()
		{
		}

		public void OnToggleUseItweenColorToAdv()
		{
		}

		public void OnToggleUseStaticQPFCtx()
		{
		}

		public void OnToggleUnitSelectTune()
		{
		}

		public void OnTogglepermDataDestroyAfterLoad()
		{
		}

		public void OnToggleLoadFromFileNonDecrypt()
		{
		}

		public void OnToggleAdaptiveAssetbundleCache()
		{
		}

		public void OnToggleUnloadUsedAssetsWhenGCCollect()
		{
		}

		public void OnToggleUnloadAudioData()
		{
		}

		public void OnToggleUseMemoryManager()
		{
		}

		public void OnToggleUseAdaptiveGCThreshold()
		{
		}
	}
}
