using UnityEngine.UI;

namespace HutongGames.PlayMaker.Actions
{
	public class UiRebuild : ComponentAction<Graphic>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public CanvasUpdate canvasUpdate;

		public bool rebuildOnExit;

		private Graphic graphic;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void DoAction()
		{
		}

		public override void OnExit()
		{
		}
	}
}
