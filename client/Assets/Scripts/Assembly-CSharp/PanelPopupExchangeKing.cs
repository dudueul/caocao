using LitJson;

public class PanelPopupExchangeKing : PanelPopup2Buttons
{
	private HttpRequest.ResponseCallback m_callback;

	private int m_townID;

	private int m_currKingID;

	private int m_currMayorID;

	private int m_needFood;

	protected override void OnSetContext()
	{
	}

	public static void Show(int townId, int unitId, int needFood, HttpRequest.ResponseCallback callback)
	{
	}

	private void OnCompleteSetKing(JsonData response)
	{
	}
}
