using UnityEngine.UI;

namespace HutongGames.PlayMaker.Actions
{
	public class UiGraphicGetColor : ComponentAction<Graphic>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmColor color;

		public bool everyFrame;

		private Graphic uiComponent;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}

		private void DoGetColorValue()
		{
		}
	}
}
