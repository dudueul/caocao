using System.Collections;
using System.Diagnostics;
using UnityEngine;

namespace RockVR.Video
{
	public class Screenshot : MonoBehaviour
	{
		public Material transformMaterial;

		public int startSeconds;

		private Texture2D frameTexture;

		private RenderTexture frameRenderTexture;

		private Cubemap frameCubemap;

		public Camera Camera
		{
			get
			{
				return null;
			}
		}

		private void Start()
		{
		}

		[DebuggerHidden]
		private IEnumerator AutoTakeScreenshot(int seconds)
		{
			return null;
		}

		public void TakeScreenshot()
		{
		}
	}
}
