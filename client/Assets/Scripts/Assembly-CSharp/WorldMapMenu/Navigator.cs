using System.Collections.Generic;

namespace WorldMapMenu
{
	public class Navigator : CaocaoUIModalPopup
	{
		public UILabelLocalized title;

		public NavigateButton prevPage;

		public NavigateButton nextPage;

		public List<PageBase> pages;

		private PageBase m_currentPage;

		private bool inited;

		public PageBase currentPage
		{
			get
			{
				return null;
			}
			set
			{
			}
		}

		protected override void Awake()
		{
		}

		protected override void OnDestroy()
		{
		}

		public override void OnFocusOn()
		{
		}

		public override void OnFocusOut()
		{
		}

		protected override void ShowPanelImpl()
		{
		}

		protected override void ClosePanelImpl()
		{
		}

		public override void OnTappingOutSide()
		{
		}

		public static void Show(string pageName)
		{
		}

		public static void Show(string pageName, List<string> excludePages)
		{
		}

		public static void ShowGuildNavigator(string pageName)
		{
		}

		private void SetCurrentPage(string pageName)
		{
		}

		public void OnNextClicked()
		{
		}

		public void OnPrevClicked()
		{
		}
	}
}
