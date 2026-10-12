using UnityEngine;
using UnityEngine.UI;

namespace HutongGames.PlayMaker.Actions
{
	public class UiGraphicSetColor : ComponentAction<Graphic>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmColor color;

		public FsmFloat red;

		public FsmFloat green;

		public FsmFloat blue;

		public FsmFloat alpha;

		public FsmBool resetOnExit;

		public bool everyFrame;

		private Graphic uiComponent;

		private Color originalColor;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}

		private void DoSetColorValue()
		{
		}

		public override void OnExit()
		{
		}
	}
}
