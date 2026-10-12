using System;

public class tk2dUIDropDownItem : tk2dUIBaseItemControl
{
	public tk2dTextMesh label;

	public float height;

	public tk2dUIUpDownHoverButton upDownHoverBtn;

	private int index;

	private Action<tk2dUIDropDownItem> OnItemSelected__BackingField;

	public int Index
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	public string LabelText
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public event Action<tk2dUIDropDownItem> OnItemSelected
	{
		add
		{
		}
		remove
		{
		}
	}

	private void OnEnable()
	{
	}

	private void OnDisable()
	{
	}

	private void ItemSelected()
	{
	}
}
