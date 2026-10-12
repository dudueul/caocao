using System;
using UnityEngine;

namespace Game.Setting
{
	[Serializable]
	public class UIResolutionSetting
	{
		[SerializeField]
		private UIButton m_fullScreenButton;

		[SerializeField]
		private GameObject m_fullscreenCheck;

		[SerializeField]
		private UIButton m_resolutionButton;

		[SerializeField]
		private GameObject m_resolutionList;

		[SerializeField]
		private UIGrid m_resolutionGrid;

		[SerializeField]
		private UISprite m_resolutionGridBg;

		[SerializeField]
		private UILabel m_resolutionLabel;

		public void Init()
		{
		}

		public void SetResolution(string widthxheight, bool force = false)
		{
		}

		public void SetResolution(int width, int height, bool force = false)
		{
		}

		public void SetFullScreen(bool fullscreen, bool force = false)
		{
		}

		private void SetResolutionList()
		{
		}

		public void OnClickFullScreen()
		{
		}

		public void OnClickResolution()
		{
		}

		public void OnClickResolutionItem(Transform item)
		{
		}

		private void ShowChangeResolutionPopup(string curResolution, string prevResolution)
		{
		}

		private void ShowChangeFullscreenPopup(bool curFullscreen)
		{
		}
	}
}
