public class PanelPopupSuggestWIFISuggestion : PanelPopup2Buttons
{
	private const float invMegaBytes = 1f / 1048576f;

	public long willBeReceivedTotalBytes { get; set; }

	protected override void OnSetContext()
	{
	}

	public override void OnTappingOutSide()
	{
	}
}
