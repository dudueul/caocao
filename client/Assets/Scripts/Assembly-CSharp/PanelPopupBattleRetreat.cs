public class PanelPopupBattleRetreat : PanelPopup2Buttons
{
	private bool m_send;

	private int m_earlyRetreatPenalty;

	private EnumData.MultiplayEarlyRetreatReason m_earlyRetreatReason;

	protected override void OnSetContext()
	{
	}

	private string GetTitleText(int penalty)
	{
		return null;
	}

	private string GetDescText(int penalty, EnumData.MultiplayEarlyRetreatReason reason)
	{
		return null;
	}

	private string GetConfirmButtonText()
	{
		return null;
	}
}
