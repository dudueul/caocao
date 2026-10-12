using UnityEngine;

namespace WorldMapMenu
{
	public class PageButtonRaid : PageButtonBase
	{
		private enum LocationType
		{
			eWorldMap = 0,
			eGuildPanel = 1
		}

		private LocationType m_locationType;

		private GameObject m_objNewtag;

		private GameObject m_objBadge;

		private UILabelLocalized m_labelBadgeCount;

		protected override void Awake()
		{
		}

		public override void OnClickImpl()
		{
		}

		private void OnEnable()
		{
		}

		private void CheckBadgeCount()
		{
		}

		private void CheckNewtagEnable()
		{
		}

		private void SetButtonEnable(bool enable)
		{
		}
	}
}
