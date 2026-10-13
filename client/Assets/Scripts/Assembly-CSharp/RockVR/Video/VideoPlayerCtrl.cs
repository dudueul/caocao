using System.Collections.Generic;
using UnityEngine;
using UnityEngine.Video;

namespace RockVR.Video
{
	public class VideoPlayerCtrl : MonoBehaviour
	{
		private List<string> videoFiles;

		private UnityEngine.Video.VideoPlayer videoPlayerImpl;

		private int index;

		public static VideoPlayerCtrl instance;

		private void Awake()
		{
		}

		public void SetRootFolder()
		{
		}

		public void PlayVideo()
		{
		}

		public void NextVideo()
		{
		}
	}
}
