using System;
using System.Collections.Generic;

public class CaocaoTooltip : CaocaoUIModalPopup
{
	public Action onSetContext;

	public TooltipHead head
	{
		get
		{
			return null;
		}
	}

	public TooltipBody body
	{
		get
		{
			return null;
		}
	}

	public TooltipTail tail
	{
		get
		{
			return null;
		}
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

	protected void OnSetContext()
	{
	}

	protected List<TooltipComponent> GetTooltipComponentsAll()
	{
		return null;
	}
}
