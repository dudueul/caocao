using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class RectTransformPixelAdjustRect : BaseUpdateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmGameObject canvas;

		[RequiredField]
		public FsmRect pixelRect;

		private RectTransform _rt;

		private Canvas _canvas;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnActionUpdate()
		{
		}

		private void DoAction()
		{
		}
	}
}
