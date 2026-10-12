using UnityEngine;

public abstract class UIItemSlot : MonoBehaviour
{
	public UISprite icon;

	public UIWidget background;

	public UILabel label;

	public AudioClip grabSound;

	public AudioClip placeSound;

	public AudioClip errorSound;

	private InvGameItem mItem;

	private string mText;

	private static InvGameItem mDraggedItem;

	protected abstract InvGameItem observedItem { get; }

	protected abstract InvGameItem Replace(InvGameItem item);

	private void OnTooltip(bool show)
	{
	}

	private void OnClick()
	{
	}

	private void OnDrag(Vector2 delta)
	{
	}

	private void OnDrop(GameObject go)
	{
	}

	private void UpdateCursor()
	{
	}

	private void Update()
	{
	}
}
