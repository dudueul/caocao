using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class RectTransformWorldToScreenPoint : BaseUpdateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmOwnerDefault camera;

		public FsmVector3 screenPoint;

		public FsmFloat screenX;

		public FsmFloat screenY;

		public FsmBool normalize;

		private RectTransform _rt;

		private Camera _cam;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnActionUpdate()
		{
		}

		private void DoWorldToScreenPoint()
		{
		}
	}
}
