namespace Worldboss
{
	public class PopupBossInfo : CaocaoUIModalPopup
	{
		public UILabelLocalized bossName;

		public UITexture bossFace;

		public UILabelLocalized bossDesc;

		public UIButton btnPassiveInfo;

		private WorldBoss m_pdWorldboss;

		private WorldBossInstance m_curInstance;

		public void Show(WorldBoss worldboss, WorldBossInstance instance)
		{
		}

		public void OnClickPassiveInfo()
		{
		}

		public override void OnFocusOn()
		{
		}

		public override void OnFocusOut()
		{
		}

		public override void OnTappingOutSide()
		{
		}

		protected override void ShowPanelImpl()
		{
		}

		protected override void ClosePanelImpl()
		{
		}
	}
}
