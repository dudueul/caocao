using UnityEngine;

namespace Worldboss
{
	public class UIWorldBossHistory : MonoBehaviour, IWorldBossTab
	{
		public UIReuseScrollView scrollView;

		public UIReuseGrid grid;

		public PopupWorldboss parent { get; private set; }

		public void SetActive(bool active)
		{
		}

		public void Initialize(PopupWorldboss parent)
		{
		}

		public void OnDestroy()
		{
		}

		public void Show()
		{
		}
	}
}
