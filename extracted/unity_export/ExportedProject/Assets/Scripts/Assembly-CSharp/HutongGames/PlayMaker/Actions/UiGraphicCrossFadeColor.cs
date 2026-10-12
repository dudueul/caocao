using UnityEngine.UI;

namespace HutongGames.PlayMaker.Actions
{
	public class UiGraphicCrossFadeColor : ComponentAction<Graphic>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmColor color;

		public FsmFloat red;

		public FsmFloat green;

		public FsmFloat blue;

		public FsmFloat alpha;

		public FsmFloat duration;

		public FsmBool ignoreTimeScale;

		public FsmBool useAlpha;

		private Graphic uiComponent;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}
	}
}
