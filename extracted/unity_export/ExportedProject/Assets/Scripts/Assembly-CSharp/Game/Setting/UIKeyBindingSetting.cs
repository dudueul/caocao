using System;
using UnityEngine;

namespace Game.Setting
{
	[Serializable]
	public class UIKeyBindingSetting
	{
		[SerializeField]
		private UIButton m_resetButton;

		[SerializeField]
		private GameObject m_keyBindingUI;

		private UIKeyBindingButton[] m_keyBindingButtons;

		public void Init()
		{
		}

		private void ReloadAll()
		{
		}

		public void ResetAll()
		{
		}
	}
}
