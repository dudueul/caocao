using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class RectTransformSetLocalPosition : BaseUpdateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmVector2 position2d;

		public FsmVector3 position;

		public FsmFloat x;

		public FsmFloat y;

		public FsmFloat z;

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

		private void DoSetValues()
		{
		}
	}
}
