using UnityEngine.UI;

namespace HutongGames.PlayMaker.Actions
{
	public class UiCanvasScalerSetScaleFactor : ComponentAction<CanvasScaler>
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmFloat scaleFactor;

		public bool everyFrame;

		private CanvasScaler component;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}

		private void DoSetValue()
		{
		}
	}
}
