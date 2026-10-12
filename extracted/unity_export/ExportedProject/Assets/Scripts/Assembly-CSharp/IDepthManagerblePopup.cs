public interface IDepthManagerblePopup
{
	bool HasVisibleBg { get; set; }

	bool HasBg { get; set; }

	bool IgnoreTappingOutSide { get; set; }

	DepthManager.Grade DepthGrade { get; set; }

	void OnFocusOn();

	void OnFocusOut();

	void OnTappingOutSide();
}
