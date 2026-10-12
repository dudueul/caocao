using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class RectTransformPixelAdjustPoint : BaseUpdateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmGameObject canvas;

		public FsmVector2 screenPoint;

		[RequiredField]
		public FsmVector2 pixelPoint;

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
