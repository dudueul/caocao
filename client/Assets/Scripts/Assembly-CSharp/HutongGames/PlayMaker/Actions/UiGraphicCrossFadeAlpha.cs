using UnityEngine.UI;

namespace HutongGames.PlayMaker.Actions
{
	public class UiGraphicCrossFadeAlpha : ComponentAction<Graphic>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmFloat alpha;

		public FsmFloat duration;

		public FsmBool ignoreTimeScale;

		private Graphic uiComponent;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}
	}
}
