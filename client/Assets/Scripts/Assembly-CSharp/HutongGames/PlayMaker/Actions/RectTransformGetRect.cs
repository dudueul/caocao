using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class RectTransformGetRect : BaseUpdateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmRect rect;

		public FsmFloat x;

		public FsmFloat y;

		public FsmFloat width;

		public FsmFloat height;

		private RectTransform _rt;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnActionUpdate()
		{
		}

		private void DoGetValues()
		{
		}
	}
}
